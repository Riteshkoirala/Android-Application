/**
 * @author Student Ritesh Koirala
 * Assignment of second term of mobile app development-1
 * student ID: 21422051
 *
 * this is the DataKeeper page where all the activities of the database is carried out
 */
package com.myfirstapp.starmedia;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import androidx.annotation.NonNull;
 // extending it up to the Sqliteopenhelper to use that
public class DataKeeper extends SQLiteOpenHelper {
// creating the database StarMediaUserAll
    public DataKeeper(Context context) {
        super(context, "StarMediaUserAll.db", null, 1);
    }

    @Override
    // Creating the table that are to be used in this assignment
    public void onCreate(SQLiteDatabase DK) {
        // creating table Users
        DK.execSQL("create Table Users( _ID INTEGER PRIMARY KEY AUTOINCREMENT, name TEXT ,email VARCHAR," +
                "date DATE, address VARCHAR," +
                "gender VARCHAR, dob DATE, password STRING, repassword STRING, uptodate DATE)");
         // creating table userPost
        DK.execSQL("create Table UserPost(_ID INTEGER PRIMARY KEY AUTOINCREMENT, name Text,post TEXT,date DATE)");
    }
    @Override
    // checking if the tables of that name above created already present in the database or not
    public void onUpgrade(SQLiteDatabase DK, int i, int i1) {
        // if yes it will delete that table and create new one
        DK.execSQL("drop Table if exists Users ");
        DK.execSQL("drop Table if exists UserPost");
        onCreate(DK);
    }
// this section of code is for taking the input from the user detail
     // and store that in the database maintaining the column
    public Boolean buttonuserdata( Data data) {
        // this line is for looking whether we are taking writable or readable data
        // this helps with the database insertion when this method will be called
        SQLiteDatabase DK = this.getWritableDatabase();
        //making containvalue to store the data in it
        ContentValues contentValues = new ContentValues();
        contentValues.put("name", data.getName());
        contentValues.put("email", data.getEmail());
        contentValues.put("date", data.getDate());
        contentValues.put("address", data.getAddress());
        contentValues.put("gender", data.getGender());
        contentValues.put("dob", data.getDob());
        contentValues.put("password", data.getPassword());
        contentValues.put("repassword", data.getRepassword());
        contentValues.put("uptodate", data.getUptodate());
        // this check whether the table in the database has been created or not
        long result = DK.insert("Users", null, contentValues);
        if (result == -1) {
            return false;
        } else {
            return true;
        }
    }
    // this section of code is for the insertion of the post that user post
    public Boolean PostData(String name,String post,String date) {
        // this line is for looking whether we are taking writable or readable data
        // this helps with the database insertion when this method will be called
        SQLiteDatabase DK = this.getWritableDatabase();
        //making containvalue to store the data in it
        ContentValues c = new ContentValues();
        c.put("name", name);
        c.put("post", post );
        c.put("date", date);
        // this check whether the table in the database has been created or not
        long r = DK.insert("UserPost",null,c);
        if (r == -1) {
            return false;
        }
        else {
            return true;
        }
    }
   // this method is for the update of the post
    public Boolean PostupdateData(String name,String post,String date) {
        // this line is for looking whether we are taking writable or readable data
        // taking the value from the place where this method has been called
        SQLiteDatabase DK = this.getWritableDatabase();
        //making containvalue to store the data in it
        ContentValues c = new ContentValues();
        c.put("name", name);
        c.put("post", post );
        c.put("date", date);
        // for the selection of the column that i wanted to post
        Cursor cursor = DK.rawQuery("Select * from UserPost where name = ?", new String[]{name});
        // it will check if the table contain such information or not
        if (cursor.getCount() > 0) {
            // now this will search for the update of the post having following query
            long userTable = DK.update("UserPost", c, "name = ?", new String[]{name});
            if (userTable == -1) {
                return false;
            } else {
                return true;
            }
        } else {
            return false;
        }
    }
//  this section of code is for the deletion of the post
    public Boolean deleteuserpostdata(String name) {
// this line is for looking whether we are taking writable or readable data
        // for the selection of the column that i wanted to delete the post
        SQLiteDatabase DK = this.getWritableDatabase();
        Cursor cursor = DK.rawQuery("Select * from userpost where name = ?", new String[]{name});

        if (cursor.getCount() > 0) {
            // its check for the delete in the table having the below query
            long userTable = DK.delete("UserPost", "name = ?", new String[]{name});
            if (userTable == -1) {
                return false;
            } else {
                return true;
            }
        } else {
            return false;
        }
    }
//  this section of code is for updating the detail of the user
    public boolean updateuserdata( Data data) {
        // this line is for looking whether we are taking writable or readable data
        // this helps with the database insertion when this method will be called
        SQLiteDatabase DK = this.getWritableDatabase();
        //making containvalue to store the data in it
        ContentValues contentValues = new ContentValues();
        contentValues.put("email", data.getEmail());
        contentValues.put("date", data.getDate());
        contentValues.put("address", data.getAddress());
        contentValues.put("gender", data.getGender());
        contentValues.put("dob", data.getDob());
        contentValues.put("password", data.getPassword());
        contentValues.put("repassword", data.getRepassword());
        contentValues.put("uptodate", data.getUptodate());

        // for the selection of the column that i wanted to post
        Cursor cursor = DK.rawQuery("Select * from users where name = ?", new String[]{data.getName()});
        if (cursor.getCount() > 0) {
            // its check for the update in the table having the below query
            long userTable = DK.update("users", contentValues, "name = ?", new String[]{data.getName()});
            if (userTable == -1) {
                return false;
            } else {

                return true;
            }
        } else {
            return false;
        }
    }

