/**
 * @author Student Ritesh Koirala
 * Assignment of second term of mobile app development-1
 * student ID: 21422051
 *
 * this is the Login page of the app through which you can get the material from the app.
 * this section validate the user before login.
 */
package com.myfirstapp.starmedia;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class LoginPage extends AppCompatActivity {
  // declaration of the variable which are further to use in the below code fragment.
    EditText email, password;
    Button button, button1;
    // Datakeeper variable
    DataKeeper DK;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_page);

        // Assigning variable their place in the layout of this page
        email = (EditText) findViewById(R.id.entry);
        password = (EditText) findViewById(R.id.passl);
        button1 = (Button) findViewById(R.id.log);

        // creating new object for DataKeeper class
        DK = new DataKeeper(this);


        // button for going on the next activity
        button = findViewById(R.id.reg);
        // adding onclick listener in the button so that we can go to the next activity.
        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // getting the value of the above email and password field in the string.
                String useremail = email.getText().toString();
                String userpass = password.getText().toString();
                // to check whether the username and password field are empty or not.
                if (useremail.equals("") || userpass.equals("")) {
                    // toasting a message if the above field are empty.
                    Toast.makeText(LoginPage.this, "enter all the detail above.", Toast.LENGTH_SHORT).show();
                } else {
                    // checking whether the username and password matches the detail in the database or not.
                    Boolean checkuserpass = DK.checkuseremailpassword(useremail, userpass);
                    // if the username and password matches in the database it will execute the following command
                    if (checkuserpass == true) {
                        // toasting a message if the above action is successful.
                        Toast.makeText(LoginPage.this, "successfull signin", Toast.LENGTH_SHORT).show();
                        // going to the next activity if the condition is true
                        Intent i = new Intent(getApplicationContext(), Home.class);
                        // sending the value of the useremail to the next activity.
                        i.putExtra("email", useremail);
                        startActivity(i);
                    }
                    // if the above username and password does not matches in the database it will execute the following command

                    else if(useremail.equals("Starmedia@admin") && userpass.equals("StarAdmin")){
                        // toasting a message if the above action is successful.
                        Toast.makeText(LoginPage.this, "successfull signin", Toast.LENGTH_SHORT).show();
                        // going to the next activity if the condition is true
                        Intent i = new Intent(LoginPage.this, AdminUser.class);
                        startActivity(i);
                    }
                    // if the username and password matches in the database does not match it will execute the following command
                    else {
                        // toasting a message if the above action is invalid.
                        Toast.makeText(LoginPage.this, "invalid login", Toast.LENGTH_SHORT).show();
                    }

                }
            }
        });
        // button which will take you to the next activity where you can create new user account.
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // passing intent to go to the createnew class.
                Intent o = new Intent(LoginPage.this, CreateNew.class);
                // starts the activity
                startActivity(o);
                finish();

            }
        });
    }
    @Override
    public void onBackPressed() {
        //there will be an alert box when you press the back button on the mobile.
        new AlertDialog.Builder(this)
                // message displayed on the alert box.
                .setMessage("Are you sure, you want to exit the StarMedia?")
                .setCancelable(false)
                // this happens when you click the yes button
                .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        finish();
                    }
                })
                // nothing happens when you click the no button on the alert box
                .setNegativeButton("No", null)

                .show();
    }
}