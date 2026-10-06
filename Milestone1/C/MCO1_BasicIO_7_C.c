/*
********************
Last names: Borbe, Caibigan, Sering, Won
Language: C
Paradigm(s): Procedural
********************
 */

/*
 * MCO1_BasicIO_C.c
 * Foreign currency exchange: type in an amount and see what it's worth
 * in each currency. Input is just the number (1000, not [1000]).
 */

#include <stdio.h>

#define NUM_CURRENCIES 6
#define DEFAULT_TARGET 0   /* PHP, first in the list */

typedef struct {
    const char *name;
    const char *code;
    double      rateToPHP;
} Currency;

/* Rates are how many pesos one unit of each currency is worth */
static const Currency currencies[NUM_CURRENCIES] = {
    { "Philippine Peso",        "PHP",  1.00 },
    { "United States Dollar",   "USD", 62.00 },
    { "Japanese Yen",           "JPY",  0.40 },
    { "British Pound Sterling", "GBP", 84.00 },
    { "Euro",                   "EUR", 72.00 },
    { "Chinese Yuan Renminni",  "CNY",  9.00 }
};

/* Keeps asking until the user types a valid number */
static double readAmount(const char *prompt)
{
    char   line[100];
    double value;
    char   leftover;

    for (;;) {
        printf("%s", prompt);

        if (fgets(line, sizeof line, stdin) == NULL) {
            return 0.0;
        }

        /* Reading one extra char catches junk like "100abc" or "[100]" */
        if (sscanf(line, "%lf %c", &value, &leftover) == 1 && value >= 0.0) {
            return value;
        }

        printf("Please type just a number, like 1000.\n\n");
    }
}

static void displayExchangeScreen(double sourceAmount)
{
    int i;

    printf("\nForeign Currency Exchange\n");
    printf("Source Amount = %.2f\n\n", sourceAmount);

    printf("Exchanged Currency\n");
    for (i = 0; i < NUM_CURRENCIES; i++) {
        printf("[%d] %s (%s) = %.2f\n",
               i + 1,
               currencies[i].name,
               currencies[i].code,
               sourceAmount * currencies[i].rateToPHP);
    }

    /* Default target currency is always PHP */
    printf("\n***\n");
    printf("Target Currency = %s (%s)\n",
           currencies[DEFAULT_TARGET].name,
           currencies[DEFAULT_TARGET].code);
    printf("Source Amount (%s) = %.2f\n",
           currencies[DEFAULT_TARGET].code,
           sourceAmount);
}

int main(void)
{
    double sourceAmount;

    printf("Foreign Currency Exchange\n");
    sourceAmount = readAmount("Enter source amount: ");

    displayExchangeScreen(sourceAmount);

    return 0;
}
