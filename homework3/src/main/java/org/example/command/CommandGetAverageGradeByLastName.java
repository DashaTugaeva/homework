package org.example.command;

import org.example.DbWork;
import org.example.models.StudentWithGroupAndGrade;

import java.util.Collection;
import java.util.Scanner;

public class CommandGetAverageGradeByLastName implements Command{
    @Override
    public void execute() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Введите фамилию:");
        String firstlLetter = scanner.nextLine();
        System.out.println("Cредняя оценка учеников по фамилии: " + firstlLetter);
        DbWork dbWork = new DbWork();
        Collection<StudentWithGroupAndGrade> student = dbWork.getAverageGradeByLastName(firstlLetter);
        for (StudentWithGroupAndGrade studentWithGroupAndGrade : student) {
            System.out.println(studentWithGroupAndGrade);
        }
        System.out.println();
    }
}
