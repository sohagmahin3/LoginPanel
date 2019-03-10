package com.example.loginpanel;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.parse.ParseException;
import com.parse.ParseUser;
import com.parse.SignUpCallback;
import com.shashank.sony.fancytoastlib.FancyToast;

public class SignUpActivity extends AppCompatActivity implements View.OnClickListener {

    private EditText editTextUsername,editTextEmail,editTextPassword;
    private Button btnSignUp,btnLogin;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);
        editTextUsername= findViewById(R.id.editTextUsername);
        editTextEmail = findViewById(R.id.editTextEmail);
        editTextPassword= findViewById(R.id.editTextPassword);

        setTitle("Sign Up Activity");
        btnSignUp = findViewById(R.id.btnSignUp);
        btnLogin = findViewById(R.id.btnLogin);

        btnSignUp.setOnClickListener(this);
        btnLogin.setOnClickListener(this);

        if (ParseUser.getCurrentUser()!=null){
            ParseUser.getCurrentUser().logOut();
        }
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.btnSignUp:
                ProgressDialog progressDialog = new ProgressDialog(this);
                progressDialog.setMessage("Signing Up "+editTextUsername.getText().toString());
                progressDialog.show();
                final ParseUser appUser = new ParseUser();
                appUser.setUsername(editTextUsername.getText().toString());
                appUser.setPassword(editTextPassword.getText().toString());
                appUser.setEmail(editTextEmail.getText().toString());
                appUser.signUpInBackground(new SignUpCallback() {
                    @Override
                    public void done(ParseException e) {
                        if(e==null){
                            FancyToast.makeText(SignUpActivity.this,appUser.get("username").toString(),FancyToast.SUCCESS,FancyToast.LENGTH_LONG,true).show();
                        }else {
                            FancyToast.makeText(SignUpActivity.this,e.getMessage(),FancyToast.ERROR,FancyToast.LENGTH_LONG,true).show();
                        }
                    }
                });
                progressDialog.dismiss();

                break;
            case R.id.btnLogin:
                Intent intent = new Intent(SignUpActivity.this,MainActivity.class);
                startActivity(intent);
                break;
        }

    }
}
