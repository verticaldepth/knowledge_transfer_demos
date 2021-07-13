package com.davewarnock.demoapp.groupchat;

import androidx.annotation.DrawableRes;

import java.util.Date;

public class GroupChatMessage {

    @DrawableRes
    private final int avatar;
    private final String sender;
    private final String message;
    private final Date timestamp;

    public GroupChatMessage(int avatar, String sender, String message) {
        this.avatar = avatar;
        this.sender = sender;
        this.message = message;
        this.timestamp = new Date();
    }

    public int getAvatar() {
        return avatar;
    }

    public String getSender() {
        return sender;
    }

    public String getMessage() {
        return message;
    }

    public Date getTimestamp() {
        return timestamp;
    }
}
