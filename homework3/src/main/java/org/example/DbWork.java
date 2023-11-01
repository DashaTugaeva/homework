package org.example;

import org.example.DAO.*;
import org.example.models.*;

import java.util.Collection;
import java.util.Map;
import java.util.Optional;

public class DbWork {
    private static final DAO<Student, Long> STUDENT_DAO = new PostgreSqlDaoStudent();
    private static final DAO<StudyGroup, Long> STUDY_GROUP_DAO = new PostgreSqlDaoStudyGroup();
    private static final DAO<StudyPlan, Long> STUDY_PLAN_DAO = new PostgreSqlDaoStudyPlan();
    private static final DAO<AcademicRecord, Long> ACADEMIC_RECORD_DAO = new PostgreSqlDaoAcademicRecord();
    private static final PostgreSqlDaoStatistics statistics = new PostgreSqlDaoStatistics();


    public Optional<Long> addStudent(Student student) {
        return STUDENT_DAO.save(student);
    }
    public Optional<Long> addStudyGroup(StudyGroup studyGroup) {
        return STUDY_GROUP_DAO.save(studyGroup);
    }
    public Optional<Long> addStudyPlan(StudyPlan studyPlan) {
        return STUDY_PLAN_DAO.save(studyPlan);
    }
    public Optional<Long> addAcademicRecord(AcademicRecord academicRecord) {
        return ACADEMIC_RECORD_DAO.save(academicRecord);
    }

    public Collection<Student> getAllExcellentStudents() {
        return statistics.getAllExcellentStudents();
    }

    public Map getAverageAssessment(String group) {

        return statistics.getAverageAssessment(group);
    }
    public Collection<StudentWithGroupAndGrade> getAverageGradeByLastName(String lastName) {

        return statistics.getAverageGradeByLastName(lastName);
    }
    public Collection<Student> getAllStudents() {
        return STUDENT_DAO.getAll();
    }
    public void updateStudent(Student student) {
        STUDENT_DAO.update(student);
    }
    public void deleteStudent(Student student) {
        STUDENT_DAO.delete(student);
    }
    public Collection<StudyGroup> getAllStudyGroups() {
        return STUDY_GROUP_DAO.getAll();
    }
}
