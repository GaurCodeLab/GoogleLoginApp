package com.gaur.logindemoapp;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.app.AlertDialog;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.FirebaseApp;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class MainActivity extends AppCompatActivity {


    private EditText email;
    private EditText pass;
    private Button Login;
    private Button Signup;
   private FirebaseAuth mAuth;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

       FirebaseApp.initializeApp(this);

        email = findViewById(R.id.email);
        pass = findViewById(R.id.password);
        Login = findViewById(R.id.login);
        Signup = findViewById(R.id.signup);
         mAuth = FirebaseAuth.getInstance();


        Login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
              //  Toast.makeText(MainActivity.this, "login btn is clicked", Toast.LENGTH_SHORT).show();

                String memail = email.getText().toString().trim();
                String mpass = pass.getText().toString().trim();


                if (TextUtils.isEmpty(memail)) {
                    email.setError("Feild Required");

                    return;
                }

                if (!Patterns.EMAIL_ADDRESS.matcher(memail).matches()) {
                    email.setError("Please enter a valid email");
                    email.requestFocus();
                    return;
                }

                if (TextUtils.isEmpty(mpass)) {
                    pass.setError("Feild Required");
                    return;
                }

                if (mpass.length() < 6) {
                    pass.setError("Minimum 6 character needed");
                    pass.requestFocus();
                    return;
                }

                mAuth.signInWithEmailAndPassword(memail, mpass)
                        .addOnCompleteListener(MainActivity.this, new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                if (task.isSuccessful()) {
                                    // Sign in success, update UI with the signed-in user's information

                                    Toast.makeText(MainActivity.this, "LoggedIn Successfull", Toast.LENGTH_SHORT).show();

                                    startActivity(new Intent(getApplicationContext(), Home.class));

                                } else {
                                    // If sign in fails, display a message to the user.

                                    Toast.makeText(MainActivity.this, "Authentication failed.", Toast.LENGTH_SHORT).show();

                                }


                            }
                        });




            }
        });

        Signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), Registration.class));
            }
        });


    }

//    @Override
//
//    public void onStart(){
//        super.onStart();
//        // Check for existing Google Sign In account, if the user is already signed in
//// the GoogleSignInAccount will be non-null.
//        GoogleSignInAccount account = GoogleSignIn.getLastSignedInAccount(this);
//        startActivity(new Intent(getApplicationContext(), Home.class));
//    }


}
