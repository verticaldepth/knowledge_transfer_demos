package com.davewarnock.demoapp.groupchat;

import com.davewarnock.demoapp.R;

public class AddMessageTask implements Runnable {

    private final GroupChatModel model;
    private final String message;

    public AddMessageTask(GroupChatModel model, String message) {
        this.model = model;
        this.message = message;
    }

    @Override
    public void run() {
        model.addMessage(
                new GroupChatMessage(R.drawable.ic_baseline_sentiment_satisfied_alt_24, "You",
                                     message));
    }
}

