package com.example.robert.tipsmart;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

/**
 * Created by Roberto on 2/28/2017.
 */

public class ActivityDisplayTotal extends AppCompatActivity {
    public final static String MESSAGE_KEY_TOTAL = "com.example.robert.tipprototypeio";
    public final static String MESSAGE_KEY_NUM_PEOPLE = "112233";
    public final static String MESSAGE_KEY_NUM_TIP = "boba6969";
    /* Declare Variables */
    Button reset;
    TextView tvPerPerson, tvGrandTotal, tvTip, tvTipPercent, tvPeopleAMT;
    Double valueNumPeople, valueNumTip, valueGrandTotal;
    Double totalTip, totalPerPerson, grandTotal;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_total);

        /* Initialize variables */
        reset = (Button) findViewById(R.id.bReset);
        tvPerPerson = (TextView) findViewById(R.id.tvPerPerson);
        tvGrandTotal = (TextView) findViewById(R.id.tvGrandTotal);
        tvTip = (TextView) findViewById(R.id.tvTip);
        tvTipPercent = (TextView) findViewById(R.id.tvTipCount);




        /* create an action bar object that will utilize the support methods */
        ActionBar actionBar = getSupportActionBar();
        /* Hides our action bar via java and not .XML */
        actionBar.hide();

        /* Intent object that will get our data */
        Intent intent = getIntent();

        /* store the value that was sent as a String before you set the value to View */
        String messageGrandTotal = intent.getStringExtra(MESSAGE_KEY_TOTAL);

        String messageNumbPeople = intent.getStringExtra(MESSAGE_KEY_NUM_PEOPLE);

        String messageNumbTip = intent.getStringExtra(MESSAGE_KEY_NUM_TIP); //<----MAKE SURE IT WORKS


        /* Must Turn our values into double so that we can make the calculations */
        try {
            valueNumPeople = Double.parseDouble(messageNumbPeople);
            valueNumTip = Double.parseDouble(messageNumbTip);
            valueGrandTotal = Double.parseDouble(messageGrandTotal);


        } catch (Exception e) {
            valueNumPeople = 0.0;
            valueNumTip = 0.0;
            valueGrandTotal = 0.0;
        }


        /*Declared our TipCalculations object */
        TipCalculations calc = new TipCalculations();

        /*Calculated all our values*/
        totalTip = calc.calculateTip(valueNumTip, valueGrandTotal);
        totalPerPerson = calc.calculatePerPerson(valueGrandTotal, valueNumPeople);
        grandTotal = calc.calculateGrandTotal(totalTip, valueGrandTotal);

        /*We must parse all of our variables to String*/

        String stringTotalTip = Double.toString(totalTip);
        String stringTotalPerPerson = Double.toString(totalPerPerson);
        String stringGrandTotal = Double.toString(grandTotal);

        /* give our View the value of message */
        if (totalPerPerson > 0 && totalTip > 0 && grandTotal > 0) {

            tvPerPerson.setText("$" + stringTotalPerPerson);

            tvTip.setText("$" + stringTotalTip);

            tvGrandTotal.setText("$" + stringGrandTotal);

        } else {
            tvPerPerson.setText("$0.00");

            tvTip.setText("$0.00");

            tvGrandTotal.setText("$0.00");

        }


        //Let our user know how much was the tip amount
        tvTipPercent.setText("Included Tip (" +messageNumbTip+"%) :");

        reset.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                /* This will allow us to traverse to the next Activity */
                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);
                finish();
            }

        });

    }

    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            //exitByBackKey();

            Intent intent = new Intent(getApplicationContext(), MainActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            startActivity(intent);
            finish();
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }

    protected void exitByBackKey() {

        AlertDialog alertbox = new AlertDialog.Builder(this)
                .setMessage("Want to go back?")
                .setPositiveButton("Yes", new DialogInterface.OnClickListener() {

                    // do something when the button is clicked
                    public void onClick(DialogInterface arg0, int arg1) {

                        Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                        intent.putExtra("EXIT", true);
                        startActivity(intent);
                        finish();
                        //close();


                    }
                })
                .setNegativeButton("No", new DialogInterface.OnClickListener() {

                    // do something when the button is clicked
                    public void onClick(DialogInterface arg0, int arg1) {
                    }
                })
                .show();

    }
}
