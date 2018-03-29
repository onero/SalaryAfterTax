package dk.adamino.salaryaftertax;

import org.junit.Test;

import dk.adamino.salaryaftertax.BLL.ISalaryAfterTaxCalculator;
import dk.adamino.salaryaftertax.BLL.SalaryAfterTaxCalculator;

import static org.junit.Assert.*;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class SalaryAfterTaxCalculatorShould {
    // Deducted before tax: https://da.wikipedia.org/wiki/Arbejdsmarkedsbidrag
    private static final double AMBI_DEDUCTION_RATE = 0.92; // 8%

    private ISalaryAfterTaxCalculator mSalaryAfterTaxCalculator;

    public SalaryAfterTaxCalculatorShould() {
        mSalaryAfterTaxCalculator = new SalaryAfterTaxCalculator();
    }

    @Test
    public void calculateSalaryAfterTax_withValidInput() {
        double salaryBeforeTax = 28_000;
        double personalDeduction = 3_900;
        double municipalityTaxPercentage = 39;

        double convertedMunicipalityTaxRate = (100 - municipalityTaxPercentage) / 100;
        double salaryWithAMBIDeducted = salaryBeforeTax * AMBI_DEDUCTION_RATE;
        double salaryWithPersonalDeductionDeducted = salaryWithAMBIDeducted - personalDeduction;
        double expectedSalaryAfterTax = (salaryWithPersonalDeductionDeducted * convertedMunicipalityTaxRate) + personalDeduction;
        double result = mSalaryAfterTaxCalculator.calculateSalaryAfterTax(salaryBeforeTax, personalDeduction, municipalityTaxPercentage);

        assertTrue(expectedSalaryAfterTax == 17_234.6);
        assertEquals(expectedSalaryAfterTax, result, 0);
    }
    @Test
    public void calculateSalaryAfterTax_returnAtMostSalaryWithAMBIDeducted() {
        double salaryBeforeTax = 28_000;
        double personalDeduction = 60_000;
        double municipalityTaxPercentage = 39;

        double convertedMunicipalityTaxRate = (100 - municipalityTaxPercentage) / 100;
        double salaryWithAMBIDeducted = salaryBeforeTax * AMBI_DEDUCTION_RATE;
        // Personal deduction can never be above salaryWithAMBIDeducted
        if (personalDeduction > salaryWithAMBIDeducted) {
            personalDeduction = salaryWithAMBIDeducted;
        }
        double salaryWithPersonalDeductionDeducted = salaryWithAMBIDeducted - personalDeduction;
        double expectedSalaryAfterTax = (salaryWithPersonalDeductionDeducted * convertedMunicipalityTaxRate) + personalDeduction;
        double result = mSalaryAfterTaxCalculator.calculateSalaryAfterTax(salaryBeforeTax, personalDeduction, municipalityTaxPercentage);

        assertTrue("Salary should match", expectedSalaryAfterTax == 25_760);
        assertEquals(expectedSalaryAfterTax, result, 0);
    }
}