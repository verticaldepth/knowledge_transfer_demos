package com.davewarnock.demoapp.contacts.recycler;

import androidx.annotation.NonNull;

import com.davewarnock.demoapp.contacts.ContactListElementViewModel;
import com.davewarnock.demoapp.contacts.ContactModel;
import com.davewarnock.demoapp.contacts.ContactSummaryView;

class ContactViewHolder extends AbstractContactViewHolder {

    private final ContactSummaryView view;

    ContactViewHolder(@NonNull ContactSummaryView view) {
        super(view);
        this.view = view;
    }

    @Override
    void bind(ContactListElementViewModel viewModel) {
        ContactModel model = viewModel.getContactModel();
        view.setAvatar(model.getAvatar());
        view.setName(model.getName());
        view.setPhoneNumber(model.getPhoneNumber());
        view.setEmailAddress(model.getEmail());
    }
}
