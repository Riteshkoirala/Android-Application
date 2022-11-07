/**
 * @author Student Ritesh Koirala
 * Assignment of second term of mobile app development-1
 * student ID: 21422051
 *
 * this is the post craetion page where you can create post.
 */
package com.myfirstapp.starmedia;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

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

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;

public class PostCreation extends AppCompatActivity {
    //making the variable for the navigation button
    BottomNavigationView bottomNavigationView;
    // declaration of the variable which are further to use in the below code fragment.
    String email1;
    String Date;
    SimpleDateFormat simpleDateFormat;
    EditText addpost;
    Button button;
    DataKeeper DK;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_post_creation);
        // Assigning variable their place in the layout of this page
        email1 = getIntent().getStringExtra("email");
        addpost = findViewById(R.id.posting);
        button = findViewById(R.id.postbutton2);
        DK = new DataKeeper(this);

        // to get the value from the database
        SQLiteDatabase DB = DK.getReadableDatabase();
        // query for selecting the required thing in database
        Cursor C = DB.rawQuery("SELECT * FROM USERS WHERE email = ?", new String[]{email1} );
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
        EditText time = findViewById(R.id.date1);
        time.setText(Date);


        // navigation button uses
        bottomNavigationView = findViewById(R.id.button);
        bottomNavigationView.setSelectedItemId(R.id.addpost);

        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            // navigation button when they are clicked
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                if(item.getItemId()==R.id.home){
                    // if we click on the home
                    Intent i = new Intent(PostCreation.this,Home.class);
                    // passing extra variable for the further use
                    i.putExtra("email",String.valueOf(email1));
                    startActivity(i);
                    overridePendingTransition(0,0);
                    return  true;
                }
                else if(item.getItemId()==R.id.addpost){
                 // we are in addpost
                    return  true;
                }
                else if(item.getItemId()==R.id.timeline){
                    // if we click on the timeline
                    Intent i = new Intent(PostCreation.this,MyTimeline.class);
                    // passing extra variable for the further use
                    i.putExtra("email",String.valueOf(email1));
                    startActivity(i);
                    overridePendingTransition(0,0);
                    return  true;
                }
                else if(item.getItemId()==R.id.profile){
                    // if we click on the profile
                    Intent i = new Intent(PostCreation.this,ProfilePage.class);
                    // passing extra variable for the further use
                    i.putExtra("email",String.valueOf(email1));
                    startActivity(i);
                    overridePendingTransition(0,0);
                    return  true;
                }
                else{
                    return  false;
                }
            }
        });
       // this button will add the post to database while clicked
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // variable that takes the input that the user has given
                String getName = name.getText().toString();
                String getPost = addpost.getText().toString();
                String postdate = time.getText().toString();
                // the method which will work as a bridge for  input to go to the database
                Boolean addPost = DK.PostData(getName, getPost,postdate);
                if (addPost) {
                    // if the post will be successfully added it will popup this message
                    Toast.makeText(PostCreation.this, "Post added Success!", Toast.LENGTH_SHORT).show();
                    // and go directly to the home page
                    Intent i = new Intent(PostCreation.this,Home.class);
                    //even passes the data of email
                    i.putExtra("email",String.valueOf(email1));
                    startActivity(i);
                    finish();
                }else {
                    // if the post addition is failed this message will pop up.
                    Toast.makeText(PostCreation.this, "Post added failed!", Toast.LENGTH_SHORT).show();
                }


            }
        });


    }
}