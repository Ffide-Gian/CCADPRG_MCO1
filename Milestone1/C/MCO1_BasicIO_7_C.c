/*
********************
Last names: Borbe, Caibigan, Sering, Won
Language: C
Paradigm(s): Procedural
********************
 */

/*
MCO1_BasicIO_C.c
Banking and currency exchange app: register an account, deposit,
withdraw, convert between currencies, record exchange rates and
show the expected interest. Every input is just the number
(1000, not [1000]).
*/

#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <ctype.h>

#define NUM_CURRENCIES 6
#define BASE_CURRENCY  0           
#define LINE_SIZE      128
#define NAME_MAX_LEN   60
#define MIN_AMOUNT     0.01        
#define MIN_RATE       0.000001    
#define MAX_AMOUNT     1000000000000.0
#define MAX_DAYS       36500       

#define ANNUAL_INTEREST_RATE 0.05
#define DAYS_IN_YEAR         365.0

typedef struct {
    const char *name;
    const char *code;
} Currency;

typedef struct {
    char   name[NAME_MAX_LEN + 1];
    double balance;                // always kept in PHP
    int    registered;
} Account;

static const Currency currencies[NUM_CURRENCIES] = {
    { "Philippine Peso",        "PHP" },
    { "United States Dollar",   "USD" },
    { "Japanese Yen",           "JPY" },
    { "British Pound Sterling", "GBP" },
    { "Euro",                   "EUR" },
    { "Chinese Yuan Renminni",  "CNY" }
};

/*
Rates are how many pesos one unit of each currency is worth.
PHP is the base so it's always 1. The rest start at 0, meaning
"not recorded yet" (C zero-fills whatever we don't list).
*/
static double rates[NUM_CURRENCIES] = { 1.0 };

static Account account;

// ---------- input helpers ----------

// Strips spaces and newlines from both ends of a string
static void trim(char *s)
{
    char  *start = s;
    size_t len;

    while (isspace((unsigned char)*start)) {
        start++;
    }
    len = strlen(start);
    while (len > 0 && isspace((unsigned char)start[len - 1])) {
        len--;
    }
    memmove(s, start, len);
    s[len] = '\0';
}

/*
Reads one whole line. Reading the full line first means bad input
like letters can't get stuck in the buffer and loop forever.
Returns 0 if the line was too long to be sensible.
*/
static int readLine(const char *prompt, char *buf, size_t size)
{
    size_t len;
    int    ch;

    printf("%s", prompt);
    fflush(stdout);

    // If the input is closed (Ctrl+D), just quit nicely
    if (fgets(buf, (int)size, stdin) == NULL) {
        printf("\nInput closed. Goodbye!\n");
        exit(0);
    }

    len = strlen(buf);
    if (len > 0 && buf[len - 1] == '\n') {
        buf[len - 1] = '\0';
    } else if (!feof(stdin)) {
        // No newline means the line didn't fit, so throw away the rest
        while ((ch = getchar()) != '\n' && ch != EOF) {
            ;
        }
        buf[0] = '\0';
        return 0;
    }

    trim(buf);
    return 1;
}

// Keeps asking until the user types a valid number between min and max
static double readAmount(const char *prompt, double min, double max)
{
    char   line[LINE_SIZE];
    char  *end;
    double value;

    for (;;) {
        if (readLine(prompt, line, sizeof line)) {
            // strtod would also accept things like "inf" or "0x10", so only
            // let plain digits and a dot through
            if (strspn(line, "0123456789.") != strlen(line)) {
                printf("Please type just a positive number, like 1000.\n\n");
                continue;
            }
            value = strtod(line, &end);

            /*
            If end isn't at the very end of the line, there was junk
            after the number (like "100abc" or "[100]")
            */
            if (end != line && *end == '\0' && value >= min && value <= max) {
                return value;
            }
        }
        printf("Please type just a positive number, like 1000.\n\n");
    }
}

