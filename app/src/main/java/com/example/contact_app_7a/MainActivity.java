package com.example.contact_app_7a;

import android.os.Bundle;
import android.view.View;
import android.widget.ListView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.fragment.app.FragmentManager;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    ListView lvContact_list;
    ArrayList<Contact> contacts;

    // hooks of fragments
    ContactListFrag contactListFrag;
    ContactDetailFrag contactDetailFrag;
    FragmentManager fragmentManager;
    FloatingActionButton fab_add_contact;

    // to get view of a specific fragment, purpose: use it for multiple hooks, rather than getting view of fragment again and again
    View viewContactDetailFrag;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        fragmentManager = getSupportFragmentManager();
        contactListFrag = (ContactListFrag) fragmentManager.findFragmentById(R.id.contact_list_frag);
        contactDetailFrag = (ContactDetailFrag) fragmentManager.findFragmentById(R.id.contact_detail_frag);
        viewContactDetailFrag = contactDetailFrag.getView();

        lvContact_list = contactListFrag.getView().findViewById(R.id.contact_list);
        contacts = new ArrayList<>();
        contacts.add(new Contact("Ali","","", "Male"));
        contacts.add(new Contact("Kashaf","","","Female"));
        contacts.add(new Contact("Kazmi","","", "Male"));
        contacts.add(new Contact("Waqas","","", "Male"));
        contacts.add(new Contact("Jahangir","","", "Male"));

        ContactAdapter contactAdapter = new ContactAdapter(this, R.layout.single_contact_design, contacts);
        lvContact_list.setAdapter(contactAdapter);


    }
}