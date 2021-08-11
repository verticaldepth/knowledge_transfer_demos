package com.davewarnock.demoapp.contacts;

import java.util.Comparator;

public class ContactsListComparator implements Comparator<ContactListElementViewModel> {

    @Override
    public int compare(ContactListElementViewModel o1, ContactListElementViewModel o2) {
        if (!o1.isHeader() && !o2.isHeader()) {
            return o1.getContactModel().getName().compareTo(o2.getContactModel().getName());
        } else if (o1.isHeader() && o2.isHeader()) {
            return o1.getHeader().compareTo(o2.getHeader());
        } else if (o1.isHeader() && !o2.isHeader()) {
            int compareTo = o1.getHeader().compareTo(o2.getContactModel().getName());
            if (compareTo == 0) {
                return -1;
            }
            return compareTo;
        } else if (!o1.isHeader() && o2.isHeader()) {
            int compareTo = o1.getContactModel().getName().compareTo(o2.getHeader());
            if (compareTo == 0) {
                return 1;
            }
            return compareTo;
        }
        return 0;
    }
}