/*
Same idea for menu choices. If defaultValue isn't 0, pressing Enter
on its own picks it.
*/
static int readChoice(const char *prompt, int low, int high, int defaultValue)
{
    char  line[LINE_SIZE];
    char *end;
    long  value;

    for (;;) {
        if (readLine(prompt, line, sizeof line)) {
            if (line[0] == '\0' && defaultValue != 0) {
                return defaultValue;
            }
            if (strspn(line, "0123456789") != strlen(line)) {
                printf("Please type just a number from %d to %d.\n\n", low, high);
                continue;
            }
            value = strtol(line, &end, 10);
            if (end != line && *end == '\0' && value >= low && value <= high) {
                return (int)value;
            }
        }
        printf("Please type just a number from %d to %d.\n\n", low, high);
    }
}

static int readYesNo(const char *prompt)
{
    char line[LINE_SIZE];

    for (;;) {
        if (readLine(prompt, line, sizeof line) && strlen(line) == 1) {
            if (tolower((unsigned char)line[0]) == 'y') {
                return 1;
            }
            if (tolower((unsigned char)line[0]) == 'n') {
                return 0;
            }
        }
        printf("Please type Y or N.\n\n");
    }
}

// ---------- small utilities ----------

/*
Rounds to the nearest centavo. Only for positive amounts, and we
keep them well under the max so the cast is safe.
*/
static double roundToCents(double x)
{
    return (double)(long long)(x * 100.0 + 0.5) / 100.0;
}

static void printCurrencyList(const char *heading)
{
    int i;

    printf("%s\n", heading);
    for (i = 0; i < NUM_CURRENCIES; i++) {
        printf("[%d] %s (%s)\n", i + 1, currencies[i].name, currencies[i].code);
    }
    printf("\n");
}

static int rateRecorded(int index)
{
    return rates[index] > 0.0;
}

static void printNoRate(int index)
{
    printf("No exchange rate recorded for %s yet. Use [5] Record Exchange Rates first.\n",
           currencies[index].code);
}

static int requireAccount(void)
{
    if (!account.registered) {
        printf("No account registered yet. Please use [1] Register Account Name first.\n");
        return 0;
    }
    return 1;
}

// ---------- transactions ----------

static void registerAccount(void)
{
    char name[LINE_SIZE];

    printf("\nRegister Account Name\n");

    for (;;) {
        if (readLine("Account Name: ", name, sizeof name)
            && name[0] != '\0' && strlen(name) <= NAME_MAX_LEN) {
            break;
        }
        printf("Name can't be empty or longer than %d characters.\n\n", NAME_MAX_LEN);
    }

    // Registering again only changes the name, the balance stays
    strcpy(account.name, name);
    account.registered = 1;
}

// Deposit and withdraw are almost identical, so they share this one
static void transact(int isDeposit)
{
    const char *title = isDeposit ? "Deposit" : "Withdraw";
    char   prompt[LINE_SIZE];
    int    index;
    double amount, converted, newBalance;

    printf("\n%s Amount\n", title);
    if (!requireAccount()) {
        return;
    }

    printf("Account Name: %s\n", account.name);
    printf("Current Balance: %.2f\n", account.balance);
    printf("Currency: %s\n\n", currencies[BASE_CURRENCY].code);

    // The balance is in PHP, but the user can pay in or take out any currency
    printCurrencyList("Transaction Currency Options:");
    snprintf(prompt, sizeof prompt, "%s Currency (Enter for PHP): ", title);
    index = readChoice(prompt, 1, NUM_CURRENCIES, BASE_CURRENCY + 1) - 1;

    if (!rateRecorded(index)) {
        printNoRate(index);
        return;
    }

    snprintf(prompt, sizeof prompt, "%s Amount: ", title);
    amount = readAmount(prompt, MIN_AMOUNT, MAX_AMOUNT);

    converted = amount * rates[index];
    if (converted > MAX_AMOUNT) {
        printf("That amount is too large.\n");
        return;
    }
    converted = roundToCents(converted);
    if (converted < MIN_AMOUNT) {
        printf("That amount is too small once converted to PHP.\n");
        return;
    }

    if (index != BASE_CURRENCY) {
        printf("Converted Amount (PHP): %.2f\n", converted);
    }

    if (isDeposit) {
        newBalance = account.balance + converted;
        if (newBalance > MAX_AMOUNT) {
            printf("This deposit would push the balance over the limit.\n");
            return;
        }
    } else {
        if (converted > account.balance) {
            printf("Insufficient balance.\n");
            return;
        }
        newBalance = roundToCents(account.balance - converted);
    }

    account.balance = roundToCents(newBalance);
    printf("Updated Balance: %.2f\n", account.balance);
}

