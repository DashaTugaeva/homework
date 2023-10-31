package org.example.command;
import org.example.*;
import java.io.IOException;
import java.util.Scanner;

public class CommandBuilder {
    private MyArrayList<Person> persons;
    public void getPersons() throws IOException {
        StudentService studentService = new StudentService();
        studentService.setPersons(new FileDataLoader());
        persons = studentService.getPersons();
    }

    public void getManual() {
        System.out.println("Введите номер команды:");
        System.out.println("1 - для вычисления средней оценки в старших классах (10 и 11)");
        System.out.println("2 - для поиска всех отличников, старше 14 лет");
        System.out.println("3 - для поиска ученика по фамилии");
        System.out.println("4 - для выхода из программы");
        Scanner scanner = new Scanner(System.in);
        returnCommand(scanner.nextLine());
    }

    public void returnCommand(String command) {
        switch (command) {
            case "1":
                CommandGetAverageScore commandGetAverageScore = new CommandGetAverageScore();
                commandGetAverageScore.execute(getClassroomDataGroups());
                getManual();
            case "2":
                CommandGetExcellentStudents commandGetExcellentStudents = new CommandGetExcellentStudents();
                commandGetExcellentStudents.execute(personAgeDataGroups());
                getManual();
            case "3":
                CommandGetLastNameStudents commandGetLastNameStudents = new CommandGetLastNameStudents();
                commandGetLastNameStudents.execute(personNameDataGroup());
                getManual();
            case "4":
                System.exit(0);
            default:
                getManual();
        }
    }

    public DataGroup getClassroomDataGroups()  {
        DataGroup classroomDataGroups = new DataGroup();
        for (int i = 0; i < persons.size(); i++) {
            classroomDataGroups.addPerson(persons.get(i), persons.get(i).getGroup(), classroomDataGroups.getSizeTable(), (a, b) -> a % b);
        }
        return classroomDataGroups;
    }
    public DataGroup personAgeDataGroups()  {
        DataGroup personAgeDataGroups = new DataGroup();
        for (int i = 0; i < persons.size(); i++) {
            personAgeDataGroups.addPerson(persons.get(i), persons.get(i).getAge(), personAgeDataGroups.getSizeTable(), (a, b) -> a % b);
        }
        return personAgeDataGroups;
    }
    public DataGroup personNameDataGroup()  {
        DataGroup personNameDataGroup = new DataGroup();
        for (int i = 0; i < persons.size(); i++) {
            personNameDataGroup.addPerson(persons.get(i), ((int)persons.get(i).getName().toUpperCase().charAt(0)), personNameDataGroup.getSizeTable(), (a, b) -> a % b);
        }
        return personNameDataGroup;
    }

}
