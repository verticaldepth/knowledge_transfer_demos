package com.davewarnock.demoapp.groupchat;

import androidx.annotation.WorkerThread;

import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

public class GroupChatModel {

    private final List<GroupChatMessage> messages = new ArrayList<>();
    private final Set<GroupChatListener> listeners = new LinkedHashSet<>();

    public GroupChatModel() {
        createSeedData();
    }

    @WorkerThread
    public synchronized void addMessage(GroupChatMessage message) {
        addMessageInternal(message);
        createResponseMessages();
    }

    @WorkerThread
    public synchronized List<GroupChatMessage> getMessages() {
        // Pretend we're doing database or networking work
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return new ArrayList<>(messages);
    }

    public synchronized void addListener(GroupChatListener listener) {
        listeners.add(listener);
    }

    private synchronized void addMessageInternal(GroupChatMessage message) {
        this.messages.add(message);
        for (GroupChatListener listener : listeners) {
            listener.onGroupChatMessagesChanged();
        }
    }

    /**
     * Called only on boot.
     */
    private void createSeedData() {
        for (int i = 0; i < 3; i++) {
            addMessageInternal(MockMessageFactory.create());
        }
    }

    /**
     * Called in response to a message.
     */
    private void createResponseMessages() {
        for (int i = 0; i < 2; i++) {
            SimpleExecutor.queue(() -> addMessageInternal(MockMessageFactory.create()), 1500 * i);
        }
    }
}
