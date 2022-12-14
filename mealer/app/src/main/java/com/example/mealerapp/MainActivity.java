package com.example.mealerapp;

import static android.util.Patterns.EMAIL_ADDRESS;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private  TextView signUp,logOut;
    private EditText editTextAdresseCourriel, editTextPassword;
    private FirebaseAuth mAuth;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        signUp = findViewById(R.id.signUpBttn1);
        signUp.setOnClickListener(this);
        logOut= findViewById(R.id.loginButton);
        logOut.setOnClickListener(this);
        editTextAdresseCourriel = findViewById(R.id.username);
        editTextPassword = (EditText) findViewById(R.id.SignInPassword);
        mAuth = FirebaseAuth.getInstance();
    }



    public void onClick(View view){
        if(view.getId()==R.id.signUpBttn1){
            startActivity(new Intent(this, SignupasActivity.class));
        }


       // Login administrator
        if(view.getId()==R.id.loginButton){
            UserLogin();
        }
    }

    private void UserLogin() {
        //Getting information and trimming it to a string
        String MotDePasse = editTextPassword.getText().toString().trim();
        String adressecourriel= editTextAdresseCourriel.getText().toString().trim();
//Getting information from editTextes  MotDePasse and Email
        if (MotDePasse.isEmpty()) {
            editTextPassword.setError(" Mot de passse est requis");
           editTextPassword.requestFocus();
            return;
        }
        if (adressecourriel.isEmpty()) {
            editTextAdresseCourriel.setError(" Adresse couuriel est requis");
            editTextAdresseCourriel.requestFocus();
            return;
        }
        if (!EMAIL_ADDRESS.matcher(adressecourriel).matches()) {
            editTextAdresseCourriel.setError(" Adresse couuriel non valide");
            editTextAdresseCourriel.requestFocus();
            return;
        }
        if (MotDePasse.length() < 4) {
            editTextPassword.setError(" Mot de passse a une longeur de 8 characteres");
            editTextPassword.requestFocus();
            return;
        }
        if(MotDePasse.equals("Administrator23") && adressecourriel.equals("administrator23@gmail.com") ){
            startActivity(new Intent(this, WelcomeAdminActivity.class));
        }
     //signing In
        mAuth.signInWithEmailAndPassword(adressecourriel,MotDePasse).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful()){
                    //getting user userType and use it to sign him in
//                    String user =task.getResult().getUser().getUid();
//                    FirebaseDatabase.getInstance().getReference().child("Clients").child(user).child("userType").addListenerForSingleValueEvent(new ValueEventListener() {
//                        @Override
//                        public void onDataChange(@NonNull DataSnapshot snapshot) {
//                            String userType=snapshot.getValue(String.class);
//
//                            if(userType.equals("Client")){
//                                startActivity(new Intent(MainActivity.this, WelcomeClientActivity.class));
//                            }
//                            else if(userType.equals("Cooker")){
//                                startActivity(new Intent(MainActivity.this, WelcomeCookActivity.class));
//                            }
//                        }
//                        @Override
//                        public void onCancelled(@NonNull DatabaseError error) {
//
//                        }
//                    });
                    //diriger vers profil utilisateur
                   startActivity(new Intent(MainActivity.this, WelcomeClientActivity.class));
                }else{
                    Toast.makeText(MainActivity.this, "Failed to login check your credentials", Toast.LENGTH_LONG).show();
                }

            }
        }
        );

    }

}