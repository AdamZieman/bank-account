package edu.uwp.cs.csci242.assignments.bankaccount;

/**
 * This class contains the main method which is the first method ran when executing the program. This class will call
 * on the other classes in the project to perform various activities through this mock bank account.
 * <p>
 *     The main method will create the objects of Checking, Savings, and CollegeSavings to be used as a mock account.
 *     The method will call various methods and perform various actions as if it these were real accounts. The other
 *     method of this class, printBalances is used to print an account summary for all of the accounts.
 * </p>
 * The purpose of this project is to demonstrate my knowledge and understanding of inheritance and polymorphism.
 * The super-class is Account
 * Accounts sub-classes are Checking and Savings
 * Savings sub-class is College Savings
 *
 *  @author Adam Zieman
 *  @edu.uwp.cs242.course CSCI 242 - Computer Science II
 *  @edu.uwp.cs242.section L081
 *  @edu.uwp.cs242.assignment 2
 *  @bugs applyInterest in both the Checking class and Savings class throws an IllegalArgumentException(), due to the
 *  Account's deposit method, if the interest is less than $1.00.
 */
public class Bank {
    /**
     * This method is the first method ran when the program executes. It is responsible for creating the objects,
     * performing various bank features on them, and printing output to the terminal.
     * <p>
     *     The method creates an object of type Checking, Savings, and CollegeSavings to be used as mock bank accounts
     *     for a single customer. It will perform various, typical bank operations on it: depositing money, paying
     *     expenses, writing checks for bills, accumulating interest, moving funds around for a vacation, and paying
     *     for tuition. After each task is completed, a summary of the accounts will be printed to display the progress
     *     of the accounts.
     * </p>
     *
     * @param args String[] value of the arguments ran when the program is executed; no arguments added.
     */
    public static void main(String[] args) {
        /*
         * Three objects are created: a Checking object, a collegeSavings object, and a Savings object. They are
         * given the arguments of the customer's name, the type of account, and their starting balance.
         */
        Checking checkingAccount = new Checking("Jared Burgess", "Checking account", 2000f);
        CollegeSavings collegeSavingsAccount = new CollegeSavings("Jared Burgess",
                "College savings account", 10000f);
        Savings savingsAccount = new Savings("Jared Burgess", "Savings account", 1000f);

        // Calls printBalance to print a summary of the accounts.
        printBalances(checkingAccount, collegeSavingsAccount, savingsAccount);

        // This section represents the customer receiving a check and disbursing it between the accounts.
        // Represents the whole paycheck
        float paycheck = 5000.00f;

        // disburse the appropriate amount of the paycheck throughout the account
        checkingAccount.deposit((5000 - 1000) / 2);
        collegeSavingsAccount.deposit(1000);
        savingsAccount.deposit((5000 - 1000) / 2 );

        // Prints a message to the terminal, then prints the account summary
        System.out.println("Got a paycheck: $5000. 1000 in college savings, then half in savings & half in checking.");
        printBalances(checkingAccount, collegeSavingsAccount, savingsAccount);

        // This section represents the customer receiving a years worth of interest on their accounts
        // Interest is calculated by each of their accounts for 1 year
        checkingAccount.applyInterest(1);
        savingsAccount.applyInterest(1);
        collegeSavingsAccount.applyInterest(1);

        // Prints a message to the terminal, then prints the account summary
        System.out.println("Time for interest!");
        printBalances(checkingAccount, collegeSavingsAccount, savingsAccount);

        // This section represents the customer paying bills
        // variables represent the various bills
        float mortgage1Bill = 150.00f;
        float mortgage2Bill = 1975.45f;
        float gasElectricBill = 145.68f;
        float waterBill = 60.34f;
        float tvInternetPhoneBill = 277.45f;

        // A check is written for each of the various bills from the checking account
        checkingAccount.writeCheck(mortgage1Bill);
        checkingAccount.writeCheck(mortgage2Bill);
        checkingAccount.writeCheck(gasElectricBill);
        checkingAccount.writeCheck(waterBill);
        checkingAccount.writeCheck(tvInternetPhoneBill);

        // Prints several messages to the terminal, then prints the account summary
        System.out.println("Write some bills...");
        System.out.println("> House mortgage, $ 150.00...");
        System.out.println("> House mortgage, $1975.45...");
        System.out.println("> Gas and Electric, $ 145.68...");
        System.out.println("> Water, $60.34...");
        System.out.println("> TV/Internet/phone, $277.45...");
        printBalances(checkingAccount, collegeSavingsAccount, savingsAccount);

        // This section represents variable expenses
        // Variables represent various variable expenses
        float groceriesExpense = 226.90f;
        float nightOutExpense = 177.80f;
        float fixCarExpense = 733.66f;

        // The funds are withdrawn from the checking account
        checkingAccount.withdraw(groceriesExpense);
        checkingAccount.withdraw(nightOutExpense);
        checkingAccount.withdraw(fixCarExpense);

        // Prints several messages to the terminal, then prints the account summary
        System.out.println("Live life...");
        System.out.println("> Groceries, $226.90...");
        System.out.println("> Night out, $177.80...");
        System.out.println("> Fix car, $733.66...");
        printBalances(checkingAccount, collegeSavingsAccount, savingsAccount);


        // This section represents going on a vacation
        // Represents the cost of going on a vacation
        float vacationExpenses = 4000.00f;

        // Represents a message if the funds are available
        String availableFunds = "> Funds available... going on vacation!";
        String notAvailableFunds = "> Funds not available... Must transfer..."; // used only for college funds
        String penaltyMessage = "> Not enough in checking+savings... Take from college fund at a penalty...";

        // Prints a message to the terminal
        System.out.println("Vacation, $4000.00...");

        /*
        Checks if the checking account has enough funds to pay for the vacation, if so, checking account withdraws
        the funds from its account. Otherwise, checks if the savings account has enough funds to pay for the vacation,
        if so, savings account withdraws the funds from its account. Otherwise, checks if the savings account with the
        college funds have enough to pay for the vacation. If so, takes out $100 at a time from the savings account,
        until it has less than $100 and takes the remaining amount from the college funds. Print a message to the
        terminal letting them know which of the accounts did not have the available funds, and whether or not they are
        going on vacation.*/
        // Checks checking account
        if (!checkingAccount.isAmountAvailable(vacationExpenses)) {
            // Checks savings account
            if (!savingsAccount.isAmountAvailable(vacationExpenses)) {
                System.out.println(notAvailableFunds + "\n" + penaltyMessage);
                /*
                 Loop that withdraws $100 from savings account as a time while savings account has greater than or
                 equal to $100.
                 */
                while (savingsAccount.getBalance() >= 100) {
                    savingsAccount.withdraw(100);
                    vacationExpenses -= 100;
                }
                // Withdraws the excess amount required to go on vacation from college funds
                collegeSavingsAccount.withdraw(vacationExpenses);
            }
            else {
                savingsAccount.withdraw(vacationExpenses);
                System.out.println(availableFunds);
            }
        }
        else {
            checkingAccount.withdraw(vacationExpenses);
            System.out.println(availableFunds);
        }

        // Prints a summary of the accounts
        printBalances(checkingAccount, collegeSavingsAccount, savingsAccount);


        // This section represents paying for tuition
        // Represents the cost of tuition
        float tuitionExpense = 8000.00f;

        // Withdraws the cost of tuition for the college fund
        collegeSavingsAccount.withdrawForCollege(tuitionExpense);

        // Prints a message to the terminal and an account summary
        System.out.println("Tuition, $" + tuitionExpense + "...");
        printBalances(checkingAccount, collegeSavingsAccount, savingsAccount);
    }

    /**
     * Prints the account summary for all the accounts.
     * <p>
     *     Prints a summary of the accounts. The header of the summary is the customer's name, which can be acquired by
     *     any of the objects. Displays information for checking account, then college savings account, the savings
     *     account. The information for each include, the account's ID, the type of account, and the balance (which is
     *     called via Account class's toString method). Finally prints an end of summary of several dashes.
     * </p>
     *
     * @param checkingType Account object of to access the Checking object
     * @param collegeSavingsType Account object to access the collegeSavings object
     * @param savingsType Account object to access the Savings object
     */
    public static void printBalances(Account checkingType, Account collegeSavingsType, Account savingsType) {
        System.out.println("+-- Customer: " + checkingType.getCustomer() + " ---");
        System.out.println("| Current balance of checking [" + checkingType.getId() + "], '" + checkingType.getName() +
                "' is: " + checkingType);
        System.out.println("| Current balance of college savings [" + collegeSavingsType.getId() + "], '" +
                collegeSavingsType.getName() + "' is: " + collegeSavingsType);
        System.out.println("| Current balance of savings [" + savingsType.getId() + "], '" + savingsType.getName() +
                "' is: " + savingsType);
        System.out.println("+-----------------------------");
    }
}
