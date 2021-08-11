package com.davewarnock.demoapp.contacts;

import java.util.Objects;

public class ContactListElementViewModel {

    private final String header;
    private final ContactModel contactModel;

    public ContactListElementViewModel(String header) {
        this.header = header;
        contactModel = null;
    }

    public ContactListElementViewModel(ContactModel contactModel) {
        this.header = null;
        this.contactModel = contactModel;
    }

    public boolean isHeader() {
        return header != null;
    }

    public String getHeader() {
        return header;
    }

    public ContactModel getContactModel() {
        return contactModel;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        ContactListElementViewModel that = (ContactListElementViewModel) o;
        return Objects.equals(header, that.header) && Objects.equals(contactModel,
                                                                     that.contactModel);
    }

    @Override
    public int hashCode() {
        return Objects.hash(header, contactModel);
    }
}
