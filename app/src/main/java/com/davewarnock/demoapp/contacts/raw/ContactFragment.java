package com.davewarnock.demoapp.contacts.raw;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.LinearLayoutCompat;

import com.davewarnock.demoapp.R;
import com.davewarnock.demoapp.contacts.AbstractContactsFragment;
import com.davewarnock.demoapp.contacts.ContactListElementViewModel;
import com.davewarnock.demoapp.contacts.ContactModel;
import com.davewarnock.demoapp.contacts.ContactSummaryView;

import java.util.List;

public class ContactFragment extends AbstractContactsFragment {

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.contacts_fragment_raw, container);
    }

    @Override
    protected void bindData(List<ContactListElementViewModel> contactListElementViewModels) {
        int viewCount = 0;
        LinearLayoutCompat layout = getView().findViewById(R.id.contacts_list);
        layout.removeAllViews();
        LayoutInflater inflater = LayoutInflater.from(layout.getContext());
        for (ContactListElementViewModel entry : contactListElementViewModels) {
            if (entry.isHeader()) {
                createAndAddHeader(layout, inflater, entry.getHeader());
                viewCount++;
            } else {
                createAndAddContact(layout, inflater, entry.getContactModel());
                viewCount++;
            }
        }
        setViewCount(viewCount);
    }

    private void createAndAddHeader(LinearLayoutCompat parent, LayoutInflater inflater,
                                    String header) {
        TextView headerView =
                (TextView) inflater.inflate(R.layout.contact_list_group_header, parent, false);
        headerView.setText(header);
        parent.addView(headerView);
    }

    private void createAndAddContact(LinearLayoutCompat parent, LayoutInflater inflater,
                                     ContactModel contact) {
        ContactSummaryView contactSummaryView =
                (ContactSummaryView) inflater.inflate(R.layout.contact_list_contact, parent, false);
        contactSummaryView.setAvatar(contact.getAvatar());
        contactSummaryView.setName(contact.getName());
        contactSummaryView.setPhoneNumber(contact.getPhoneNumber());
        contactSummaryView.setEmailAddress(contact.getEmail());
        parent.addView(contactSummaryView);
    }
}
