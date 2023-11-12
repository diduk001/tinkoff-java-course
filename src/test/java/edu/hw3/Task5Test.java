package edu.hw3;

import edu.hw3.Task5.Contact;
import edu.hw3.Task5.ContactParser;
import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.ValueSource;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class Task5Test {
    private static final List<String> SAMPLE_1 =
        List.of("John Locke", "Thomas Aquinas", "David Hume", "Rene Descartes");
    private static final List<String> SAMPLE_2 = List.of("Paul Erdos", "Leonhard Euler", "Carl Gauss");
    private static final List<String> SAMPLE_3 = List.of();
    private static final List<String> SAMPLE_4 = null;

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
    void sampleTests(List<String> contactNames, String order) {
        List<Contact> result = ContactParser.parseContacts(contactNames, order);
        List<String> resultStrings = new ArrayList<>();
        result.forEach(contact -> resultStrings.add(contact.getContactName()));
        List<String> expected;
        if (contactNames == null || contactNames.isEmpty()) {
            expected = List.of();
        } else if (contactNames.equals(SAMPLE_1) && order.equals("ASC")) {
            expected = List.of("Thomas Aquinas", "Rene Descartes", "David Hume", "John Locke");
        } else if (contactNames.equals(SAMPLE_2) && order.equals("DESC")) {
            expected = List.of("Carl Gauss", "Leonhard Euler", "Paul Erdos");
        } else {
            expected = List.of();
        }
        assertThat(resultStrings).isEqualTo(expected);
    }

    @Test
    @DisplayName("Только имена (\"A\", \"B\", \"C\", \"D\")")
    void onlyNamesTest() {
        List<String> contactNames = new ArrayList<>();
        contactNames.add("B");
        contactNames.add("D");
        contactNames.add("C");
        contactNames.add("A");

        List<Contact> resultAsc = ContactParser.parseContacts(contactNames, "ASC");
        List<String> resultAscStrings = new ArrayList<>();
        resultAsc.forEach(contact -> resultAscStrings.add(contact.getContactName()));

        List<Contact> resultDesc = ContactParser.parseContacts(contactNames, "DESC");
        List<String> resultDescStrings = new ArrayList<>();
        resultDesc.forEach(contact -> resultDescStrings.add(contact.getContactName()));

        final List<String> expectedAsc = List.of("A", "B", "C", "D");
        final List<String> expectedDesc = List.of("D", "C", "B", "A");

        assertThat(resultAscStrings).isEqualTo(expectedAsc);
        assertThat(resultDescStrings).isEqualTo(expectedDesc);
    }

    @Test
    @DisplayName("Имена и фамилии (\"A A\", \"B\", \"C C\", \"D\"")
    void namesAndSurnamesTest() {
        List<String> contactNames = new ArrayList<>();
        contactNames.add("B");
        contactNames.add("D");
        contactNames.add("C C");
        contactNames.add("A A");

        List<Contact> resultAsc = ContactParser.parseContacts(contactNames, "ASC");
        List<String> resultAscStrings = new ArrayList<>();
        resultAsc.forEach(contact -> resultAscStrings.add(contact.getContactName()));

        List<Contact> resultDesc = ContactParser.parseContacts(contactNames, "DESC");
        List<String> resultDescStrings = new ArrayList<>();
        resultDesc.forEach(contact -> resultDescStrings.add(contact.getContactName()));

        final List<String> expectedAsc = List.of("B", "D", "A A", "C C");
        final List<String> expectedDesc = List.of("C C", "A A", "D", "B");

        assertThat(resultAscStrings).isEqualTo(expectedAsc);
        assertThat(resultDescStrings).isEqualTo(expectedDesc);
    }

    @ParameterizedTest(name = "Невалидное имя - \"{0}\"")
    @ValueSource(strings = {"", "a b c", "a   b ", "      ", "A    "})
    void invalidContactNamesTest(String contactName) {
        assertThrows(IllegalArgumentException.class, () -> new Contact(contactName));
    }

    @ParameterizedTest(name = "Валидное имя - \"{0}\"")
    @ValueSource(strings = {"A B", "!!!", "Ab Ba", "Aaaaa Bbbbb", "A"})
    void validContactNamesTest(String contactName) {
        assertDoesNotThrow(() -> new Contact(contactName));
    }
}

