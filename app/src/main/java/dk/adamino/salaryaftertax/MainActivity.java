package dk.adamino.salaryaftertax;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.text.DecimalFormat;

import dk.adamino.salaryaftertax.BLL.ISalaryAfterTaxCalculator;
import dk.adamino.salaryaftertax.BLL.SalaryAfterTaxCalculator;

public class MainActivity extends AppCompatActivity {

    private static final DecimalFormat SALARY_FORMATTER = new DecimalFormat("#,###.##");

    private TextView txtSalary, txtDeduction, txtTax, txtSalaryAfterTax;
    private ISalaryAfterTaxCalculator mSalaryAfterTaxCalculator;

    private double mSalary = 0;
    private double mPersonalDeduction = 0;
    private double mMunicipalityTax = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        txtSalary = findViewById(R.id.salary);
        txtDeduction = findViewById(R.id.deduction);
        txtTax = findViewById(R.id.tax);
        txtSalaryAfterTax = findViewById(R.id.lblSalaryAfterTa);
        Button calculate = findViewById(R.id.calculate);

        mSalaryAfterTaxCalculator = new SalaryAfterTaxCalculator();

        View.OnClickListener calculateListener = new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try  {
                    mSalary = Double.parseDouble(txtSalary.getText().toString());
                    mPersonalDeduction = Double.parseDouble(txtDeduction.getText().toString());
                    mMunicipalityTax = Double.parseDouble(txtTax.getText().toString());
                } catch (NumberFormatException nfe) {
                    System.out.println("Bad format " + nfe.getMessage());
                }
                double salaryAfterTax = mSalaryAfterTaxCalculator.calculateSalaryAfterTax(mSalary, mPersonalDeduction, mMunicipalityTax);

                String salaryAfterTaxAsString = SALARY_FORMATTER.format(salaryAfterTax);

                txtSalaryAfterTax.setText(salaryAfterTaxAsString);
            }
        };
        calculate.setOnClickListener(calculateListener);
    }
}
