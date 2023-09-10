package edu.uwp.cs.csci242.assignments.bankaccount;

import java.util.Random;

/**
 * This class is the super class in the inheritance hierarchy. It holds private class variables, getters and setters,
 * creates an ID, has constructors, withdraw and deposit methods, a method to check if funds are available in an
 * account, and an overridden toString method.
 * <p>
 *     This is the super class in the inheritance hierarchy, with it's subclasses being Checking and Savings.
 *     Additionally, Savings has a subclass of CollegeSavings. The class-level variables are all private, meaning they
 *     are only accessible to this class. Their is a default constructor which is required by the contructors of the
 *     subclasses. And a three-parameter constructor to create Account objects, and assign them a random ID number. A
 *     setter and getter are established for many of these methods to access and modify them outside of the class.
 *     makeID creates a random String array, made of numbers zero through nine. A withdraw method withdraws funds from
 *     an account given certain conditions. A deposit method adds funds to an account given certain conditions. An
 *     isAvailable method is used to check if a transaction is greater than the funds in the account. And a toString
 *     method is used to print a formatted version of the balance.
 * </p>
 *
 *  @author Adam Zieman
 *  @edu.uwp.cs242.course CSCI 242 - Computer Science II
 *  @edu.uwp.cs242.section L081
 *  @edu.uwp.cs242.assignment 2
 *  @bugs applyInterest in both the Checking class and Savings class throws an IllegalArgumentException(), due to the
 *  Account's deposit method, if the interest is less than $1.00.
 */
public class Account {
    /**
     * Balance of the account
     */
    private float balance;

    /**
     * Name of the customer
     */
    private String customer;

    /**
     * ID of the account
     */
    private String id;

    /**
     * name of the type of account
     */
    private String name;

    /**
     * Default constructor for the Account class.
     */
    public Account() {

    }

    /**
     * Constructs a three-parameter object of Account.
     * <p>
     *     Initializes the parameters to the object variables, and creates a random ID associated to that account.
     * </p>
     *
     * @param customer String value of the name of the customer.
     * @param name String value of the name of the account type.
     * @param balance float value of the balance of the account.
     */
    public Account(String customer, String name, float balance) {
        this.customer = customer;
        this.name = name;
        this.balance = balance;
        id = makeId(10);
    }

    /**
     * Checks if the account has enough funds to complete the transaction.
     *
     * @param amount float value of the amount to compare to the balance
     * @return boolean value of balance being greater than or equal to the amount
     */
    public boolean isAmountAvailable(float amount) {
        return getBalance() >= amount;
    }

    /**
     * Deposits the funds to an account.
     * <p>
     *     This method deposits the requests funds to an account if it is greater than or equal to the minimum
     *     transaction amount. Otherwise it throws an IllegalArgumentException. It returns the updated balance of the
     *     account.
     * </p>
     *
     * @param amount float value of the amount requested to deposit.
     * @return float value of the new account balance.
     */
    public float deposit(float amount) {
        // Represents the required minimum transaction amount.
        int minimumTransaction = 1;

        /*
         Throws an IllegalArgumentException if the requested withdrawn amount is less than the required minimum
         transaction amount.
         */
        if (amount < minimumTransaction) {
            throw new IllegalArgumentException();
        }

        return balance += amount;
    }

    /**
     * Withdraws the funds from an account.
     *<p>
     *     This method withdraws the requested funds from an account if it is greater than or equal to the minimum
     *     transaction amount. Otherwise it throws an IllegalArgumentException. It returns the updated balance of the
     *     account.
     *</p>
     *
     * @param amount float value of the amount requested for withdraw.
     * @return float value of the new account balance.
     */
    public float withdraw(float amount) {
        // Represents the required minimum transaction amount.
        int minimumTransaction = 1;

        /*
         Throws an IllegalArgumentException if the requested withdrawn amount is less than the required minimum
         transaction amount.
         */
        if (amount < minimumTransaction) {
            throw new IllegalArgumentException();
        }

        return balance -= amount;
    }

    /**
     * Generates a random ID of a set length.
     * <p>
     *     Creates a StringBuilder object and a Random object. The Random object will randomly pick integers between 0
     *     and a (max number - 1), to append to the String builder until it reaches size, assigned by the parameter.
     *     Then returns the completed random ID StringBuilder as a String.
     * </p>
     *
     * @param size int value of the length of the ID.
     * @return String value of the random ID.
     */
    private String makeId(int size) {
        // Creates a Random object
        Random randomNumber = new Random();

        // Creates a StringBuilder object to hold the String values of the ID while being built
        StringBuilder id = new StringBuilder();

        // Represents the maximum number the random number will generate
        int maxNum = 10;

        /*
        Generates random integers from 0 to (the maxNum - 1) to be appended to the StringBuilder object.
         */
        for (int index = 0; index < size; index++) {
            id.append(randomNumber.nextInt(maxNum));
        }

        // Returns the StringBuilder as a String
        return id.toString();
    }

    /**
     * Overrides the Object class's toString method to return a reformatted balance.
     * <p>
     *     The balance is saved into a String variable, with only 2 decimal places. If the number is greater than or
     *     equal to 1,000 and less than 1,000,000, the thousands places are placed in a thousands variable via the
     *     substring method. The hundreds and decimals are placed in the hundreds variable via the substring method.
     *     A String is returns with a leading dollar sign, the thousands variable, a comma, then the hundreds variable.
     *     If the balance is less than 1,000, a String is returns with a leading dollar sign.
     * </p>
     *
     * @return String value of the reformatted balance.
     */
    @Override
    public String toString() {
        // Formats the balance to a String with two decimal places
        String strBalance = String.format("%.2f", balance);

        // Represent the values of balance from the thousands place to the hundred-thousands place
        String thousands;

        // Represents the values of balance from the hundreds place to the hundredth place
        String hundreds;

        /*
        Checks the whole number size of the balance to reformat the balance as a string with a leading dollar signn two
        decimal places and commas to separate the thousand from the hundreds.
         */
        if (balance > 1000000) {
            thousands = strBalance.substring(0,3);
            hundreds = strBalance.substring(3);
            return "$" + thousands + "," + hundreds;
        }
        else if (balance >= 10000 && balance < 100000) {
            thousands = strBalance.substring(0,2);
            hundreds = strBalance.substring(2);
            return "$" + thousands + "," + hundreds;
        }
        else if (balance >= 1000 && balance < 10000) {
            thousands = strBalance.substring(0,1);
            hundreds = strBalance.substring(1);
            return "$" + thousands + "," + hundreds;
        }
        else {
            return "$" + strBalance;
        }
    }

    /**
     * Retrieves the ID of the account.
     *
     * @return String value of the ID of the account.
     */
    public String getId() {
        return id;
    }

    /**
     * Retrieves the balance of the account.
     *
     * @return float value of the balance of the account.
     */
    public float getBalance() {
        return balance;
    }

    /**
     * Retrieves the name of the customer.
     *
     * @return String value of the customer.
     */
    public String getCustomer() {
        return customer;
    }

    /**
     * Sets the name of the customer.
     *
     * @param customer String value of the customer
     */
    public void setCustomer(String customer) {
        this.customer = customer;
    }

    /**
     * Retrieves the name of the account type
     *
     * @return String value of the name of the account type
     */
    public String getName() {
        return name;
    }

    /**
     * Sets the name of the account type.
     *
     * @param name String value of the name of the account type.
     */
    public void setName(String name) {
        this.name = name;
    }
}
