package edu.hw3;

import java.util.ArrayList;
import java.util.Collections;
import org.jetbrains.annotations.NotNull;

public final class Task5 {
    final private static String DESC_ORDER = "DESC";
    final private static String ASC_ORDER = "ASC";

    private Task5() {
        throw new UnsupportedOperationException("This is a utility class");
    }

    static public ArrayList<Contact> parseContacts(final ArrayList<String> contactNames, final String order) {
        if (contactNames == null || contactNames.isEmpty()) {
            return new ArrayList<>();
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

    final static public class Contact implements Comparable<Contact> {
        private final String name;
        private String surname = "";

        public Contact(String contactName) {
            if (contactName.isBlank()) {
                throw new IllegalArgumentException("contactName can't be empty");
            }

            if (!contactName.contains(" ")) {
                this.name = contactName;
            } else {
                String[] splitContact = contactName.split(" ");
                if (splitContact.length != 2) {
                    throw new IllegalArgumentException("contactName can't contain more than one space symbol");
                }
                this.name = splitContact[0];
                this.surname = splitContact[1];
                if (this.name.isBlank() || this.surname.isBlank()) {
                    throw new IllegalArgumentException("Name and Surname can't be empty");
                }
            }
        }

        @Override
        public int compareTo(@NotNull Contact o) {
            if (this.surname.isEmpty() && o.getSurname().isEmpty()) {
                return this.name.compareTo(o.getName());
            } else if (this.surname.isEmpty()) {
                return -1;
            } else if (o.getSurname().isEmpty()) {
                return 1;
            }
            return this.surname.compareTo(o.getSurname());
        }

        public String getName() {
            return name;
        }

        public String getSurname() {
            return surname;
        }

        public String getContactName() {
            if (surname.isBlank()) {
                return name;
            }
            return name + " " + surname;
        }
    }
}
