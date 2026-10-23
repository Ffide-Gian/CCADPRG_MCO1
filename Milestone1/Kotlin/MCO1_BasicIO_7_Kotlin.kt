/*
********************
Last names: Borbe, Caibigan, Sering, Won
Language: Kotlin
Paradigm(s): Procedural
********************
 */
fun register(): String{
    while(true){
        println("Register Account Name");
        print("Account Name (Last Name, First Name): ")
        val name = readln();
        println("\n***");
        println("Account Name = $name\n");
        print("Back to the Main Menu (Y/N): ");
        val choice = readln();
        if(choice == "Y" || choice == "y"){
            return name;
        }
    }
}

fun deposit(name: String, currBal: Double): Double {
    var balance = currBal;

    while(true){
        println("Deposit Amount");
        println("Account Name: $name");
        println("Current Balance: $balance");
        print("Currency: ");
        val currency = readln();
        print("\nDeposit Amount: ");
        val deposit = readln().toDouble();
        when(currency){
            "PHP" -> balance += deposit;
            "USD" -> balance += (deposit*62.00);
            "JPY" -> balance += (deposit*0.40);
            "GBP" -> balance += (deposit*84.00);
            "EUR" -> balance += (deposit*72.00);
            "CNY" -> balance += (deposit*9.00);
            else -> println("Invalid currency!");
        }
        println("Updated Balance = $balance\n");
        print("Back to the Main Menu (Y/N): ");
        val choice = readln();
        if(choice == "Y" || choice == "y"){
            return balance;
        }
    }
}

fun withdraw(name: String, currBal: Double): Double{
    var balance = currBal;
    while(true){
        println("Withdraw Amount");
        println("Account Name: $name");
        println("Current Balance: $balance");
        println("Currency: PHP\n");
        print("Withdraw Amount: ");
        val withdraw = readln().toDouble();
        balance -= withdraw;
        println("Updated Balance = $balance\n");
        print("Back to the Main Menu (Y/N): ");
        val choice = readln();
        if(choice == "Y" || choice == "y"){
            return balance;
        }
    }
}

fun currExchange(){
    while(true){
        println("Foreign Current Exchange");
        println("Source Currency Options:");
        println("[1] Philippine Peso (PHP)");
        println("[2] United States Dollar (USD)");
        println("[3] Japanese Yen (JPY)");
        println("[4] British Pound Sterling (GBP)");
        println("[5] Euro (EUR)");
        println("[6] Chinese Yuan Renminni (CNY)");
        print("Source Currency: ");
        val srcCurr = readln().toInt();
        print("Source Amount: ");
        val srcAmt = readln().toDouble();
        println("\nExchanged Currency Options:");
        println("[1] Philippine Peso (PHP)");
        println("[2] United States Dollar (USD)");
        println("[3] Japanese Yen (JPY)");
        println("[4] British Pound Sterling (GBP)");
        println("[5] Euro (EUR)");
        println("[6] Chinese Yuan Renminni (CNY)");
        print("Exchange Currency: ");
        val exCurr = readln().toInt();
        if(exCurr == srcCurr){
            println("\nSource Currency can't be the same as Exchange Currency!\n");
            break;
        } else{
            var exAmt: Double = 0.00;
            when(srcCurr){
                1 -> //PHP
                    when(exCurr){
                        2 -> exAmt = srcAmt * 0.016; //to USD
                        3 -> exAmt = srcAmt * 2.51; //to JPY
                        4 -> exAmt = srcAmt * 0.041; //to GBP
                        5 -> exAmt = srcAmt * 0.014; //to EUR
                        6 -> exAmt = srcAmt * 0.11; //to CNY
                    }
                2 -> //USD
                    when(exCurr){
                        1 -> exAmt = srcAmt * 62.0; //to PHP
                        3 -> exAmt = srcAmt * 158.24; //to JPY
                        4 -> exAmt = srcAmt * 0.75; //to GBP
                        5 -> exAmt = srcAmt * 0.89; //to EUR
                        6 -> exAmt = srcAmt * 6.69; //to CNY
                    }
                3 -> //JPY
                    when(exCurr){
                        1 -> exAmt = srcAmt * 0.40; //to PHP
                        2 -> exAmt = srcAmt * 0.0063; //to USD
                        4 -> exAmt = srcAmt * 0.0047; //to GBP
                        5 -> exAmt = srcAmt * 0.0056; //to EUR
                        6 -> exAmt = srcAmt * 0.0042; //to CNY
                    }
                4 -> //GBP
                    when(exCurr){
                        1 -> exAmt = srcAmt * 84.00; //to PHP
                        2 -> exAmt = srcAmt * 1.33; //to USD
                        3 -> exAmt = srcAmt * 210.91; //to JPY
                        5 -> exAmt = srcAmt * 1.19; //to EUR
                        6 -> exAmt = srcAmt * 8.92; //to CNY
                    }
                5 -> //EUR
                    when(exCurr){
                        1 -> exAmt = srcAmt * 72.00; //to PHP
                        2 -> exAmt = srcAmt * 1.12; //to USD
                        3 -> exAmt = srcAmt * 177.43; //to JPY
                        4 -> exAmt = srcAmt * 0.84; //to GBP
                        6 -> exAmt = srcAmt * 7.51; //to CNY
                    }
                6 -> //CNY
                    when(exCurr){
                        1 -> exAmt = srcAmt * 9.00; //to PHP
                        2 -> exAmt = srcAmt * 0.15; //to USD
                        3 -> exAmt = srcAmt * 23.65; //to JPY
                        4 -> exAmt = srcAmt * 0.11; //to GBP
                        5 -> exAmt = srcAmt * 0.13; //to EUR
                    }
            }
            println("Exchange Amount: $exAmt\n");
        }
        print("Convert another currency (Y/N): ");
        val choice = readln();
        if(choice == "N" || choice == "n"){
            break;
        }
    }
}

