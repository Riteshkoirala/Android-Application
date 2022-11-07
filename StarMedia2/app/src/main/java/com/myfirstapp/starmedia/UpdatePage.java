/**
 * @author Student Ritesh Koirala
 * Assignment of second term of mobile app development-1
 * student ID: 21422051
 *
 * this is the update page where thw user will update their data
 */
package com.myfirstapp.starmedia;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.text.DateFormat;
import java.util.Calendar;

public class UpdatePage extends AppCompatActivity {
    // declaration of the variable which are further to use in the below code fragment.
    RadioGroup radio;
    String email1;
    RadioButton radioButton;
    EditText per, name, email, address,dob,
            password, repassword;
    Button button, back;
    DataKeeper DK;
    // this is for the date picker
    DatePickerDialog.OnDateSetListener onDateSetListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_page);
        // getting the intent that was received from before activity
        email1 = getIntent().getStringExtra("email");
        // assigning the variable in the id of the layout
        radio = findViewById(R.id.editgender);
        per = findViewById(R.id.userid);
        name = findViewById(R.id.editfull);
        email = findViewById(R.id.editemail);
        address = findViewById(R.id.editadd);
        password = findViewById(R.id.editpass);
        repassword = findViewById(R.id.editrepass);
        button = findViewById(R.id.editregis);
        back = findViewById(R.id.goback);

        // setting of the recent date
        final Calendar calenda = Calendar.getInstance();
        String currentDate = DateFormat.getDateInstance(DateFormat.FULL).format(calenda.getTime());
         // variable for placing date in  the layout
        EditText update = findViewById(R.id.updatedate);
        // set the value of update
        update.setText(currentDate);

        // pattern on how the date will appear on the dialog box
        final Calendar calendar = Calendar.getInstance();
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH);
        int day = calendar.get(Calendar.DAY_OF_MONTH);

        dob =findViewById(R.id.editdatedob);
        // what happens when the dob box will be pressed once
        dob.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // it will make a dialog box which will be easy for picking the birth date
                DatePickerDialog datePickerDialog = new DatePickerDialog(
                        UpdatePage.this, android.R.style.Theme_Holo_Dialog_MinWidth,
                        onDateSetListener, year, month, day);
                datePickerDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                datePickerDialog.show();
            }
        });
        // when the date is sate then this will place that into this pattern in the database
        onDateSetListener = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int year, int month, int dayOfMonth) {
                month = month+1;
                String date = dayOfMonth+"/"+month+"/"+year;
                dob.setText(date);
            }
        };
        // creating the object for accessing the dataKeeper class
        Data data = new Data();
        DK = new DataKeeper(this);
        SQLiteDatabase DB = DK.getReadableDatabase();
        // use of cursor for getting the desired item from tables
        Cursor C = DB.rawQuery("SELECT * FROM USERS WHERE email = ?", new String[]{email1} );
        if(C != null){
            C.moveToFirst();
        }
        // string builder will be used to bring the data from database
        StringBuilder b = new StringBuilder();
        StringBuilder we = new StringBuilder();
        StringBuilder our = new StringBuilder();
        StringBuilder us = new StringBuilder();
        StringBuilder pas = new StringBuilder();
        StringBuilder get = new StringBuilder();
        StringBuilder repas = new StringBuilder();

        do{
            // setting up the string and getting the value of column on the string variable
            String name = C.getString(1);
            String email = C.getString(2);
            String date = C.getString(3);
            String address = C.getString(4);
            String dob = C.getString(6);
            String password = C.getString(7);
            String repassword = C.getString(8);
            // taking up the value from the table
            b.append(name);
            we.append(email);
            our.append(address);
            us.append(date);
            get.append(dob);
            pas.append(password);
            repas.append(repassword);
        }while (C.moveToNext());
        // setting of the value of the variable in the textname id in the layout.
        EditText name = (EditText) findViewById(R.id.editfull);
        name.setText(b.toString());

        EditText usemail = (EditText) findViewById(R.id.editemail);
        usemail.setText(we.toString());

        EditText address = (EditText) findViewById(R.id.editadd);
        address.setText(our.toString());

        TextView olddate = (TextView) findViewById(R.id.regDate);
        olddate.setText(us.toString());

        EditText dob = (EditText) findViewById(R.id.editdatedob);
        dob.setText(get.toString());

        EditText password = (EditText) findViewById(R.id.editpass);
        password.setText(pas.toString());

        EditText repassword = (EditText) findViewById(R.id.editrepass);
        repassword.setText(repas.toString());
        // this is the click listener for the update button which will pass all the entered
        // value from the above field to the database
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Creating new variable for the value that is to be taken from the
                // above field and assigning them to variable
                String getName = name.getText().toString();
                String useremail = email.getText().toString();
                String registerdate = olddate.getText().toString();
                String useraddress = address.getText().toString();
                int ID1 = radio.getCheckedRadioButtonId();
                radioButton = findViewById(ID1);
                String usergender = radioButton.getText().toString();
                String newDob = dob.getText().toString();
                String newpassword = password.getText().toString();
                String newrepassword = repassword.getText().toString();
                String newuptodate = update.getText().toString();
                // this will check whether the above field are empty or not
                if(getName.equals("")||useremail.equals("")||useraddress.equals("")||
                        usergender.equals("")||newpassword.equals("")||newrepassword.equals("")){
                    // if empty it will toast this message
                    Toast.makeText(UpdatePage.this, "DATA are missing", Toast.LENGTH_SHORT).show();
                }
                // this condition will check whether the password and repassword
                // field matches or not
                else if (newpassword.equals(newrepassword)) {
                    // if the above condition is correct
                    // this will send the all data to the database and update the previous one
                    boolean update = DK.updateuserdata(new Data(getName, useremail, registerdate, useraddress, usergender, newDob , newpassword, newrepassword, newuptodate));
                    if (!update) {
                        // if the update in not successful
                        Toast.makeText(UpdatePage.this, "update Failed!", Toast.LENGTH_SHORT).show();
                    }else {
                        // if the update is successful this method will be called
                        dialog();
                    }
                } else {
                    // when the user already exict the below message is pop up
                    Toast.makeText(UpdatePage.this, "passoword does not match", Toast.LENGTH_SHORT).show();
                }
            }
            // this is the button which will send you back to the profile page
        });
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(UpdatePage.this, ProfilePage.class);
                // passing on the next activity the value of email1.
                i.putExtra("email",String.valueOf(email1));
                startActivity(i);
                finish();
            }
        });
    }
    // method that has been called when the delete button will be pressed
    public void dialog(){
        DataKeeper DK = new DataKeeper(this);
        // getting extra thing that was passed from the before activity
        email1 = getIntent().getStringExtra("email");
        // the box that will be displayed when delete button will be clicked
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        //title of the alert box
        builder.setTitle("UPDATE ACCOUNT")
                // message to be shown in the alert box
                .setMessage("Are You Sure, you want to save this detail")
                // declare what happens if the no is pressed in the alert box
                .setNegativeButton("No",null)
                // declare what happens if the no is pressed in the alert box
                .setPositiveButton("yes,sure", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        // if profile update is successful this message appear
                        Toast.makeText(UpdatePage.this, "update successful", Toast.LENGTH_SHORT).show();
                        // go to the login page activity
                        Intent iN = new Intent(UpdatePage.this,LoginPage.class);
                        startActivity(iN);
                        finish();
                    }
                });
        builder.create();
        builder.show();

    }

}