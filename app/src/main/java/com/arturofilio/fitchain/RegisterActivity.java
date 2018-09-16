package com.arturofilio.fitchain;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.arturofilio.fitchain.models.User;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.FirebaseApp;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;


public class RegisterActivity extends AppCompatActivity {

    private static final String TAG = "RegisterActivity";

    EditText mUsername, mEmail, mPassword, mPasswordConfirm;
    Button btnRegister;

    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_singup);

        mEmail = (EditText) findViewById(R.id.txt_email);
        mPasswordConfirm = (EditText) findViewById(R.id.confirm_password);
        mPassword = (EditText) findViewById(R.id.password);
        mUsername = (EditText) findViewById(R.id.txt_name);

        btnRegister = (Button) findViewById(R.id.register_btn);

        // Init Firebase
        mAuth = FirebaseAuth.getInstance();

        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                signUpUser();
            }
        });

    }

    private void signUpUser() {

        String emailField = mEmail.getText().toString();
        String passwordField = mPassword.getText().toString().trim();
        String passwordConfirm = mPasswordConfirm.getText().toString();
        String nameField = mUsername.getText().toString();

        if(TextUtils.isEmpty(emailField) || TextUtils.isEmpty(passwordField)
                || TextUtils.isEmpty(passwordConfirm) || TextUtils.isEmpty(nameField)) {

            Toast.makeText(this, "All fields are required", Toast.LENGTH_SHORT).show();
            return;
        }

        if(!Patterns.EMAIL_ADDRESS.matcher(emailField).matches()) {
            Toast.makeText(this, "Please enter a valid email address", Toast.LENGTH_SHORT).show();
            return;
        }

        if(passwordField.length() < 6) {
            Toast.makeText(this, "Password must contain at least 6 characters", Toast.LENGTH_SHORT).show();
            return;
        }

        if(passwordField.equals(passwordConfirm)) {
            mAuth.createUserWithEmailAndPassword(emailField, passwordField).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    if(task.isSuccessful()) {
                        Toast.makeText(RegisterActivity.this, "Registration Successful!", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(RegisterActivity.this, SignInActivity.class);
                        startActivity(intent);
                    } else {
                        Toast.makeText(RegisterActivity.this, "Oh oh, something went wrong, please try again later ", Toast.LENGTH_SHORT).show();
                    }
                }
            });
        } else {
            Toast.makeText(this, "Passwords must be the same", Toast.LENGTH_SHORT).show();
            return;
        }
        

    }

}
