package com.mhealth.t4l;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.telephony.SmsManager;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.mhealth.sqlite.R;

/**
 * Created by DELL on 12/11/2015.
 */
public class Welcome extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.welcome);

        final EditText txtUnitsIn = (EditText) findViewById(R.id.txtUnitsIn);
        if (txtUnitsIn.getText().toString().trim().length() == 0)
            txtUnitsIn.setError("Units in is required!");

        final EditText txtUnitsScreened = (EditText) findViewById(R.id.txtUnitsScreened);
        if (txtUnitsScreened.getText().toString().trim().length() == 0)
            txtUnitsScreened.setError("Number of Screened units is required!");

        final EditText txtUnscreenedUnits = (EditText) findViewById(R.id.txtUnscreenedUnits);
        if (txtUnscreenedUnits.getText().toString().trim().length() == 0)
            txtUnscreenedUnits.setError("Number of Unscreened units is required!");

        final EditText txtUnitsSorted = (EditText) findViewById(R.id.txtUnitsSorted);
        if (txtUnitsSorted.getText().toString().trim().length() == 0)
            txtUnitsSorted.setError("Number of Screened units is required!");

        final EditText txtUnitsOut = (EditText) findViewById(R.id.txtUnitsOut);
        if (txtUnitsOut.getText().toString().trim().length() == 0)
            txtUnitsOut.setError("Number of units out is required!");

        final EditText txtfacilitycode = (EditText) findViewById(R.id.txtfacilitycode);
        if (txtfacilitycode.getText().toString().trim().length() == 0)
            txtfacilitycode.setError("Facility code is required!");


        final Context gratitude = this;
        final Button btnSSubmit = (Button) findViewById(R.id.btnSSubmit);
        btnSSubmit.setEnabled(true);
        btnSSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String UnitsIn = txtUnitsIn.getText().toString();
                String UnitsScreened = txtUnitsScreened.getText().toString();
                String UnitsUnscreened = txtUnscreenedUnits.getText().toString();
                String UnitsSorted = txtUnitsSorted.getText().toString();
                String UnitsOut = txtUnitsOut.getText().toString();
                String FaciltyCode = txtfacilitycode.getText().toString();
                String Command = "BS";
                String Space = "*";
                String PhoneNumber = "+254703775979";
                String Message = Command + Space + UnitsIn + Space + UnitsScreened + Space + UnitsUnscreened + Space
                        + UnitsSorted + Space + UnitsOut + Space + FaciltyCode ;
                System.out.println("Values Keyed in : "+Message);

                try {
                    if (txtUnitsIn.getText().toString().trim().length() > 0 &
                            txtUnitsOut.getText().toString().trim().length() > 0 &
                            txtUnitsScreened.getText().toString().trim().length() > 0 &
                            txtUnscreenedUnits.getText().toString().trim().length() > 0 &
                            txtUnitsSorted.getText().toString().trim().length() > 0 &
                            txtfacilitycode.getText().toString().trim().length() > 0)
                    {
                        SmsManager sm = SmsManager.getDefault();
                        sm.sendTextMessage(PhoneNumber, null, Message, null, null);




                        btnSSubmit.setEnabled(true);

                    } else {

                        Toast toast = Toast.makeText(Welcome.this, "Please enter the data", Toast.LENGTH_LONG);
                        toast.setGravity(Gravity.CENTER | Gravity.CENTER_HORIZONTAL, 0, 0);
                        toast.show();

                        return;
                    }


                    LayoutInflater layoutInflater = LayoutInflater.from(gratitude);
                    View cadreView = layoutInflater.inflate(R.layout.confirmation, null);
                    final AlertDialog.Builder Dialog = new AlertDialog.Builder(gratitude);
                    Dialog.setView(cadreView);
                    Dialog
                            .setCancelable(true)
                            .setTitle("Submit Units?")
                            .setMessage("");

                    Dialog.setNegativeButton("No", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            Intent i = new Intent(getBaseContext(), Welcome.class);
                            startActivity(i);


                            Toast inputs = Toast.makeText(getApplicationContext(), "Review units and submit", Toast.LENGTH_SHORT);
                            inputs.setGravity(Gravity.CENTER | Gravity.CENTER_HORIZONTAL, 0, 0);
                            inputs.show();
                        }
                    });
                    Dialog.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {

                            Intent i = new Intent(getBaseContext(), MainActivity.class);
                            startActivity(i);

                            Toast data = Toast.makeText(getApplicationContext(), "Data Submission Successful!", Toast.LENGTH_SHORT);
                            data.setGravity(Gravity.CENTER | Gravity.CENTER_HORIZONTAL, 0, 0);
                            data.show();
                        }
                    });


                    AlertDialog alertDialog = Dialog.create();
                    alertDialog.show();


                    txtUnitsIn.setText("");
                    txtUnitsOut.setText("");
                    txtUnitsScreened.setText("");
                    txtUnscreenedUnits.setText("");
                    txtUnitsSorted.setText("");
                    txtfacilitycode.setText("");


                } catch (Exception e) {

                    Toast unsuccessful = Toast.makeText(getApplicationContext(), "Data Submission UnSuccessful", Toast.LENGTH_SHORT);
                    unsuccessful.setGravity(Gravity.CENTER | Gravity.CENTER_HORIZONTAL, 0, 0);
                    unsuccessful.show();

                    System.out.println("Error Message : "+e.getMessage());


                }
            }
        });
    }
}

