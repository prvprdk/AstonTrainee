package org.example.service;

import org.example.db.ConnectionDB;
import org.example.entity.Direction;
import org.example.entity.LearnClass;
import org.example.servlets.payload.DirectionUpdate;

import java.sql.*;
import java.util.HashSet;
import java.util.Set;

public class DefaultDirectionService implements DirectionService {

    private final static String SQL_FIND_ALL = "SELECT * FROM direction";
    private final static String SQL_ADD_DIRECTION = "INSERT INTO direction (name) VALUES (?)";
    private final static String SQL_DELETE_BY_ID = "DELETE FROM direction WHERE id = ?";
    private final static String SQL_FIND_BY_ID = "SELECT * FROM direction WHERE id = ?  ";
    private final static String SQL_UPDATE_DIRECTION = "UPDATE direction SET name = ? WHERE ID = ?";
    private final static String SQL_FIND_DIRECTION_CLASSES = "SELECT * FROM class WHERE id_direction = ?";

    @Override
    public Set<Direction> findAll() {
        ConnectionDB connectionDB = new ConnectionDB();
        Set<Direction> set = new HashSet<>();
        try (Connection connection = connectionDB.getConnection()) {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(SQL_FIND_ALL);
            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String name = resultSet.getString("name");
                set.add(new Direction(id, name));
            }
        } catch (ClassNotFoundException | SQLException e) {
            throw new RuntimeException(e);
        }

        return set;
    }

    @Override
    public void add(Direction direction) {

        ConnectionDB connectionDB = new ConnectionDB();

        try (Connection connection = connectionDB.getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement(SQL_ADD_DIRECTION);
            preparedStatement.setString(1, direction.getName());
            preparedStatement.execute();

        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Direction findById(int id) {

        ConnectionDB connectionDB = new ConnectionDB();
        return statementDirection(connectionDB, id);


    }

    @Override
    public void deleteById(int id) {
        ConnectionDB connectionDB = new ConnectionDB();
        try (Connection connection = connectionDB.getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement(SQL_DELETE_BY_ID);
            preparedStatement.setInt(1, id);
            preparedStatement.execute();
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }

    }

    @Override
    public void update(DirectionUpdate update) {
        ConnectionDB connectionDB = new ConnectionDB();

        try (Connection connection = connectionDB.getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement(SQL_UPDATE_DIRECTION);
            preparedStatement.setString(1, update.name());
            preparedStatement.setInt(2, update.id());

        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }

    }


    private Direction statementDirection(ConnectionDB connectionDB, int id) {

        Direction direction = null;

        try (Connection connection = connectionDB.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SQL_FIND_BY_ID);) {

            preparedStatement.setInt(1, id);
            ResultSet resultSetDirection = preparedStatement.executeQuery();

            if (resultSetDirection.next()) {
                Direction directionFromDb = new Direction();
                directionFromDb.setId(resultSetDirection.getInt("id"));
                directionFromDb.setName(resultSetDirection.getString("name"));
                directionFromDb.setClasses(statementSetClasses(connectionDB, id));
                return directionFromDb;
            }
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }

        return null;
    }


    private Set<LearnClass> statementSetClasses(ConnectionDB connectionDB, int id) {

        Set<LearnClass> classes = new HashSet<>();
        try (Connection connection = connectionDB.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SQL_FIND_DIRECTION_CLASSES);
        ) {

            preparedStatement.setInt(1, id);
            ResultSet resultSetDirection = preparedStatement.executeQuery();

            while (resultSetDirection.next()) {
                classes.add(new LearnClass(resultSetDirection.getInt("id"),
                        resultSetDirection.getString("name")));
            }

        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }

        return classes;
    }
}
