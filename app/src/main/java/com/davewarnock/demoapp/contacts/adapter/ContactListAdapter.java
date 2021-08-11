package com.davewarnock.demoapp.contacts.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.davewarnock.demoapp.R;
import com.davewarnock.demoapp.contacts.ContactListElementViewModel;
import com.davewarnock.demoapp.contacts.ContactModel;
import com.davewarnock.demoapp.contacts.ContactSummaryView;

public class ContactListAdapter extends ArrayAdapter<ContactListElementViewModel> {

    private final MutableLiveData<Integer> viewCount = new MutableLiveData<>();

    public ContactListAdapter(@NonNull Context context) {
        super(context, 0);
        viewCount.setValue(0);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        ContactListElementViewModel model = getItem(position);
        if (model.isHeader()) {
            return getHeaderView(parent, convertView, model.getHeader());
        } else {
            return getContactView(parent, convertView, model.getContactModel());
        }
    }

    @Override
    public void notifyDataSetChanged() {
        super.notifyDataSetChanged();
        viewCount.setValue(0);
    }

    public LiveData<Integer> getViewCount() {
        return viewCount;
    }

    private View getHeaderView(ViewGroup parent, @Nullable View convertView, String header) {
        if (!(convertView instanceof TextView)) {
            convertView = LayoutInflater
                    .from(parent.getContext())
                    .inflate(R.layout.contact_list_group_header, parent, false);
            viewCount.setValue(viewCount.getValue() + 1);
        }
        TextView headerView = (TextView) convertView;
        headerView.setText(header);
        return headerView;
    }

    private View getContactView(ViewGroup parent, @Nullable View convertView,
                                ContactModel contact) {
        ContactSummaryView contactSummaryView;
        if (convertView instanceof ContactSummaryView) {
            contactSummaryView = (ContactSummaryView) convertView;
        } else {
            contactSummaryView = (ContactSummaryView) LayoutInflater
                    .from(parent.getContext())
                    .inflate(R.layout.contact_list_contact, parent, false);
            viewCount.setValue(viewCount.getValue() + 1);
        }
        contactSummaryView.setAvatar(contact.getAvatar());
        contactSummaryView.setName(contact.getName());
        contactSummaryView.setPhoneNumber(contact.getPhoneNumber());
        contactSummaryView.setEmailAddress(contact.getEmail());
        return contactSummaryView;
    }
}
