package com.example.creditcardinputexercise;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.basgeekball.awesomevalidation.AwesomeValidation;
import com.basgeekball.awesomevalidation.ValidationStyle;
import com.basgeekball.awesomevalidation.utility.RegexTemplate;

public class MainActivity extends AppCompatActivity {
    Button b1;
    EditText e1,e2,e3,e4;
    AwesomeValidation v;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        b1 = (Button)findViewById(R.id.button);
        e1 = (EditText)findViewById(R.id.editTextTextPersonName2);
        e2 = (EditText)findViewById(R.id.editTextTextPersonName4);
        e3 = (EditText)findViewById(R.id.editTextTextPersonName3);
        e4 = (EditText)findViewById(R.id.editTextNumber);
        v = new AwesomeValidation(ValidationStyle.BASIC);
        // Validation for Secuirity Code
        v.addValidation(this,R.id.editTextNumber,"[0-9]{3}",R.string.invalid_cvv);
        // Validation for First Name
        v.addValidation(this,R.id.editTextTextPersonName4, RegexTemplate.NOT_EMPTY,R.string.invalid_firstName);
        //Validation for Last Name
        v.addValidation(this,R.id.editTextTextPersonName3,RegexTemplate.NOT_EMPTY,R.string.invalid_lastName);
        //Validation for Expiry Date
        v.addValidation(this,R.id.editTextDate,"(0[1-9]|1[0-2]{2})/([0-9]{2})",R.string.invalid_lastName);
        // Alert After Submit Button
        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(v.validate()){
                    AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                    builder.setTitle("  Alert  ! ");
                    builder.setMessage("Payment is Successful");
                    builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            finish();
                        }
                    });
                    builder.setNeutralButton("Want to do Another Payment", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            dialogInterface.cancel();
                        }
                    });
                    // Create the Alert dialog
                    AlertDialog alertDialog = builder.create();

                    // Show the Alert Dialog box
                    alertDialog.show();
                }
                else {
                    Toast.makeText(MainActivity.this, "Validation Failed", Toast.LENGTH_SHORT).show();
                }
            }
        });

    }
}