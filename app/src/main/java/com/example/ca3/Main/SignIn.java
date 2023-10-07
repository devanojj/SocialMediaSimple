package com.example.ca3.Main;

import android.app.Application;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.ca3.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.FirebaseApp;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.core.Context;

public class SignIn extends AppCompatActivity {
    EditText email1;
    EditText password1;
    Button SignIn, SignUp;
    FirebaseAuth mAuth = FirebaseAuth.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sign_in);
        email1 = findViewById(R.id.username);
        password1 = findViewById(R.id.password);
        SignIn = findViewById(R.id.SignIn);
        SignUp = findViewById(R.id.SignUp);
        mAuth = FirebaseAuth.getInstance();
        FirebaseDatabase database = FirebaseDatabase.getInstance();

        SignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Bundle bundle1 = new Bundle();
                Intent intent1 = new Intent(SignIn.this, Register.class);
                intent1.putExtras(bundle1);
                startActivity(intent1);
                Toast.makeText(SignIn.this, "Register Page", Toast.LENGTH_SHORT).show();
            }
        });


        SignIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick (View view){
                String email = email1.getText().toString();
                String password = password1.getText().toString();
                loginUser(email, password);
            }
        });

    }

    private void loginUser(String email, String password) {{
        mAuth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {

                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            Toast.makeText(SignIn.this, "User signed in", Toast.LENGTH_SHORT).show();

                            Bundle bundle = new Bundle();
                            bundle.putString("email", email);
                            bundle.putString("password", password);

                            Intent intent = new Intent(SignIn.this, DogImage.class);
                            intent.putExtras(bundle);
                            startActivity(intent);
                        } else {
                            Log.w("MySignIn", "SignInUserWithEmail:failure", task.getException());
                            Toast.makeText(SignIn.this, "Failed", Toast.LENGTH_SHORT).show();
                        }
                    }
                });


    }
    }

}
