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

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class SignUpActivity extends AppCompatActivity {


    ProgressBar pb;
    EditText emailET, passwordET, passverET;
    Button regis;
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        pb = findViewById(R.id.PB);

        emailET = findViewById(R.id.SignUpEmail);
        passwordET = findViewById(R.id.SignUpPass);
        passverET = findViewById(R.id.SignUpPassV);

        regis = findViewById(R.id.btnCreateAccount);
        regis.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                registerUser();
            }
        });

        mAuth = FirebaseAuth.getInstance();
    }

    private void registerUser(){

        final String email = emailET.getText().toString().trim();
        String pass = passwordET.getText().toString().trim();
        String passV = passverET.getText().toString().trim();




        boolean error = false;

        if(email.isEmpty()){
            emailET.setError("Email required");
            error = true;
        }else if(!Patterns.EMAIL_ADDRESS.matcher(email).matches()){
            emailET.setError("Please enter a valid email");
            error = true;
        }

        if(pass.isEmpty()){
            passwordET.setError("Password required");
            error = true;
        }else if(pass.length() < 8){
            passwordET.setError("Password must be at least 8 characters long");
            error = true;
        }else if(passV.isEmpty()){
            passverET.setError("Password verification required");
            error = true;
        }else if(!pass.equals(passV)){
            passverET.setError("Password does not match");
            error = true;
        }

        if(error){
            return;
        }

        pb.setVisibility(View.VISIBLE);

        mAuth.createUserWithEmailAndPassword(email, pass)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            Log.d("AUTH", "createUserWithEmail:success");
                            FirebaseUser user = mAuth.getCurrentUser();
                            finish();

                            Intent intent = new Intent(SignUpActivity.this, SignInActivity.class);

                            intent.putExtra("email",emailET.getText().toString());
                            intent.putExtra("password",passwordET.getText().toString());

                            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                            startActivity(intent);


                            //updateUI(user);
                        } else {
                            // If sign in fails, display a message to the user.
                            Log.w("AUTH", "createUserWithEmail:failure", task.getException());
                            Toast.makeText(SignUpActivity.this, "Authentication failed.\n"+task.getException().getMessage(),
                                    Toast.LENGTH_SHORT).show();
                            //updateUI(null);
                        }
                        pb.setVisibility(View.GONE);
                        // ...
                    }
                });
    }

    @Override
    public void onStart() {
        super.onStart();
        //
        // Check if user is signed in (non-null) and update UI accordingly.
        FirebaseUser currentUser = mAuth.getCurrentUser();
        if(currentUser!=null) {
//            finish();
//            Intent intent = new Intent(SignUpActivity.this, SignInActivity.class);
//            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
//            startActivity(intent);
        }
    }
}
