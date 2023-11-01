package org.example.DAO;

import org.example.models.Student;
import org.example.models.StudentWithGroupAndGrade;
import org.example.services.JDBCService;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;

public class PostgreSqlDaoStatistics {
    private static final Logger LOGGER = Logger.getLogger(PostgreSqlDaoStatistics.class.getName());
    private final Optional<Connection> connection;

    public PostgreSqlDaoStatistics() {
        this.connection = JDBCService.getConnection();
    }

    public Collection<Student> getAllExcellentStudents() {
        Collection<Student> students = new ArrayList<>();
        String sql = "SELECT * FROM\n" +
                "(SELECT student_id, first_name, last_name, age,  COUNT(*) as count_assessment \n" +
                "FROM student JOIN academic_record ON student.id = student_id \n" +
                "WHERE age > 14 AND assessment = 5 GROUP BY student_id, first_name, last_name, age) \n" +
                "WHERE count_assessment = 6; ";

        connection.ifPresent(conn -> {
            try (Statement statement = conn.createStatement();
                 ResultSet resultSet = statement.executeQuery(sql)) {
                while (resultSet.next()) {
                    String firstName = resultSet.getString("first_name");
                    String lastName = resultSet.getString("last_name");
                    Integer age = resultSet.getInt("age");

                    Student student = new Student(firstName, lastName, age);
                    students.add(student);
                }

            } catch (SQLException ex) {
                LOGGER.log(Level.SEVERE, null, ex);
            }
        });

        return students;
    }

    public Map getAverageAssessment(String group) {
        Map<String, Double> averageAssessment = new HashMap<>();
        String sql = "SELECT study_plan.name, AVG(assessment) FROM study_group \n" +
                "JOIN student ON study_group.id = student.study_group_id\n" +
                "JOIN academic_record ON student.id = academic_record.student_id \n" +
                "JOIN study_plan ON study_plan.id = academic_record.study_plan_id \n" +
                "WHERE study_group.name = '" + group + "'\n" +
                "GROUP BY study_plan.name;";

        connection.ifPresent(conn -> {
            try (Statement statement = conn.createStatement();
                 ResultSet resultSet = statement.executeQuery(sql)) {
                while (resultSet.next()) {
                    String name = resultSet.getString("name");
                    Double grade = resultSet.getDouble("avg");
                    averageAssessment.put(name, grade);
                }

            } catch (SQLException ex) {
                LOGGER.log(Level.SEVERE, null, ex);
            }
        });

        return averageAssessment;
    }

    public Collection<StudentWithGroupAndGrade> getAverageGradeByLastName(String lastNames) {
        Collection<StudentWithGroupAndGrade> studentWithGroupAndGrades = new ArrayList<>();
        String sql = "SELECT name, first_name, last_name, age, AVG(assessment) FROM study_group JOIN student " +
                "ON study_group.id = student.study_group_id\n" +
                "JOIN academic_record ON student.id = academic_record.student_id \n" +
                "WHERE first_name LIKE '" + lastNames + "'\n" +
                "GROUP BY name, first_name, last_name, age;";

        connection.ifPresent(conn -> {
            try (Statement statement = conn.createStatement();
                 ResultSet resultSet = statement.executeQuery(sql)) {
                while (resultSet.next()) {
                    String firstName = resultSet.getString("first_name");
                    String lastName = resultSet.getString("last_name");
                    Integer age = resultSet.getInt("age");
                    String group = resultSet.getString("name");
                    Double averageGrade = resultSet.getDouble("avg");

                    StudentWithGroupAndGrade student = new StudentWithGroupAndGrade(firstName, lastName, age, group, averageGrade);
                    studentWithGroupAndGrades.add(student);
                }
            } catch (SQLException ex) {
                LOGGER.log(Level.SEVERE, null, ex);
            }
        });
        return studentWithGroupAndGrades;
    }
}
