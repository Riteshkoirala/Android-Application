/**
 * @author Student Ritesh Koirala
 * Assignment of second term of mobile app development-1
 * student ID: 21422051
 * this is the post adaptor for showing the post of all the user
 */
package com.myfirstapp.starmedia;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
// recyclerview for viewing all the post in the Homepage
public class PostAdapter extends RecyclerView.Adapter<PostAdapter.MyViewHolder> {
    // creating variable of the Context
    private Context context;
    //arraylist for taking the variable
    private ArrayList name_id, post_id, date_id;

    public PostAdapter(Context context, ArrayList name_id, ArrayList post_id, ArrayList date_id) {
        // assigning the above variable
        this.context = context;
        this.name_id = name_id;
        this.post_id = post_id;
        this.date_id = date_id;
    }
    @NonNull
    @Override
    // this is the method which connect the main body with the recycler view layout
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.post_keeper,parent,false);
        return new MyViewHolder(v);
    }
    @Override
    // this is the method which gives the value and position for the value to occur in the activity
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        holder.name_id.setText(String.valueOf(name_id.get(position)));
        holder.post_id.setText(String.valueOf(post_id.get(position)));
        holder.date_id.setText(String.valueOf(date_id.get(position)));
    }
    @Override
    //return the size
    public int getItemCount() {
        return name_id.size();
    }
    public class MyViewHolder extends RecyclerView.ViewHolder {
        // identifying the variable to is is in the layout of the activity
        TextView name_id, post_id, date_id;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            // assigning value to the  textview for the user to see
            name_id = itemView.findViewById(R.id.textname);
            post_id = itemView.findViewById(R.id.textemail);
            date_id = itemView.findViewById(R.id.textage);
        }
    }
}
