package org.example.DAO;

import org.example.models.StudyGroup;
import org.example.services.JDBCService;

import java.sql.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Objects;
import java.util.Optional;
import java.util.logging.Level;
import java.util.logging.Logger;

public class PostgreSqlDaoStudyGroup implements DAO<StudyGroup, Long> {
    private static final Logger LOGGER = Logger.getLogger(PostgreSqlDaoStudyGroup.class.getName());
    private final Optional<Connection> connection;

    public PostgreSqlDaoStudyGroup() {
        this.connection = JDBCService.getConnection();
    }


    @Override
    public Optional<StudyGroup> get(Long id) {
        return connection.flatMap(conn -> {
            Optional<StudyGroup> studyGroup = Optional.empty();
            String sql = "SELECT * FROM study_group WHERE id = " + id;
            try (Statement statement = conn.createStatement();
                 ResultSet resultSet = statement.executeQuery(sql)) {
                if (resultSet.next()) {
                    String name = resultSet.getString("name");
                    studyGroup = Optional.of(new StudyGroup(id, name));
                }
            } catch (SQLException ex) {
                LOGGER.log(Level.SEVERE, null, ex);
            }
            return studyGroup;
        });
    }

    @Override
    public Collection<StudyGroup> getAll() {
        Collection<StudyGroup> studyGroups = new ArrayList<>();
        String sql = "SELECT * FROM study_group";
        connection.ifPresent(conn -> {
            try (Statement statement = conn.createStatement();
                 ResultSet resultSet = statement.executeQuery(sql)) {
                while (resultSet.next()) {
                    Long id = resultSet.getLong("id");
                    String name = resultSet.getString("name");
                    StudyGroup studyGroup = new StudyGroup(id, name);
                    studyGroups.add(studyGroup);
                }

            } catch (SQLException ex) {
                LOGGER.log(Level.SEVERE, null, ex);
            }
        });
        return studyGroups;
    }

    @Override
    public Optional<Long> save(StudyGroup studyGroup) {
        StudyGroup nonNullStudyGroup = Objects.requireNonNull(studyGroup);
        String sql = "INSERT INTO "
                + "study_group(name) "
                + "VALUES(?)";
        return connection.flatMap(conn -> {
            Optional<Long> generatedId = Optional.empty();

            try (PreparedStatement statement = conn.prepareStatement(sql,
                    Statement.RETURN_GENERATED_KEYS)) {

                statement.setString(1, nonNullStudyGroup.getName());
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
    public void update(StudyGroup studyGroup) {
        StudyGroup nonNullStudyGroup = Objects.requireNonNull(studyGroup);
        String sql = "UPDATE study_group "
                + "SET "
                + "name = ? "
                + "WHERE "
                + "id = ?";

        connection.ifPresent(conn -> {
            try (PreparedStatement statement = conn.prepareStatement(sql)) {
                statement.setString(1, nonNullStudyGroup.getName());
                statement.setLong(2, nonNullStudyGroup.getId());
                statement.executeUpdate();


            } catch (SQLException ex) {
                LOGGER.log(Level.SEVERE, null, ex);
            }
        });
    }

    @Override
    public void delete(StudyGroup studyGroup) {
        StudyGroup nonNullStudyGroup = Objects.requireNonNull(studyGroup);
        String sql = "DELETE FROM study_group WHERE id = ?";

        connection.ifPresent(conn -> {
            try (PreparedStatement statement = conn.prepareStatement(sql)) {
                statement.setLong(1, nonNullStudyGroup.getId());
                statement.executeUpdate();
            } catch (SQLException ex) {
                LOGGER.log(Level.SEVERE, null, ex);
            }
        });
    }
}
