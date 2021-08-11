package com.davewarnock.demoapp.contacts.recycler;

import android.widget.TextView;

import androidx.annotation.NonNull;

import com.davewarnock.demoapp.contacts.ContactListElementViewModel;

class SectionHeaderViewHolder extends AbstractContactViewHolder {

    private TextView view;

    SectionHeaderViewHolder(@NonNull TextView itemView) {
        super(itemView);
        this.view = itemView;
    }

    @Override
    void bind(ContactListElementViewModel model) {
        this.view.setText(model.getHeader());
    }
}
