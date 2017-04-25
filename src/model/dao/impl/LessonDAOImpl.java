package model.dao.impl;

import model.dao.interfaces.LessonDAO;
import model.entity.Lesson;
import model.entity.Student;
import services.DataSourceFactory;

import java.sql.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * Created by admin on 19.04.2017.
 */
public class LessonDAOImpl implements LessonDAO {
    public Lesson getById(Long id) {
        Lesson lesson = null;
        String sql = "SELECT * FROM lesson WHERE id = ?;";

        try {
            Connection connection = DataSourceFactory.getDataSource().getConnection();
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setLong(1, id);
            ResultSet resultSet = statement.executeQuery();

            resultSet.next();
            lesson = new Lesson(resultSet.getLong("id"), resultSet.getTimestamp("lesson_date"),
                    resultSet.getLong("room"), resultSet.getString("description"),
                    resultSet.getLong("study_group_id"));

            GroupDAOImpl groupDAO = new GroupDAOImpl(); //TODO Надо что ли?
            lesson.setGroup(groupDAO.getById(resultSet.getLong("study_group_id")));

            resultSet.close();
            statement.close();
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

//        if (lesson == null) { //TODO Надо что ли?
//            throw new SQLException("Record with PK = " + id + " not found.");
//        }

        return lesson;
    }

    public Collection<Lesson> getAll() {
        List<Lesson> list = new ArrayList();
        try {
            Connection connection = DataSourceFactory.getDataSource().getConnection();
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT * FROM lesson;");

            while (resultSet.next()) {
                Lesson lesson = new Lesson(resultSet.getLong("id"), resultSet.getTimestamp("lesson_date"),
                        resultSet.getLong("room"), resultSet.getString("description"),
                        resultSet.getLong("study_group_id"));

            GroupDAOImpl groupDAO = new GroupDAOImpl(); //TODO Надо что ли?
            lesson.setGroup(groupDAO.getById(resultSet.getLong("study_group_id")));
                list.add(lesson);
            }

            resultSet.close();
            statement.close();
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return list;

    }

    public Lesson save(Lesson lesson) {
        if (lesson == null) {
            return null;
        }

        if(lesson.getId() > 0){
            update(lesson);
        } else {
            long newId = insert(lesson);
            lesson.setId(newId);
        }

        return lesson;
    }

    public Long insert(Lesson lesson) {
        long lastId = 0;

        if (lesson == null) {
            return lastId;
        }

        if (lesson.getId() > 0){
            return lesson.getId(); //TODO Надо что ли? Или лучше Exception?
        }

        if(lesson.getGroup() != null){
            GroupDAOImpl groupDAO = new GroupDAOImpl();
            groupDAO.save(lesson.getGroup());
            lesson.setGroupId(lesson.getGroup().getId());
        }

        try {
            Connection connection = DataSourceFactory.getDataSource().getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO lesson (lesson_date, room, description, study_group_id) VALUES (?, ?, ?, ?)", Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setTimestamp(1, lesson.getLessonDate());
            preparedStatement.setLong(2, lesson.getRoom());
            preparedStatement.setString(3, lesson.getDescription());
            preparedStatement.setLong(4, lesson.getGroupId());
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

    public int update(Lesson lesson) {
        int count = 0;

        if (lesson == null) {
            return count;
        }

        if(lesson.getGroup() != null){
            GroupDAOImpl groupDAO = new GroupDAOImpl();
            groupDAO.save(lesson.getGroup());
            lesson.setGroupId(lesson.getGroup().getId());
        }

        try {
            Connection connection = DataSourceFactory.getDataSource().getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement("UPDATE lesson SET lesson_date = ?, room = ?, description = ?, study_group_id = ? WHERE id = ?;");
            preparedStatement.setTimestamp(1, lesson.getLessonDate());
            preparedStatement.setLong(2, lesson.getRoom());
            preparedStatement.setString(3, lesson.getDescription());
            preparedStatement.setLong(4, lesson.getGroupId());

            count = preparedStatement.executeUpdate();
            preparedStatement.close();
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return count;
    }

    public int delete(Lesson lesson) { //TODO Каскадное удаление делать на уровне БД?
        int count = 0;

        if (lesson == null) {
            return count;
        }

        try {
            Connection connection = DataSourceFactory.getDataSource().getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement("DELETE FROM lesson WHERE id = ?;");
            preparedStatement.setLong(1, lesson.getId());

            count = preparedStatement.executeUpdate();
            preparedStatement.close();
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return count;
    }
}
