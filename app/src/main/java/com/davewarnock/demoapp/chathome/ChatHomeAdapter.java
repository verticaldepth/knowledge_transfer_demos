package com.davewarnock.demoapp.chathome;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.davewarnock.demoapp.R;

import java.util.ArrayList;
import java.util.List;

public class ChatHomeAdapter extends RecyclerView.Adapter<ChatViewHolder> {

    List<ChatViewModel> data = new ArrayList<>();

    @NonNull
    @Override
    public ChatViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ChatView chatView = (ChatView) LayoutInflater
                .from(parent.getContext())
                .inflate(R.layout.chathome_wrapper, parent, false);
        return new ChatViewHolder(chatView);
    }

    @Override
    public void onBindViewHolder(@NonNull ChatViewHolder holder, int position) {
        holder.bind(data.get(position));
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    void bind(List<ChatViewModel> newData) {
        data.clear();
        data.addAll(newData);
        this.notifyDataSetChanged();
    }
}
