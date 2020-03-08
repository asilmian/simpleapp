package com.example.simpleapp;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.google.firebase.auth.FirebaseAuth;

import androidx.appcompat.app.AppCompatActivity;

public class Home extends AppCompatActivity {

    private final static String TAG = Home.class.toString();

    private Button logoutB;
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        mAuth = FirebaseAuth.getInstance();


        //init buttons
        logoutB = findViewById(R.id.logOutButton);

        logoutB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                logout();
            }
        });
    }

    private void logout() {
        mAuth.signOut();
        try {
            Thread.sleep(1000);
        } catch (Exception e) {
            Log.e(TAG, "logout: ", e);
        }
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }
}
