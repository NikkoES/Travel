package com.nikkoes.travel.adapter;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.nikkoes.travel.R;

import java.util.ArrayList;

/**
 * Created by Nikko Eka Saputra on 30/09/2017.
 */

public class NotificationAdapter extends ArrayAdapter<Notification> {

    public NotificationAdapter(Activity context, ArrayList<Notification> notification) {
        super(context, 0, notification);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // Check if the existing view is being reused, otherwise inflate the view
        View listItemView = convertView;
        if(listItemView == null) {
            listItemView = LayoutInflater.from(getContext()).inflate(
                    R.layout.list_item_booking, parent, false);
        }

        //membuat objek yg memanggil class word
        Notification current = getItem(position);

        TextView idBook = (TextView) listItemView.findViewById(R.id.id_booking);
        idBook.setText("ID : "+current.getIdBook());

        TextView tanggal = (TextView) listItemView.findViewById(R.id.tanggal);
        tanggal.setText(current.getTanggal());

        TextView riwayat = (TextView) listItemView.findViewById(R.id.riwayat);
        riwayat.setText(current.getRiwayat());

        TextView tvTotal = (TextView) listItemView.findViewById(R.id.tv_total);
        tvTotal.setText("Total :");

        TextView total = (TextView) listItemView.findViewById(R.id.total);
        total.setText("Rp. "+current.getTotal());

        ImageView imageIcon = (ImageView) listItemView.findViewById(R.id.image);

        if(current.hasImage()){
            imageIcon.setImageResource(current.getImageResourceId());
            imageIcon.setVisibility(View.VISIBLE);
        }
        else{
            imageIcon.setVisibility(View.GONE);
        }

        return listItemView;
    }
}