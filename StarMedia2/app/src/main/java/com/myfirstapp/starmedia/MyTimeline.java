/**
 * @author Student Ritesh Koirala
 * Assignment of second term of mobile app development-1
 * student ID: 21422051
 *
 * this is the My timeline page where the post which only
 * logged in has posted will change.
 */
package com.myfirstapp.starmedia;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.ArrayList;

public class MyTimeline extends AppCompatActivity {
    //making the variable for the navigation button
    BottomNavigationView bottomNavigationView;
    // declaring the variable for the RecycleView
    RecyclerView recyclerView;
    // arraylist
    ArrayList<String> name, post, date;
    // DataKeeper
    DataKeeper DK;
    // postAdapter
    MyPost adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_timeline);
        // getting the variable from the previous page to this page for the further use
        String email1 = getIntent().getStringExtra("email");
        // new object for the DataKeeper class
        DataKeeper DK = new DataKeeper(this);
        SQLiteDatabase DB = DK.getReadableDatabase();
        // cursor for checking the data if its present or not in the database
        Cursor C = DB.rawQuery("SELECT * FROM USERS WHERE email = ?", new String[]{email1} );
        if(C != null){
            C.moveToFirst();
        }
        // string builder will be used to bring the data from database
        StringBuilder b = new StringBuilder();

        // setting up the string and getting the value of column on the string variable
        do{
            String name1 = C.getString(1);
            // taking up the value from the table
            b.append(name1);

        }while (C.moveToNext());
        // setting of the value of the variable in the textname id in the layout.
        TextView name1 = (TextView) findViewById(R.id.nam);
        name1.setText(b.toString());

        String username = name1.getText().toString();
        // new object for the name variable
        name = new ArrayList<>();
        // new object for the post variable
        post = new ArrayList<>();
        // new object for the date variable
        date = new ArrayList<>();
        // assigning the variable in the id of the layout
        recyclerView = findViewById(R.id.recyclerview);
        // creating new object of postAdapter passing the three variable
        adapter = new MyPost(this, name, post, date);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        // running the void method created
        displaydata(username);
    }

    private void displaydata(String name2)
    {
        DK = new DataKeeper(this);
        // cursor for checking the data if its present or not in the database
        Cursor cursor = DK.getpostdataover(name2);
        if(cursor.getCount()==0)
        {
            // toast these message if there is no data in the database
            Toast.makeText(MyTimeline.this, "No Entry Exists", Toast.LENGTH_SHORT).show();
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
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                //this will take us to home page
                  if(item.getItemId()==R.id.home){
                    Intent i = new Intent(MyTimeline.this,Home.class);
                    i.putExtra("email",String.valueOf(email1));
                    startActivity(i);
                    overridePendingTransition(0,0);
                    return  true;
                }
                  // this will take us to the post creation page
                else if(item.getItemId()==R.id.addpost){
                    Intent i = new Intent(MyTimeline.this,PostCreation.class);
                    i.putExtra("email",String.valueOf(email1));

                    startActivity(i);
                    overridePendingTransition(0,0);
                    return  true;
                }
                 // this is when we are at the timeline
                else if(item.getItemId()==R.id.timeline){
                    return  true;
                }
                // this will take us to the profile page
                else if(item.getItemId()==R.id.profile){
                    Intent i = new Intent(MyTimeline.this,ProfilePage.class);
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
}