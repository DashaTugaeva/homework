package org.example;

import java.io.IOException;

public interface DataLoader {

    MyArrayList<Person> getPersons() throws IOException;
}
