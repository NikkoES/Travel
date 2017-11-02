package com.nikkoes.travel;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ListView;

import com.nikkoes.travel.adapter.Notification;
import com.nikkoes.travel.adapter.NotificationAdapter;
import com.nikkoes.travel.database.DatabaseHelper;
import com.nikkoes.travel.database.SessionManager;

import java.util.ArrayList;
import java.util.HashMap;

public class NotificationActivity extends AppCompatActivity {

    protected Cursor cursor;
    DatabaseHelper dbHelper;
    SQLiteDatabase db;

    // Session Manager Class
    SessionManager session;

    String id_book, asal, tujuan, tanggal, dewasa, anak, riwayat, total;

    String email;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notification);

        dbHelper = new DatabaseHelper(this);

        db = dbHelper.getReadableDatabase();

        // Session class instance
        session = new SessionManager(getApplicationContext());

        // get user data from session
        HashMap<String, String> user = session.getUserDetails();

        // email
        email = user.get(SessionManager.KEY_EMAIL);

        refreshList();
    }

    public void refreshList(){
        final ArrayList<Notification> hasil = new ArrayList<Notification>();

        cursor = db.rawQuery("SELECT * FROM TB_BOOK, TB_HARGA WHERE TB_BOOK.id_book = TB_HARGA.id_book AND username='"+ email +"'", null);
        cursor.moveToFirst();
        for(int i=0;i<cursor.getCount();i++){
            cursor.moveToPosition(i);
            id_book = cursor.getString(0).toString();
            asal = cursor.getString(1).toString();
            tujuan = cursor.getString(2).toString();
            tanggal = cursor.getString(3).toString();
            dewasa = cursor.getString(4).toString();
            anak = cursor.getString(5).toString();
            total = cursor.getString(10).toString();
            riwayat = "Berhasil melakukan booking untuk melakukan perjalanan dari " + asal + " menuju " + tujuan + " pada tanggal " + tanggal + ". " +
            "Jumlah pembelian tiket dewasa sejumlah " + dewasa + " dan tiket anak-anak sejumlah " + anak + ".";
            hasil.add(new Notification(id_book, tanggal, riwayat, total, R.drawable.profile));
        }

        ListView listBook = (ListView) findViewById(R.id.list_booking);

        NotificationAdapter arrayAdapter = new NotificationAdapter(this, hasil);

        listBook.setAdapter(arrayAdapter);
    }
}
