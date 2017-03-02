package com.example.robert.tipsmart;

import java.text.DecimalFormat;

/**
 * Created by Roberto on 2/28/2017.
 * <p>
 * PURPOSE: To calculate Tip, Grand Total, and Tip per person
 * will be utilized by the ActivityDisplayTotal Activity
 */
public class TipCalculations extends MainActivity {
    double totalTip, grandTotal, perPerson;

    /* Calculates Tip */
    public double calculateTip(double tipAmt, double totalBill) {
        DecimalFormat df2 = new DecimalFormat("#,###,###,#00.00");

        totalTip = (tipAmt / 100) * totalBill;

        return Double.valueOf(df2.format(totalTip));
    }

    /* Calculates grandTotal */
    public double calculateGrandTotal(double totalTip, double totalBill) {
        DecimalFormat df2 = new DecimalFormat("#,###,###,#00.00");

        grandTotal = totalTip + totalBill;

        return Double.valueOf(df2.format(grandTotal));
    }


    /* Calculates tips per person */
    public double calculatePerPerson(double grandTotal, double totalPeople) {
        DecimalFormat df2 = new DecimalFormat("#,###,###,#00.00");

        perPerson = (grandTotal / totalPeople) + (totalTip / totalPeople);

        return Double.valueOf(df2.format(perPerson));
    }
}
