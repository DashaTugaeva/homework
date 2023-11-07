package org.example.command;

import org.example.DataGroup;
import org.example.MyArrayList;
import org.example.Person;

import java.util.Scanner;

public class CommandGetLastNameStudents implements Command{
    @Override
    public void execute(MyArrayList<Person> persons) {
        DataGroup dataGroup = new DataGroup();
        for (int i = 0; i < persons.size(); i++) {
            dataGroup.addPerson(persons.get(i), ((int)persons.get(i).getName().toUpperCase().charAt(0)), dataGroup.getSizeTable(), (a, b) -> a % b);
        }
        System.out.println("3) Поиск ученика по фамилии (фамилия ученика задается через консоль):");
        MyArrayList<Person> lastNameStudents = new MyArrayList<>(Person.class);
        Scanner scanner = new Scanner(System.in);
        System.out.println("Введите фамилию:");
        String firstlLetter = scanner.nextLine();
        MyArrayList<Person> persons1 = dataGroup.getPersons((int)firstlLetter.toUpperCase().charAt(0), dataGroup.getSizeTable(), (a, b) -> a % b);
        for (int i = 0; i < persons1.size(); i++) {
            String[] nameStudent = persons1.get(i).getName().toUpperCase().split(" ");
            if(nameStudent[0].equals(firstlLetter.toUpperCase())){
                lastNameStudents.add(persons.get(i), Person.class);
            }
        }
        if(lastNameStudents.size() > 0){
            for (int i = 0; i < lastNameStudents.size(); i++) {
                System.out.println(lastNameStudents.get(i));
            }
        }else{
            System.out.println("Учеников с фамилией " + firstlLetter + " нет");
        }
        System.out.println();
    }
}
