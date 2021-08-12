package com.davewarnock.demoapp.contacts;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.ViewModelProvider;

import com.davewarnock.demoapp.R;
import com.davewarnock.demoapp.contacts.adapter.ContactAdapterFragment;
import com.davewarnock.demoapp.contacts.raw.ContactFragment;
import com.davewarnock.demoapp.contacts.recycler.ContactRecyclerFragment;
import com.google.android.material.tabs.TabLayout;

public class ContactsActivity extends AppCompatActivity {

    private TabLayout tabLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.contacts_activity);
        tabLayout = findViewById(R.id.contacts_tab_layout);
        tabLayout.addOnTabSelectedListener(new TabSelectionListener());
        Toolbar toolbar = findViewById(R.id.toolbar);
        toolbar.setTitle(R.string.contacts_activity);
        setSupportActionBar(toolbar);
        tabLayout.selectTab(null);
        tabLayout.selectTab(tabLayout.getTabAt(0));
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.contact_options, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (R.id.refresh == item.getItemId()) {
            ContactsActivityViewModel viewModel =
                    new ViewModelProvider(this).get(ContactsActivityViewModel.class);
            viewModel.refreshData();
            return true;
        } else if (R.id.regenerate == item.getItemId()) {
            ContactsActivityViewModel viewModel =
                    new ViewModelProvider(this).get(ContactsActivityViewModel.class);
            viewModel.regenerateData(this);
            return true;
        }
        return false;
    }

    private class TabSelectionListener implements TabLayout.OnTabSelectedListener {

        @Override
        public void onTabSelected(TabLayout.Tab tab) {
            if (tab != null) {
                // This is bad code, but all solutions are hacks
                // See https://stackoverflow.com/q/51537672
                setFragment(tab.getContentDescription().toString());
            }
        }

        @Override
        public void onTabUnselected(TabLayout.Tab tab) {
            // Nothing
        }

        @Override
        public void onTabReselected(TabLayout.Tab tab) {
            // Nothing
        }
    }

    /**
     * Sets the current fragment
     *
     * @param tag MockTag for the fragment.
     */
    private void setFragment(String tag) {
        FragmentManager fragmentManager = getSupportFragmentManager();
        Fragment fragment = fragmentManager.findFragmentByTag(tag);
        if (fragment == null) {
            switch (tag) {
                case ContactFragment.FRAGMENT_TAG:
                    fragment = new ContactFragment();
                    break;
                case ContactAdapterFragment.FRAGMENT_TAG:
                    fragment = new ContactAdapterFragment();
                    break;
                case ContactRecyclerFragment.FRAGMENT_TAG:
                    fragment = new ContactRecyclerFragment();
                    break;
                default:
                    throw new IllegalStateException("Unknown Fragment Tag");
            }
        }
        if (fragment.isAdded()) {
            fragmentManager.beginTransaction().remove(fragment).commit();
        }
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.fragment_holder, fragment, tag).commit();
    }
}
