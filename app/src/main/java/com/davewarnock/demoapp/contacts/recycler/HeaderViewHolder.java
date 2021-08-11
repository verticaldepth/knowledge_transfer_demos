package com.davewarnock.demoapp.contacts.recycler;

import android.widget.Button;

import androidx.annotation.NonNull;

import com.davewarnock.demoapp.contacts.ContactListElementViewModel;

public class HeaderViewHolder extends AbstractContactViewHolder {

    public HeaderViewHolder(@NonNull Button itemView) {
        super(itemView);
    }

    @Override
    void bind(ContactListElementViewModel model) {
        // Do nothing, nothing to bind.
    }
}
