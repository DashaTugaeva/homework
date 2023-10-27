package org.example;

import java.io.IOException;

public class StudentService {
    private MyArrayList<Person> persons = new MyArrayList<>(Person.class);

    public MyArrayList<Person> getPersons() {
        return persons;
    }
    public void setPersons(DataLoader dataLoader) throws IOException {
        this.persons = dataLoader.getPersons();
    }
}
