package com.davewarnock.demoapp.contacts;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.davewarnock.demoapp.R;

public class ContactsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.contact_activity);
        if (savedInstanceState == null) {
            getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.container, ContactFragment.newInstance())
                    .commitNow();
        }
    }
}