fun exchangeRates(){
    var rate: Double = 0.00;
    while(true){
        println("Record Exchange Rates\n");
        println("[1] Philippine Peso (PHP)");
        println("[2] United States Dollar (USD)");
        println("[3] Japanese Yen (JPY)");
        println("[4] British Pound Sterling (GBP)");
        println("[5] Euro (EUR)");
        println("[6] Chinese Yuan Renminni (CNY)\n");
        print("Select Foreign Currency: ");
        val foreign = readln().toInt();
        when (foreign) {
            1 -> rate = 1.00;
            2 -> rate = 62.00;
            3 -> rate = 0.40;
            4 -> rate = 84.00;
            5 -> rate = 72.00;
            6 -> rate = 9.00;
            else -> println("Please enter a number between 2-6!");
        }
        println("Exchange Rate: $rate\n");
        print("Back to the Main Menu (Y/N): ");
        val choice = readln();
        if(choice == "Y" || choice == "y"){
            break;
        }
    }
}

fun interestRates(name: String, currBal: Double){
    var balance = currBal;
    val interestRate: Double = 0.05;
    while(true){
        println("Show Interest Amount");
        println("Account Name: $name");
        println("Current Balance: $balance");
        println("Currency: PHP");
        println("Interest Rate: ${interestRate*100}%\n");
        print("Total Number of Days: ");
        val days = readln().toInt();
        val dailyInterest = balance * (interestRate / 365);
        println("\nDay | Interest | Balance |");
        for(i in 1..days){
            balance += dailyInterest;
            println("$i   | ${"%.2f".format(dailyInterest)}     | ${"%.2f".format(balance)} |");
        }
        print("\nBack to the Main Menu (Y/N): ");
        val choice = readln();
        if(choice == "Y" || choice == "y"){
            break;
        }
    }
}

fun main(){
    var name = "";
    var currBal: Double = 1000.00;
    while(true){
        println("Select Transaction:");
        println("[1] Register Account Name");
        println("[2] Deposit Amount");
        println("[3] Withdraw Amount");
        println("[4] Currency Exchange");
        println("[5] Record Exchange Rates");
        println("[6] Show Interest Amount");
        println("[7] Exit");
        print("Choice: ");
        val choice = readln().toInt();
        println("\n***");
        println("Choice = $choice\n");
        when (choice){
            1 -> name = register();

            2 -> if(name.isEmpty()){
                println("Please register an account first!\n");
            } else{
                currBal = deposit(name, currBal);
            }

            3 -> if(name.isEmpty()){
                println("Please register an account first!\n");
            } else{
                currBal = withdraw(name, currBal);
            }

            4 -> currExchange();

            5 -> exchangeRates();

            6 -> if(name.isEmpty()){
                println("Please register an account first!\n");
            } else {
                interestRates(name, currBal);
            }

            7 -> {
                print("Program will now exit!");
                break;
            }

            else -> println("Please enter a number between 1-7!\n");
        }
    }
}
