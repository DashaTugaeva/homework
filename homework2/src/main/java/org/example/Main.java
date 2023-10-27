package org.example;
import java.io.*;

public class Main {
    public static void main(String[] args) throws IOException {
        StudentService studentService = new StudentService();
        studentService.setPersons(new FileDataLoader());
        MyArrayList<Person> persons = studentService.getPersons();

        DataGroup classroomDataGroups = new DataGroup();
        DataGroup personAgeDataGroups = new DataGroup();
        DataGroup personNameDataGroup = new DataGroup();
        for (int i = 0; i < persons.size(); i++) {
            classroomDataGroups.addPerson(persons.get(i), persons.get(i).getGroup(), classroomDataGroups.getSizeTable(), (a, b) -> a % b);
            personAgeDataGroups.addPerson(persons.get(i), persons.get(i).getAge(), classroomDataGroups.getSizeTable(), (a, b) -> a % b);
            personNameDataGroup.addPerson(persons.get(i), (persons.get(i).getName().toUpperCase().charAt(0)), classroomDataGroups.getSizeTable(), (a, b) -> a % b);
        }

        CommandBuilder commandBuilder = new CommandBuilder();
        commandBuilder.printTitle();
        commandBuilder.getAverageScore(classroomDataGroups, 10);
        commandBuilder.getAverageScore(classroomDataGroups, 11);
        System.out.println();
        commandBuilder.getExcellentStudents(personAgeDataGroups);
        commandBuilder.getLastNameStudents(personNameDataGroup);
    }
}