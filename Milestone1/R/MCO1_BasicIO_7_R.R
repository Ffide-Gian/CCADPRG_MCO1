print("Selection Transaction:")
print("[1] Register Account Name")
print("[2] Deposit Amount")
print("[3] Withdraw Amount")
print("[4] Currency Exchange")
print("[5] Record Exchange Rates")
print("[6] Show Interest Amount")

cat("\n")
choice <- readline("Choice: ")
cat("\n")

print("***")
print(sprintf("Choice = %s", choice))



cat("\n")
cat("\n")
cat("\n")



print("Register Account Name")
accName <- readline("Account Name: ")
  
cat("\n")
print("***")
print(sprintf("Account Name = %s", accName))
  


cat("\n")
cat("\n")
cat("\n")



print("Deposit Amount")
print(sprintf("Account Name: %s", accName))
curBal <- 1000.00
print(sprintf("Current Balance: %.2f", curBal))
print("Currency: PHP")

cat("\n")

depAmount <- as.numeric(readline("Deposit Amount: "))

cat("\n")

print("***")
print(sprintf("Account Name = %s", accName))
print(sprintf("Deposit Amount = %.2f", depAmount))



cat("\n")
cat("\n")
cat("\n")



print("Withdraw Amount")
print(sprintf("Account Name: %s", accName))
curBal <- 1000.00
print(sprintf("Current Balance: %.2f", curBal))
print("Currency: PHP")

cat("\n")

withAmount <- as.numeric(readline("Withdraw Amount: "))

cat("\n")

print("***")
print(sprintf("Account Name = %s", accName))
print(sprintf("Withdraw Amount = %.2f", withAmount))



cat("\n")
cat("\n")
cat("\n")
  


print("Record Exchange Rate")

cat("\n")

print("[1] Philippine Peso (PHP)")
print("[2] United States Dollar (USD)")
print("[3] Japanese Yen (JPY)")
print("[4] British Pound Sterling (GBP)")
print("[5] Euro (EUR)")
print("[6] Chinese Yuan Renminni (CNY)")

cat("\n")

selCurr <- as.numeric(readline("Select Foreign Currency: "))
exRate <- as.numeric(readline("Exchange Rate: "))

print(sprintf("Exchange Rate = %.2f", exRate))

cat("\n")

print("***")
print(sprintf("Select Foreign Currency = [%d]", selCurr))
print(sprintf("Exchange Rate = %.2f", exRate))



cat("\n")
cat("\n")
cat("\n")



print("Foreign Currency Exchange")
srcAmount <- as.numeric(readline("Source Amount (PHP): "))

cat("\n")

print("Exchanged Currency")

phpCurr <- srcAmount
usdCurr <- srcAmount * 62
jpyCurr <- srcAmount * 0.4
gbpCurr <- srcAmount * 84
eurCurr <- srcAmount * 72
cnyCurr <- srcAmount * 9


print(sprintf("[1] Philippine Peso (PHP) = %.2f", phpCurr))
print(sprintf("[2] United States Dollar (USD) = %.2f", usdCurr))
print(sprintf("[3] Japanese Yen (JPY) = %.2f", jpyCurr))
print(sprintf("[4] British Pound Sterline (GBP) = %.2f", gbpCurr))
print(sprintf("[5] Euro (EUR) = %.2f", eurCurr))
print(sprintf("[6] Chinese Yuan Renminni (CNY) = %.2f", cnyCurr))

cat("\n")

print("***")
print("Source Currency = Philippine Peso (PHP)")
print(sprintf("Source Amount (PHP) = %.2f", phpCurr))

