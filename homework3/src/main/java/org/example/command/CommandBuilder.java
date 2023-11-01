package org.example.command;

import java.util.Scanner;

public class CommandBuilder {
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
                Command command1 = new CommandGetAverageGrade();
                command1.execute();
                getManual();
            case "2":
                Command command2 = new CommandGetExcellentStudents();
                command2.execute();
                getManual();
            case "3":
                Command command3 = new CommandGetAverageGradeByLastName();
                command3.execute();
                getManual();
            case "4":
                System.exit(0);
            default:
                getManual();
        }
    }
}
