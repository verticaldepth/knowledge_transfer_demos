package com.davewarnock.demoapp.groupchat;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import com.google.common.truth.Truth;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * A bad example of a unit test.
 */
@RunWith(JUnit4.class)
public class SpamMessageDetectorTest {

    private static final long RATE_LIMIT = 50;
    private static final String[] FORBIDDEN_TERMS = {"uwu", "Kenobi"};

    private SpamMessageDetector spamDetectorNotConfigured;
    private SpamMessageDetector spamDetectorNormal;
    private GroupChatMessage testMessage1UserA;
    private GroupChatMessage testMessage2UserA;
    private GroupChatMessage testMessage3UserB;

    @Before
    public void setUp() throws Exception {
        spamDetectorNotConfigured = new SpamMessageDetector(-1, null);
        spamDetectorNormal = new SpamMessageDetector(RATE_LIMIT, Arrays.asList(FORBIDDEN_TERMS));
        testMessage1UserA = new GroupChatMessage(0, "Kenobi", "Hello");
        testMessage2UserA = new GroupChatMessage(0, "Kenobi", "There");
        testMessage3UserB = new GroupChatMessage(0, "Grevious", "General Kenobi!");
    }

    @Test
    public void logMessageSent() {
        SpamMessageDetector spamDetectorNotConfigured = new SpamMessageDetector(-1, null);
        GroupChatMessage testMessage = new GroupChatMessage(0, "Kenobi", "Hello");
        spamDetectorNotConfigured.logMessageSent(testMessage);
        Truth
                .assertThat(spamDetectorNotConfigured.getLastMessages())
                .containsEntry(testMessage.getSender(), testMessage);
    }

    @Test
    public void getForbiddenTerms() {
        assertNotNull(spamDetectorNotConfigured.getForbiddenTerms());
        assertEquals(spamDetectorNotConfigured.getForbiddenTerms().size(), 0);
        assertNotNull(spamDetectorNormal.getForbiddenTerms());
        assertEquals(spamDetectorNormal.getForbiddenTerms().size(), FORBIDDEN_TERMS.length);
        for (String forbiddenTerm : FORBIDDEN_TERMS) {
            assertTrue(spamDetectorNormal.getForbiddenTerms().contains(forbiddenTerm));
        }
    }

    @Test
    public void isOutsideRateLimit() {
        for (SpamMessageDetector detector : new SpamMessageDetector[]{
                spamDetectorNormal, spamDetectorNotConfigured
        }) {
            detector.logMessageSent(testMessage1UserA);
            detector.logMessageSent(testMessage3UserB);
        }
        assertFalse(spamDetectorNormal.isBelowRateLimit(testMessage2UserA));
        assertTrue(spamDetectorNotConfigured.isBelowRateLimit(testMessage2UserA));
        assertFalse(spamDetectorNormal.isBelowRateLimit(testMessage3UserB));
        assertTrue(spamDetectorNotConfigured.isBelowRateLimit(testMessage3UserB));
    }

    @Test
    public void testMessageAcceptedWhenNoForbiddenTerms() {
        SpamMessageDetector spamDetectorNotConfigured = new SpamMessageDetector(-1, null);
        GroupChatMessage testMessage = new GroupChatMessage(0, "Kenobi", "Hello");
        assertTrue(spamDetectorNotConfigured.isPermittedMessage(testMessage));
        Truth.assertThat(spamDetectorNotConfigured.isPermittedMessage(testMessage)).isTrue();
    }

    @Test
    public void testMessageWithForbiddenTermIsRejected() {
        List<String> forbiddenTerms = new ArrayList<>();
        forbiddenTerms.add("ForbiddenTerm");
        SpamMessageDetector spamDetector = new SpamMessageDetector(-1, forbiddenTerms);
        GroupChatMessage testMessage = new GroupChatMessage(0, "Kenobi", "I am a ForbiddenTerm");
        assertFalse(spamDetector.isPermittedMessage(testMessage));
    }

    @Test
    public void testMessageWithNoForbiddenTermsIsAccepted() {
        List<String> forbiddenTerms = new ArrayList<>();
        forbiddenTerms.add("ForbiddenTerm");
        SpamMessageDetector spamDetector = new SpamMessageDetector(-1, forbiddenTerms);
        GroupChatMessage testMessage = new GroupChatMessage(0, "Kenobi", "I am not Forbidden");
        assertTrue(spamDetector.isPermittedMessage(testMessage));
    }
}
