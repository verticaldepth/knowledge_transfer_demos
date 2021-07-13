package com.davewarnock.demoapp.groupchat;

import androidx.annotation.AnyThread;

import com.davewarnock.demoapp.R;

import java.util.List;

public class GroupChatController {

    private final GroupChatActivity view;
    private final GroupChatModel model;

    public GroupChatController(GroupChatActivity view) {
        this.view = view;
        this.model = new GroupChatModel();
        model.addListener(this::updateViewFromModel);
        updateViewFromModel();
        view.setSendButtonListener(src -> sendMessage());
    }

    private void sendMessage() {
        String message = view.getMessage();
        view.clearMessage();
        SimpleExecutor.queue(() -> {
            model.addMessage(
                    new GroupChatMessage(R.drawable.ic_baseline_sentiment_satisfied_alt_24, "You",
                                         message));
        });
    }

    @AnyThread
    private void updateViewFromModel() {
        SimpleExecutor.queue(() -> {
            final List<GroupChatMessage> messageList = model.getMessages();
            view.runOnUiThread(() -> view.setMessages(messageList));
        });
    }
}
