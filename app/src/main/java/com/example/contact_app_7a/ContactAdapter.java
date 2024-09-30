package com.example.contact_app_7a;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.List;

public class ContactAdapter extends ArrayAdapter<Contact> {
    Context context;
    int resource;

    public ContactAdapter(@NonNull Context context,int resource, @NonNull List<Contact> objects) {
        super(context, resource, objects);
        this.context = context;
        this.resource = resource;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        if(convertView == null)
        {
            // view attachment
            convertView = LayoutInflater.from(context).inflate(resource, parent, false);
        }

        // set data to this view
        Contact contact = getItem(position);
        TextView tvName = convertView.findViewById(R.id.tvContact_Name);
        ImageView ivProfile_pic = convertView.findViewById(R.id.ivProfile_pic);
        tvName.setText(contact.getName());

        if(contact.getGender().equals("Male"))
            ivProfile_pic.setImageResource(R.drawable.icon_person);
        else
            ivProfile_pic.setImageResource(R.drawable.icon_female);

        return convertView;
    }
}
