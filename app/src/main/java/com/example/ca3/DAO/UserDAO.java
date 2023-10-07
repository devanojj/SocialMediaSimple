package com.example.ca3.DAO;

import com.example.ca3.Entity.User;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class UserDAO {
    private DatabaseReference databaseReference;

    public UserDAO(User person1) {

        FirebaseDatabase db = FirebaseDatabase.getInstance();
        databaseReference = db.getReference("User");
        databaseReference.child(person1.getName()).setValue(person1);
        // Inside getReference User.class.getSimpleName()
        databaseReference.push();

    }

    /*
    public Task<Void> add(User person1) {


        return databaseReference.push().child(person1.getEmail()).setValue(person1);
    }*/
}