   // this section is used for deleting the user data by himself
    public Boolean deleteuserdata(String email) {
        // this line is for looking whether we are taking writable or readable data
        SQLiteDatabase DK = this.getWritableDatabase();
        // it will check if the table contain such information or not
        Cursor cursor = DK.rawQuery("Select * from users where email = ?", new String[]{email});

        if (cursor.getCount() > 0) {
            // its check for the delete in the table having the below query
            long userTable = DK.delete("users", "email = ?", new String[]{email});
            if (userTable == -1) {
                return false;
            } else {
                return true;
            }
        } else {
            return false;
        }
    }
     // this section is used for deleting the user data by the admin
     public Boolean admindeletedata(String name) {
// this line is for looking whether we are taking writable or readable data
        SQLiteDatabase DK = this.getWritableDatabase();
        // it will check if the table contain such information or not
        Cursor cursor = DK.rawQuery("Select * from users where name = ?", new String[]{name});

        if (cursor.getCount() > 0) {
            // its check for the delete in the table having the below query
            long userTable = DK.delete("users", "name = ?", new String[]{name});
            if (userTable == -1) {
                return false;
            } else {
                return true;
            }
        } else {
            return false;
        }
    }
     // this section is used for deleting the user post data by the admin
     public Boolean admindeletepost(String name) {
// this line is for looking whether we are taking writable or readable data
        SQLiteDatabase DK = this.getWritableDatabase();
        // it will check if the table contain such information or not
        Cursor cursor = DK.rawQuery("Select * from UserPost where name = ?", new String[]{name});

        if (cursor.getCount() > 0) {
            // its check for the delete in the table having the below query
            long userTable = DK.delete("UserPost", "name = ?", new String[]{name});
            if (userTable == -1) {
                return false;
            } else {
                return true;
            }
        } else {
            return false;
        }
    }
     // this section is for checking whether the username the user has
     // used while login is correct or not
    public boolean checkuseremail (String email){
     // this section says its writable database
        SQLiteDatabase DK = this.getWritableDatabase();
      // checking the above email in the table using the following query
        Cursor cursor = DK.rawQuery("Select * from users where email = ?", new String[]{email});
         // if the detail is present it returns true
        if (cursor.getCount() > 0) {
            return true;
        } else {
            return false;
        }
    }
     // this section is for checking whether the username and password the user has
     // used while login is correct or not
    public boolean checkuseremailpassword (String email, String password){
        // this section says its writable database
        SQLiteDatabase DK = this.getWritableDatabase();
        // checking the above email, password in the table using the following query
        Cursor cursor = DK.rawQuery("Select * from users where email = ? and password = ?",
                new String[]{email, password});
        // if the detail is present it returns true
        if (cursor.getCount() > 0) {
            return true;
        } else {
            return false;
        }
    }
    // its for getting the every data from the userpost table
    public Cursor getdata()
    {
        // this line is for looking whether we are taking writable or readable data
        SQLiteDatabase DB = this.getWritableDatabase();
        // it will check if the table contain such information or not
        Cursor cursor  = DB.rawQuery("Select * from UserPost ", null);
        return cursor;
    }
    // its for getting the data from the table whose name = ?.
    public Cursor getpostdataover(String name)
    {
        // this line is for looking whether we are taking writable or readable data
        SQLiteDatabase DB = this.getWritableDatabase();
        // it will check if the table contain such information or not
        Cursor cursor  = DB.rawQuery("Select * from UserPost where name = ? ", new String[]{name});
        return cursor;
    }
    // its from getting all the data from user table
    public Cursor getuserdata()
    {
        // this line is for looking whether we are taking writable or readable data
        SQLiteDatabase DB = this.getWritableDatabase();
        // it will check if the table contain such information or not
        Cursor cursor  = DB.rawQuery("Select * from Users", null);
        return cursor;
    }

}
