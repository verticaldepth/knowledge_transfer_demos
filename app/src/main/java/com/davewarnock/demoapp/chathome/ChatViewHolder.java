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
        chatView.setAvatar(viewModel.getAvatar());
        chatView.setChatName(viewModel.getChatName());
        chatView.setChatMessage(viewModel.getLastMessage());
        chatView.setLastMessageDate(viewModel.getLastMessageTime());
        chatView.setHasBeenRead(viewModel.isRead());
    }
}
