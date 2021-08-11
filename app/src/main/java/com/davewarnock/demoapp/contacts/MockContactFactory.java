package com.davewarnock.demoapp.contacts;

import android.content.Context;
import android.util.Log;

import androidx.annotation.DrawableRes;

import com.davewarnock.demoapp.R;
import com.github.javafaker.Faker;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Locale;
import java.util.UUID;

public class MockContactFactory {

    private static final Faker FAKER = new Faker();
    private static final List<Integer> UNUSED_AVATAR_IDS = new ArrayList<>();
    private static final List<Integer> ALL_AVATAR_IDS = new ArrayList<>();

    static ContactModel create() {
        return create(null);
    }

    static ContactModel create(Context context) {

        @DrawableRes
        int avatar = R.drawable.ic_baseline_person_24;
        if (context != null) {
            avatar = getAvatar(context);
        }
        String id = UUID.randomUUID().toString();
        String name = FAKER.name().fullName();
        String phone = FAKER.phoneNumber().cellPhone();
        String email = FAKER.internet().emailAddress();
        return new ContactModel(id, avatar, name, phone, email);
    }

    static Collection<ContactModel> create(int count) {
        return create(count, null);
    }

    static Collection<ContactModel> create(int count, Context context) {
        List<ContactModel> contactModelList = new ArrayList<>();
        for (int i = 0; i < count; i++) {
            contactModelList.add(create(context));
        }
        return contactModelList;
    }

    @DrawableRes
    static int getAvatar(Context context) {
        if (UNUSED_AVATAR_IDS.isEmpty()) {
            reseedAvatarIds(context);
        }
        return UNUSED_AVATAR_IDS.remove(0);
    }

    private static void reseedAvatarIds(Context context) {
        if (ALL_AVATAR_IDS.isEmpty()) {
            loadAvatarIds(context);
        }
        UNUSED_AVATAR_IDS.clear();
        UNUSED_AVATAR_IDS.addAll(ALL_AVATAR_IDS);
        Collections.shuffle(UNUSED_AVATAR_IDS);
    }

    static void loadAvatarIds(Context context) {
        ALL_AVATAR_IDS.clear();
        for (int i = 1; i <= 20; i++) {
            String id = String.format(Locale.getDefault(), "cat_%02d", i);
            int resourceId =
                    context.getResources().getIdentifier(id, "drawable", context.getPackageName());
            if (resourceId == 0) {
                Log.e(MockContactFactory.class.getSimpleName(), "Failed to load resource: " + id);
            } else {
                ALL_AVATAR_IDS.add(resourceId);
            }
        }
    }
}
