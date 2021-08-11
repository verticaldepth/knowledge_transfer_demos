package com.davewarnock.demoapp.contacts;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.viewpager2.adapter.FragmentStateAdapter;
import androidx.viewpager2.widget.ViewPager2;

import com.davewarnock.demoapp.R;
import com.davewarnock.demoapp.contacts.adapter.ContactAdapterFragment;
import com.davewarnock.demoapp.contacts.raw.ContactFragment;
import com.davewarnock.demoapp.contacts.recycler.ContactRecyclerFragment;
import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;

public class ContactsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.contacts_activity);
        ViewPager2 viewPager = findViewById(R.id.pager);
        ContactsPagerAdapter pagerAdapter = new ContactsPagerAdapter(this);
        viewPager.setAdapter(pagerAdapter);
        TabLayout tabLayout = findViewById(R.id.contacts_tab_layout);
        TabLayoutMediator mediator =
                new TabLayoutMediator(tabLayout, viewPager, new ContactsTabConfigurationStrategy());
        mediator.attach();
        Toolbar toolbar = findViewById(R.id.toolbar);
        toolbar.setTitle(R.string.contacts_activity);
        setSupportActionBar(toolbar);
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

    /**
     * A simple pager adaptor for the 3 fragments.
     */
    private static class ContactsPagerAdapter extends FragmentStateAdapter {

        public ContactsPagerAdapter(@NonNull FragmentActivity fragmentActivity) {
            super(fragmentActivity);
        }

        @Override
        public Fragment createFragment(int position) {
            switch (position) {
                case 0:
                    return new ContactFragment();

                case 1:
                    return new ContactAdapterFragment();

                case 2:
                    return new ContactRecyclerFragment();
            }
            return null;
        }

        @Override
        public int getItemCount() {
            return 3;
        }
    }

    /**
     * Basic implementation of tab config strategy that we only need because it's required to
     * implement one.
     */
    private static class ContactsTabConfigurationStrategy
            implements TabLayoutMediator.TabConfigurationStrategy {

        @Override
        public void onConfigureTab(@NonNull TabLayout.Tab tab, int position) {
            switch (position) {
                case 0:
                    tab.setText(R.string.contacts_raw_label);
                    break;

                case 1:
                    tab.setText(R.string.contacts_adapter_label);
                    break;

                case 2:
                    tab.setText(R.string.contacts_recycler_label);
                    break;
            }
        }
    }
}
