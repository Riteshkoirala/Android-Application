/**
 * @author Student Ritesh Koirala
 * Assignment of second term of mobile app development-1
 * student ID: 21422051
 *
 * this is the AdProfileLOOK page in which admin can look profile and delete the user along with the post
 */
package com.myfirstapp.starmedia;

import static android.app.ProgressDialog.show;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AlertDialogLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.AlertDialog;
import android.content.DialogInterface;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.ArrayList;

public class AdProfileLook extends AppCompatActivity {
    // declaration of the variable which are further to use in the below code fragment.
    String username;
    Button del, back;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ad_profile_look);
        DataKeeper DK = new DataKeeper(this);
        // getting the intent that was received from before activity
        username = getIntent().getStringExtra("name");
        // button that will delete both the user and their activity
        del = findViewById(R.id.debtn);
        del.setOnClickListener(new View.OnClickListener() {
            @Override
            // method that will be called when the delete button will be pressed
            public void onClick(View v) {
                dialog();
            }
        });
        // button that will take you back to the useradmin page
        back = findViewById(R.id.btnn);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // passing intent for going to the next page
                Intent iN = new Intent(AdProfileLook.this, AdminUser.class);
                startActivity(iN);
                finish();
            }
        });
        //setting database into readable database
        SQLiteDatabase DB = DK.getReadableDatabase();
        // use of cursor for getting the desired item from tables
        Cursor C = DB.rawQuery("SELECT * FROM USERS WHERE name = ?", new String[]{username});
        if (C != null) {
            C.moveToFirst();
        }
        // string builder will be used to bring the data from database
        StringBuilder b = new StringBuilder();
        StringBuilder we = new StringBuilder();
        StringBuilder our = new StringBuilder();
        StringBuilder us = new StringBuilder();
        StringBuilder go = new StringBuilder();
        StringBuilder get = new StringBuilder();
        StringBuilder last = new StringBuilder();

        do {
            // setting up the string and getting the value of column on the string variable
            String name = C.getString(1);
            String email = C.getString(2);
            String date = C.getString(3);
            String address = C.getString(4);
            String gender = C.getString(5);
            String dob = C.getString(6);
            String las = C.getString(9);
            // taking up the value from the table
            b.append(name);
            we.append(" Connect me " + email);
            our.append("Lives in " + address);
            us.append("Joined in " + date);
            go.append("Gender " + gender);
            get.append("Date Of Birth  " + dob);
            last.append("Last Updated " + las);

        } while (C.moveToNext());
        // setting of the value of the variable in the textname id in the layout.
        TextView name = (TextView) findViewById(R.id.textname);
        name.setText(b.toString());

        TextView usemail = (TextView) findViewById(R.id.textemail);
        usemail.setText(we.toString());

        TextView address = (TextView) findViewById(R.id.textaddress);
        address.setText(our.toString());

        TextView date = (TextView) findViewById(R.id.textdatejoined);
        date.setText(us.toString());

        TextView gen = (TextView) findViewById(R.id.gender);
        gen.setText(go.toString());

        TextView dob = (TextView) findViewById(R.id.dob);
        dob.setText(get.toString());

        TextView lasty = (TextView) findViewById(R.id.LASTUPDATED);
        lasty.setText(last.toString());
    }

    // method that has been called when the delete button will be pressed
    public void dialog() {
        // creating new instance of the database
        DataKeeper DK = new DataKeeper(this);
        // getting extra thing that was passed from the before activity
        username = getIntent().getStringExtra("name");
        // the box that will be displayed when delete button will be clicked
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        //title of the alert box
        builder.setTitle("DELETE ACCOUNT")
                // declare what happens if the yes is pressed in the alert box
                .setMessage("Are You Sure, you want to delete this account")
                .setPositiveButton("yes,sure", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        Boolean B = DK.admindeletedata(username);
                        Boolean C = DK.admindeletepost(username);
                        // checking whether the condition in correct or not
                        if (B == true && C == true) {
                            // if the condition is true this message will be toasted
                            Toast.makeText(AdProfileLook.this, "User Account Deleted", Toast.LENGTH_SHORT).show();
                            // the intent will pass you to the next activity AdminUser
                            Intent iN = new Intent(AdProfileLook.this, AdminUser.class);
                            startActivity(iN);
                            finish();
                            // if the condition is false this message will be toasted
                        } else {
                            Toast.makeText(AdProfileLook.this, "User Account not Deleted", Toast.LENGTH_SHORT).show();
                        }
                    }
                })
                // when no is pressed in the alert box
                .setNegativeButton("No", null);
        builder.create();
        builder.show();

    }
}