/*
********************
Last names: Borbe, Caibigan, Sering, Won
Language: Java
Paradigm(s): Procedural
********************
 */
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.Locale;
import java.util.NoSuchElementException;
import java.util.Scanner;

public class MCO1_BasicIO_7_Java {

    static final Scanner sc = new Scanner(System.in);

    static final String[] NAMES = {
        "Philippine Peso", "United States Dollar", "Japanese Yen",
        "British Pound Sterling", "Euro", "Chinese Yuan Renminni"
    };
    static final String[] CODES = { "PHP", "USD", "JPY", "GBP", "EUR", "CNY" };

    static final BigDecimal[] RATES = {
        new BigDecimal("1.00"), new BigDecimal("62.00"), new BigDecimal("0.40"),
        new BigDecimal("84.00"), new BigDecimal("72.00"), new BigDecimal("9.00")
    };

    static final BigDecimal ANNUAL_INTEREST = new BigDecimal("0.05");
    static final BigDecimal DAYS_IN_YEAR = new BigDecimal("365");
    static final int MAX_INTEREST_DAYS = 3650;

    static final DecimalFormat RATE_FORMAT =
            new DecimalFormat("0.00##", DecimalFormatSymbols.getInstance(Locale.US));

    static String accountName = "";
    static BigDecimal balance = BigDecimal.ZERO;

    static String readLine(String prompt) {
        System.out.print(prompt);
        try {
            return sc.nextLine().trim();
        } catch (NoSuchElementException e) {
            System.out.println("\nInput ended. Program will now exit!");
            System.exit(0);
            return "";
        }
    }

    static int readInt(String prompt, int min, int max) {
        while (true) {
            String input = readLine(prompt);
            try {
                int value = Integer.parseInt(input);
                if (value >= min && value <= max) {
                    return value;
                }
            } catch (NumberFormatException e) {
            }
            System.out.println("Please enter a whole number between " + min + " and " + max + ".\n");
        }
    }

        static BigDecimal readAmount(String prompt) {
        while (true) {
            String input = readLine(prompt);
            try {
                BigDecimal value = new BigDecimal(input).setScale(2, RoundingMode.HALF_UP);
                if (value.signum() > 0) {
                    return value;
                }
            } catch (NumberFormatException e) {
            }
            System.out.println("Please enter a number greater than 0, like 1000.00.\n");
        }
    }

        static BigDecimal readRate(String prompt) {
        while (true) {
            String input = readLine(prompt);
            try {
                BigDecimal value = new BigDecimal(input);
                if (value.signum() > 0) {
                    return value;
                }
            } catch (NumberFormatException e) {
            }
            System.out.println("Please enter a rate greater than 0, like 52.00.\n");
        }
    }

    static boolean readYesNo(String prompt) {
        while (true) {
            String input = readLine(prompt);
            if (input.equalsIgnoreCase("Y")) {
                return true;
            }
            if (input.equalsIgnoreCase("N")) {
                return false;
            }
            System.out.println("Please enter Y or N.\n");
        }
    }

    static String money(BigDecimal value) {
        return value.setScale(2, RoundingMode.HALF_UP).toPlainString();
    }

        static BigDecimal convert(BigDecimal amount, int from, int to) {
        return amount.multiply(RATES[from]).divide(RATES[to], 2, RoundingMode.HALF_UP);
    }

    static void printCurrencyOptions() {
        for (int i = 0; i < CODES.length; i++) {
            System.out.println("[" + (i + 1) + "] " + NAMES[i] + " (" + CODES[i] + ")");
        }
    }

        static int selectCurrency(String header, String prompt) {
        System.out.println(header);
        printCurrencyOptions();
        System.out.println();
        return readInt(prompt, 1, CODES.length) - 1;
    }

    static boolean hasAccount() {
        if (accountName.isEmpty()) {
            System.out.println("Please register an account first!\n");
            return false;
        }
        return true;
    }

        static boolean backToMenu() {
        System.out.println();
        boolean back = readYesNo("Back to the Main Menu (Y/N): ");
        System.out.println();
        return back;
    }

    static void register() {
        do {
            System.out.println("Register Account Name");

            if (!accountName.isEmpty()) {
                System.out.println("Current account: " + accountName);
                if (!readYesNo("Replace it? Balance will reset to 0.00 (Y/N): ")) {
                    return;
                }
            }

            String name;
            while (true) {
                name = readLine("Account Name (Last Name, First Name): ");
                if (!name.isEmpty() && name.contains(",") && !name.startsWith(",") && !name.endsWith(",")) {
                    break;
                }
                System.out.println("Please use the format: Last Name, First Name (e.g. Dela Cruz, Juan).\n");
            }

            accountName = name;
            balance = BigDecimal.ZERO;
            System.out.println("Account registered: " + accountName);
        } while (!backToMenu());
    }

