/**
 * @author Student Ritesh Koirala
 * Assignment of second term of mobile app development-1
 * student ID: 21422051
 *
 * this is the registration page where you can register the user and user the given email
 * and password for the login.
 */
package com.myfirstapp.starmedia;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import java.text.DateFormat;
import java.util.Calendar;

public class CreateNew extends AppCompatActivity {
    // declaration of the variable which are further to use in the below code fragment.
    RadioGroup radio;
    RadioButton radioButton;
    EditText name, email, date, address,
            dob, password, repassword;
    Button button, back;
    // Datakeeper variable
    DataKeeper DK;
    // variable for keeping the time in dob
    DatePickerDialog.OnDateSetListener onDateSetListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_new);

        // Assigning variable their place in the layout of this page
        radio = findViewById(R.id.gender);
        name = findViewById(R.id.full);
        email = findViewById(R.id.email);
        address = findViewById(R.id.add);
        password = findViewById(R.id.pass);
        repassword = findViewById(R.id.repass);
        button = findViewById(R.id.regis);
        back = findViewById(R.id.back);

        // taking the today date and keeping them in the variable
        final Calendar calenda = Calendar.getInstance();
        String currentDate = DateFormat.getDateInstance(DateFormat.FULL).format(calenda.getTime());

        // taking the date for the two different thing
        EditText date = findViewById(R.id.regdate);
        date.setText(currentDate);
        EditText uptodate = findViewById(R.id.update);
        uptodate.setText(currentDate);

        // this is the declaration for taking the exact same date in variable
        final Calendar calendar = Calendar.getInstance();
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH);
        int day = calendar.get(Calendar.DAY_OF_MONTH);

        dob =findViewById(R.id.datedob);

        // this helps to pick the date of your birthday
        dob.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // you can pick the date you like
                // creating the dialog box from where we will pick up the birth date
                DatePickerDialog datePickerDialog = new DatePickerDialog(
                        CreateNew.this, android.R.style.Theme_Holo_Dialog_MinWidth,
                        onDateSetListener, year, month, day);
                datePickerDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                datePickerDialog.show();

            }
        });
        // this will set the dob to the variable which can be used later in many place to stare it
        onDateSetListener = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int year, int month, int dayOfMonth) {
                month = month+1;
                // this is the variable where we store the date of birth
                String date = dayOfMonth+"/"+month+"/"+year;
                dob.setText(date);
            }
        };
        // creating new object for the DataKeeper
        DK = new DataKeeper(this);
        // this is the click listener for the register button which will pass all the entered
        // value from the above field to the database
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                // Creating new variable for the value that is to be taken from the
                // above field and assigning them to variable
                String username = name.getText().toString();
                String useremail = email.getText().toString();
                String registerdate = date.getText().toString();
                String useraddress = address.getText().toString();
                // for the radio button to be checked
                int ID = radio.getCheckedRadioButtonId();
                radioButton = findViewById(ID);
                String usergender = radioButton.getText().toString();
                String userdob = dob.getText().toString();
                String newpassword = password.getText().toString();
                String newrepassword = repassword.getText().toString();
                String newuptodate = uptodate.getText().toString();


                // this will check whether the above field are empty or not
                if (username.equals("") || useremail.equals("") || registerdate.equals("") ||
                        useraddress.equals("") || usergender.equals("") || userdob.equals("") ||
                        newpassword.equals("") || newrepassword.equals("")|| newuptodate.equals("")) {
                    // if empty it will toast this message
                    Toast.makeText(CreateNew.this, "Please fill all the field", Toast.LENGTH_SHORT).show();
                }
                else {
                    // this condition will check whether the password and repassword
                    // field matches or not
                    if (newpassword.equals(newrepassword)) {
                        // checking the data in the database whether it is already there or not
                        Boolean checkuser = DK.checkuseremail(useremail);
                        // if it is already their it will display the following
                        if (!checkuser) {
                            // if the above both condition is true then the data is inserted in the table
                            Boolean insert = DK.buttonuserdata(new Data(username, useremail, registerdate, useraddress,
                                    usergender, userdob, newpassword, newrepassword, newuptodate));
                            // if the registration is successful
                            if (insert) {
                                Toast.makeText(CreateNew.this, "Registration successful", Toast.LENGTH_SHORT).show();
                            }
                            //if the registration is failed
                            else {
                                Toast.makeText(CreateNew.this, "Registration Failed!", Toast.LENGTH_SHORT).show();
                            }
                            // if the  user already in the database
                        } else {
                            Toast.makeText(CreateNew.this, "User already exists! go sign in", Toast.LENGTH_SHORT).show();
                        }// if the password does not match to each other
                    } else {
                        Toast.makeText(CreateNew.this, "Password not matching. recheck it!", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });
        // going back to the login page
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // passing intent to go to the LoginPage class.
                Intent i = new Intent(getApplicationContext(), LoginPage.class);
                // starts the activity
                startActivity(i);
                finish();
            }
        });

    }
}