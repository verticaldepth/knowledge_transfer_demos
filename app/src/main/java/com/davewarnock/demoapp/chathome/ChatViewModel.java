package com.davewarnock.demoapp.chathome;

import androidx.annotation.DrawableRes;

import java.time.OffsetDateTime;

public class ChatViewModel {

    private final String chatId;

    @DrawableRes
    private final int avatar;
    private final String chatName;
    private final String lastMessage;
    private final OffsetDateTime lastMessageTime;
    private final boolean read;

    public ChatViewModel(String chatId, int avatar, String chatName, String lastMessage,
                         OffsetDateTime messageTime, boolean read) {
        this.chatId = chatId;
        this.avatar = avatar;
        this.chatName = chatName;
        this.lastMessage = lastMessage;
        this.lastMessageTime = messageTime;
        this.read = read;
    }

    public String getChatId() {
        return chatId;
    }

    @DrawableRes
    public int getAvatar() {
        return avatar;
    }

    public String getChatName() {
        return chatName;
    }

    public String getLastMessage() {
        return lastMessage;
    }

    public OffsetDateTime getLastMessageTime() {
        return lastMessageTime;
    }

    public boolean isRead() {
        return read;
    }
}
