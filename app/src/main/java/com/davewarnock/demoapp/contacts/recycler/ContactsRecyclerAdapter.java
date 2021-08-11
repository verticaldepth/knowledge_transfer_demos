package com.davewarnock.demoapp.contacts.recycler;

import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.recyclerview.widget.RecyclerView;

import com.davewarnock.demoapp.R;
import com.davewarnock.demoapp.contacts.ContactListElementViewModel;
import com.davewarnock.demoapp.contacts.ContactSummaryView;

import java.util.ArrayList;
import java.util.List;

public class ContactsRecyclerAdapter extends RecyclerView.Adapter<AbstractContactViewHolder> {

    private final MutableLiveData<Integer> viewCount = new MutableLiveData<>();
    private final List<ContactListElementViewModel> data = new ArrayList<>();

    public ContactsRecyclerAdapter() {
        viewCount.setValue(0);
    }

    @Override
    public int getItemViewType(int position) {
        return data.get(position).isHeader() ? 1 : 2;
    }

    @NonNull
    @Override
    public AbstractContactViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        viewCount.setValue(viewCount.getValue() + 1);
        switch (viewType) {
            case 1:
                return new HeaderViewHolder(inflateHeaderView(parent));
            case 2:
                return new ContactViewHolder(inflateContentsSummaryView(parent));
        }
        throw new IllegalStateException("Unknown type!");
    }

    @Override
    public void onBindViewHolder(@NonNull AbstractContactViewHolder holder, int position) {
        holder.bind(data.get(position));
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    private TextView inflateHeaderView(ViewGroup parent) {
        return (TextView) LayoutInflater
                .from(parent.getContext())
                .inflate(R.layout.contact_list_group_header, parent, false);
    }

    private ContactSummaryView inflateContentsSummaryView(ViewGroup parent) {
        return (ContactSummaryView) LayoutInflater
                .from(parent.getContext())
                .inflate(R.layout.contact_list_contact, parent, false);
    }

    public LiveData<Integer> getViewCount() {
        return viewCount;
    }

    public void setData(List<ContactListElementViewModel> newData) {
        viewCount.setValue(0);
        data.clear();
        data.addAll(newData);
        notifyDataSetChanged();
    }
}
