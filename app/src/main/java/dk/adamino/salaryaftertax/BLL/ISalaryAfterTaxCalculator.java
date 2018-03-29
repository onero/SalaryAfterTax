package dk.adamino.salaryaftertax.BLL;

/**
 * Created by Adamino.
 */
public interface ISalaryAfterTaxCalculator {

    /***
     * Calculate provided Salary after AM and deduction
     * @param salaryBeforeTax
     * @param personalDeduction
     * @param municipalityTaxPercentage
     * @return Salary after tax as double
     */
    double calculateSalaryAfterTax(double salaryBeforeTax, double personalDeduction, double municipalityTaxPercentage);
}
