package edu.hw3.Task5;

import org.jetbrains.annotations.NotNull;

public final class Contact implements Comparable<Contact> {
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

    @Override public int compareTo(@NotNull Contact o) {
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
