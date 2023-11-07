package org.example.command;

import org.example.util.MyArrayList;
import org.example.model.Person;

public interface Command {
    void execute(MyArrayList<Person> persons);

}
