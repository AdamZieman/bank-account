package edu.uwp.cs.csci242.assignments.bankaccount;

/**
 * This class extends the Savings class, constructs a CollegeSavings object, and has two withdraw methods.
 * <p>
 *     This class has a is-a relationship with the Savings class. The method has a default constructor that calls the
 *     Savings class default constructor. A three-parameter constructor that calls the Savings class three-parameter
 *     constructor; allowing the parameters to be stored in the Account class's private variables. A withdraw method,
 *     which overrides Account class's withdraw method to penalize the customer from improperly withdrawing from this
 *     account type. And a withdrawForCollege method which allows the customer to withdraw from this fund, to pay for
 *     college tuition, without no penalty.
 * </p>
 *
 * @author Adam Zieman
 * @edu.uwp.cs242.course CSCI 242 - Computer Science II
 * @edu.uwp.cs242.section L081
 * @edu.uwp.cs242.assignment 2
 * @bugs none
 */
public class CollegeSavings extends Savings {
    /**
     * Default constructor for CollegeSavings that calls the Savings class's default constructor.
     */
    public CollegeSavings() {
        super();
    }

    /**
     * Constructs a three-parameter object of CollegeSavings.
     *
     * @param customer String value of the name of the customer.
     * @param name String value of the name of the account type.
     * @param balance float value of the balance of the account.
     */
    public CollegeSavings(String customer, String name, float balance) {
        // Calls the Savings class's three-parameter constructor
        super(customer, name, balance);
    }

    /**
     * Calculates and returns the total withdrawn amount from this account, after improperly withdrawing money.
     * <p>
     *     This method overrides the Account class withdraw method, penalizing the prior to withdraw balance. The
     *     sum of the total withdrawn amount is used as an argument for the Account class's withdraw method. Per the
     *     super method, a withdraw must be greater than or equal to the minimum transaction amount. Account's method
     *     then returns the new balance of this account, after withdrawing the penalty amount and the requested amount.
     *     </p>
     *
     * @param amount float value of the amount of money requested to withdraw from this account.
     * @return float value of the new balance of this account.
     */
    @Override
    public float withdraw(float amount) {
        // Represents the 10% penalty to the balance, as a decimal, if improperly withdrawing funds from this account
        float penaltyPercentage = 0.1f;

        /*
        Represents the penalty amount to be withdrawn from this account, if improperly withdrawing funds from this
        account
         */
        float penaltyAmount = getBalance() * penaltyPercentage;

        return super.withdraw(amount + penaltyAmount);
    }

    /**
     * Calculates and returns the withdrawn amount from this account.
     * <p>
     *     The amount of money requested to be withdrawn by this method is used as an argument for the Account class's
     *     withdraw method. Per the super method, a withdraw must be greater than or equal to the minimum transaction
     *     amount. Account's method then returns the new balance of this account, after the withdrawing the requested
     *     amount.
     * </p>
     *
     * @param amount float value of the amount of money requested to withdraw from this account.
     * @return float value of the new balance of this account.
     */
    public float withdrawForCollege(float amount) {
        return super.withdraw(amount);
    }
}
