/**
 * @author Student Ritesh Koirala
 * Assignment of second term of mobile app development-1
 * student ID: 21422051
 * this is the mypost for showing the post of loggedin user
 */
package com.myfirstapp.starmedia;


import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
// recyclerview for viewing all the post in the Homepage
    public class MyPost extends RecyclerView.Adapter<com.myfirstapp.starmedia.MyPost.MyViewHolder> {
    // creating variable of the Context
    private Context context;
    //arraylist for taking the variable
    private ArrayList name_id, post_id, date_id;

        public MyPost(Context context, ArrayList name_id, ArrayList post_id, ArrayList date_id) {
            // assigning the above variable
            this.context = context;
            this.name_id = name_id;
            this.post_id = post_id;
            this.date_id = date_id;
        }

        @NonNull
        @Override
        // this is the method which connect the main body with the recycler view layout
        public com.myfirstapp.starmedia.MyPost.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            View v = LayoutInflater.from(context).inflate(R.layout.mypost,parent,false);
            return new com.myfirstapp.starmedia.MyPost.MyViewHolder(v);
        }

        @Override
        // this is the method which gives the value and position for the value to occur in the activity
        public void onBindViewHolder(@NonNull com.myfirstapp.starmedia.MyPost.MyViewHolder holder, @SuppressLint("RecyclerView") int position) {
            holder.name_id.setText(String.valueOf(name_id.get(position)));
            holder.post_id.setText(String.valueOf(post_id.get(position)));
            holder.date_id.setText(String.valueOf(date_id.get(position)));
            //on click the card view it will redirect according to this
            holder.up.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    // passing the intent for going to the next page
                    Intent i = new Intent(context,UpdatePost.class);
                    // passing the extra so that the only detail of clicked user will be shown
                    i.putExtra("name", String.valueOf(name_id.get(position)));
                    context.startActivity(i);
                }
            });


        }

        @Override
        //return the size
        public int getItemCount() {
            return name_id.size();
        }

        public class MyViewHolder extends RecyclerView.ViewHolder {
            // identifying the variable to is is in the layout of the activity
            TextView name_id, post_id, date_id;
            Button up;
            public MyViewHolder(@NonNull View itemView) {
                super(itemView);
                // assigning value to the  textview for the user to see
                name_id = itemView.findViewById(R.id.textname);
                post_id = itemView.findViewById(R.id.textemail);
                date_id = itemView.findViewById(R.id.textage);
                up = (Button) itemView.findViewById(R.id.btnnup);
            }
        }
    }


