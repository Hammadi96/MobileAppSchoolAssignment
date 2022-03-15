package ca.gbc.comp3074.assignment1;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {
    //creating EditTexts and TextViews as global variables so that they can be accessible inside the onClick method calculateTaxAndPay
    EditText noOfHoursEditText;
    EditText hourlyRateEditText;
    TextView payTextView, overtimePayTextView, totalPayTextView, taxTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        // we need to get the EditTexts and TextViews we defined in XML into our Java file in order to take input and display output to the user
        // we do it by specifying an Id in XML file and referencing the Id to fetch the elements in Java code
        // these EditTexts are used for taking input from the user
        noOfHoursEditText = findViewById(R.id.num_hours_edit_text);
        hourlyRateEditText = findViewById(R.id.hourly_rate_edit_text);
        // these TextViews are used for displaying output to the user
        payTextView = findViewById(R.id.pay_text_view);
        overtimePayTextView = findViewById(R.id.overtime_pay_text_view);
        totalPayTextView = findViewById(R.id.total_pay_text_view);
        taxTextView = findViewById(R.id.tax_text_view);
    }

    // as we have specified this method in the onClick attribute of our button, this method will be called if the user clicks on the button
    public void calculateTaxAndPay(View view) {
        try {
            // after the button is clicked we fetch the data entered in the EditTexts using getText and toString methods
            // we then convert the string into double type using parseDouble method
            double noOfHoursWorked = Double.parseDouble(noOfHoursEditText.getText().toString());
            double hourlyRate = Double.parseDouble(hourlyRateEditText.getText().toString());
            // initializing methods for displaying in the output
            double pay = 0;
            double overtimePay = 0;
            double totalPay = 0;
            double tax = 0;
            // calculating the output using the given formulas
            if (noOfHoursWorked <= 40) {
                pay = noOfHoursWorked * hourlyRate;
                overtimePay = 0;
                totalPay = pay;
            } else {
                pay = 40 * hourlyRate;
                overtimePay = (noOfHoursWorked - 40) * hourlyRate;
                totalPay = pay + overtimePay;
            }
            tax = totalPay * 0.18;
            // displaying the output to the user by using setText method on TextViews
            // also used String format method in order to show only decimal places for double values
            payTextView.setText(String.format("%.2f", pay));
            overtimePayTextView.setText(String.format("%.2f", overtimePay));
            totalPayTextView.setText(String.format("%.2f", totalPay));
            taxTextView.setText(String.format("%.2f", tax));
        } catch (NumberFormatException nfe) {
            // If the user does not give valid input a toast message will be displayed
            Toast.makeText(this, "Invalid input!", Toast.LENGTH_SHORT).show();
        }
    }

    // this method is used to inflate(render) the menu from the XML file
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu, menu);
        return true;
    }

    // this method is used to implement functionality when any of the menu items are clicked
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle item selection
        switch (item.getItemId()) {
            case R.id.about_me:
                // startActivity method is used to navigate from one activity to another
                startActivity(new Intent(MainActivity.this, AboutActivity.class));
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}