package com.nikkoes.travel;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.nikkoes.travel.adapter.AlertDialogManager;
import com.nikkoes.travel.database.DatabaseHelper;
import com.nikkoes.travel.database.SessionManager;

public class LoginActivity extends AppCompatActivity {

    // Email, password edittext
    EditText txtUsername, txtPassword;

    // login button
    Button btnLogin, btnForgot, btnRegister;

    // Alert Dialog Manager
    AlertDialogManager alert = new AlertDialogManager();

    // Session Manager Class
    SessionManager session;

    DatabaseHelper dbHelper;

    SQLiteDatabase db;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        dbHelper = new DatabaseHelper(this);

        db = dbHelper.getWritableDatabase();

        // Session Manager
        session = new SessionManager(getApplicationContext());

        // Email, Password input text
        txtUsername = (EditText) findViewById(R.id.email);
        txtPassword = (EditText) findViewById(R.id.password);

        btnLogin = (Button) findViewById(R.id.masuk);
        btnForgot = (Button) findViewById(R.id.ke_forgot);
        btnRegister = (Button) findViewById(R.id.ke_daftar);

        // Login button click event
        btnLogin.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {
                // Get username, password from EditText
                String username = txtUsername.getText().toString();
                String password = txtPassword.getText().toString();

                // Check if username, password is filled
                try{
                    // Check if username, password is filled
                    if(username.trim().length() > 0 && password.trim().length() > 0){
                        dbHelper.open();

                        if(dbHelper.Login(username, password)){

                            session.createLoginSession(username);

                            // Staring MainActivity
                            finish();
                            Intent i = new Intent(getApplicationContext(), MainActivity.class);
                            startActivity(i);

                        }
                        else{
                            // username / password doesn't match
                            alert.showAlertDialog(LoginActivity.this, "Login failed..", "Username/Password is incorrect", false);
                        }
                    }
                    else{
                        // user didn't entered username or password
                        // Show alert asking him to enter the details
                        alert.showAlertDialog(LoginActivity.this, "Login failed..", "Please enter username and password", false);
                    }
                }
                catch (Exception e){
                    Toast.makeText(LoginActivity.this,e.getMessage(), Toast.LENGTH_LONG).show();
                }
            }
        });

        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(LoginActivity.this, RegisterActivity.class);
                startActivity(i);
            }
        });
    }
}
