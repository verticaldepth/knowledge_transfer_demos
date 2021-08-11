package com.davewarnock.demoapp.contacts.adapter;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.davewarnock.demoapp.R;
import com.davewarnock.demoapp.contacts.AbstractContactsFragment;
import com.davewarnock.demoapp.contacts.ContactListElementViewModel;

import java.util.List;

public class ContactAdapterFragment extends AbstractContactsFragment {

    public static final String FRAGMENT_TAG = "ContactAdapterFragment";

    private ListView contactsView;
    private ContactListAdapter adapter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.contacts_fragment_adapter, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        contactsView = view.findViewById(R.id.contacts_list);
        adapter = new ContactListAdapter(requireContext());
        contactsView.setAdapter(adapter);
        adapter.getViewCount().observe(getViewLifecycleOwner(), this::setViewCount);
        contactsView.addHeaderView(inflateListHeaderView(contactsView));
    }

    @Override
    protected void bindData(List<ContactListElementViewModel> contactListElementViewModels) {
        adapter.clear();
        adapter.addAll(contactListElementViewModels);
        adapter.notifyDataSetChanged();
    }

    private Button inflateListHeaderView(ViewGroup parent) {
        return (Button) LayoutInflater
                .from(parent.getContext())
                .inflate(R.layout.contact_list_header, parent, false);
    }
}
