/**
 * @author Student Ritesh Koirala
 * Assignment of second term of mobile app development-1
 * student ID: 21422051
 * this is the user adaptor for showing  all the user
 */
package com.myfirstapp.starmedia;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.BroadcastReceiver;
import android.content.ComponentName;
import android.content.ContentResolver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.IntentSender;
import android.content.ServiceConnection;
import android.content.SharedPreferences;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.content.res.AssetManager;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.database.DatabaseErrorHandler;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.icu.text.Transliterator;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.UserHandle;
import android.view.Display;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.RecyclerView;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
// recyclerview for viewing all the user in the useradmin page
public class UserAdapter extends RecyclerView.Adapter<UserAdapter.MyViewHolder> {
    // creating variable of the Context
    private Context context;
    //arraylist for taking the variable
    private ArrayList username_id;

    public UserAdapter(Context context, ArrayList username_id) {
        // assigning the above variable
        this.context = context;
        this.username_id = username_id;
    }
    @NonNull
    @Override
    // this is the method which connect the main body with the recycler view layout
    public UserAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.user_keeper,parent,false);
        return new MyViewHolder(v);
    }
    @Override
    // this is the method which gives the value and position for the value to occur in the activity
    public void onBindViewHolder(@NonNull MyViewHolder holder, @SuppressLint("RecyclerView") int position) {
        holder.username_id.setText(String.valueOf(username_id.get(position)));
        //on click the card view it will redirect according to this
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // passing the intent for going to the next page
                Intent i = new Intent(view.getContext(), AdProfileLook.class);
               // passing the extra so that the only detail of clicked user will be shown
                i.putExtra("name", String.valueOf(username_id.get(position)));
                context.startActivity(i);
            }
        });
    }
    @Override
    //return the size
    public int getItemCount() {
        return username_id.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        // identifying the variable to is is in the layout of the activity
        TextView username_id;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            // assigning value to the  textview for the user to see
            username_id = itemView.findViewById(R.id.username);
        }
    }
}
