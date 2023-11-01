package org.example.DAO;

import org.example.models.StudyPlan;
import org.example.services.JDBCService;

import java.sql.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Objects;
import java.util.Optional;
import java.util.logging.Level;
import java.util.logging.Logger;

public class PostgreSqlDaoStudyPlan implements DAO<StudyPlan, Long> {

    private static final Logger LOGGER = Logger.getLogger(PostgreSqlDaoStudyPlan.class.getName());
    private final Optional<Connection> connection;

    public PostgreSqlDaoStudyPlan() {
        this.connection = JDBCService.getConnection();
    }
    @Override
    public Optional<StudyPlan> get(Long id) {
        return connection.flatMap(conn -> {
            Optional<StudyPlan> studyPlan = Optional.empty();
            String sql = "SELECT * FROM study_plan WHERE id = " + id;
            try (Statement statement = conn.createStatement();
                 ResultSet resultSet = statement.executeQuery(sql)) {
                if (resultSet.next()) {
                    String name = resultSet.getString("name");
                    studyPlan = Optional.of(new StudyPlan(id, name));
                }
            } catch (SQLException ex) {
                LOGGER.log(Level.SEVERE, null, ex);
            }
            return studyPlan;
        });
    }

    @Override
    public Collection<StudyPlan> getAll() {
        Collection<StudyPlan> studyPlans = new ArrayList<>();
        String sql = "SELECT * FROM study_plan";
        connection.ifPresent(conn -> {
            try (Statement statement = conn.createStatement();
                 ResultSet resultSet = statement.executeQuery(sql)) {
                while (resultSet.next()) {
                    Long id = resultSet.getLong("id");
                    String name = resultSet.getString("name");
                    StudyPlan studyPlan = new StudyPlan(id, name);
                    studyPlans.add(studyPlan);
                }

            } catch (SQLException ex) {
                LOGGER.log(Level.SEVERE, null, ex);
            }
        });
        return studyPlans;
    }

    @Override
    public Optional<Long> save(StudyPlan studyPlan) {
        StudyPlan nonNullStudyPlan = Objects.requireNonNull(studyPlan);
        String sql = "INSERT INTO "
                + "study_plan(name) "
                + "VALUES(?)";
        return connection.flatMap(conn -> {
            Optional<Long> generatedId = Optional.empty();

            try (PreparedStatement statement = conn.prepareStatement(sql,
                    Statement.RETURN_GENERATED_KEYS)) {

                statement.setString(1, nonNullStudyPlan.getName());
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
    public void update(StudyPlan studyPlan) {
        StudyPlan nonNullStudyPlan = Objects.requireNonNull(studyPlan);
        String sql = "UPDATE study_plan "
                + "SET "
                + "name = ? "
                + "WHERE "
                + "id = ?";

        connection.ifPresent(conn -> {
            try (PreparedStatement statement = conn.prepareStatement(sql)) {
                statement.setString(1, nonNullStudyPlan.getName());
                statement.setLong(2, nonNullStudyPlan.getId());
                statement.executeUpdate();


            } catch (SQLException ex) {
                LOGGER.log(Level.SEVERE, null, ex);
            }
        });
    }

    @Override
    public void delete(StudyPlan studyPlan) {
        StudyPlan nonNullStudyPlan = Objects.requireNonNull(studyPlan);
        String sql = "DELETE FROM study_plan WHERE id = ?";

        connection.ifPresent(conn -> {
            try (PreparedStatement statement = conn.prepareStatement(sql)) {
                statement.setLong(1, nonNullStudyPlan.getId());
                statement.executeUpdate();
            } catch (SQLException ex) {
                LOGGER.log(Level.SEVERE, null, ex);
            }
        });
    }
}
