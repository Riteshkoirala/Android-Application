/**
 * @author Student Ritesh Koirala
 * Assignment of second term of mobile app development-1
 * student ID: 21422051
 *
 * this is the main activity which displays the splash
 * page at the beggining of the app for 1 second.
 */

package com.myfirstapp.starmedia;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {
    //time period for how much time the splashpage will be kept.
    private static int SPLASH_TIME_OUT = 1000;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                //this is the intent which will make us pass to the next activity.
                Intent o = new Intent(MainActivity.this, LoginPage.class);
                //declaration for going to the next page
                startActivity(o);
                finish();
            }
        }, SPLASH_TIME_OUT);
    }
}