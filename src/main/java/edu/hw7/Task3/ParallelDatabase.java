package edu.hw7.Task3;

import java.util.ArrayList;
import java.util.List;

public class ParallelDatabase implements PersonDatabase {
    private List<Person> persons = new ArrayList<>();

    public ParallelDatabase() {
    }

    public ParallelDatabase(List<Person> persons) {
        this.persons = new ArrayList<>(persons);
    }

    @Override
    public synchronized void add(Person person) {
        persons.add(person);
    }

    @Override
    public synchronized void delete(int id) {
        persons.removeIf(person -> person.id() == id);
    }

    @Override
    public List<Person> findByName(String name) {
        return persons.stream().parallel().filter(person -> person.name().equals(name)).toList();
    }

    @Override
    public List<Person> findByAddress(String address) {
        return persons.stream().parallel().filter(person -> person.address().equals(address)).toList();
    }

    @Override
    public List<Person> findByPhone(String phone) {
        return persons.stream().parallel().filter(person -> person.phoneNumber().equals(phone)).toList();
    }
}
