package com.davewarnock.demoapp.contacts.recycler;

import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.recyclerview.widget.DiffUtil;
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
        ContactListElementViewModel item = data.get(position);
        if (item.isListHeaderPlaceholder()) {
            return 0;
        } else if (item.isHeader()) {
            return 1;
        } else if (item.isContact()) {
            return 2;
        }
        throw new IllegalStateException("Unknown type!");
    }

    @NonNull
    @Override
    public AbstractContactViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        viewCount.setValue(viewCount.getValue() + 1);
        switch (viewType) {
            case 0:
                return new HeaderViewHolder(inflateListHeaderView(parent));
            case 1:
                return new SectionHeaderViewHolder(inflateHeaderView(parent));
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

    private Button inflateListHeaderView(ViewGroup parent) {
        return (Button) LayoutInflater
                .from(parent.getContext())
                .inflate(R.layout.contact_list_header, parent, false);
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
        List<ContactListElementViewModel> tmpCopy = new ArrayList<>(newData);
        tmpCopy.add(0, new ContactListElementViewModel());
        PostDiffCallback callback = new PostDiffCallback(data, tmpCopy);
        DiffUtil.DiffResult diffResult = DiffUtil.calculateDiff(callback);
        viewCount.setValue(0);
        data.clear();
        data.addAll(tmpCopy);
        diffResult.dispatchUpdatesTo(this);
    }

    private static class PostDiffCallback extends DiffUtil.Callback {

        private final List<ContactListElementViewModel> oldData;
        private final List<ContactListElementViewModel> newData;

        public PostDiffCallback(List<ContactListElementViewModel> oldData,
                                List<ContactListElementViewModel> newData) {
            this.oldData = oldData;
            this.newData = newData;
        }

        @Override
        public int getOldListSize() {
            return oldData.size();
        }

        @Override
        public int getNewListSize() {
            return newData.size();
        }

        @Override
        public boolean areItemsTheSame(int oldItemPosition, int newItemPosition) {
            ContactListElementViewModel a = oldData.get(oldItemPosition);
            ContactListElementViewModel b = newData.get(newItemPosition);
            if (a.isListHeaderPlaceholder() && b.isListHeaderPlaceholder()) {
                return true;
            } else if (a.isHeader() && b.isHeader() && a.getHeader().equals(b.getHeader())) {
                return true;
            } else if (a.isContact() && b.isContact() && a
                    .getContactModel()
                    .getId()
                    .equals(b.getContactModel().getId())) {
                return true;
            }
            return false;
        }

        @Override
        public boolean areContentsTheSame(int oldItemPosition, int newItemPosition) {
            ContactListElementViewModel a = oldData.get(oldItemPosition);
            ContactListElementViewModel b = newData.get(newItemPosition);
            if (a.isListHeaderPlaceholder() || a.isHeader()) {
                return true;
            } else if (a.isContact()) {
                return a.equals(b);
            }
            return false;
        }
    }
}
