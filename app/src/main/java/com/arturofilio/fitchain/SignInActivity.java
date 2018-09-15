package com.arturofilio.fitchain;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class SignInActivity extends AppCompatActivity {

    private static final String TAG = "SignInActivity";

    EditText mUsername, mPassword;
    Button btnSignIn;
    TextView txtLink, txtPasswordLink;

    private FirebaseAuth auth;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signin);

        mUsername = (EditText) findViewById(R.id.txt_username);
        mPassword = (EditText) findViewById(R.id.txt_password);
        btnSignIn = (Button) findViewById(R.id.btn_login);
        txtLink = (TextView) findViewById(R.id.txt_link);
        txtPasswordLink = (TextView) findViewById(R.id.txt_password_link);

        // Init Firebase Auth
        auth = FirebaseAuth.getInstance();

        // Check if session is already active (true),
        if(auth.getCurrentUser() != null) {
            Intent intent = new Intent(SignInActivity.this, MainActivity.class);
            startActivity(intent);
        }

        txtLink.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SignInActivity.this, RegisterActivity.class);
                startActivity(intent);
            }
        });

        txtPasswordLink.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SignInActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });

        btnSignIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loginUser(mUsername.getText().toString(), mPassword.getText().toString());
            }
        });

    }

    private void loginUser(String email, final String password) {
        auth.signInWithEmailAndPassword(email,password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(!task.isSuccessful()) {
                            if(password.length() < 6) {
                                Toast.makeText(SignInActivity.this, "Password must have 6 characters", Toast.LENGTH_SHORT).show();
                            }
                        } else {
                            Intent intent = new Intent(SignInActivity.this, MainActivity.class);
                            startActivity(intent);
                        }
                    }
                });
    }
}
