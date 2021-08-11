package com.davewarnock.demoapp.contacts.recycler;

import android.view.View;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.davewarnock.demoapp.contacts.ContactListElementViewModel;

abstract class AbstractContactViewHolder extends RecyclerView.ViewHolder {

    AbstractContactViewHolder(@NonNull View itemView) {
        super(itemView);
    }

    abstract void bind(ContactListElementViewModel model);
}
