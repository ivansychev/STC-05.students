package model.dao.impl;

import model.dao.interfaces.StudentDAO;
import model.entity.Group;
import model.entity.Student;
import org.springframework.stereotype.Repository;
import services.DataSourceFactory;

import java.sql.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * Created by admin on 19.04.2017.
 */
@Repository
public class StudentDAOImpl implements StudentDAO {
    public Student getById(Long id) {
        Student student = null;
        String sql = "SELECT * FROM student WHERE id = ?;";

        try {
            Connection connection = DataSourceFactory.getDataSource().getConnection();
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setLong(1, id);
            ResultSet resultSet = statement.executeQuery();

            resultSet.next();
            student = new Student(resultSet.getLong("id"), resultSet.getString("name"),
                    resultSet.getInt("age"), resultSet.getLong("group_id"));

            GroupDAOImpl groupDAO = new GroupDAOImpl(); //TODO Надо что ли?
            student.setGroup(groupDAO.getById(resultSet.getLong("group_id")));

            resultSet.close();
            statement.close();
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

//        if (student == null) { //TODO Надо что ли?
//            throw new SQLException("Record with PK = " + id + " not found.");
//        }

        return student;
    }

    public Collection<Student> getAll() {
        List<Student> list = new ArrayList();
        try {
            Connection connection = DataSourceFactory.getDataSource().getConnection();
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT * FROM student;");

            while (resultSet.next()) {
                Student student = new Student(resultSet.getLong("id"), resultSet.getString("name"),
                        resultSet.getInt("age"), resultSet.getLong("group_id"));

            GroupDAOImpl groupDAO = new GroupDAOImpl(); //TODO Надо что ли?
            student.setGroup(groupDAO.getById(resultSet.getLong("group_id")));
                list.add(student);
            }

            resultSet.close();
            statement.close();
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return list;

    }

    public Student save(Student student) {
        if (student == null) {
            return null;
        }

        if(student.getId() > 0){
            update(student);
        } else {
            long newId = insert(student);
            student.setId(newId);
        }

        return student;
    }

    public Long insert(Student student) {
        long lastId = 0;

        if (student == null) {
            return lastId;
        }

        if (student.getId() > 0){
            return student.getId(); //TODO Надо что ли? Или лучше Exception?
        }

        if(student.getGroup() != null){
            GroupDAOImpl groupDAO = new GroupDAOImpl();
            groupDAO.save(student.getGroup());
            student.setGroup_id(student.getGroup().getId());
        }

        try {
            Connection connection = DataSourceFactory.getDataSource().getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO student (name, age, group_id) VALUES (?, ?, ?)", Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setString(1, student.getName());
            preparedStatement.setLong(2, student.getAge());
            preparedStatement.setLong(3, student.getGroup_id());
            preparedStatement.executeUpdate();

            ResultSet resultSet = preparedStatement.getGeneratedKeys();
            if (resultSet.next()) {
                lastId = resultSet.getInt(1);
            }
            resultSet.close();
            preparedStatement.close();
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return lastId;
    }

    public int update(Student student) {
        int count = 0;

        if (student == null) {
            return count;
        }

        if(student.getGroup() != null){
            GroupDAOImpl groupDAO = new GroupDAOImpl();
            groupDAO.save(student.getGroup());
            student.setGroup_id(student.getGroup().getId());
        }

        try {
            Connection connection = DataSourceFactory.getDataSource().getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement("UPDATE student SET name = ?, age = ?, group_id = ? WHERE id = ?;");
            preparedStatement.setString(1, student.getName());
            preparedStatement.setLong(2, student.getAge());
            preparedStatement.setLong(3, student.getGroup_id());

            count = preparedStatement.executeUpdate();
            preparedStatement.close();
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return count;
    }

    public int delete(Student student) { //TODO Каскадное удаление делать на уровне БД?
        int count = 0;

        if (student == null) {
            return count;
        }

        try {
            Connection connection = DataSourceFactory.getDataSource().getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement("DELETE FROM student WHERE id = ?;");
            preparedStatement.setLong(1, student.getId());

            count = preparedStatement.executeUpdate();
            preparedStatement.close();
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return count;
    }
}
