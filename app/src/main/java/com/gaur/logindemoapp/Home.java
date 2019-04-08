package com.gaur.logindemoapp;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.common.SignInButton;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.ResultCallback;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;

public class Home extends AppCompatActivity {

  GoogleApiClient mGoogleSignInClient;
  private Button Signout;
  private Button Revoke;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
       Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        Signout = findViewById(R.id.logout);
        Revoke = findViewById(R.id.revok);

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
//                        .setAction("Action", null).show();
                startActivity(new Intent(getApplicationContext(), MainActivity.class));
            }
        });


//        toolbar.setOnMenuItemClickListener(new Toolbar.OnMenuItemClickListener() {
//            @Override
//            public boolean onMenuItemClick(MenuItem menuItem) {
//                signOut();
//            return false ;}
//        });
        Signout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                signOut();

            }
        });
            // For Revoking user Account

//        private void revokeAccess() {
//            Auth.GoogleSignInApi.revokeAccess(mGoogleSignInClient).setResultCallback(
//                    new ResultCallback<Status>() {
//                        @Override
//                        public void onResult(Status status) {
//                            Toast.makeText(Home.this, "Account Revoked", Toast.LENGTH_SHORT).show();
//                        }
//                    });
//        }
//
//        Revoke.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                revokeAccess();
//            }
//        });

    }



    private void signOut() {
        Auth.GoogleSignInApi.signOut(mGoogleSignInClient).setResultCallback(
                new ResultCallback<Status>() {
                    @Override
                    public void onResult(Status status) {
                        startActivity(new Intent(getApplicationContext(), MainActivity.class));
                    }
                });
//        mGoogleSignInClient.signOut()
//                .addOnCompleteListener(this, new OnCompleteListener<Void>() {
//                    @Override
//                    public void onComplete(@NonNull Task<Void> task) {
//                        // ...
//                    }
//                });
    }


}
