/*
********************
Last names: Borbe, Caibigan, Sering, Won
Language: Kotlin
Paradigm(s): Procedural
********************
 */
fun register(): String{
    println("Register Account Name");
    print("Account Name (Last Name, First Name): ")
    val name = readln();
    println("\n***");
    println("Account Name = $name\n");
    return name;
}

fun deposit(name: String){
    println("Deposit Amount");
    println("Account Name: $name");
    val currBal: Double = 1000.00;
    println("Current Balance: $currBal");
    println("Currency: PHP\n");
    print("Deposit Amount: ");
    val deposit = readln().toDouble();
    println("\n***");
    println("Account Name = $name");
    println("Deposit Amount = $deposit\n");
}

fun withdraw(name: String){
    println("Withdraw Amount");
    println("Account Name: $name");
    val currBal: Double = 1000.00;
    println("Current Balance: $currBal");
    println("Currency: PHP\n");
    print("Withdraw Amount: ");
    val withdraw = readln().toDouble();
    println("\n***");
    println("Account Name = $name");
    println("Withdraw Amount = $withdraw\n");
}

fun currExchange(){
    println("Foreign Current Exchange");
    val currBal: Double = 1000.00;
    println("Source Amount (PHP): $currBal");
    println("Exchanged Currency");
    println("[1] Philippine Peso (PHP) = $currBal");
    println("[2] United States Dollar (USD) = ${currBal*62}");
    println("[3] Japanese Yen (JPY) = ${currBal*0.4}");
    println("[4] British Pound Sterling (GBP) = ${currBal*84}");
    println("[5] Euro (EUR) = ${currBal*72}");
    println("[6] Chinese Yuan Renminni (CNY) = ${currBal*9}");
    println("\n***");
    println("Target Currency = Philippine Peso (PHP)");
    println("Source Amount (PHP) = $currBal\n");
}

fun exchangeRates(){
    var rate: Double = 0.00;
    println("Record Exchange Rates\n");
    println("[1] Philippine Peso (PHP)");
    println("[2] United States Dollar (USD)");
    println("[3] Japanese Yen (JPY)");
    println("[4] British Pound Sterling (GBP)");
    println("[5] Euro (EUR)");
    println("[6] Chinese Yuan Renminni (CNY)\n");
    print("Select Foreign Currency: ");
    var foreign = readln().toInt();
    when (foreign) {
        2 -> rate = 62.00;
        3 -> rate = 0.40;
        4 -> rate = 84.00;
        5 -> rate = 72.00;
        6 -> rate = 9.00;
        else -> println("Please enter a number between 2-6!");
    }
    println("Exchange Rate: $rate");
    println("\n***");
    println("Select Foreign Currency = $foreign");
    println("Exchange Rate = $rate\n");
}

fun main(){
    var name = "";
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
                deposit(name);
            }

            3 -> if(name.isEmpty()){
                println("Please register an account first!\n");
            } else{
                withdraw(name);
            }

            4 -> currExchange();

            5 -> exchangeRates();

            6 -> println("Interest Amount");

            7 -> {
                print("Program will now exit!");
                break;
            }

            else -> println("Please enter a number between 1-7!\n");
        }
    }
}
