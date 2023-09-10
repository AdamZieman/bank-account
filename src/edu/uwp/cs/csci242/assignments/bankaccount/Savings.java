package edu.uwp.cs.csci242.assignments.bankaccount;
/**
 * This class extends the Account class, constructs a Savings object, has a method to get the interest earned on this
 * account, and a method to get and deposit the interest earned on this account.
 * <p>
 *     This class has a is-a relationship with the Account class. The method has has a default constructor that calls
 *     the Account class default constructor. A three-parameter constructor that calls the Account class three-parameter
 *     constructor; allowing the parameters to be stored in the Account class's private variables. A getInterest method
 *     that utilizes the Interest class's compound method to calculate the interest earned through compound interest.
 *     And an applyInterest method which calls the getInterest method for the calculated interest earned on this
 *     account, then calls the Account class's deposit method to deposit the earned interest and returns the updated
 *     account balance.
 * </p>
 *
 * @author Adam Zieman
 * @edu.uwp.cs242.course CSCI 242 - Computer Science II
 * @edu.uwp.cs242.section L081
 * @edu.uwp.cs242.assignment 2
 * @bugs applyInterest method throws an IllegalArgumentException, due to the Account class's deposit method throwing an
 * error if the deposit is less than $1.
 */
public class Savings extends Account {
        /**
         * Default constructor for Savings that calls the Account class's default constructor.
         */
        public Savings() {
                super();
        }

        /**
         * Constructs a three-parameter object of Savings.
         *
         * @param customer String value of the name of the customer.
         * @param name String value of the name of the account type.
         * @param balance float value of the balance of the account.
         */
        public Savings(String customer, String name, float balance) {
                // Calls the Account class's three-parameter constructor
                super(customer, name, balance);
        }


        /**
         * Calls the getInterest method to get the amount of interest earned, and deposits it into the account.
         * <p>
         *     This method calls the getInterest method with the parameter period, representing the amount of time in
         *     years, to calculate the earned interest. Afterwards, the Account class deposit method; per the method, a
         *     deposit must be greater than or equal to the minimum transaction amount. Account's method then returns
         *     the new balance of this account, after depositing the interest earned.
         * </p>
         *
         * @param period int value of the period of time that interest will be calculated for, in years.
         * @return float value of the new balance of this account.
         */
        public float applyInterest(int period) {
                return deposit(getInterest(period));
        }

        /**
         * Calls the Interest class's compound method to calculate the interest earned through compounding interest.
         * <p>
         *     Calls the Interest class's compound method with the balance of the account as the principal, an interest
         *     rate in decimal form, the compounding periods per year, and the period in years (supplied by the
         *     parameter of this method). Then returns the interest earned, which was calculated by the Interest class's
         *     method.
         * </p>
         *
         * @param period int value of the period of time that interest will be calculated for, in years.
         * @return float value of the earned interest on this account.
         */
        public float getInterest(int period) {
                // Represent the interest rate of 1.25% as a decimal
                float interestRate = 0.0125f;

                // Represents a monthly compounding period
                int compounded = 12;

                return Interest.compound(getBalance(), interestRate, compounded, period);
        }
}
