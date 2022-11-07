/**
 * @author Student Ritesh Koirala
 * Assignment of second term of mobile app development-1
 * student ID: 21422051
 *
 * this is the Home page of the app where you can see every post the user have posted.
 */
package com.myfirstapp.starmedia;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.ArrayList;

public class Home extends AppCompatActivity {
    //making the variable for the navigation button
    BottomNavigationView bottomNavigationView;
    // declaring the variable for the RecycleView
    RecyclerView recyclerView;
    // arraylist
    ArrayList<String> name, post, date;
    // DataKeeper
    DataKeeper DK;
    // postAdapter
    PostAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        // new object for the DataKeeper class
        DK = new DataKeeper(this);
        // new object for the name variable
        name = new ArrayList<>();
        // new object for the post variable
        post = new ArrayList<>();
        // new object for the date variable
        date = new ArrayList<>();

        // assigning the variable in the id of the layout
        recyclerView = findViewById(R.id.recyclerview);
        // creating new object of postAdapter passing the three variable
        adapter = new PostAdapter(this, name, post, date);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        // running the void method created
        displaydata();
    }
    // method which has been called in the main section
    private void displaydata()
    {
        // cursor for checking the data if its present or not in the database
        Cursor cursor = DK.getdata();
        if(cursor.getCount()==0)
        {
            // toast these message if there is no data in the database
            Toast.makeText(Home.this, "No Entry Exists", Toast.LENGTH_SHORT).show();
            return;
        }
        // if the data in database remains then display these
        else
        {
            while(cursor.moveToNext())
            {
                // getting the column index from the database
                name.add(cursor.getString(1));
                post.add(cursor.getString(2));
                date.add(cursor.getString(3));
            }
       }
        // getting the variable from the previous page to this page for the further use
         String email1 = getIntent().getStringExtra("email");
        // navigation button uses
        bottomNavigationView = findViewById(R.id.button);
        bottomNavigationView.setSelectedItemId(R.id.home);

        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            // navigation button when they are clicked
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                // its in the home page
                if(item.getItemId()==R.id.home){
                    return  true;
                }
                else if(item.getItemId()==R.id.addpost){
                    // if we click on the create post
                    Intent i = new Intent(Home.this,PostCreation.class);
                    // passing extra variable for the further use
                    i.putExtra("email",String.valueOf(email1));

                    startActivity(i);
                    overridePendingTransition(0,0);
                    return  true;
                }
                else if(item.getItemId()==R.id.timeline){
                    // if we click on the create post
                    Intent i = new Intent(Home.this,MyTimeline.class);
                    // passing extra variable for the further use
                    i.putExtra("email",String.valueOf(email1));
                    startActivity(i);
                    overridePendingTransition(0,0);
                    return  true;
                }
                else if(item.getItemId()==R.id.profile){
                    // if we click on the create post
                    Intent i = new Intent(Home.this,ProfilePage.class);
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
    }
    @Override
    public void onBackPressed() {
        //there will be an alert box when you press the back button on the mobile.
        new AlertDialog.Builder(this)
                // message displayed on the alert box.
                .setMessage("Are you sure, you want to LOGOUT?")
                .setCancelable(false)
                // this happens when you click the yes button
                .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        // going back to the login page
                        Intent in = new Intent(Home.this,LoginPage.class);

                        startActivity(in);
                        finish();
                    }
                })
                // nothing happens when you click the no button on the alert box
                .setNegativeButton("No", null)

                .show();
    }
}