    static void deposit() {
        do {
            System.out.println("Deposit Amount");
            System.out.println("Account Name: " + accountName);
            System.out.println("Current Balance: " + money(balance) + " PHP\n");

            int cur = selectCurrency("Currency Options:", "Currency: ");
            BigDecimal amount = readAmount("Deposit Amount: ");
            BigDecimal inPhp = convert(amount, cur, 0);

            balance = balance.add(inPhp);

            System.out.println();
            if (cur != 0) {
                System.out.println("Converted Amount: " + money(inPhp) + " PHP");
            }
            System.out.println("Updated Balance: " + money(balance) + " PHP");
        } while (!backToMenu());
    }

    static void withdraw() {
        do {
            System.out.println("Withdraw Amount");
            System.out.println("Account Name: " + accountName);
            System.out.println("Current Balance: " + money(balance) + " PHP\n");

            int cur = selectCurrency("Currency Options:", "Currency: ");
            BigDecimal amount = readAmount("Withdraw Amount: ");
            BigDecimal inPhp = convert(amount, cur, 0);

            System.out.println();
            if (inPhp.compareTo(balance) > 0) {
                System.out.println("Insufficient balance! This withdrawal equals "
                        + money(inPhp) + " PHP. Transaction cancelled.");
            } else {
                balance = balance.subtract(inPhp);
                if (cur != 0) {
                    System.out.println("Converted Amount: " + money(inPhp) + " PHP");
                }
                System.out.println("Updated Balance: " + money(balance) + " PHP");
            }
        } while (!backToMenu());
    }

    static void currencyExchange() {
        boolean another;
        do {
            System.out.println("Foreign Currency Exchange\n");
            int src = selectCurrency("Source Currency Options:", "Source Currency: ");
            BigDecimal amount = readAmount("Source Amount: ");

            System.out.println();
            int tgt = selectCurrency("Exchanged Currency Options:", "Exchange Currency: ");
            BigDecimal result = convert(amount, src, tgt);

            System.out.println();
            System.out.println("Exchange Amount: " + money(result) + " " + CODES[tgt]);

            System.out.println();
            another = readYesNo("Convert another currency (Y/N)? ");
            System.out.println();
        } while (another);
    }

    static void recordExchangeRates() {
        do {
            System.out.println("Record Exchange Rate\n");
            int cur = selectCurrency("", "Select Foreign Currency: ");

            if (cur == 0) {
                System.out.println("PHP is the base currency. Its rate is fixed at 1.00.");
            } else {
                BigDecimal rate = readRate("Exchange Rate (PHP per 1 " + CODES[cur] + "): ");
                RATES[cur] = rate;
                System.out.println("Recorded: 1 " + CODES[cur] + " = " + RATE_FORMAT.format(rate) + " PHP");
            }
        } while (!backToMenu());
    }

    static void showInterest() {
        do {
            System.out.println("Show Interest Amount");
            System.out.println("Account Name: " + accountName);
            System.out.println("Current Balance: " + money(balance));
            System.out.println("Currency: PHP");
            System.out.println("Interest Rate: 5%");

            int days = readInt("Total Number of Days: ", 1, MAX_INTEREST_DAYS);
            System.out.println("Total Number of Days: " + days + (days == 1 ? " day" : " days") + "\n");

            System.out.println("Day | Interest | Balance |");
            BigDecimal running = balance;
            for (int day = 1; day <= days; day++) {
                BigDecimal interest = running.multiply(ANNUAL_INTEREST)
                        .divide(DAYS_IN_YEAR, 2, RoundingMode.HALF_UP);
                running = running.add(interest);
                System.out.println(day + " | " + money(interest) + " | " + money(running) + " |");
            }
        } while (!backToMenu());
    }

    static void printMenu() {
        System.out.println("Select Transaction:");
        System.out.println("[1] Register Account Name");
        System.out.println("[2] Deposit Amount");
        System.out.println("[3] Withdraw Amount");
        System.out.println("[4] Currency Exchange");
        System.out.println("[5] Record Exchange Rates");
        System.out.println("[6] Show Interest Amount");
        System.out.println("[7] Exit\n");
    }

    public static void main(String[] args) {
        while (true) {
            printMenu();
            int choice = readInt("Choice: ", 1, 7);
            System.out.println();

            switch (choice) {
                case 1:
                    register();
                    break;
                case 2:
                    if (hasAccount()) {
                        deposit();
                    }
                    break;
                case 3:
                    if (hasAccount()) {
                        withdraw();
                    }
                    break;
                case 4:
                    currencyExchange();
                    break;
                case 5:
                    recordExchangeRates();
                    break;
                case 6:
                    if (hasAccount()) {
                        showInterest();
                    }
                    break;
                case 7:
                    System.out.println("Program will now exit!");
                    sc.close();
                    return;
            }
        }
    }
}
