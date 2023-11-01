package org.example;

import org.example.models.AcademicRecord;
import org.example.models.Student;
import org.example.models.StudyGroup;
import org.example.models.StudyPlan;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

public class FileDataLoader {
    private String[] disciplines = {"физика", "математика", "русский", "литература", "геометрия", "информатика"};
    private String[] group = {"1", "2", "3", "4", "5", "6","7", "8", "9", "10", "11", "12"};
    DbWork dbWork = new DbWork();
    public void saveStudyPlan() {
        for (int i = 0; i < disciplines.length; i++) {
            StudyPlan studyPlan = new StudyPlan(getDisciplines()[i]);
            dbWork.addStudyPlan(studyPlan);
        }
    }
    public void saveStudyGroup() {
        for (int i = 0; i < group.length; i++) {
            StudyGroup studyGroup = new StudyGroup(getGroup()[i]);
            dbWork.addStudyGroup(studyGroup);
        }
    }
    public void saveData() throws IOException {
        saveStudyPlan();
        saveStudyGroup();
        BufferedReader reader = new BufferedReader(new FileReader("src/students.csv"));
        Scanner scanner = null;
        int index = 0;
        String line = reader.readLine();
        String firstName = null;
        String lastName = null;
        Integer age = 0;
        Long groupId = 0L;
        Long studentId = 1L;
        while ((line = reader.readLine()) != null) {
            scanner = new Scanner(line);
            scanner.useDelimiter(",");
            while (scanner.hasNext()) {
                String data = scanner.next();
                switch (index) {
                    case 0:
                        firstName = data;
                        break;
                    case 1:
                        lastName = data;
                        break;
                    case 2:
                        age = Integer.parseInt(data);
                        break;
                    case 3:
                        groupId = Long.parseLong(data);
                        Student student = new Student(firstName, lastName, age, groupId);
                        dbWork.addStudent(student);
                        break;
                    case 4:
                        AcademicRecord academicRecord = new AcademicRecord(Integer.parseInt(data), studentId, 1L);
                        dbWork.addAcademicRecord(academicRecord);
                        break;
                    case 5:
                        AcademicRecord academicRecord2 = new AcademicRecord(Integer.parseInt(data), studentId, 2L);
                        dbWork.addAcademicRecord(academicRecord2);
                        break;
                    case 6:
                        AcademicRecord academicRecord3 = new AcademicRecord(Integer.parseInt(data), studentId, 3L);
                        dbWork.addAcademicRecord(academicRecord3);
                        break;
                    case 7:
                        AcademicRecord academicRecord4 = new AcademicRecord(Integer.parseInt(data), studentId, 4L);
                        dbWork.addAcademicRecord(academicRecord4);
                        break;
                    case 8:
                        AcademicRecord academicRecord5 = new AcademicRecord(Integer.parseInt(data), studentId, 5L);
                        dbWork.addAcademicRecord(academicRecord5);
                        break;
                    case 9:
                        AcademicRecord academicRecord6 = new AcademicRecord(Integer.parseInt(data), studentId, 6L);
                        dbWork.addAcademicRecord(academicRecord6);
                        break;
                }
                index++;
            }

            index = 0;
            studentId ++;
        }
        reader.close();
    }

    public String[] getDisciplines() {
        return disciplines;
    }

    public String[] getGroup() {
        return group;
    }

}