static void currencyExchange(void)
{
    int    src, dst;
    double amount;

    do {
        printf("\nForeign Currency Exchange\n");
        printCurrencyList("Source Currency Option:");
        src = readChoice("Source Currency: ", 1, NUM_CURRENCIES, 0) - 1;

        if (!rateRecorded(src)) {
            printNoRate(src);
            continue;
        }

        amount = readAmount("Source Amount: ", MIN_AMOUNT, MAX_AMOUNT);

        // Default target currency is always PHP
        printf("\nDefault Target Currency = %s (%s)\n",
               currencies[BASE_CURRENCY].name,
               currencies[BASE_CURRENCY].code);
        printf("Source Amount (%s) = %.2f\n\n",
               currencies[BASE_CURRENCY].code,
               amount * rates[src]);

        printCurrencyList("Exchanged Currency Options:");
        dst = readChoice("Exchange Currency (Enter for PHP): ",
                         1, NUM_CURRENCIES, BASE_CURRENCY + 1) - 1;

        if (!rateRecorded(dst)) {
            printNoRate(dst);
            continue;
        }

        // Go through PHP: source -> pesos -> target
        printf("Exchange Amount: %.2f\n", amount * rates[src] / rates[dst]);

        /*
        continue in a do-while jumps to this check, so the error
        cases above still get asked "convert another?"
        */
    } while (readYesNo("\nConvert another currency (Y/N)? "));
}

static void recordExchangeRate(void)
{
    int index;

    printf("\nRecord Exchange Rate\n\n");
    printCurrencyList("Currency Options:");

    index = readChoice("Select Foreign Currency: ", 1, NUM_CURRENCIES, 0) - 1;

    if (index == BASE_CURRENCY) {
        printf("%s is the base currency, its rate is fixed at 1.00.\n",
               currencies[BASE_CURRENCY].code);
        return;
    }

    printf("Type how many %s one %s is worth.\n",
           currencies[BASE_CURRENCY].code, currencies[index].code);
    rates[index] = readAmount("Exchange Rate: ", MIN_RATE, MAX_AMOUNT);
}

static void showInterest(void)
{
    int    days, day;
    double balance, interest;

    printf("\nShow Interest Amount\n");
    if (!requireAccount()) {
        return;
    }

    printf("Account Name: %s\n", account.name);
    printf("Current Balance: %.2f\n", account.balance);
    printf("Currency: %s\n", currencies[BASE_CURRENCY].code);
    printf("Interest Rate: %.0f%%\n\n", ANNUAL_INTEREST_RATE * 100.0);

    days = readChoice("Total Number of Days: ", 1, MAX_DAYS, 0);

    /*
    Each day: interest = end-of-day balance x (annual rate / 365),
    rounded to the centavo and added to the balance. This is only a
    preview, the real balance doesn't change.
    */
    balance = account.balance;
    printf("\nDay | Interest | Balance |\n");
    for (day = 1; day <= days; day++) {
        interest = roundToCents(balance * ANNUAL_INTEREST_RATE / DAYS_IN_YEAR);
        balance  = roundToCents(balance + interest);
        printf("%-3d | %-8.2f | %-7.2f |\n", day, interest, balance);
    }
}

// ---------- main ----------

static int showMainMenu(void)
{
    printf("\nSelect Transaction:\n");
    printf("[1] Register Account Name\n");
    printf("[2] Deposit Amount\n");
    printf("[3] Withdraw Amount\n");
    printf("[4] Currency Exchange\n");
    printf("[5] Record Exchange Rates\n");
    printf("[6] Show Interest Amount\n\n");

    return readChoice("Enter choice: ", 1, 6, 0);
}

int main(void)
{
    int keepGoing = 1;

    printf("Banking and Currency Exchange Application\n");

    while (keepGoing) {
        switch (showMainMenu()) {
            case 1: registerAccount();    break;
            case 2: transact(1);          break;
            case 3: transact(0);          break;
            case 4: currencyExchange();   break;
            case 5: recordExchangeRate(); break;
            case 6: showInterest();       break;
        }

        // Answering N here is how you leave the program
        keepGoing = readYesNo("\nBack to the Main Menu (Y/N): ");
    }

    printf("\nThank you for banking with us!\n");
    return 0;
}
