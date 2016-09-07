package com.example.a1440740.loancalculator;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void onClick(View v){

        ((TextView) findViewById(R.id.errorMsg)).setText("");

        String loanAmount = ((EditText) findViewById(R.id.loanAmt)).getText().toString();
        String loanTerm = ((EditText) findViewById(R.id.loanTerm)).getText().toString();
        String loanRate = ((EditText) findViewById(R.id.loanRate)).getText().toString();

        try {
            double amount = Double.parseDouble(loanAmount);
            int term = Integer.parseInt(loanTerm);
            double rate = Double.parseDouble(loanRate);

            if(rate > 100 || term < 1 || term > 25)
                throw new IllegalArgumentException();

            LoanCalculator loan = new LoanCalculator(amount, term, rate);

            TextView monthlyP = (TextView) findViewById(R.id.monthlyP);
            TextView totalP = (TextView) findViewById(R.id.totalP);
            TextView totalInterest = (TextView) findViewById(R.id.totalInterest);

            monthlyP.setText(loan.getMonthlyPayment() + "");
            totalP.setText(loan.getTotalCostOfLoan() + "");
            totalInterest.setText(loan.getTotalInterest() + "");
        }
        catch(IllegalArgumentException e)
        {
            ((TextView) findViewById(R.id.errorMsg)).setText("Invalid Input");
        }
    }

    public void onClickClear(View v){

        ((TextView) findViewById(R.id.monthlyP)).setText("");
        ((TextView) findViewById(R.id.totalP)).setText("");
        ((TextView) findViewById(R.id.totalInterest)).setText("");

        ((EditText) findViewById(R.id.loanAmt)).setText("");
        ((EditText) findViewById(R.id.loanTerm)).setText("");
        ((EditText) findViewById(R.id.loanRate)).setText("");

    }

}
