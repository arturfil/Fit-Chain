package com.arturofilio.fitchain;

import android.app.ProgressDialog;
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

import com.arturofilio.fitchain.models.User;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.FirebaseApp;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class RegisterActivity extends AppCompatActivity {

    private static final String TAG = "RegisterActivity";

    EditText mUsername, mUseremail, mPassword, mPasswordConfirm;
    Button btnRegister;

    private FirebaseAuth auth;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_singup);

        mUsername = (EditText) findViewById(R.id.txt_username);
        mUseremail = (EditText) findViewById(R.id.txt_email);
        mPassword = (EditText) findViewById(R.id.txt_password);
        mPasswordConfirm = (EditText) findViewById(R.id.confirm_password);

        btnRegister = (Button) findViewById(R.id.register_btn);

        // Init Firebase
        auth = FirebaseAuth.getInstance();

        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                signUpUser(mUseremail.getText().toString(), mPassword.getText().toString(), mPasswordConfirm.getText().toString());
            }
        });

    }

    private void signUpUser(String email, String password, String password2) {
        auth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(!task.isSuccessful()) {
                            Toast.makeText(RegisterActivity.this, "There was an error, please try again", Toast.LENGTH_SHORT).show();
                        } else if ( mPassword.getText().toString().length() > 6) {
                            Toast.makeText(RegisterActivity.this, "Password must have 6 characters", Toast.LENGTH_SHORT).show();
                        } else {
                            if(mPassword.getText().toString() == mPasswordConfirm.getText().toString()) {
                                Toast.makeText(RegisterActivity.this, "Registered Successfully!", Toast.LENGTH_SHORT).show();
                                Intent intent = new Intent(RegisterActivity.this, SignInActivity.class);
                                startActivity(intent);
                                finish();
                            }
                        }
                    }
                });
    }
}
