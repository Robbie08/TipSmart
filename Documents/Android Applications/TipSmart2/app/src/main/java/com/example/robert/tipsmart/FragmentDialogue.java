package com.example.robert.tipsmart;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;


/**
 * Created by Roberto on 2/28/2017.
 * <p>
 * PURPOSE: Will contain the functionality and communication between
 * this fragment and our MainActivity. This activity will collect the
 * CustomTip
 */
public class FragmentDialogue extends DialogFragment {
    //public final static String MESSAGE_CUSTOM_TIP = "1122334455";
    EditText etCustomTip;

    /*Create an instance of interface */
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        final View view;
        LayoutInflater inflater = LayoutInflater.from(getActivity());
        view = inflater.inflate(R.layout.fragment_dialogue, null);

        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());


        etCustomTip = (EditText) view.findViewById(R.id.etCustomTip);

        builder.setTitle("Enter Custom Tip:");
        builder.setView(view);

        builder.setPositiveButton("Confirm Tip", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

                /*Create Main Activity Object*/
                //  MainActivity main = new MainActivity();

                /*Parsed our variable to be accessible */
                String stringTipAmt = etCustomTip.getText().toString();
                Double doubleTipAmt = Double.parseDouble(stringTipAmt);


                /*Return to Main Activity by calling setter*/
                MainActivity.setCustomTip(doubleTipAmt);

                /*We can display a toast that will allow the user to know what percent he put*/
                Toast.makeText(getActivity(), "Your custom percent was: " + stringTipAmt + "%", Toast.LENGTH_LONG).show();

            }
        });

        builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {


            }
        });


        return builder.create();
    }
}
