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
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;

public class CookersignupPageActivity extends AppCompatActivity implements View.OnClickListener{
    private Button SignUp;
    private EditText editTextPrenom, editTextNom, editTextAdresseCourriel, editTextMotDePasse, editTextAdresse, editTextConfirm;
    private FirebaseAuth mAuth;
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.cookersignup_page);
        mAuth = FirebaseAuth.getInstance();
        SignUp = findViewById(R.id.signUpBttn1);
        SignUp.setOnClickListener(this);

        editTextPrenom =  findViewById(R.id.Prenom1);
        editTextNom =  findViewById(R.id.Nom1);
        editTextAdresseCourriel =  findViewById(R.id.AdresseCourriel1);
        editTextMotDePasse =  findViewById(R.id.SignInPassword);
        editTextConfirm =  findViewById(R.id.confirm_password1);
        editTextAdresse =  findViewById(R.id.address);

    }



    private void resisterUser() {

        String Prenom = editTextPrenom.getText().toString().trim();
        String Nom = editTextNom.getText().toString().trim();
        String adressecourriel = editTextAdresseCourriel.getText().toString().trim();
        String MotDePasse = editTextMotDePasse.getText().toString().trim();
        String Adresse = editTextAdresse.getText().toString().trim();
        String MotDePasseConfirm = editTextConfirm.getText().toString().trim();

        if (Prenom.isEmpty()) {
            editTextPrenom.setError("Prenom est requis");
            editTextPrenom.requestFocus();
            return;
        }
        if (Nom.isEmpty()) {
            editTextNom.setError(" Nom est requis");
            editTextNom.requestFocus();
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
        if (MotDePasse.isEmpty()) {
            editTextMotDePasse.setError(" Mot de passse est requis");
            editTextMotDePasse.requestFocus();
            return;
        }

        if (MotDePasse.length() < 8) {
            editTextMotDePasse.setError(" Mot de passse a une longeur de 8 characteres");
            editTextMotDePasse.requestFocus();
            return;
        }

        if (MotDePasseConfirm.isEmpty()) {
            editTextConfirm.setError(" Confirmer votre mot de passe");
            editTextConfirm.requestFocus();

            return;
        }else {
            if (!MotDePasse.equals(MotDePasseConfirm)) {
                editTextConfirm.setError(" Champs ne matche pas Mot de passe");
                editTextConfirm.requestFocus();
                return;
            }
        }
        if (Adresse.isEmpty()) {
            editTextAdresse.setError(" Une adresse est requis");
            editTextAdresse.requestFocus();
            return;
        }


//        if (InformationsCarteCredit.isEmpty()) {
//            editTextInformationsCarteCredit.setError(" InformationsCarteCrédit est requis");
//            editTextInformationsCarteCredit.requestFocus();
//            return;
//        }
//        if (cvv.isEmpty()) {
//            editTextCVV.setError("Prenom est requis");
//            editTextCVV.requestFocus();
//            return;
//        }
        // progressBar.setVisibility(View.VISIBLE);

        mAuth.createUserWithEmailAndPassword(adressecourriel, MotDePasse)
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful()) {
                            User user=new Cooker(Prenom, Nom, adressecourriel, MotDePasse, Adresse,"Cooker");
                            FirebaseDatabase.getInstance().getReference("Cookers")
                                    .child(FirebaseAuth.getInstance().getCurrentUser().getUid()).setValue(user).addOnCompleteListener(new OnCompleteListener<Void>() {

                                        @Override
                                        public void onComplete(@NonNull Task<Void> task) {
                                            if (task.isSuccessful()) {
                                                Toast.makeText(CookersignupPageActivity.this, "L'utilisateur  enregistrer", Toast.LENGTH_LONG).show();
                                                //progresseBar.setVisibility(View.GONE);
                                                //rediriger vers login layout
                                                startActivity(new Intent(CookersignupPageActivity.this, WelcomeCookActivity.class));
                                            } else {
                                                Toast.makeText(CookersignupPageActivity.this, "L'utilisateur  non enregistrer essaiyer encore", Toast.LENGTH_LONG).show();
                                                //progresseBar.setVisibility(View.GONE);

                                            }
                                        }

                                    });
                        }else {
                            Toast.makeText(CookersignupPageActivity.this,"L'utilisateur  non enregistrer essaiyer encore", Toast.LENGTH_LONG).show();
                            //progresseBar.setVisibility(View.GONE);

                        }
                    }

                });
    }


    @Override
    public void onClick(View view) {
        switch (view.getId()) {
//           case R.id.banner:
//            StratActivity(new Intent(packageContext.this, MainActivity.class));
//            break;
            case R.id.signUpBttn1:
                resisterUser();
                break;
        }

    }
}