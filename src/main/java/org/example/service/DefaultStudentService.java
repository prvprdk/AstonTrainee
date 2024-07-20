package org.example.service;

import org.example.db.ConnectionDB;
import org.example.entity.LearnClass;
import org.example.entity.Student;
import org.example.servlets.payload.UpdateStudent;

import java.sql.*;
import java.util.HashSet;
import java.util.Set;

public class DefaultStudentService implements StudentService {

    private final static String SQL_FIND_STUDENT_BY_ID = "SELECT  * FROM student WHERE id = ?";
    private final static String SQL_FIND_ALL_STUDENTS = "SELECT * FROM student";
    private final static String SQL_FIND_CLASS_FOR_STUDENT =
            "SELECT * FROM class JOIN class_student on class.id = class_student.id_class WHERE class_student.id_student = ?";
    private final static String SQL_UPDATE_STUDENT = "UPDATE student SET name = ? WHERE ID = ?";
    private final static String SQL_ADD_STUDENT = "INSERT INTO student (name) VALUES (?, ?)";
    private final static String SQL_DELETE_STUDENT_BY_ID = "DELETE FROM student WHERE id = ?";


    @Override
    public void add(Student student) {
        ConnectionDB connectionDB = new ConnectionDB();

        try (Connection connection = connectionDB.getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement(SQL_ADD_STUDENT);
            preparedStatement.setString(1, student.getName());
            preparedStatement.execute();

        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }

    }

    @Override
    public Set<Student> findAll() {
        ConnectionDB connectionDB = new ConnectionDB();
        Set<Student> set = new HashSet<>();
        try (Connection connection = connectionDB.getConnection()) {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(SQL_FIND_ALL_STUDENTS);
            while (resultSet.next()) {

                Student student = new Student();
                student.setId(resultSet.getInt("id"));
                student.setName(resultSet.getString("name"));
                set.add(student);
            }
        } catch (ClassNotFoundException | SQLException e) {
            throw new RuntimeException(e);
        }

        return set;
    }

    @Override
    public Student findById(int id) {

        ConnectionDB connectionDB = new ConnectionDB();
        return getStudent(connectionDB, id);

    }

    @Override
    public void deleteById(int id) {

        ConnectionDB connectionDB = new ConnectionDB();
        try (Connection connection = connectionDB.getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement(SQL_DELETE_STUDENT_BY_ID);
            preparedStatement.setInt(1, id);
            preparedStatement.execute();
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }

    }

    @Override
    public void update(UpdateStudent update) {
        ConnectionDB connectionDB = new ConnectionDB();

        try (Connection connection = connectionDB.getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement(SQL_UPDATE_STUDENT);
            preparedStatement.setString(1, update.name());
            preparedStatement.setInt(2, update.id());


        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    private Student getStudent(ConnectionDB connectionDB, int id) {

        try (Connection connection = connectionDB.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SQL_FIND_STUDENT_BY_ID);) {

            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                Student student = new Student();
                student.setId(resultSet.getInt("id"));
                student.setName(resultSet.getString("name"));
                student.setClasses(setClasses(connectionDB, id));

                return student;
            }

        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        return null;
    }

    private Set<LearnClass> setClasses(ConnectionDB connectionDB, int id) {

        Set<LearnClass> classes = new HashSet<>();

        try (Connection connection = connectionDB.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SQL_FIND_CLASS_FOR_STUDENT);
        ) {

            preparedStatement.setInt(1, id);
            ResultSet resultSetDirection = preparedStatement.executeQuery();

            while (resultSetDirection.next()) {
                LearnClass learnClass = new LearnClass();
                learnClass.setId(resultSetDirection.getInt("id"));
                learnClass.setName(resultSetDirection.getString("name"));
                classes.add(learnClass);
            }

        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }

        return classes;
    }
}
