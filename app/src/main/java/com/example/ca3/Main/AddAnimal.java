package com.example.ca3.Main;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.ca3.DAO.AnimalDAO;
import com.example.ca3.Entity.Animal;
import com.example.ca3.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;

public class AddAnimal extends AppCompatActivity {
    Button profileB, enterTask, updateB, deleteB, exploreB, homeB;

    EditText name, breed, age, lifestyle;
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_animal);

        homeB = findViewById(R.id.homeButton);
        enterTask = findViewById(R.id.EnterButton);
        updateB = findViewById(R.id.updateButton);
        deleteB = findViewById(R.id.deleteButton);
        exploreB = findViewById(R.id.exloreButton);

        name = findViewById(R.id.Name1);
        breed = findViewById(R.id.Breed);
        age = findViewById(R.id.Age);
        lifestyle = findViewById(R.id.lifestyle);

        homeB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Bundle bundle2 = new Bundle();
                Intent intent2 = new Intent(AddAnimal.this, DogImage.class);
                intent2.putExtras(bundle2);
                startActivity(intent2);
            }
        });

        exploreB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Bundle bundle3 = new Bundle();
                Intent intent3 = new Intent(AddAnimal.this, Explore.class);
                intent3.putExtras(bundle3);
                startActivity(intent3);
            }
        });

        enterTask.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Animal pet1 = new Animal(name.getText().toString(), breed.getText().toString(), age.getText().toString(), lifestyle.getText().toString());
                AnimalDAO aDao = new AnimalDAO(pet1);
                Toast.makeText(AddAnimal.this, "Entered Data", Toast.LENGTH_SHORT).show();

            }
        });


        updateB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String name1 = name.getText().toString();
                String breed1 = breed.getText().toString();
                String age1 = age.getText().toString();
                String lifestyle1 = lifestyle.getText().toString();
                updateData(name1, breed1, age1, lifestyle1);
            }
        });

        deleteB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String name1 = name.getText().toString();
                String breed1 = breed.getText().toString();
                if (!name1.isEmpty()) {
                    deleteData(name1, breed1);
                } else {
                    Toast.makeText(AddAnimal.this, "Category is empty", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private void deleteData(String name1, String breed1) {
        DatabaseReference dataRef = FirebaseDatabase.getInstance().getReference("Animal");
        dataRef.child(name1).removeValue().addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                if(task.isSuccessful()) {
                    Toast.makeText(AddAnimal.this, "Success", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(AddAnimal.this, "Failure", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private void updateData(String name1, String breed1, String age1, String lifestyle1) {
        HashMap AnimalH = new HashMap();
        AnimalH.put("name", name1);
        AnimalH.put("breed", breed1);
        AnimalH.put("age", age1);
        AnimalH.put("lifestyle", lifestyle1);

        DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference("Animal");
        databaseReference.child(name1).updateChildren(AnimalH).addOnCompleteListener(new OnCompleteListener() {
            @Override
            public void onComplete(@NonNull Task task) {
                if(task.isSuccessful()) {
                    Toast.makeText(AddAnimal.this, "Success", Toast.LENGTH_SHORT).show();
                }
            }
        });

    }
}
