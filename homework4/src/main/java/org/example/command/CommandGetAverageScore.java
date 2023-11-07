package org.example.command;

import org.example.DataGroup;
import org.example.util.MyArrayList;
import org.example.model.Person;

public class CommandGetAverageScore implements Command{

    @Override
    public void execute(MyArrayList<Person> persons) {
        DataGroup dataGroup = new DataGroup();
        for (int i = 0; i < persons.size(); i++) {
            dataGroup.addPerson(persons.get(i), persons.get(i).getGroup(), dataGroup.getSizeTable(), (a, b) -> a % b);
        }
        System.out.println("1) Вычисление средней оценки в старших классах (10 и 11):");
        double averageScore = 0.0;
        int countStudents = 0;
        int group = 10;
        MyArrayList<Person> persons1 = dataGroup.getPersons(group, dataGroup.getSizeTable(), (a, b) -> a % b);
        for (int i = 0; i < persons1.size(); i++) {
            averageScore += persons1.get(i).getAverageScore();
            countStudents++;
        }
        System.out.println("Средний балл учеников " + group + " класса: " + String.format("%.2f", averageScore / countStudents));
        averageScore = 0.0;
        countStudents = 0;
        group = 11;
        MyArrayList<Person> persons2 = dataGroup.getPersons(group, dataGroup.getSizeTable(), (a, b) -> a % b);
        for (int i = 0; i < persons2.size(); i++) {
            averageScore += persons2.get(i).getAverageScore();
            countStudents++;
        }
        System.out.println("Средний балл учеников " + group + " класса: " + String.format("%.2f", averageScore / countStudents));
        System.out.println();

    }
}
