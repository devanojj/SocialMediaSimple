package com.example.ca3.Main;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.ca3.DAO.UserDAO;

import com.example.ca3.Entity.User;
import com.example.ca3.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.util.ArrayList;

// Sign Up activity
public class Register extends AppCompatActivity {
    Button submit, goBack;
    EditText inputName, password, email, phoneNum;
    FirebaseAuth mAuth = FirebaseAuth.getInstance();
    FirebaseUser mUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        // Buttons
        submit = findViewById(R.id.button);
        goBack = findViewById(R.id.backButton);

        // Edit Text
        inputName = findViewById(R.id.NameEdit);
        password = findViewById(R.id.passwordEdit);
        email = findViewById(R.id.emailEdit);
        phoneNum = findViewById(R.id.phoneNumEdit);

        goBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(Register.this, "Login Page", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(getApplicationContext(), SignIn.class);
                startActivity(intent);
            }
        });

        submit.setOnClickListener(view -> {
            User person2 = new User(inputName.getText().toString(), password.getText().toString(), email.getText().toString(), phoneNum.getText().toString());

            String email1 = email.getText().toString();
            String password1 = password.getText().toString();
            registerUser(email1, password1);

            // DAO mapping
            UserDAO uDao = new UserDAO(person2);


            Bundle bundle = new Bundle();
            bundle.putString("email", email1);
            bundle.putString("password", password1);
            String uname = inputName.getText().toString();
            bundle.putString("username", uname);
            String pnum = phoneNum.getText().toString();
            bundle.putString("phoneNumber", pnum);

            Intent intent1 = new Intent(Register.this, SignIn.class);
            intent1.putExtras(bundle);

        });
    }

    private void registerUser(String email1, String password1) { {
        mAuth.createUserWithEmailAndPassword(email1, password1)
                .addOnCompleteListener(Register.this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            Log.d("myActivity", "createUserWithEmail:success");
                            mUser = mAuth.getCurrentUser();

                            Intent intent = new Intent(getApplicationContext(), SignIn.class);
                            startActivity(intent);
                        } else {

                        }
                    }
                });
    }
    }
}