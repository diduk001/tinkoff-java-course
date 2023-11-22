package edu.hw7;

import edu.hw7.Task3_5.ParallelDatabase;
import edu.hw7.Task3_5.Person;
import edu.hw7.Task3_5.PersonDatabase;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

public class Task35Test {
    @Test
    @DisplayName("Тест добавления в БД")
    void addTest() {
        final PersonDatabase db = new ParallelDatabase();
        final Person person = new Person(1, "Иван", "Москва", "88005553535");

        db.add(person);

        assertThat(db.findByName("Иван")).hasSize(1).contains(person);
        assertThat(db.findByAddress("Москва")).hasSize(1).contains(person);
        assertThat(db.findByPhone("88005553535")).hasSize(1).contains(person);
    }

    @Test
    @DisplayName("Тест добавления и удаления из БД")
    void addThanRemoveTest() {
        final PersonDatabase db = new ParallelDatabase();
        final Person person = new Person(1, "Иван", "Москва", "88005553535");

        db.add(person);
        db.delete(1);

        assertThat(db.findByName("Иван")).hasSize(0);
        assertThat(db.findByAddress("Москва")).hasSize(0);
        assertThat(db.findByPhone("88005553535")).hasSize(0);
    }

    @Test
    @DisplayName("Тест нескольких записей в БД")
    void severalRecordsTest() {
        final Person person1 = new Person(1, "Иван", "Москва", "88005553535");
        final Person person2 = new Person(2, "Петр", "Москва", "88005553536");
        final Person person3 = new Person(3, "Иван", "Санкт-Петербург", "88005553537");
        PersonDatabase db = new ParallelDatabase(List.of(person1, person2, person3));

        assertThat(db.findByName("Иван"))
            .hasSize(2)
            .contains(person1)
            .contains(person3);
        assertThat(db.findByAddress("Москва"))
            .hasSize(2)
            .contains(person1)
            .contains(person2);
        assertThat(db.findByPhone("88005553535"))
            .hasSize(1)
            .contains(person1);
        assertThat(db.findByPhone("88005553536"))
            .hasSize(1)
            .contains(person2);
        assertThat(db.findByPhone("88005553537"))
            .hasSize(1)
            .contains(person3);
    }

    @Test
    @DisplayName("Тест нескольких записей в БД с удалением")
    void severalRecordsWithDeleteTest() {
        final Person person1 = new Person(1, "Иван", "Москва", "88005553535");
        final Person person2 = new Person(2, "Петр", "Москва", "88005553536");
        final Person person3 = new Person(3, "Иван", "Санкт-Петербург", "88005553537");
        PersonDatabase db = new ParallelDatabase(List.of(person1, person2, person3));

        assertThat(db.findByName("Иван"))
            .hasSize(2)
            .contains(person1)
            .contains(person3);

        db.delete(1);

        assertThat(db.findByName("Иван")).hasSize(1).contains(person3);
    }
}
