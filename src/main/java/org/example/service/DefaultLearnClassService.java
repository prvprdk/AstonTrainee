package org.example.service;

import org.example.db.ConnectionDB;
import org.example.entity.LearnClass;
import org.example.entity.Student;
import org.example.servlets.payload.LearnClassUpdate;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashSet;
import java.util.Set;

public class DefaultLearnClassService implements LearnClassService {

    private final static String SQL_FIND_CLASS_BY_ID = "SELECT * FROM class where id = ?";
    private final static String SQL_ADD_STUDENT_TO_CLASS =
            "INSERT INTO class_student ( id_class, id_student) VALUES (?, ?)";
    private final static String SQL_FIND_STUDENTS_FOR_CLASS =
            "SELECT * FROM student JOIN class_student on student.id = class_student.id_student WHERE class_student.id_class = ?";
    private final static String SQL_UPDATE_CLASS = "UPDATE class SET name = ? WHERE ID = ?";
    private final static String SQL_ADD_CLASS = "INSERT INTO class (name, id_direction ) VALUES (?, ?)";
    private final static String SQL_DELETE_CLASS_BY_ID = "DELETE FROM class WHERE id = ?";

    @Override
    public void add(LearnClass direction) {
        ConnectionDB connectionDB = new ConnectionDB();

        try (Connection connection = connectionDB.getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement(SQL_ADD_CLASS);
            preparedStatement.setString(1, direction.getName());
            preparedStatement.setInt(1, direction.getDirection().getId());
            preparedStatement.execute();

        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void addStudentToClass(int idLearnClass, int idStudent) {

        ConnectionDB connectionDB = new ConnectionDB();
        try (Connection connection = connectionDB.getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement(SQL_ADD_STUDENT_TO_CLASS);
            preparedStatement.setInt(1, idLearnClass);
            preparedStatement.setInt(2, idStudent);
            preparedStatement.execute();
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }

    }


    @Override
    public LearnClass findById(int id) {

        ConnectionDB connectionDB = new ConnectionDB();
        return getLearnClass(connectionDB, id);
    }

    @Override
    public void deleteById(int id) {
        ConnectionDB connectionDB = new ConnectionDB();
        try (Connection connection = connectionDB.getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement(SQL_DELETE_CLASS_BY_ID);
            preparedStatement.setInt(1, id);
            preparedStatement.execute();
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void update(LearnClassUpdate update) {

        ConnectionDB connectionDB = new ConnectionDB();

        try (Connection connection = connectionDB.getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement(SQL_UPDATE_CLASS);
            preparedStatement.setString(1, update.name());
            preparedStatement.setInt(2, update.id());

        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }


    }

    private LearnClass getLearnClass(ConnectionDB connectionDB, int id) {

        try (Connection connection = connectionDB.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SQL_FIND_CLASS_BY_ID)) {

            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                LearnClass learnClass = new LearnClass();
                learnClass.setId(resultSet.getInt("id"));
                learnClass.setName(resultSet.getString("name"));

                learnClass.setStudents(setStudents(connectionDB, id));

                return learnClass;
            }

        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        return null;
    }

    private Set<Student> setStudents(ConnectionDB connectionDB, int id) {
        Set<Student> students = new HashSet<>();

        try (Connection connection = connectionDB.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SQL_FIND_STUDENTS_FOR_CLASS)
        ) {

            preparedStatement.setInt(1, id);
            ResultSet resultSetDirection = preparedStatement.executeQuery();

            while (resultSetDirection.next()) {
                Student student = new Student();
                student.setId(resultSetDirection.getInt("id"));
                student.setName(resultSetDirection.getString("name"));
                students.add(student);
            }

        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }

        return students;
    }
}
