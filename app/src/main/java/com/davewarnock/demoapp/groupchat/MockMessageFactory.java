package com.davewarnock.demoapp.groupchat;

import com.davewarnock.demoapp.R;
import com.github.javafaker.Faker;

public class MockMessageFactory {

    private static final Faker FAKER = new Faker();

    static GroupChatMessage create() {
        return new GroupChatMessage(R.drawable.ic_baseline_person_24, FAKER.lebowski().character(),
                                    FAKER.lebowski().quote());
    }
}
