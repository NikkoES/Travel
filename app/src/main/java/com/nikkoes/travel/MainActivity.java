package com.nikkoes.travel;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.nikkoes.travel.adapter.AlertDialogManager;
import com.nikkoes.travel.database.SessionManager;

public class MainActivity extends AppCompatActivity {

    // Alert Dialog Manager
    AlertDialogManager alert = new AlertDialogManager();

    // Session Manager Class
    SessionManager session;

    // Button Logout
    Button btnLogout;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Session class instance
        session = new SessionManager(getApplicationContext());

        // Button logout
        btnLogout = (Button) findViewById(R.id.keluar);

        /**
         * Call this function whenever you want to check user login
         * This will redirect user to LoginActivity is he is not
         * logged in
         * */
        session.checkLogin();

        /**
         * Logout button click event
         * */
        btnLogout.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {
                AlertDialog dialog = new AlertDialog.Builder(MainActivity.this)
                        .setTitle("Anda yakin ingin keluar ?")
                        .setPositiveButton("Ya", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                finish();
                                session.logoutUser();
                            }
                        })
                        .setNegativeButton("Tidak", null)
                        .create();
                dialog.show();
            }
        });
    }

    public void profileMenu(View v){
        Intent i = new Intent(this, ProfileActivity.class);
        startActivity(i);
    }

    public void notificationMenu(View v){
        Intent i = new Intent(this, NotificationActivity.class);
        startActivity(i);
    }

    public void bookKereta(View v){
        Intent i = new Intent(this, BookKeretaActivity.class);
        startActivity(i);
    }

    public void bookHotel(View v){
        Toast.makeText(getApplicationContext(), "Mohon maaf, sistem sedang dalam pengembangan.", Toast.LENGTH_LONG).show();
//        Intent i = new Intent(this, Book.class);
//        startActivity(i);
    }
}
