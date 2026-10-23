main_menu <- TRUE
curBal <- 1000
usdRate <- 1
jpyRate <- 1
gbpRate <- 1
eurRate <- 1
cnyRate <- 1
accName <- ""


while (main_menu == TRUE){
  
  print("Select Transaction: ")
  print("[1] Register Account Name")
  print("[2] Deposit Amount")
  print("[3] Withdraw Amount")
  print("[4] Currency Exchange")
  print("[5] Record Exchange Rates")
  print("[6] Show Interest Amount")
  
  cat("\n")
  choice <- as.numeric(readline("Choice: "))
  cat("\n")
  
  if (choice == 1){
    print("Register Account Name")
    accName <- readline("Account Name: ")
    
    cat("\n")
    
    back <- readline("Back to the Main Menu (Y/N): ")
    
    if(toupper(back) == "N"){
      main_menu <- FALSE
    }
    cat("\n")
    
  } else if(choice == 2){
    
    if(accName == ""){
      print("Please register an account name first!")
    } else {
      
      print("Deposit Amount")
      print(sprintf("Account Name: %s", accName))
      print(sprintf("Current Balance: %.2f", curBal))
      print("Currency: PHP")
      
      cat("\n")
      
      depAmount <- as.numeric(readline("Deposit Amount: "))
      
      if(is.na(depAmount) || depAmount <= 0){
        printf("Enter Valid Input")
      } else{
        curBal <- curBal + depAmount
        print(sprintf("Updated Balance: %.2f", curBal)) 
      }
      
      cat("\n")
      
      back <- readline("Back to the Main Menu (Y/N): ")
      
      if(toupper(back) == "N"){
        main_menu <- FALSE
      }
      cat("\n")
    }
    
    
    
  } else if(choice == 3){
    
    if(accName == ""){
      print("Please register an account name first!")
    } else {
      print("Withdraw Amount")
      print(sprintf("Account Name: %s", accName))
      print(sprintf("Current Balance: %.2f", curBal))
      print("Currency: PHP")
      
      cat("\n")
      
      withAmount <- as.numeric(readline("Withdraw Amount: "))
      
      if(is.na(withAmount) || withAmount <= 0){
        print("Enter valid Input")
      } else if (withAmount > curBal){
        print("Insufficient balance.")
      } else{
        curBal <- curBal - withAmount
        print(sprintf("Updated Balance: %.2f", curBal))
      }
      
      cat("\n")
      
      back <- readline("Back to the Main Menu (Y/N): ")
      
      if(toupper(back) == "N"){
        main_menu <- FALSE
      }
      cat("\n")
    }
    
  } else if(choice == 4){
    
    backCurr <- "Y"
    
    while(backCurr == "Y"){
      
      print("Foreign Currency Exchange")
      print("Source Currency Option: ")
      print("[1] Philippine Peso (PHP)")
      print("[2] United States Dollar (USD)")
      print("[3] Japanese Yen (JPY)")
      print("[4] British Pound Sterling (GBP)")
      print("[5] Euro (EUR)")
      print("[6] Chinese Yuan Renminni (CNY)")
      
      cat("\n")
      
      srcCurrOpt <- as.numeric(readline("Source Currency: "))
      srcExAmount <- as.numeric(readline("Source Amount: "))
      
      if(srcCurrOpt == 1){
        
        exAmount <- srcExAmount
        
      } else if(srcCurrOpt == 2){
        
        exAmount <- srcExAmount * usdRate
        
      } else if(srcCurrOpt == 3){
        
        exAmount <- srcExAmount * jpyRate
        
      } else if(srcCurrOpt == 4){
        
        exAmount <- srcExAmount * gbpRate
        
      } else if(srcCurrOpt == 5){
        
        exAmount <- srcExAmount * eurRate
        
      } else if(srcCurrOpt == 6){
        
        exAmount <- srcExAmount * cnyRate
        
      } else{
        
        print("Please Choose a number between 1 to 6 for source currency!")
        
      }
      
      cat("\n")
      
      print("Exchanged Currency Options:")
      print("[1] Philippine Peso (PHP)")
      print("[2] United States Dollar (USD)")
      print("[3] Japanese Yen (JPY)")
      print("[4] British Pound Sterling (GBP)")
      print("[5] Euro (EUR)")
      print("[6] Chinese Yuan Renminni (CNY)")
      
      cat("\n")
      
      exCurrOpt <- as.numeric(readline("Exchange Currency: "))
      
      if(exCurrOpt == 1){
        
        resAmount <- exAmount
        curBal = curBal + exAmount
        
      } else if(exCurrOpt == 2){
        
        resAmount <- exAmount / usdRate
        curBal = curBal + exAmount
        
      } else if(exCurrOpt == 3){
        
        resAmount <- exAmount / jpyRate
        curBal = curBal + exAmount
        
      } else if(exCurrOpt == 4){
        
        resAmount <- exAmount / gbpRate
        curBal = curBal + exAmount
        
      } else if(exCurrOpt == 5){
        
        resAmount <- exAmount / eurRate
        curBal = curBal + exAmount
        
      } else if(exCurrOpt == 6){
        
        resAmount <- exAmount / cnyRate
        curBal = curBal + exAmount
        
      } else{
        
        print("Please Choose a number between 1 to 6 for source currency!")
        
      }
      
      print(sprintf("Exchange Amount: %.2f", resAmount))
      
      cat("\n")
      
      backCurr <- readline("Convert another currency (Y/N)?... ")
      
      cat("\n")
      
    }
    
    
  } else if(choice == 5){
    
    print("Record Exchange Rate")
    
    cat("\n")
    
    print("[1] Philippine Peso (PHP)")
    print("[2] United States Dollar (USD)")
    print("[3] Japanese Yen (JPY)")
    print("[4] British Pound Sterling (GBP)")
    print("[5] Euro (EUR)")
    print("[6] Chinese Yuan Renminni (CNY)")
    
    cat("\n")
    
    selForCurr <- as.numeric(readline("Select Foreign Currency: "))
    exRate <- as.numeric(readline("Exchange Rate: "))
    
    if(is.na(exRate) || exRate <= 0){
      
      print("Enter valid exchange rate")
      
    } else{
        if(selForCurr == 1){
          
          if(exRate != 1){
            print("You cannot change the PHP exchange rate, as it is the base currency")
          
            cat("\n")
          }
        
        } else if(selForCurr == 2){
        
          usdRate = exRate
        
        } else if (selForCurr == 3){
        
          jpyRate = exRate
        
        } else if (selForCurr == 4){
        
          gbpRate = exRate
        
        } else if (selForCurr == 5){
        
          eurRate = exRate
        
        } else if (selForCurr == 6){
        
          cnyRate = exRate
        
        } else{
        
          print("Choose a valid currency (1 ~ 6)")
        
        }
      
      cat("\n")
    }
    
    back <- readline("Back to the Main Menu (Y/N): ")
    
    if(toupper(back) == "N"){
      main_menu <- FALSE
    }
    cat("\n")
    
  } else if(choice == 6){
    
    if(accName == ""){
      print("Please register an account name first!")
    } else {
      print("Show Interest Amount")
      print(sprintf("Account Name: %s", accName))
      print(sprintf("Current Balance: %.2f", curBal))
      print("Currency: PHP")
      print("Interest Rate: 5%")
      
      cat("\n")
      
      numDays <- as.numeric(readline("Total Number of Days: "))
      
      cat("\n")
      
      print("Day | Interest | Balance |")
      
      dailyRate <- 0.05/365
      
      i = 1
      
      for(i in 1:numDays){
        
        dailyInterest <- curBal * dailyRate
        
        curBal <- curBal + dailyInterest
        
        print(sprintf("%d   | %.2f    | %.2f |", i, dailyInterest, curBal))
      }
      
      cat("\n")
      
      back <- readline("Back to the Main Menu (Y/N): ")
      
      if(toupper(back) == "N"){
        main_menu <- FALSE
      }
      cat("\n")
      
    }
  }
  
  
  
  
}