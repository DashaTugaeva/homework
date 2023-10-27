package org.example;

import java.util.Scanner;

public class CommandBuilder implements Command{
    @Override
    public void getAverageScore(DataGroup dataGroup, int group) {

        double averageScore = 0.0;
        int countStudents = 0;
        MyArrayList<Person> persons = dataGroup.getPersons(group, dataGroup.getSizeTable(), (a, b) -> a % b);
        for (int i = 0; i < persons.size(); i++) {
            averageScore += persons.get(i).getAverageRating();
            countStudents++;
        }
        System.out.println("Средний балл учеников " + group + " класса: " + String.format("%.2f", averageScore / countStudents));
    }
    @Override
    public void getExcellentStudents(DataGroup dataGroup) {

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
        println(excellentStudents);
    }
    @Override
    public void getLastNameStudents(DataGroup dataGroup) {
        System.out.println("3) Поиск ученика по фамилии (фамилия ученика задается через консоль):");
        MyArrayList<Person> lastNameStudents = new MyArrayList<>(Person.class);
        Scanner scanner = new Scanner(System.in);
        System.out.println("Введите фамилию:");
        String firstlLetter = scanner.nextLine();
        MyArrayList<Person> persons = dataGroup.getPersons((int)firstlLetter.toUpperCase().charAt(0), dataGroup.getSizeTable(), (a, b) -> a % b);
        for (int i = 0; i < persons.size(); i++) {
            String[] nameStudent = persons.get(i).getName().toUpperCase().split(" ");
            if(nameStudent[0].equals(firstlLetter.toUpperCase())){
                lastNameStudents.add(persons.get(i), Person.class);
            }
        }
        println(lastNameStudents);

    }

    public void println(MyArrayList<Person> persons){
        for (int i = 0; i < persons.size(); i++) {
            System.out.println(persons.get(i));
        }
        System.out.println();
    }

    public void printTitle(){
        System.out.println("1) Вычисление средней оценки в старших классах (10 и 11):");
    }
}
