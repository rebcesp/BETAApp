package com.example.rebcesp.fastdrinkfinal;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;


import com.example.rebcesp.fastdrinkfinalv1.Home;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class SignInActivity extends AppCompatActivity {

    private FirebaseAuth mAuth;

    ProgressBar pb;
    Button signUp, signIn, signAnon;
    EditText emailET, passET;

    private String TAGA = "AnonymousAuth";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);
//
        pb = (ProgressBar) findViewById(R.id.PB);

        emailET = (EditText) findViewById(R.id.SignInEmail);
        passET = (EditText) findViewById(R.id.SignInPass);

        signIn = (Button) findViewById(R.id.btnLogIn);
        signUp = (Button) findViewById(R.id.btnSignUp);
        signAnon = (Button) findViewById(R.id.SignInAnon);


        emailET.setText(getIntent().getStringExtra("email"));
        passET.setText(getIntent().getStringExtra("password"));


        mAuth = FirebaseAuth.getInstance();


        signIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loginAttempt();
            }
        });

        signUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                signUpActivitiy();
            }
        });


        signAnon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                signAnonymously();
            }
        });


    }

    private void loginAttempt() {
        String email = emailET.getText().toString().trim();
        String pass = passET.getText().toString().trim();



        boolean error = false;

        if (email.isEmpty()) {
            emailET.setError("Email required");
            error = true;
        } else if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            emailET.setError("Please enter a valid email");
            error = true;
        }

        if (pass.isEmpty()) {
            passET.setError("Password required");
            error = true;
        } else if (pass.length() < 8) {
            passET.setError("Password must be at least 8 characters long");
            error = true;
        }

        if (error) {
            return;
        }

        pb.setVisibility(View.VISIBLE);

        mAuth.signInWithEmailAndPassword(email, pass).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                pb.setVisibility(View.GONE);
                if (task.isSuccessful()) {
                    Log.d("AUTH", "signInWithEmail:success");
                    FirebaseUser user = mAuth.getCurrentUser();

                    Intent homeIntent = new Intent(SignInActivity.this, Home.class);
//                    Common.currentUser = user;
                    startActivity(homeIntent);
                    finish();

//                    loggedIn();
                } else {
                    Log.w("AUTH", "signInWithEmail:failure", task.getException());
                    Toast.makeText(SignInActivity.this, "Authentication failed.\n" + task.getException().getMessage(),
                            Toast.LENGTH_SHORT).show();
                }
            }
        });

    }

    private void signAnonymously() {
        pb.setVisibility(View.VISIBLE);

        mAuth.signInAnonymously()
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            Log.d(TAGA, "signInAnonymously:success");
                            FirebaseUser user = mAuth.getCurrentUser();
//                            //updateUI(user);
//                            loggedIn();
                        } else {
                            // If sign in fails, display a message to the user.
                            Log.w(TAGA, "signInAnonymously:failure", task.getException());
                            Toast.makeText(SignInActivity.this, "Authentication failed.",
                                    Toast.LENGTH_SHORT).show();
                            //updateUI(null);
                        }

                        // [START_EXCLUDE]
                        //hideProgressDialog();
                        pb.setVisibility(View.GONE);
                        // [END_EXCLUDE]
                    }
                });
    }

//    private void loggedIn() {
//        finish();
//        Intent intent = new Intent(SignInActivity.this, MainActivity.class);
//        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
//        startActivity(intent);

//}

    private void signUpActivitiy() {
        Intent signUpAct = new Intent(SignInActivity.this, SignUpActivity.class);
        Log.d("Logg", "SignUpActivity:" + "hola");
        startActivity(signUpAct);


    }

//    public void onStart() {
//        super.onStart();
//        FirebaseUser currentUser = mAuth.getCurrentUser();
//        if (currentUser != null) {
//            loggedIn();
//        }
    }


