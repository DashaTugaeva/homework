package org.example.command;

import org.example.loader.FileDataLoader;
import org.example.util.MyArrayList;
import org.example.model.Person;
import org.example.services.StudentService;

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
                Command command1 = new CommandGetAverageScore();
                command1.execute(persons);
                getManual();
            case "2":
                Command command2 = new CommandGetExcellentStudents();
                command2.execute(persons);
                getManual();
            case "3":
                Command command3 = new CommandGetLastNameStudents();
                command3.execute(persons);
                getManual();
            case "4":
                System.exit(0);
            default:
                getManual();
        }
    }

}
