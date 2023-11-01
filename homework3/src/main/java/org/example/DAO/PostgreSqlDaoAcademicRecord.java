package org.example.DAO;

import org.example.models.AcademicRecord;
import org.example.services.JDBCService;

import java.sql.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Objects;
import java.util.Optional;
import java.util.logging.Level;
import java.util.logging.Logger;

public class PostgreSqlDaoAcademicRecord implements DAO<AcademicRecord, Long> {
    private static final Logger LOGGER = Logger.getLogger(PostgreSqlDaoAcademicRecord.class.getName());
    private final Optional<Connection> connection;

    public PostgreSqlDaoAcademicRecord() {
        this.connection = JDBCService.getConnection();
    }

    @Override
    public Optional<AcademicRecord> get(Long id) {
        return connection.flatMap(conn -> {
            Optional<AcademicRecord> academicRecord = Optional.empty();
            String sql = "SELECT * FROM academic_record WHERE id = " + id;

            try (Statement statement = conn.createStatement();
                 ResultSet resultSet = statement.executeQuery(sql)) {
                if (resultSet.next()) {
                    Integer assessment = resultSet.getInt("assessment");
                    Long studentId = resultSet.getLong("student_id");
                    Long studyPlanId = resultSet.getLong("study_plan_id");
                    academicRecord = Optional.of(new AcademicRecord(id, assessment, studentId, studyPlanId));
                }
            } catch (SQLException ex) {
                LOGGER.log(Level.SEVERE, null, ex);
            }
            return academicRecord;
        });
    }

    @Override
    public Collection<AcademicRecord> getAll() {
        Collection<AcademicRecord> academicRecords = new ArrayList<>();
        String sql = "SELECT * FROM academic_record";

        connection.ifPresent(conn -> {
            try (Statement statement = conn.createStatement();
                 ResultSet resultSet = statement.executeQuery(sql)) {
                while (resultSet.next()) {
                    Long id = resultSet.getLong("id");
                    Integer assessment = resultSet.getInt("assessment");
                    Long studentId = resultSet.getLong("student_id");
                    Long studyPlanId = resultSet.getLong("study_plan_id");
                    AcademicRecord academicRecord= new AcademicRecord(id, assessment, studentId, studyPlanId);
                    academicRecords.add(academicRecord);
                }

            } catch (SQLException ex) {
                LOGGER.log(Level.SEVERE, null, ex);
            }
        });

        return academicRecords;
    }

    @Override
    public Optional<Long> save(AcademicRecord academicRecord) {
        AcademicRecord nonNullAcademicRecord = Objects.requireNonNull(academicRecord);
        String sql = "INSERT INTO "
                + "academic_record(assessment, student_id, study_plan_id) "
                + "VALUES(?, ?, ?)";

        return connection.flatMap(conn -> {
            Optional<Long> generatedId = Optional.empty();

            try (PreparedStatement statement = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
                statement.setInt(1, nonNullAcademicRecord.getAssessment());
                statement.setLong(2, nonNullAcademicRecord.getStudentId());
                statement.setLong(3, nonNullAcademicRecord.getStudyPlanId());
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
    public void update(AcademicRecord academicRecord) {
        AcademicRecord nonNullAcademicRecord = Objects.requireNonNull(academicRecord);
        String sql = "UPDATE academic_record "
                + "SET "
                + "assessment = ?, "
                + "student_id = ?, "
                + "study_plan_id = ? "
                + "WHERE "
                + "id = ?";
        connection.ifPresent(conn -> {
            try (PreparedStatement statement = conn.prepareStatement(sql)) {
                statement.setInt(1, nonNullAcademicRecord.getAssessment());
                statement.setLong(2, nonNullAcademicRecord.getStudentId());
                statement.setLong(3, nonNullAcademicRecord.getStudyPlanId());
                statement.setLong(4, nonNullAcademicRecord.getId());
                statement.executeUpdate();
            } catch (SQLException ex) {
                LOGGER.log(Level.SEVERE, null, ex);
            }
        });
    }

    @Override
    public void delete(AcademicRecord academicRecord) {
        AcademicRecord nonNullAcademicRecord = Objects.requireNonNull(academicRecord);
        String sql = "DELETE FROM academic_record WHERE id = ?";
        connection.ifPresent(conn -> {
            try (PreparedStatement statement = conn.prepareStatement(sql)) {
                statement.setLong(1, nonNullAcademicRecord.getId());
                statement.executeUpdate();
            } catch (SQLException ex) {
                LOGGER.log(Level.SEVERE, null, ex);
            }
        });
    }
}
