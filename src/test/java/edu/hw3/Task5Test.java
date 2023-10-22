package edu.hw3;

import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.ValueSource;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class Task5Test {
    final static private ArrayList<String> SAMPLE_1 =
        new ArrayList<>(List.of("John Locke", "Thomas Aquinas", "David Hume", "Rene Descartes"));
    final static private ArrayList<String> SAMPLE_2 =
        new ArrayList<>(List.of("Paul Erdos", "Leonhard Euler", "Carl Gauss"));
    final static private ArrayList<String> SAMPLE_3 = new ArrayList<>(List.of());
    final static private ArrayList<String> SAMPLE_4 = null;

    static Arguments[] sampleTests() {
        return new Arguments[] {
            Arguments.of(SAMPLE_1, "ASC"),
            Arguments.of(SAMPLE_2, "DESC"),
            Arguments.of(SAMPLE_3, "DESC"),
            Arguments.of(SAMPLE_4, "DESC"),

        };
    }

    @ParameterizedTest(name = "Тест из условия #{index} - {0}; {1}")
    @MethodSource("sampleTests")
    void sampleTests(ArrayList<String> contactNames, String order) {
        ArrayList<Task5.Contact> result = Task5.parseContacts(contactNames, order);
        ArrayList<String> resultStrings = new ArrayList<>();
        result.forEach(contact -> resultStrings.add(contact.getContactName()));
        ArrayList<String> expected = new ArrayList<>();
        if (contactNames == null || contactNames.isEmpty()) {
            expected = new ArrayList<>();
        } else if (contactNames.equals(SAMPLE_1) && order.equals("ASC")) {
            expected = new ArrayList<>(List.of("Thomas Aquinas", "Rene Descartes", "David Hume", "John Locke"));
        } else if (contactNames.equals(SAMPLE_2) && order.equals("DESC")) {
            expected = new ArrayList<>(List.of("Carl Gauss", "Leonhard Euler", "Paul Erdos"));
        }
        assertThat(resultStrings).isEqualTo(expected);
    }

    @Test
    @DisplayName("Только имена (\"A\", \"B\", \"C\", \"D\")")
    void onlyNamesTest() {
        ArrayList<String> contactNames = new ArrayList<>();
        contactNames.add("B");
        contactNames.add("D");
        contactNames.add("C");
        contactNames.add("A");

        ArrayList<Task5.Contact> resultAsc = Task5.parseContacts(contactNames, "ASC");
        ArrayList<String> resultAscStrings = new ArrayList<>();
        resultAsc.forEach(contact -> resultAscStrings.add(contact.getContactName()));

        ArrayList<Task5.Contact> resultDesc = Task5.parseContacts(contactNames, "DESC");
        ArrayList<String> resultDescStrings = new ArrayList<>();
        resultDesc.forEach(contact -> resultDescStrings.add(contact.getContactName()));

        final ArrayList<String> expectedAsc = new ArrayList<>(List.of("A", "B", "C", "D"));
        final ArrayList<String> expectedDesc = new ArrayList<>(List.of("D", "C", "B", "A"));

        assertThat(resultAscStrings).isEqualTo(expectedAsc);
        assertThat(resultDescStrings).isEqualTo(expectedDesc);
    }

    @Test
    @DisplayName("Имена и фамилии (\"A A\", \"B\", \"C C\", \"D\"")
    void namesAndSurnamesTest() {
        ArrayList<String> contactNames = new ArrayList<>();
        contactNames.add("B");
        contactNames.add("D");
        contactNames.add("C C");
        contactNames.add("A A");

        ArrayList<Task5.Contact> resultAsc = Task5.parseContacts(contactNames, "ASC");
        ArrayList<String> resultAscStrings = new ArrayList<>();
        resultAsc.forEach(contact -> resultAscStrings.add(contact.getContactName()));

        ArrayList<Task5.Contact> resultDesc = Task5.parseContacts(contactNames, "DESC");
        ArrayList<String> resultDescStrings = new ArrayList<>();
        resultDesc.forEach(contact -> resultDescStrings.add(contact.getContactName()));

        final ArrayList<String> expectedAsc = new ArrayList<>(List.of("B", "D", "A A", "C C"));
        final ArrayList<String> expectedDesc = new ArrayList<>(List.of("C C", "A A", "D", "B"));

        assertThat(resultAscStrings).isEqualTo(expectedAsc);
        assertThat(resultDescStrings).isEqualTo(expectedDesc);
    }

    @ParameterizedTest(name = "Невалидное имя - \"{0}\"")
    @ValueSource(strings = {"", "a b c", "a   b ", "      ", "A    "})
    void invalidContactNamesTest(String contactName) {
        boolean threwException = false;
        try {
            new Task5.Contact(contactName);
        } catch (Exception e) {
            threwException = true;
        }
        assertThat(threwException).isTrue();
    }

    @ParameterizedTest(name = "Валидное имя - \"{0}\"")
    @ValueSource(strings = {"A B", "!!!", "Ab Ba", "Aaaaa Bbbbb", "A"})
    void validContactNamesTest(String contactName) {
        boolean threwException = false;
        try {
            new Task5.Contact(contactName);
        } catch (Exception e) {
            threwException = true;
        }
        assertThat(threwException).isFalse();
    }
}

