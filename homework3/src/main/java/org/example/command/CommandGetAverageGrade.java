package org.example.command;

import org.example.DbWork;

import java.util.Map;

public class CommandGetAverageGrade implements Command{
    @Override
    public void execute() {
        DbWork dbWork = new DbWork();
        System.out.println("Среднии оценки по предметам в 10 классе:");
        Map<String, Double> averageGrades = dbWork.getAverageAssessment("10");
        for (Map.Entry<String, Double> averageGrade : averageGrades.entrySet()) {
            System.out.println(averageGrade.getKey() + " - " + String.format("%.2f", averageGrade.getValue()));
        }
        System.out.println();
        System.out.println("Среднии оценки по предметам в 11 классе:");
        Map<String, Double> averageGrades1 = dbWork.getAverageAssessment("11");
        for (Map.Entry<String, Double> averageGrade : averageGrades1.entrySet()) {
            System.out.println(averageGrade.getKey() + " - " + String.format("%.2f", averageGrade.getValue()));
        }
        System.out.println();
    }
}
