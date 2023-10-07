package com.example.ca3.DAO;

import com.example.ca3.Entity.Animal;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class AnimalDAO {
    private DatabaseReference databaseReference;
    public AnimalDAO(Animal pet1) {
        FirebaseDatabase db = FirebaseDatabase.getInstance();
        databaseReference = db.getReference("Animal");
        databaseReference.child(pet1.getName()).setValue(pet1);
        databaseReference.push();
    }

}
