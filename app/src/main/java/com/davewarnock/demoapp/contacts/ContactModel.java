package com.davewarnock.demoapp.contacts;

import androidx.annotation.DrawableRes;

import java.util.Objects;

public class ContactModel {

    private final String id;
    @DrawableRes
    private final int avatar;
    private final String name;
    private final String phoneNumber;
    private final String email;

    public ContactModel(String id, @DrawableRes int avatar, String name, String phoneNumber,
                        String email) {
        this.id = id;
        this.avatar = avatar;
        this.name = name;
        this.phoneNumber = phoneNumber;
        this.email = email;
    }

    public String getId() {
        return id;
    }

    @DrawableRes
    public int getAvatar() {
        return avatar;
    }

    public String getName() {
        return name;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public String getEmail() {
        return email;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        ContactModel that = (ContactModel) o;
        return avatar == that.avatar && Objects.equals(id, that.id) && Objects.equals(name,
                                                                                      that.name)
               && Objects.equals(phoneNumber, that.phoneNumber) && Objects.equals(email,
                                                                                  that.email);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, avatar, name, phoneNumber, email);
    }
}
