package org.example.command;

import org.example.FileDataLoader;
import org.example.MyArrayList;
import org.example.Person;
import org.example.StudentService;

import java.io.IOException;
import java.util.Scanner;

public class CommandBuilder{
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
                commandGetAverageScore.execute(persons);
                getManual();
            case "2":
                CommandGetExcellentStudents commandGetExcellentStudents = new CommandGetExcellentStudents();
                commandGetExcellentStudents.execute(persons);
                getManual();
            case "3":
                CommandGetLastNameStudents commandGetLastNameStudents = new CommandGetLastNameStudents();
                commandGetLastNameStudents.execute(persons);
                getManual();
            case "4":
                System.exit(0);
            default:
                getManual();
        }
    }

}
