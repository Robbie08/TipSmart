package com.example.robert.tipsmart;

import java.text.DecimalFormat;

/**
 * Created by Roberto on 2/28/2017.
 */
public class TipCalculations extends MainActivity{
    double totalTip, grandTotal, perPerson;

    /* Calculates Tip */
    public double calculateTip(double tipAmt, double totalBill){
        DecimalFormat df2 = new DecimalFormat( "#,###,###,#00.00" );

        totalTip = (tipAmt/100)* totalBill;

        double totalTipFormat = new Double(df2.format(totalTip)).doubleValue();

        return totalTipFormat;
    }
    /* Calculates grandTotal */
    public double calculateGrandTotal(double totalTip , double totalBill){
        DecimalFormat df2 = new DecimalFormat( "#,###,###,#00.00" );
        grandTotal = totalTip + totalBill;

        double grandTotalFormat = new Double(df2.format(grandTotal)).doubleValue();
        return grandTotalFormat;
    }


    /* Calculates tips per person */
    public double calculatePerPerson(double grandTotal, double totalPeople){
        DecimalFormat df2 = new DecimalFormat( "#,###,###,#00.00" );

        perPerson = (grandTotal/totalPeople) + (totalTip/totalPeople) ;

        double perPersonFormat = new Double(df2.format(perPerson)).doubleValue();
        return perPersonFormat;
    }
}
