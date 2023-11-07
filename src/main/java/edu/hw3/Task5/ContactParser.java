package edu.hw3.Task5;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public final class ContactParser {
    private static final String DESC_ORDER = "DESC";
    private static final String ASC_ORDER = "ASC";

    private ContactParser() {
        throw new UnsupportedOperationException("This is a utility class");
    }

    public static List<Contact> parseContacts(final List<String> contactNames, final String order) {
        if (contactNames == null || contactNames.isEmpty()) {
            return List.of();
        } else if (!order.equals(ASC_ORDER) && !order.equals(DESC_ORDER)) {
            throw new IllegalArgumentException("order must be \"ASC\" or \"DESC\"");
        }

        ArrayList<Contact> contacts = new ArrayList<>();
        contactNames.forEach((contactName) -> contacts.add(new Contact(contactName)));
        Collections.sort(contacts);
        if (order.equals(DESC_ORDER)) {
            Collections.reverse(contacts);
        }
        return contacts;
    }

}
