package org.example.command;

import org.example.MyArrayList;
import org.example.Person;

public interface Command {
    void execute(MyArrayList<Person> persons);

}
