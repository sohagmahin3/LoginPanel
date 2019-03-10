package com.example.loginpanel;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;

import com.parse.LogInCallback;
import com.parse.ParseException;
import com.parse.ParseUser;
import com.shashank.sony.fancytoastlib.FancyToast;


public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    private EditText editTextUsername,editTextPassword;
    private Button btnLogin,btnSwitchActivity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editTextUsername= findViewById(R.id.editTextUsername);
        editTextPassword= findViewById(R.id.editTextPassword);

        btnLogin=findViewById(R.id.btnLogin);
        btnSwitchActivity=findViewById(R.id.btnSwitchActivity);
        btnLogin.setOnClickListener(MainActivity.this);
        btnSwitchActivity.setOnClickListener(MainActivity.this);
        if(ParseUser.getCurrentUser()!=null){
            ParseUser.getCurrentUser().logOut();
        }

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.btnLogin:
                ProgressDialog progressDialog = new ProgressDialog(this);
                progressDialog.setMessage("Loging "+editTextUsername.getText().toString());
                progressDialog.show();
                ParseUser.logInInBackground(editTextUsername.getText().toString(), editTextPassword.getText().toString(), new LogInCallback() {
                    @Override
                    public void done(ParseUser user, ParseException e) {
                        if(user!=null && e==null){
                            FancyToast.makeText(MainActivity.this,"Login Successed!",FancyToast.SUCCESS,FancyToast.LENGTH_LONG,true).show();
                        }else {
                            FancyToast.makeText(MainActivity.this,e.getMessage().toString(),FancyToast.SUCCESS,FancyToast.LENGTH_LONG,true).show();
                        }
                    }
                });
                progressDialog.dismiss();
                break;

            case R.id.btnSwitchActivity:
                Intent intent = new Intent(MainActivity.this,SignUpActivity.class);
                startActivity(intent);
                break;
        }

    }
}