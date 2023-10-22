package org.example;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;



public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader("src/students.csv"));
        Person[] persons = new Person[(Files.readAllLines(Paths.get("src/students.csv")).size())-1];
        Scanner scanner = null;
        int index = 0;
        String line = reader.readLine();
        int count = 0;
        while ((line = reader.readLine()) != null) {
            Person person = new Person();
            scanner = new Scanner(line);
            scanner.useDelimiter(",");
            int sumRating =0;
            String name = "";
            while (scanner.hasNext()) {
                String data = scanner.next();
                switch (index){
                    case 0 -> name += data + " ";
                    case 1 -> name += data;
                    case 2 -> person.setAge(Integer.parseInt(data));
                    case 3 -> person.setGroup(Integer.parseInt(data));
                    default -> sumRating += Integer.parseInt(data);
                }
                index++;
            }
            person.setName(name);
            person.setAverageRating(sumRating / 6.0);
            index = 0;
            persons[count] = person;
            count++;
        }
        reader.close();


        ClassroomDataGroups classroomDataGroups = new ClassroomDataGroups();
        PersonAgeDataGroups personAgeDataGroups = new PersonAgeDataGroups();
        PersonNameDataGroup personNameDataGroup = new PersonNameDataGroup();
        for (Person person : persons) {
            classroomDataGroups.addPerson(person);
            personAgeDataGroups.addPerson(person);
            personNameDataGroup.addPerson(person);
        }


        System.out.println("1) Вычисление средней оценки в старших классах (10 и 11):");
        System.out.println("Средний балл учеников 10 класса: " + String.format("%.2f", getAverageScore(classroomDataGroups, 10)));
        System.out.println("Средний балл учеников 11 класса: " + String.format("%.2f", getAverageScore(classroomDataGroups, 11)));
        System.out.println();
        System.out.println("2) Поиск всех отличников, старше 14 лет:");
        MyArrayList<Person> excellentStudents = getExcellentStudents(personAgeDataGroups);
        for (int i = 0; i < excellentStudents.size(); i++) {
            System.out.println(excellentStudents.get(i));
        }
        System.out.println();
        System.out.println("3) Поиск ученика по фамилии (фамилия ученика задается через консоль):");
        MyArrayList<Person> lastNameStudents = getLastNameStudents(personNameDataGroup);
        for (int i = 0; i < lastNameStudents.size(); i++) {
            System.out.println(lastNameStudents.get(i));
        }
    }

    /* Сложность метода getAverageScore будет составлять О(1) */
    public static double getAverageScore(ClassroomDataGroups classroomDataGroups, int group){
        double averageScore = 0.0;
        int countStudents = 0;
        MyArrayList<Person> persons = classroomDataGroups.getPersons(group);
        for (int i = 0; i < persons.size(); i++) {
            averageScore += persons.get(i).getAverageRating();
            countStudents++;
        }
        return averageScore / countStudents;
    }

    /* Сложность метода getExcellentStudents при получении списка Person для конкретного возраста составляет О(1),
       далее при поиски средней оценки учеников из списка person сложность составляет O(n), так как нужно пройтись по всему списку */
    public static MyArrayList<Person> getExcellentStudents(PersonAgeDataGroups personAgeDataGroups) {
        MyArrayList<Person> excellentStudents = new MyArrayList<>();
        for (int i = 14; i <= 17; i++) {
            MyArrayList<Person> persons = personAgeDataGroups.getPersons(i);
            for (int j = 0; j < persons.size(); j++) {
                if (persons.get(j).getAverageRating() == 5) {
                    excellentStudents.add(persons.get(j));
                }
            }
        }
        return excellentStudents;
    }

    public static MyArrayList<Person> getLastNameStudents(PersonNameDataGroup personNameDataGroup){
        MyArrayList<Person> lastNameStudents = new MyArrayList<>();
        Scanner scanner = new Scanner(System.in);
        System.out.println("Введите фамилию:");
        String firstlLetter = scanner.nextLine();
        MyArrayList<Person> persons = personNameDataGroup.getPersons(firstlLetter);
        for (int i = 0; i < persons.size(); i++) {
            String[] nameStudent = persons.get(i).getName().toUpperCase().split(" ");
            if(nameStudent[0].equals(firstlLetter.toUpperCase())){
                lastNameStudents.add(persons.get(i));
            }
        }
        return lastNameStudents;
    }


}