package com.example.askdaekmek;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class LoginActivity extends AppCompatActivity {

    EditText etEmail, etPassword;
    Button btnLogin, btnRegisterGo;
    String email, password ;
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        mAuth = FirebaseAuth.getInstance();

        etEmail = findViewById(R.id.etLoginEmail);
        etPassword = findViewById(R.id.etLoginPassword);
        btnLogin = findViewById(R.id.btnLogin);
        btnRegisterGo = findViewById(R.id.btnRegister);

        btnRegisterGo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goRegister();
            }
        });

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                email = etEmail.getText().toString();

                password = etPassword.getText().toString();
                if (!email.isEmpty() && !password.isEmpty()) {
                    Login(email, password);
                }
            }

        });
    }

    private void bundleEmail() {


        Intent homeIntent = new Intent(getApplicationContext(),MainPageActivity.class);
        Bundle emailBundle =new Bundle();
        emailBundle.putString("bundledEmail" , email);
        homeIntent.putExtras(emailBundle);
        startActivity(homeIntent);
    }

    private void Login(String email, String password) {
        mAuth.signInWithEmailAndPassword(email, password).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()) {

                    // Sign in success, update UI with the signed-in user's information
                    FirebaseUser user = mAuth.getCurrentUser();
                    Toast.makeText(LoginActivity.this, "Haydi askıya 1 ekmek bırak",
                            Toast.LENGTH_SHORT).show();

                   bundleEmail();

                } else {
                    // If sign in fails, display a message to the user.
                    Toast.makeText(LoginActivity.this, "Authentication failed.",
                            Toast.LENGTH_SHORT).show();

                }

                // ...
            }
        });

    }


    private void goRegister() {
        Intent goRegisterIntent = new Intent(getApplicationContext(),RegisterActivity.class);
        startActivity(goRegisterIntent);
    }

}