package org.example.command;

import org.example.DbWork;
import org.example.models.Student;

import java.util.Collection;

public class CommandGetExcellentStudents implements Command{
    @Override
    public void execute() {
        System.out.println("Список всех отличников, старше 14 лет:");
        DbWork dbWork = new DbWork();
        Collection<Student> students = dbWork.getAllExcellentStudents();
        for (Student student : students) {
            System.out.println(student);
        }
        System.out.println();
    }
}
