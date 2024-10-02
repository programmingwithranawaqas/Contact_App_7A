package com.example.contact_app_7a;

import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.fragment.app.FragmentManager;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements ContactAdapter.ContactSelected{

    ListView lvContact_list;
    ArrayList<Contact> contacts;
    TextView tv_name, tv_address;
    ImageView iv_phone;


    // hooks of fragments
    ContactListFrag contactListFrag;
    ContactDetailFrag contactDetailFrag;
    FragmentManager fragmentManager;
    FloatingActionButton fab_add_contact;

    // to get view of a specific fragment, purpose: use it for multiple hooks, rather than getting view of fragment again and again
    View viewContactDetailFrag;

    LinearLayout portrait;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        fragmentManager = getSupportFragmentManager();
        contactListFrag = (ContactListFrag) fragmentManager.findFragmentById(R.id.contact_list_frag);
        contactDetailFrag = (ContactDetailFrag) fragmentManager.findFragmentById(R.id.contact_detail_frag);
        viewContactDetailFrag = contactDetailFrag.getView();

        iv_phone = viewContactDetailFrag.findViewById(R.id.iv_phone);
        tv_name = viewContactDetailFrag.findViewById(R.id.tv_name);
        tv_address = viewContactDetailFrag.findViewById(R.id.tv_address);
        portrait = findViewById(R.id.portrait);

        if(portrait!=null)
        {
            fragmentManager.beginTransaction()
                    .show(contactListFrag)
                    .hide(contactDetailFrag)
                    .commit();
        }
        else {

                fragmentManager.beginTransaction()
                        .show(contactListFrag)
                        .show(contactDetailFrag)
                        .commit();

        }

        iv_phone.setVisibility(View.GONE);
        tv_address.setVisibility(View.GONE);
        tv_name.setText("Select record to show details");

        lvContact_list = contactListFrag.getView().findViewById(R.id.contact_list);
        contacts = new ArrayList<>();
        contacts.add(new Contact("Ali","123","Honda Mor", "Male"));
        contacts.add(new Contact("Kashaf","","","Female"));
        contacts.add(new Contact("Kazmi","","", "Male"));
        contacts.add(new Contact("Waqas","","", "Male"));
        contacts.add(new Contact("Jahangir","","", "Male"));

        ContactAdapter contactAdapter = new ContactAdapter(this, R.layout.single_contact_design, contacts);
        lvContact_list.setAdapter(contactAdapter);

        fab_add_contact = findViewById(R.id.fab_add_contact);
        fab_add_contact.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                AlertDialog.Builder add_dialog = new AlertDialog.Builder(MainActivity.this);
                add_dialog.setTitle("Add New Record");
                add_dialog.setMessage("Do you really want to add new record?");

                add_dialog.setPositiveButton("Add", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                                        contacts.add(new Contact("New Contact", "1111", "xyz", "Female"));
                                        contactAdapter.notifyDataSetChanged();
                    }
                });
                add_dialog.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        Toast.makeText(MainActivity.this, "Thank you", Toast.LENGTH_SHORT).show();
                    }
                });

                add_dialog.show();

            }
        });

    }

    @Override
    public void onConatctClick(int position) {
        if(portrait!=null)
        {
            fragmentManager.beginTransaction()
                    .hide(contactListFrag)
                    .show(contactDetailFrag)
                    .addToBackStack(null)
                    .commit();
        }

        iv_phone.setVisibility(View.VISIBLE);
        tv_address.setVisibility(View.VISIBLE);
        Contact c = contacts.get(position);
        tv_name.setText(c.getName());
        tv_address.setText(c.getAddress());
        iv_phone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(Intent.ACTION_DIAL);
                i.setData(Uri.parse("tel:"+c.getPhone()));
                startActivity(i);
            }
        });


    }
}