package org.example.command;

import org.example.DataGroup;
import org.example.MyArrayList;
import org.example.Person;

public class CommandGetExcellentStudents implements Command{
    @Override
    public void execute(DataGroup dataGroup) {
        System.out.println("2) Поиск всех отличников, старше 14 лет:");
        MyArrayList<Person> excellentStudents = new MyArrayList<>(Person.class);
        for (int i = 14; i <= 17; i++) {
            MyArrayList<Person> persons = dataGroup.getPersons(i, dataGroup.getSizeTable(), (a, b) -> a % b);
            for (int j = 0; j < persons.size(); j++) {
                if (persons.get(j).getAverageRating() == 5) {
                    excellentStudents.add(persons.get(j), Person.class);
                }
            }
        }
        for (int i = 0; i < excellentStudents.size(); i++) {
            System.out.println(excellentStudents.get(i));
        }
        System.out.println();
    }
}
