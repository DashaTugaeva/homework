package org.example.loader;

import org.example.model.Person;
import org.example.util.MyArrayList;

import java.io.IOException;

public interface DataLoader {

    MyArrayList<Person> getPersons() throws IOException;
}
