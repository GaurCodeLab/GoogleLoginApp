package com.gaur.logindemoapp;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.SignInButton;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.FirebaseApp;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class Registration extends AppCompatActivity {

    private EditText memail;
    private EditText mpass;
    private EditText ConfrmPass;
    public Button mSignup;
    private SignInButton Gsignin;

    GoogleSignInClient mGoogleSignInClient;
    FirebaseAuth mAuth;
   // GoogleApiClient.OnConnectionFailedListener{}

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);

        FirebaseApp.initializeApp(this);

        memail = findViewById(R.id.memail);
        mpass = findViewById(R.id.mpassword);
        ConfrmPass = findViewById(R.id.confrmpassword);
        mSignup = findViewById(R.id.btnsignup);
        Gsignin = findViewById(R.id.btn_google_signin);

        // Configure sign-in to request the user's ID, email address, and basic
// profile. ID and basic profile are included in DEFAULT_SIGN_IN.
        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestEmail()
                .build();

        // Build a GoogleSignInClient with the options specified by gso.
        mGoogleSignInClient = GoogleSignIn.getClient(this, gso);

        mAuth = FirebaseAuth.getInstance();

        //Google SignIN

       Gsignin.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               Intent signInIntent = mGoogleSignInClient.getSignInIntent();
               startActivityForResult(signInIntent, 007);
               startActivity(new Intent(getApplicationContext(), Home.class));

           }
       });



        // for signup
        mSignup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //  Toast.makeText(Registration.this, "signup is clicked", Toast.LENGTH_SHORT).show();

                String email = memail.getText().toString().trim();
                String pass = mpass.getText().toString().trim();
                String passconfirm = ConfrmPass.getText().toString().trim();

                if (TextUtils.isEmpty(email)) {
                    memail.setError("Feild Required");

                    return;
                }

                if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
                    memail.setError("Please enter a valid email");
                    memail.requestFocus();
                    return;
                }

                if (TextUtils.isEmpty(pass)) {
                    mpass.setError("Feild Required");
                    return;
                }

                if (pass.length() < 6) {
                    mpass.setError("Minimum lenght of password should be 6");
                    mpass.requestFocus();
                    return;
                }

                if (TextUtils.isEmpty(passconfirm)) {
                    ConfrmPass.setError("Feild Required");
                    mpass.requestFocus();
                    return;
                }

                if (!passconfirm.equals(pass)) {
                    ConfrmPass.setError("Passwoord does not match");
                    mpass.requestFocus();
                    return;
                }


                mAuth.createUserWithEmailAndPassword(email, pass).addOnCompleteListener(Registration.this, new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                if (task.isSuccessful()) {
                                    // Sign in success, update UI with the signed-in user's information
                                    Toast.makeText(Registration.this, "Account Created", Toast.LENGTH_SHORT).show();

                                    startActivity(new Intent(getApplicationContext(), Home.class));
                                    FirebaseUser user = mAuth.getCurrentUser();

                                } else {
                                    // If sign in fails, display a message to the user.
                                    //   Toast.makeText(Registration.this, "Registration Failed!!!", Toast.LENGTH_SHORT).show();

                                    showErrorDialog("Registration Failed!!");
                                }

                            }
                        });

            }
        });


    }




        public void showErrorDialog (String message){
            new AlertDialog.Builder(this)
                    .setTitle("Oops")
                    .setMessage(message)
                    .setPositiveButton(android.R.string.ok, null)
                    .setIcon(android.R.drawable.ic_dialog_alert)
                    .show();
        }


    }