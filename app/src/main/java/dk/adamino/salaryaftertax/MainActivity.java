package dk.adamino.salaryaftertax;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.text.DecimalFormat;

public class MainActivity extends AppCompatActivity {

    private TextView mSalary;
    private TextView mDeduction;
    private TextView mTax;
    private TextView mSalaryAfterTax;
    private Button mCalculate;

    private double salary = 0;
    private double deduction = 0;
    private double tax = 0;
    private double salaryAfterTax;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mSalary = (TextView) findViewById(R.id.salary);
        mDeduction = (TextView) findViewById(R.id.deduction);
        mTax = (TextView) findViewById(R.id.tax);
        mSalaryAfterTax = (TextView) findViewById(R.id.lblSalaryAfterTa);
        mCalculate = (Button) findViewById(R.id.calculate);

        View.OnClickListener calculateListener = new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try  {
                    salary = Double.parseDouble(mSalary.getText().toString());
                    deduction = Double.parseDouble(mDeduction.getText().toString());
                    tax = Double.parseDouble(mTax.getText().toString());
                } catch (NumberFormatException nfe) {
                    System.out.println("Bad format " + nfe.getMessage().toString());
                }


                double salaryAfterAM = salary * 0.91;

                double salaryAfterDeduction = salaryAfterAM - deduction;

                double taxConvertedToDouble = (100 - tax) / 100;

                double salaryMinusTax = salaryAfterDeduction * taxConvertedToDouble;

                salaryAfterTax = salaryMinusTax + deduction;

                DecimalFormat formatter = new DecimalFormat("#,###.00");

                String salaryAfterTaxAsString = formatter.format(salaryAfterTax);

                mSalaryAfterTax.setText(salaryAfterTaxAsString);
            }
        };
        mCalculate.setOnClickListener(calculateListener);
    }
}
