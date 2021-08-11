package com.davewarnock.demoapp.contacts.recycler;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.davewarnock.demoapp.R;
import com.davewarnock.demoapp.contacts.AbstractContactsFragment;
import com.davewarnock.demoapp.contacts.ContactListElementViewModel;

import java.util.List;

public class ContactRecyclerFragment extends AbstractContactsFragment {

    public static final String FRAGMENT_TAG = "ContactRecyclerFragment";

    private RecyclerView contactsView;
    private ContactsRecyclerAdapter adapter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.contacts_fragment_recycler, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        contactsView = view.findViewById(R.id.contacts_recycler_view);
        contactsView.setLayoutManager(new LinearLayoutManager(view.getContext()));
        adapter = new ContactsRecyclerAdapter();
        adapter.getViewCount().observe(getViewLifecycleOwner(), this::setViewCount);
        contactsView.setAdapter(adapter);
    }

    @Override
    protected void bindData(List<ContactListElementViewModel> contactListElementViewModels) {
        adapter.setData(contactListElementViewModels);
    }
}
