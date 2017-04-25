package model.dao.impl;

import model.dao.interfaces.JournalDAO;
import model.entity.Journal;
import model.entity.Lesson;
import services.DataSourceFactory;

import java.sql.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * Created by admin on 19.04.2017.
 */
public class JournalDAOImpl implements JournalDAO {
    public Journal getById(Long id) {
        Journal journal = null;
        String sql = "SELECT * FROM journal WHERE id = ?;";

        try {
            Connection connection = DataSourceFactory.getDataSource().getConnection();
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setLong(1, id);
            ResultSet resultSet = statement.executeQuery();

            resultSet.next();
            journal = new Journal(resultSet.getLong("id"), resultSet.getLong("lesson_id"),
                    resultSet.getLong("student_id"));

            LessonDAOImpl lessonDAO = new LessonDAOImpl(); //TODO Надо что ли?
            journal.setLesson(lessonDAO.getById(resultSet.getLong("lesson_id")));

            StudentDAOImpl studentDAO = new StudentDAOImpl(); //TODO Надо что ли?
            journal.setStudent(studentDAO.getById(resultSet.getLong("student_id")));

            resultSet.close();
            statement.close();
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

//        if (journal == null) { //TODO Надо что ли?
//            throw new SQLException("Record with PK = " + id + " not found.");
//        }

        return journal;
    }

    public Collection<Journal> getAll() {
        List<Journal> list = new ArrayList();
        try {
            Connection connection = DataSourceFactory.getDataSource().getConnection();
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT * FROM journal;");

            while (resultSet.next()) {
                Journal journal = new Journal(resultSet.getLong("id"), resultSet.getLong("lesson_id"),
                        resultSet.getLong("student_id"));

            LessonDAOImpl lessonDAO = new LessonDAOImpl(); //TODO Надо что ли?
            journal.setLesson(lessonDAO.getById(resultSet.getLong("lesson_id")));

            StudentDAOImpl studentDAO = new StudentDAOImpl(); //TODO Надо что ли?
            journal.setStudent(studentDAO.getById(resultSet.getLong("student_id")));
                list.add(journal);
            }

            resultSet.close();
            statement.close();
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return list;

    }

    public Journal save(Journal journal) {
        if (journal == null) {
            return null;
        }

        if(journal.getId() > 0){
            update(journal);
        } else {
            long newId = insert(journal);
            journal.setId(newId);
        }

        return journal;
    }

    public Long insert(Journal journal) {
        long lastId = 0;

        if (journal == null) {
            return lastId;
        }

        if (journal.getId() > 0){
            return journal.getId(); //TODO Надо что ли? Или лучше Exception?
        }

        if(journal.getStudent() != null){
            StudentDAOImpl studentDAO = new StudentDAOImpl();
            studentDAO.save(journal.getStudent());
            journal.setStudentId(journal.getStudent().getId());
        }

        if(journal.getLesson() != null){
            LessonDAOImpl lessonDAO = new LessonDAOImpl();
            lessonDAO.save(journal.getLesson());
            journal.setLessonId(journal.getLesson().getId());
        }

        try {
            Connection connection = DataSourceFactory.getDataSource().getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO journal (lesson_id, student_id) VALUES (?, ?)", Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setLong(1, journal.getLessonId());
            preparedStatement.setLong(2, journal.getStudentId());
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

    public int update(Journal journal) {
        int count = 0;

        if (journal == null) {
            return count;
        }

        if(journal.getStudent() != null){
            StudentDAOImpl studentDAO = new StudentDAOImpl();
            studentDAO.save(journal.getStudent());
            journal.setStudentId(journal.getStudent().getId());
        }

        if(journal.getLesson() != null){
            LessonDAOImpl lessonDAO = new LessonDAOImpl();
            lessonDAO.save(journal.getLesson());
            journal.setLessonId(journal.getLesson().getId());
        }

        try {
            Connection connection = DataSourceFactory.getDataSource().getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement("UPDATE journal SET lesson_id = ?, student_id = ? WHERE id = ?;");
            preparedStatement.setLong(1, journal.getLessonId());
            preparedStatement.setLong(2, journal.getStudentId());

            count = preparedStatement.executeUpdate();
            preparedStatement.close();
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return count;
    }

    public int delete(Journal journal) { //TODO Каскадное удаление делать на уровне БД?
        int count = 0;

        if (journal == null) {
            return count;
        }

        try {
            Connection connection = DataSourceFactory.getDataSource().getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement("DELETE FROM journal WHERE id = ?;");
            preparedStatement.setLong(1, journal.getId());

            count = preparedStatement.executeUpdate();
            preparedStatement.close();
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return count;
    }
}
