package com.example.robert.tipsmart;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.support.annotation.Nullable;
import android.content.Intent;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.SeekBar;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    /* Declare Local Variables */
    public Button calculate;
    public EditText totalBill, etCustomTip;
    public final static String MESSAGE_KEY_TOTAL = "com.example.robert.tipprototypeio";
    public final static String MESSAGE_KEY_NUM_PEOPLE = "112233";
    public final static String MESSAGE_KEY_NUM_TIP = "boba6969";
    public final static String MESSAGE_CUSTOM_TIP = "1122334455";
    public SeekBar sbTotalPeople;
    public TextView numPeople;
    public CheckBox cb15Percent, cb18Percent, cb20Percent, cbCustomTip;
    public String messageNumbPeople, messageTotalTip, messageTotalBill, smsTotalTip;
    public String test;
    public static Double doubleCustomTip;


    /**
     * Will execute
     * @param savedInstanceState
     */

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        //Create our Intent so that we can get our data
        Intent i = getIntent();

        smsTotalTip = i.getStringExtra(MESSAGE_CUSTOM_TIP);

        //This will disable the keyboard from popping up in the beginning
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN);

        //create an action bar object that will utilize the support methods
        ActionBar actionBar = getSupportActionBar();
        //Hides our action bar via java and not .XML
        assert actionBar != null;
        actionBar.hide();


        /* Initialize variables */
        calculate = (Button) findViewById(R.id.button);
        totalBill = (EditText) findViewById(R.id.etBillAmt);
        etCustomTip = (EditText) findViewById(R.id.etCustomTip);
        sbTotalPeople = (SeekBar) findViewById(R.id.seekBarNumberOfPeople);
        numPeople = (TextView) findViewById(R.id.tvSeekBarPeople);
        cb15Percent = (CheckBox) findViewById(R.id.cb15Percent);
        cb18Percent = (CheckBox) findViewById(R.id.cb18Percent);
        cb20Percent = (CheckBox) findViewById(R.id.cb20Percent);
        cbCustomTip = (CheckBox) findViewById(R.id.cbCustom);


        //Event Listener for seekBar to keep track of our number of people
        sbTotalPeople.getProgressDrawable().setColorFilter(
                Color.WHITE, PorterDuff.Mode.SRC_IN);
        sbTotalPeople.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                //Multiplying i by 5 will cause the value to increment by 5
                int increment1 = ((i * 5) / 25) +1;
                numPeople.setText(increment1 +" People" );

            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {


            }
        });

    }



    /**
     * Method will set up a switch statement that will interprete each
     * checkbox that is checked off by the user. We have 4 checkboxes
     * 15% , 18% , 20% , CustomTip. It uses another class called
     * FragmentDialogue.java which will allow us to access the Dialog we
     * have created when the user checks the CustomTip checkbox.
     *
     * @param view of View will pass the view and allow for the user to
     */
    public void selectTip(@Nullable View view) {


        //Boolean object will check if our checkbox is checked or not
        boolean checked = ((CheckBox) view).isChecked();

        //Create instance of Fragment Dialog
        FragmentDialogue dialogFragment = new FragmentDialogue();



        switch (view.getId()) {

            //Case if 15% is clicked
            case R.id.cb15Percent:
                if (checked) {


                    //store our value into the ArrayList
                    messageTotalTip = "15";

                    //To make sure we cant click any other box
                    cb18Percent.setChecked(false);
                    cb20Percent.setChecked(false);
                    cbCustomTip.setChecked(false);

                }
                break;


            //Case if 18% is clicked
            case R.id.cb18Percent:
                if (checked) {
                    //store our value into the ArrayList
                    messageTotalTip = "18";

                    //Make sure we cant click any other box
                    cb15Percent.setChecked(false);
                    cb20Percent.setChecked(false);
                    cbCustomTip.setChecked(false);

                }
                break;


            //Case if 20% is clicked
            case R.id.cb20Percent:
                if (checked) {
                    //store our value into the ArrayList
                    messageTotalTip = "20";

                    //Make sure we cant click any other box
                    cb15Percent.setChecked(false);
                    cb18Percent.setChecked(false);
                    cbCustomTip.setChecked(false);

                }
                break;


            //Case if the custom box is checked
            case R.id.cbCustom:
                if (checked) {


                    try{
                        //We have to call our Dialog Fragment here
                        dialogFragment.show(getFragmentManager(),"tsf");


                    }catch (Exception ex){
                        Log.d("CRAP", "THIS DID NOT WORK");
                    }


                    /*Make sure we cant click any other box*/
                    cb15Percent.setChecked(false);
                    cb18Percent.setChecked(false);
                    cb20Percent.setChecked(false);


                }
                break;
        }


    }


    /**
     * will change our values toString if they have not yet been set as one
     * and then will package up the information( messageTotalTip, messageTotalBill,
     * messageTotalPeople) and send it through an intent that will later be opened
     * in the ActivityDisplayTotal.java. They will be sent with a KEY then unpacked
     *
     * @param view View data type USED: in conjunction with ActivityDisplayTotal.java
     */
    public void sendMessage(View view) {


        //Parse variables to String
        messageTotalBill = totalBill.getText().toString();
        messageNumbPeople = numPeople.getText().toString();
        messageNumbPeople = messageNumbPeople.replace(" People","");

        if(doubleCustomTip == null){
            doubleCustomTip =0.00;
        }
//        if(!Objects.equals(messageTotalTip, "")) {
//            doubleCustomTip = 0.00;
//        }
        test = Double.toString(doubleCustomTip);
        messageTotalTip = test;

        //TEST THAT OUR VALUE IS NOT NULL
        Log.d("SWEET", "OUR VALUE IS: " + messageTotalTip);



        Intent in = new Intent(this, ActivityDisplayTotal.class);

        //Destroys the activity once intent has been executed
        in.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);

        // attach the variable into the intent
        in.putExtra(MESSAGE_KEY_NUM_PEOPLE, messageNumbPeople);
        in.putExtra(MESSAGE_KEY_NUM_TIP, messageTotalTip);
        in.putExtra(MESSAGE_KEY_TOTAL, messageTotalBill);

        // Will start the next activity and send the data
        startActivity(in);
        finish();

    }


    /**
     * Will retrieve the information from a EditText that is in our FragmentDialogue.
     * This will then be saved into our doubleCustomTip value
     *
     * IMPROVE CODE: This code could be improved by parsing the double directly into a String
     * @param customTip Will be our value
     */
    public static void setCustomTip(Double customTip){

        //Returns the vale of our edit text
        doubleCustomTip = customTip;


        Log.d("SHIT", "THIS WORKS"+doubleCustomTip);

    }

    /**
     *
     * Method will generate a true or false value that we will then use in our exitByBackKey().
     *
     * @param keyCode
     * @param event
     * @return true if the key is clicked then it will return a true value
     */
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            exitByBackKey();


            return true;
        }
        return super.onKeyDown(keyCode, event);
    }

    /**
     * Will display a dialog that will prompt the user to exit the app
     * once the the back key button on your device is clicked
     */
    protected void exitByBackKey() {

        AlertDialog alertbox = new AlertDialog.Builder(this)
                .setMessage("Are you sure you want to do this?")
                .setPositiveButton("Yes", new DialogInterface.OnClickListener() {

                    // do something when the button is clicked
                    public void onClick(DialogInterface arg0, int arg1) {

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
