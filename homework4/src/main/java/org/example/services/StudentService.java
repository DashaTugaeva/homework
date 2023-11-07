package org.example.services;

import org.example.loader.DataLoader;
import org.example.model.Person;
import org.example.util.MyArrayList;

import java.io.IOException;

public class StudentService {
    public MyArrayList<Person> persons = new MyArrayList<>(Person.class);

    public MyArrayList<Person> getPersons() {
        return persons;
    }
    public void setPersons(DataLoader dataLoader) throws IOException {
        this.persons = dataLoader.getPersons();
    }
}
