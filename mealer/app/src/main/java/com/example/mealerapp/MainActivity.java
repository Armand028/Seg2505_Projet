package com.example.mealerapp;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private  TextView signUp,logOut;
    private EditText editTextemail, editTextPassword;
    @SuppressLint({"MissingInflatedId", "WrongViewCast"})
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        signUp = (Button) findViewById(R.id.signUpBttn1);
        signUp.setOnClickListener(this);
        logOut=(Button) findViewById(R.id.loginButton);
        logOut.setOnClickListener(this);
        editTextemail = (EditText) findViewById(R.id.SignInEmail);
        editTextPassword = (EditText) findViewById(R.id.SignInPassword);
    }
    public void onClick(View view){
        String MotDePasse = editTextPassword.getText().toString().trim();
        String Adresse = editTextemail.getText().toString().trim();
        if(view.getId()==R.id.signUpBttn1){
            startActivity(new Intent(this, SignupasActivity.class));
        }
        if(view.getId()==R.id.loginButton){
            if(MotDePasse.equals("Administrator23") && Adresse.equals("administrator23@gmail.com") ){
            startActivity(new Intent(this, WelcomeAdminActivity.class));
            }
        }
    }

    @Override
    public void onPointerCaptureChanged(boolean hasCapture) {
        super.onPointerCaptureChanged(hasCapture);
    }
}