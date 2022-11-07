/**
 * @author Student Ritesh Koirala
 * Assignment of second term of mobile app development-1
 * student ID: 21422051
 *
 * this is the data constuctor where you will store the given details
 */
package com.myfirstapp.starmedia;

import android.widget.TextView;

public class Data {

    //variable declared
    String name;
    String email;
    String date;
    String address;
    String gender;
    String dob;
    String password;
    String repassword;
    String uptodate;

// setting up the values
    public Data( String name, String email, String date, String address,
                 String gender, String dob, String password, String repassword, String uptodate){

        this.name = name;
        this.email = email;
        this.date = date;
        this.address = address;
        this.gender = gender;
        this.dob = dob;
        this.password = password;
        this.repassword = repassword;
        this.uptodate = uptodate;

    }
// an empty constructor
    public Data() {

    }
// making getter and setter method for all the variable declared above

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getDob() {
        return dob;
    }

    public void setDob(String dob) {
        this.dob = dob;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRepassword() {
        return repassword;
    }

    public void setRepassword(String repassword) {
        this.repassword = repassword;
    }

    public String getUptodate() {
        return uptodate;
    }

    public void setUptodate(String uptodate) {
        this.uptodate = uptodate;
    }
}
