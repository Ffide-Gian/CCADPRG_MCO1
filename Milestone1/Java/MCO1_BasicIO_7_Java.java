/*
********************
Last names: Borbe, Caibigan, Sering, Won
Language: Java
Paradigm(s): Procedural
********************
 */
import java.util.Scanner;

public class MCO1_BasicIO_7_Java {

    static Scanner sc = new Scanner(System.in);

    static String register() {
        System.out.println("Register Account Name");
        System.out.print("Account Name (Last Name, First Name): ");
        String name = sc.nextLine();
        System.out.println("\n***");
        System.out.println("Account Name = " + name + "\n");
        return name;
    }

    static void deposit(String name) {
        double currBal = 1000.00;
        System.out.println("Deposit Amount");
        System.out.println("Account Name: " + name);
        System.out.printf("Current Balance: %.2f%n", currBal);
        System.out.println("Currency: PHP\n");
        System.out.print("Deposit Amount: ");
        double deposit = Double.parseDouble(sc.nextLine());
        System.out.println("\n***");
        System.out.println("Account Name = " + name);
        System.out.printf("Deposit Amount = %.2f%n%n", deposit);
    }

    static void withdraw(String name) {
        double currBal = 1000.00;
        System.out.println("Withdraw Amount");
        System.out.println("Account Name: " + name);
        System.out.printf("Current Balance: %.2f%n", currBal);
        System.out.println("Currency: PHP\n");
        System.out.print("Withdraw Amount: ");
        double withdraw = Double.parseDouble(sc.nextLine());
        System.out.println("\n***");
        System.out.println("Account Name = " + name);
        System.out.printf("Withdraw Amount = %.2f%n%n", withdraw);
    }

    static void currExchange() {
        System.out.println("Foreign Currency Exchange");
        System.out.print("Source Amount (PHP): ");
        double src = Double.parseDouble(sc.nextLine());
        System.out.println("\nExchanged Currency");
        System.out.printf("[1] Philippine Peso (PHP) = %.2f%n", src);
        System.out.printf("[2] United States Dollar (USD) = %.2f%n", src * 62.00);
        System.out.printf("[3] Japanese Yen (JPY) = %.2f%n", src * 0.40);
        System.out.printf("[4] British Pound Sterling (GBP) = %.2f%n", src * 84.00);
        System.out.printf("[5] Euro (EUR) = %.2f%n", src * 72.00);
        System.out.printf("[6] Chinese Yuan Renminni (CNY) = %.2f%n", src * 9.00);
        System.out.println("\n***");
        System.out.println("Source Currency = Philippine Peso (PHP)");
        System.out.printf("Source Amount (PHP) = %.2f%n%n", src);
    }

    static void exchangeRates() {
        System.out.println("Record Exchange Rate\n");
        System.out.println("[1] Philippine Peso (PHP)");
        System.out.println("[2] United States Dollar (USD)");
        System.out.println("[3] Japanese Yen (JPY)");
        System.out.println("[4] British Pound Sterling (GBP)");
        System.out.println("[5] Euro (EUR)");
        System.out.println("[6] Chinese Yuan Renminni (CNY)\n");
        System.out.print("Select Foreign Currency: ");
        int foreign = Integer.parseInt(sc.nextLine());
        System.out.print("Exchange Rate: ");
        double rate = Double.parseDouble(sc.nextLine());
        System.out.println("\n***");
        System.out.println("Select Foreign Currency = [" + foreign + "]");
        System.out.printf("Exchange Rate = %.2f%n%n", rate);
    }

    public static void main(String[] args) {
        String name = "";
        while (true) {
            System.out.println("Select Transaction:");
            System.out.println("[1] Register Account Name");
            System.out.println("[2] Deposit Amount");
            System.out.println("[3] Withdraw Amount");
            System.out.println("[4] Currency Exchange");
            System.out.println("[5] Record Exchange Rates");
            System.out.println("[6] Show Interest Amount");
            System.out.println("[7] Exit\n");
            System.out.print("Choice: ");
            int choice = Integer.parseInt(sc.nextLine());
            System.out.println("\n***");
            System.out.println("Choice = " + choice + "\n");

            switch (choice) {
                case 1:
                    name = register();
                    break;
                case 2:
                    if (name.isEmpty()) {
                        System.out.println("Please register an account first!\n");
                    } else {
                        deposit(name);
                    }
                    break;
                case 3:
                    if (name.isEmpty()) {
                        System.out.println("Please register an account first!\n");
                    } else {
                        withdraw(name);
                    }
                    break;
                case 4:
                    currExchange();
                    break;
                case 5:
                    exchangeRates();
                    break;
                case 6:
                    System.out.println("Interest Amount\n");
                    break;
                case 7:
                    System.out.println("Program will now exit!");
                    sc.close();
                    return;
                default:
                    System.out.println("Please enter a number between 1-7!\n");
            }
        }
    }
}