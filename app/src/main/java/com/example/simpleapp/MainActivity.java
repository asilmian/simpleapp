package com.example.simpleapp;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    EditText email;
    EditText password;
    Button loginB;
    Button signUpB;

    private final static String TAG = MainActivity.class.toString();

    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mAuth = FirebaseAuth.getInstance();

        //init buttons and editTexts
        email = findViewById(R.id.emailText);
        password = findViewById(R.id.passText);
        loginB = findViewById(R.id.loginButton);
        signUpB = findViewById(R.id.signUpButton);

        loginB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                login(v);
            }
        });

        signUpB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                signUp(v);
            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();
        FirebaseUser currentUser = mAuth.getCurrentUser();
        if (currentUser != null) {
            showLoginScreen();
        }
    }

    private void login(View v) {
        String emailS = email.getText().toString();
        String passwordS = password.getText().toString();

        mAuth.signInWithEmailAndPassword(emailS, passwordS)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            Log.d(MainActivity.class.toString(), "Sign in with Email: Success");
                            showLoginScreen();
                        } else {
                            Log.w(TAG, "instance initializer: ", task.getException());
                            Toast.makeText(MainActivity.this, "Authentication Failed.", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }

    private void showLoginScreen() {
        Intent intent = new Intent(this, Home.class);
        startActivity(intent);
    }

    private void signUp(View v) {
        Intent intent = new Intent(this, SignUp.class);
        startActivity(intent);
    }


}
