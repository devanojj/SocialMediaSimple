package com.example.ca3.Main;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.ca3.Adapter.newAdapter;
import com.example.ca3.Entity.Animal;
import com.example.ca3.R;
import com.example.ca3.ViewHolder.CategoryViewHolder;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class Explore extends AppCompatActivity {
    Button home, profile , explore, calTest;

    RecyclerView recyclerView;
    DatabaseReference dref;
    FirebaseRecyclerOptions<Animal> options;
    FirebaseRecyclerAdapter<Animal, CategoryViewHolder> adapter;
    EditText editText;
    ArrayList<Animal> arrayList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.explore_layout);

        //Buttons
        profile = findViewById(R.id.profileButton);
        home = findViewById(R.id.homeButton);
        //calTest = findViewById(R.id.calTest1);

        recyclerView = findViewById(R.id.recyclerview);//
        recyclerView.setHasFixedSize(true);
        arrayList = new ArrayList<>();

        // EditTexts
        editText = findViewById(R.id.inputVariable);

        profile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view1) {
                Bundle bundle2 = new Bundle();
                Intent intent2 = new Intent(Explore.this, AddAnimal.class);
                intent2.putExtras(bundle2);
                startActivity(intent2);
            }
        });

        home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view1) {
                Bundle bundle2 = new Bundle();
                Intent intent2 = new Intent(Explore.this, DogImage.class);
                intent2.putExtras(bundle2);
                startActivity(intent2);
            }
        });

        editText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {}
            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {}
            @Override
            public void afterTextChanged(Editable editable) {
                if(!editable.toString().isEmpty()) {
                    searching(editable.toString());
                }
                else {
                    searching("");
                }
            }
        });

        dref = FirebaseDatabase.getInstance().getReference().child("Animal");

        options = new FirebaseRecyclerOptions.Builder<Animal>()
                .setQuery(dref,Animal.class).build();
        adapter = new FirebaseRecyclerAdapter<Animal, CategoryViewHolder>(options) {
            @Override
            protected void onBindViewHolder(@NonNull CategoryViewHolder holder, int position, @NonNull Animal model) {
                holder.n.setText(model.getName());
                holder.b.setText(model.getBreed());
                holder.a.setText(model.getAge());
                holder.l.setText(model.getlifestyle());

            }

            @NonNull
            @Override
            public CategoryViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
                View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.animal_lookup,parent,false);
                return new CategoryViewHolder(view);

            }
        };

        GridLayoutManager gridLayoutManager = new GridLayoutManager(getApplicationContext(),2);
        recyclerView.setLayoutManager(gridLayoutManager);
        adapter.startListening();
        recyclerView.setAdapter(adapter);

    }

    private void searching(String editable) {
        Query query = dref.orderByChild("name")
                .startAt(editable).endAt(editable+"\uf8ff");

        Query query1 = dref.orderByChild("breed")
                .startAt(editable).endAt(editable+"\uf8ff");

        query.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if(snapshot.hasChildren()) {
                    arrayList.clear();
                    for(DataSnapshot sd: snapshot.getChildren()) {
                        final Animal tlist = sd.getValue(Animal.class);
                        arrayList.add(tlist);
                    }

                    newAdapter newAdapter = new newAdapter(getApplicationContext(), arrayList);
                    recyclerView.setAdapter(newAdapter);
                    newAdapter.notifyDataSetChanged();

                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        query1.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if(snapshot.hasChildren()) {
                    arrayList.clear();
                    for(DataSnapshot sd: snapshot.getChildren()) {
                        final Animal tlist = sd.getValue(Animal.class);
                        arrayList.add(tlist);
                    }

                    newAdapter newAdapter = new newAdapter(getApplicationContext(), arrayList);
                    recyclerView.setAdapter(newAdapter);
                    newAdapter.notifyDataSetChanged();

                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();
        if(adapter!=null)
            adapter.startListening();
    }

    @Override
    protected void onStop() {
        if(adapter!=null)
            adapter.startListening();
        super.onStop();
    }

    @Override
    protected void onResume() {
        super.onResume();
        if(adapter!=null)
            adapter.startListening();
    }


}
