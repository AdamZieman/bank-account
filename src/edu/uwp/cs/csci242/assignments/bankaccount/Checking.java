package edu.uwp.cs.csci242.assignments.bankaccount;

/**
 * This class extends the Account class, constructs a Checking object, has a method for writing checks, a method for
 * returning the interest earned on this account, and a method to get and deposit the interest earned on this account.
 * <p>
 *     This class has a is-a relationship with the Account class. The method has a default constructor that class the
 *     Account class default constructor. A three-parameter constructor that calls the Account class three-parameter
 *     constructor; allowing the parameters to be stored in the Account class's private variables. A getInterest method
 *     that utilizes the Interest class's compound method to calculate the interest earned through compound interest.
 *     An applyInterest method which calls the getInterest method for the calculated interest earned on this
 *     account, then calls the Account class's deposit method to deposit the earned interest and returns the updated
 *     account balance. And a writeCheck class that withdraws funds from this account.
 * </p>
 *
 * @author Adam Zieman
 * @edu.uwp.cs242.course CSCI 242 - Computer Science II
 * @edu.uwp.cs242.section L081
 * @edu.uwp.cs242.assignment 2
 * @bugs applyInterest throws an IllegalArgumentException(), due to the Account's deposit method, if the interest is
 * less than $1
 */
public class Checking extends Account {
    /**
     * Default constructor for Checking that calls the Account class's default constructor.
     */
    public Checking() {

    }

    /**
     * Constructs a three-parameter object of Checking.
     *
     * @param customer String value of the name of the customer.
     * @param name String value of the name of the account type.
     * @param balance float value of the balance of the account.
     */
    public Checking(String customer, String name, float balance) {
        super(customer, name, balance);
    }

    /**
     * This method is a redundancy to the Account class's withdraw method.
     *
     * @param amount float value of the amount of money to withdraw from this account.
     * @return float value of the new balance of the account.
     */
    public float writeCheck(float amount) {
        return withdraw(amount);
    }

    /**
     * This method calls getInterest method to calculate the interest earned on this account, then deposits it.
     * <p>
     *     This method will call on the getInterest method to calculate the interest earned on this account over the
     *     period, in years. It then calls on the Account class's deposit method; per the method, requires a minimum
     *     deposit. Then returns the new balance of this account.
     * </p>
     *
     * @param period int value of the period of time that interest will be calculated for, in years.
     * @return float value of the new balance of this account.
     */
    public float applyInterest(int period) {
        return deposit(getInterest(period));
    }

    /**
     * This method calculates the interest earned on this account.
     * <p>
     *     If the balance of this account is greater than or equal to the minimum balance required to earn interest,
     *     than call the Interest class's simple method to calculate the earned interest on the funds greater than and
     *     equal to the minimum balance. Otherwise, no interest was earned on this account.
     * </p>
     *
     * @param period int value of the period of time that interest will be calculated for, in years.
     * @return float value of the interest earned on this account
     */
    public float getInterest(int period) {
        // Represents the minimum balance required to begin earning interest.
        int minimumBalance = 700;

        // Represents the
        float principal = getBalance() - minimumBalance;

        // Represents the interest rate of 2% as a decimal
        float interestRate = 0.02f;

        /*
         Interest is calculated only on the funds greater than or equal to this minimum balance. If the funds are less
         than the minimum balance, no interest was earned.
         */
        if (getBalance() > minimumBalance) {
            return Interest.simple(principal, interestRate, period);
        }
        else {
            return 0;
        }
    }
}
