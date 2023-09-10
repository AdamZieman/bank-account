package edu.uwp.cs.csci242.assignments.bankaccount;

/**
 * This class calculates and returns the earned interest.
 * <p>
 *     This class contains two methods that calculates and returns the interest earned from an account using either
 *     simple interest, or compound interest.
 * </p>
 * Simple interest earned is calculated utilizing the following formula:
 * interestEarned = principal * interestRate * time
 * Compound interest earned is calculated utilizing the following formula:
 * compoundInterest = principal * ((1 + interestRate / compounded ^(time * compounded)) - 1)
 *
 * @author Adam Zieman
 * @edu.uwp.cs242.course CSCI 242 - Computer Science II
 * @edu.uwp.cs242.section L081
 * @edu.uwp.cs242.assignment 2
 * @bugs none
 */
public class Interest {
    /**
     * Calculates and returns the interest earned from an account that uses simple interest.
     *
     * @param principal float value the balance that interest will be calculated from.
     * @param interestRate float value the annual interest rate, in decimal form.
     * @param time int value of the period of time that interest will be calculated for, in years.
     * @return float value of the earned interest.
     */
    public static float simple(float principal, float interestRate, int time) {
        return principal * interestRate * time;
    }

    /**
     * Calculates and returns the interest earned from an account that uses compound interest.
     *
     * @param principal float value the balance that interest will be calculated from.
     * @param interestRate float value of the annual interest rate, in decimal form.
     * @param compounded int value of the number of compounding periods per year.
     * @param time int value of the period of time that interest will be calculated for, in years.
     * @return the earned interest.
     */
    public static float compound(float principal, float interestRate, int compounded, int time) {
        // Because Math.pow() returns a double, it must be type cast to a float for the method to return a float
        return principal * ((float)Math.pow((1 + interestRate / compounded), (time * compounded)) - 1);
    }
}
