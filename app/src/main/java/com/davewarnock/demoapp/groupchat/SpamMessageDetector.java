package com.davewarnock.demoapp.groupchat;

import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class SpamMessageDetector {

    private final long rateLimit;
    private final Collection<String> forbiddenTerms;
    private final Map<String, GroupChatMessage> lastMessages;

    /**
     * @param rateLimit -1 is no rate limit
     * @param forbiddenWords null for no forbidden terms
     */
    public SpamMessageDetector(long rateLimit, Collection<String> forbiddenWords) {
        this.rateLimit = rateLimit;
        this.forbiddenTerms = forbiddenWords == null ? Collections.emptySet() : forbiddenWords;
        this.lastMessages = new HashMap<>();
    }

    public void logMessageSent(GroupChatMessage message) {
        lastMessages.put(message.getSender(), message);
    }

    public Collection<String> getForbiddenTerms() {
        return forbiddenTerms;
    }

    public boolean isBelowRateLimit(GroupChatMessage message) {
        if (rateLimit > 0 && lastMessages.containsKey(message.getSender())) {
            GroupChatMessage lastMessage = lastMessages.get(message.getSender());
            long timeElapsed =
                    message.getTimestamp().getTime() - lastMessage.getTimestamp().getTime();
            return timeElapsed > rateLimit;
        }
        return true;
    }

    public boolean isPermittedMessage(GroupChatMessage message) {
        for (String forbiddenTerm : forbiddenTerms) {
            if (message.getMessage().contains(forbiddenTerm)) {
                return false;
            }
        }
        return true;
    }
}
