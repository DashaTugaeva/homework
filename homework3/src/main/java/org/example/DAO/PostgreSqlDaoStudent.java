package org.example.DAO;

import org.example.models.Student;
import org.example.services.JDBCService;


import java.sql.*;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Objects;
import java.util.Optional;
import java.util.logging.Level;
import java.util.logging.Logger;

public class PostgreSqlDaoStudent implements DAO<Student, Long> {

    private static final Logger LOGGER = Logger.getLogger(PostgreSqlDaoStudent.class.getName());
    private final Optional<Connection> connection;

    public PostgreSqlDaoStudent() {
        this.connection = JDBCService.getConnection();
    }
    @Override
    public Optional<Student> get(Long id) {
        return connection.flatMap(conn -> {
            Optional<Student> student = Optional.empty();
            String sql = "SELECT * FROM student WHERE id = " + id;

            try (Statement statement = conn.createStatement();
                 ResultSet resultSet = statement.executeQuery(sql)) {

                if (resultSet.next()) {
                    String firstName = resultSet.getString("first_name");
                    String lastName = resultSet.getString("last_name");
                    Integer age = resultSet.getInt("age");
                    Long studyGroupId = resultSet.getLong("study_group_id");
                    student = Optional.of(new Student(id, firstName, lastName, age, studyGroupId));
                }
            } catch (SQLException ex) {
                LOGGER.log(Level.SEVERE, null, ex);
            }
            return student;
        });
    }

    @Override
    public Collection<Student> getAll() {
        Collection<Student> students = new ArrayList<>();
        String sql = "SELECT * FROM student";

        connection.ifPresent(conn -> {
            try (Statement statement = conn.createStatement();
                 ResultSet resultSet = statement.executeQuery(sql)) {
                while (resultSet.next()) {
                    Long id = resultSet.getLong("id");
                    String firstName = resultSet.getString("first_name");
                    String lastName = resultSet.getString("last_name");
                    Integer age = resultSet.getInt("age");
                    Long studyGroupId = resultSet.getLong("study_group_id");

                    Student student = new Student(id, firstName, lastName, age, studyGroupId);
                    students.add(student);
                }

            } catch (SQLException ex) {
                LOGGER.log(Level.SEVERE, null, ex);
            }
        });

        return students;
    }

    @Override
    public Optional<Long> save(Student student) {
        Student nonNullStudent = Objects.requireNonNull(student);
        String sql = "INSERT INTO "
                + "student(first_name, last_name, age, study_group_id) "
                + "VALUES(?, ?, ?, ?)";

        return connection.flatMap(conn -> {
            Optional<Long> generatedId = Optional.empty();

            try (PreparedStatement statement = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

                statement.setString(1, nonNullStudent.getFirstName());
                statement.setString(2, nonNullStudent.getLastName());
                statement.setInt(3, nonNullStudent.getAge());
                statement.setLong(4, nonNullStudent.getStudyGroupId());

                int numberOfInsertedRows = statement.executeUpdate();

                if (numberOfInsertedRows > 0) {
                    try (ResultSet resultSet = statement.getGeneratedKeys()) {
                        if (resultSet.next()) {
                            generatedId = Optional.of(resultSet.getLong(1));
                        }
                    }
                }
            } catch (SQLException ex) {
                LOGGER.log(Level.SEVERE, null, ex);
            }
            return generatedId;
        });
    }

    @Override
    public void update(Student student) {
        Student nonNullStudent = Objects.requireNonNull(student);
        String sql = "UPDATE student "
                + "SET "
                + "first_name = ?, "
                + "last_name = ?, "
                + "age = ?, "
                + "study_group_id = ? "
                + "WHERE "
                + "id = ?";

        connection.ifPresent(conn -> {
            try (PreparedStatement statement = conn.prepareStatement(sql)) {
                statement.setString(1, nonNullStudent.getFirstName());
                statement.setString(2, nonNullStudent.getLastName());
                statement.setInt(3, nonNullStudent.getAge());
                statement.setLong(4, nonNullStudent.getStudyGroupId());
                statement.setLong(5, nonNullStudent.getId());
                statement.executeUpdate();
            } catch (SQLException ex) {
                LOGGER.log(Level.SEVERE, null, ex);
            }
        });
    }

    @Override
    public void delete(Student student) {
        Student nonNullStudent = Objects.requireNonNull(student);
        String sql = "DELETE FROM student WHERE id = ?";

        connection.ifPresent(conn -> {
            try (PreparedStatement statement = conn.prepareStatement(sql)) {
                statement.setLong(1, nonNullStudent.getId());
                statement.executeUpdate();
            } catch (SQLException ex) {
                LOGGER.log(Level.SEVERE, null, ex);
            }
        });
    }
}
