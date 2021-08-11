package com.davewarnock.demoapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.davewarnock.demoapp.contacts.ContactsActivity;
import com.davewarnock.demoapp.groupchat.GroupChatActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_activity);
        setupContactsButton();
        setupGroupChatButton();
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        toolbar.setTitle(R.string.app_name);
    }

    private void setupContactsButton() {
        View view = findViewById(R.id.contacts_button);
        view.setOnClickListener(btn -> {
            startActivity(new Intent(this, ContactsActivity.class));
        });
    }

    private void setupGroupChatButton() {
        View view = findViewById(R.id.groupchat_button);
        view.setOnClickListener(btn -> {
            startActivity(new Intent(this, GroupChatActivity.class));
        });
    }
}
