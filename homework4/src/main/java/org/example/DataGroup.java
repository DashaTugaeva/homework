package org.example;

import org.example.model.Person;
import org.example.util.Entry;
import org.example.util.MyArrayList;

public class DataGroup {
    Entry<Integer, MyArrayList<Person>>[] table = new Entry[50];
    public void addPerson(Person person, int a, int size, GroupCriterion<Integer, Integer> function) {
        int index = function.getKey(a, size);
        if (table[index] == null) {
            Entry entry = new Entry(a, new MyArrayList<Person>(Person.class));
            table[index] = entry;
        }
        table[index].getValue().add(person, Person.class);
    }

    public MyArrayList<Person> getPersons(int a, int size, GroupCriterion<Integer, Integer> function) {
        int index = function.getKey(a, size);
        return table[index] != null ? table[index].getValue() : null;
    }

    public int getSizeTable() {
        return table.length;
    }
}
