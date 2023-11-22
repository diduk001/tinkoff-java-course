package edu.hw7.Task3_5;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class ParallelDatabase implements PersonDatabase {
    private final ReadWriteLock readWriteLock = new ReentrantReadWriteLock();
    private final Lock readLock = readWriteLock.readLock();
    private final Lock writeLock = readWriteLock.writeLock();
    private List<Person> persons = new ArrayList<>();

    public ParallelDatabase() {
    }

    public ParallelDatabase(List<Person> persons) {
        this.persons = new ArrayList<>(persons);
    }

    @Override public void add(Person person) {
        writeLock.lock();
        persons.add(person);
        writeLock.unlock();
    }

    @Override public synchronized void delete(int id) {
        writeLock.lock();
        persons.removeIf(person -> person.id() == id);
        writeLock.unlock();
    }

    @Override public List<Person> findByName(String name) {
        readLock.lock();
        List<Person> result = persons.stream().parallel().filter(person -> person.name().equals(name)).toList();
        readLock.unlock();
        return result;
    }

    @Override public List<Person> findByAddress(String address) {
        readLock.lock();
        List<Person> result = persons.stream().parallel().filter(person -> person.address().equals(address)).toList();
        readLock.unlock();
        return result;
    }

    @Override public List<Person> findByPhone(String phone) {
        readLock.lock();
        List<Person> result = persons.stream().parallel().filter(person -> person.phoneNumber().equals(phone)).toList();
        readLock.unlock();
        return result;
    }
}
