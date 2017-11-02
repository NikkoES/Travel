package com.nikkoes.travel;

import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.nikkoes.travel.database.DatabaseHelper;

public class RegisterActivity extends AppCompatActivity {

    // Email, password edittext
    EditText txtName, txtUsername, txtPassword;

    // login button
    Button btnDaftar, btnKeLogin;

    DatabaseHelper dbHelper;

    SQLiteDatabase db;

    String name, username, password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        dbHelper = new DatabaseHelper(this);

        db = dbHelper.getWritableDatabase();

        txtName = (EditText) findViewById(R.id.reg_nama);
        txtUsername = (EditText) findViewById(R.id.reg_email);
        txtPassword = (EditText) findViewById(R.id.reg_password);

        btnDaftar = (Button) findViewById(R.id.daftar);
        btnKeLogin = (Button) findViewById(R.id.ke_login);

        btnDaftar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                name = txtName.getText().toString();
                username = txtUsername.getText().toString();
                password = txtPassword.getText().toString();
                try {
                    if(username.trim().length() > 0 && password.trim().length() > 0 && name.trim().length() > 0){
                        dbHelper.open();
                        dbHelper.Register(username, password, name);
                        Toast.makeText(RegisterActivity.this, "Daftar berhasil", Toast.LENGTH_LONG).show();
                        finish();
                    }
                    else{
                        Toast.makeText(RegisterActivity.this, "Daftar gagal, data belum diisi", Toast.LENGTH_LONG).show();
                    }
                }
                catch (Exception e) {
                    // TODO: handle exception
                    Toast.makeText(RegisterActivity.this, e.getMessage(), Toast.LENGTH_LONG).show();
                }
            }
        });

        btnKeLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
}
