/**
 * @author Student Ritesh Koirala
 * Assignment of second term of mobile app development-1
 * student ID: 21422051
 *
 * this is the UpdatePost where we will update the post that the loggedin user has created
 */
package com.myfirstapp.starmedia;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

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

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class UpdatePost extends AppCompatActivity {
    // declaration of the variable which are further to use in the below code fragment.
    String username;
    String Date;
    SimpleDateFormat simpleDateFormat;
    EditText addpost;
    Button button, button1;
    DataKeeper DK;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_post);
        // Assigning variable their place in the layout of this page
        username = getIntent().getStringExtra("name");
        addpost = findViewById(R.id.posting1);
        button = findViewById(R.id.postbutton2);
        button1 = findViewById(R.id.delbtn);

        DK = new DataKeeper(this);

        // to get the value from the database
        SQLiteDatabase DB = DK.getReadableDatabase();
        // query for selecting the required thing in database
        Cursor C = DB.rawQuery("SELECT * FROM USERS WHERE name = ?", new String[]{username} );
        if(C != null){
            C.moveToFirst();
        }
        //String builder for taking the value from above described table
        StringBuilder b = new StringBuilder();
        do{
            // selecting the column where your desired value is kept
            String name = C.getString(1);
            //getting the name
            b.append(name);
        }while (C.moveToNext());
        // keeping the name that we got from the above database
        TextView name = (TextView) findViewById(R.id.name1);
        // setting the value
        name.setText(b.toString());
        // this is for the date and time when the post will be posted
        final Calendar calenda = Calendar.getInstance();
        simpleDateFormat=new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
        Date=simpleDateFormat.format(calenda.getTime());
        EditText time = findViewById(R.id.date12);
        time.setText(Date);
        // this button will update the post to database while clicked
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // variable that takes the input that the user has given
                String getName = name.getText().toString();
                String getPost = addpost.getText().toString();
                String postdate = time.getText().toString();
                // the method which will work as a bridge for  input to go to the database
                Boolean addPost = DK.PostupdateData(getName, getPost,postdate);
                if (addPost) {
                    // if the post will be successfully updated it will popup this message
                    Toast.makeText(UpdatePost.this, "Post updated Success!", Toast.LENGTH_SHORT).show();
                    // and go directly to the home page
                    Intent i = new Intent(UpdatePost.this,Home.class);
                    startActivity(i);
                    finish();
                }else {
                    // if the post update is failed this message will pop up.
                    Toast.makeText(UpdatePost.this, "Post update failed!", Toast.LENGTH_SHORT).show();
                }
            }
        });
        //button for deleting the account
        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String getName = name.getText().toString();
                // method being called
                dialog(getName);
            }
        });
    }
    // method that has been called when the delete button will be pressed
    public void dialog(String name){
        // creating new instance of the database
        DataKeeper DK = new DataKeeper(this);
        // the box that will be displayed when delete button will be clicked
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        //title of the alert box
        builder.setTitle("Delete post")
                // message to be shown in the alert box
                .setMessage("Are You Sure, you want to delete this post")
                // declare what happens if the yes is pressed in the alert box
                .setPositiveButton("yes,sure", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        // the condition to be checked
                        Boolean B = DK.deleteuserpostdata(username);

                        if(B==true){
                           // if the condition is true the user post will be deleated
                            Toast.makeText(UpdatePost.this, "User post Deleted", Toast.LENGTH_SHORT).show();
                            // starting of the new activity
                            Intent iN = new Intent(UpdatePost.this,Home.class);
                            startActivity(iN);
                            finish();
                        }
                        // if condition is not true the user post will not be completed
                        else{
                            Toast.makeText(UpdatePost.this, "User post not Deleted", Toast.LENGTH_SHORT).show();
                        }
                    }
                })
                .setNegativeButton("No",null);

        builder.create();
        builder.show();

    }

}