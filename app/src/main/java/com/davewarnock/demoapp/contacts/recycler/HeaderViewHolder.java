package com.davewarnock.demoapp.contacts.recycler;

import android.widget.TextView;

import androidx.annotation.NonNull;

import com.davewarnock.demoapp.contacts.ContactListElementViewModel;

class HeaderViewHolder extends AbstractContactViewHolder {

    private TextView view;

    HeaderViewHolder(@NonNull TextView itemView) {
        super(itemView);
        this.view = itemView;
    }

    @Override
    void bind(ContactListElementViewModel model) {
        this.view.setText(model.getHeader());
    }
}
