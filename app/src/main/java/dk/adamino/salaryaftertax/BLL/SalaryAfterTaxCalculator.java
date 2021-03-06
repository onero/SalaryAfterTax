package dk.adamino.salaryaftertax.BLL;

/**
 * Created by Adamino.
 */
public class SalaryAfterTaxCalculator implements ISalaryAfterTaxCalculator {
    private static final double AMBI_DEDUCTION_RATE = 0.92; // 8%

    @Override
    public double calculateSalaryAfterTax(double salaryBeforeTax, double personalDeduction, double municipalityTaxPercentage) {
        double municipalityTaxRate = (100 - municipalityTaxPercentage) / 100;

        double salaryWithAMBIDeducted = salaryBeforeTax * AMBI_DEDUCTION_RATE;

        // Ensure that personal deduction is never more than salaryWithAMBIDeducted
        if (personalDeduction > salaryWithAMBIDeducted) {
            personalDeduction = salaryWithAMBIDeducted;
        } else // Personal deduction should never be more than salary
            if (personalDeduction > salaryBeforeTax) {
                personalDeduction = salaryBeforeTax;
            }
        double salaryWithPersonalDeductionDeducted = salaryWithAMBIDeducted - personalDeduction;
        return (salaryWithPersonalDeductionDeducted * municipalityTaxRate) + personalDeduction;
    }
}
