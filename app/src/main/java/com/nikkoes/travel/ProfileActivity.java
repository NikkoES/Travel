package com.nikkoes.travel;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import com.nikkoes.travel.database.DatabaseHelper;
import com.nikkoes.travel.database.SessionManager;

import java.util.HashMap;

public class ProfileActivity extends AppCompatActivity {

    protected Cursor cursor;
    DatabaseHelper dbHelper;
    SQLiteDatabase db;

    SessionManager session;

    String name, email;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        dbHelper = new DatabaseHelper(this);

        session = new SessionManager(getApplicationContext());

        HashMap<String, String> user = session.getUserDetails();
        email = user.get(SessionManager.KEY_EMAIL);

        db = dbHelper.getReadableDatabase();
        cursor = db.rawQuery("SELECT * FROM TB_USER WHERE username = '"+ email +"'", null);
        cursor.moveToFirst();
        if(cursor.getCount()>0){
            cursor.moveToPosition(0);
            name = cursor.getString(2).toString();
        }

        TextView lblName = (TextView) findViewById(R.id.lblName);
        TextView lblEmail = (TextView) findViewById(R.id.lblEmail);

        lblName.setText(name);
        lblEmail.setText(email);

    }
}