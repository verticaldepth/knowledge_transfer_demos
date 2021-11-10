package com.davewarnock.demoapp.chathome;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class ChatViewHolder extends RecyclerView.ViewHolder {

    private final ChatView chatView;

    public ChatViewHolder(@NonNull ChatView chatView) {
        super(chatView);
        this.chatView = chatView;
    }

    void bind(ChatViewModel viewModel) {
        chatView.setData(viewModel);
    }
}
