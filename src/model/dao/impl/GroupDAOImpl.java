package model.dao.impl;

import model.dao.interfaces.GroupDAO;
import model.entity.Group;
import services.DataSourceFactory;

import java.sql.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * Created by admin on 19.04.2017.
 */
public class GroupDAOImpl implements GroupDAO {

    public Group getById(Long id) {
        Group group = null;
        String sql = "SELECT * FROM study_group WHERE id = ?;";

        try {
            Connection connection = DataSourceFactory.getDataSource().getConnection();
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setLong(1, id);
            ResultSet resultSet = statement.executeQuery();

            resultSet.next();
            group = new Group(resultSet.getLong("id"), resultSet.getString("name"));

            resultSet.close();
            statement.close();
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

//        if (group == null) { //TODO Надо что ли?
//            throw new SQLException("Record with PK = " + id + " not found.");
//        }

        return group;
    }

    public Collection<Group> getAll() {
        List<Group> list = new ArrayList();
        try {
            Connection connection = DataSourceFactory.getDataSource().getConnection();
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT * FROM study_group;");

            while (resultSet.next()) {
                Group group = new Group(resultSet.getLong("id"), resultSet.getString("name"));
                list.add(group);
            }

            resultSet.close();
            statement.close();
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return list;
    }

    public Group save(Group group) {
        if (group == null) {
            return null;
        }

        if(group.getId() > 0){
            update(group);
        } else {
            long newId = insert(group);
            group.setId(newId);
        }

        return group;
    }

    public Long insert(Group group) {
        long lastId = 0;

        if (group == null) {
            return lastId;
        }

        if (group.getId() > 0){
            return group.getId(); //TODO Надо что ли? Или лучше Exception?
        }

        try {
            Connection connection = DataSourceFactory.getDataSource().getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO study_group (name) VALUES (?)", Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setString(1, group.getName());
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

    public int update(Group group) {
        int count = 0;

        if (group == null) {
            return count;
        }

        try {
            Connection connection = DataSourceFactory.getDataSource().getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement("UPDATE study_group SET name = ? WHERE id = ?;");
            preparedStatement.setString(1, group.getName());

            count = preparedStatement.executeUpdate();
            preparedStatement.close();
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return count;
    }

    public int delete(Group group) { //TODO Каскадное удаление делать на уровне БД?
        int count = 0;

        if (group == null) {
            return count;
        }

        try {
            Connection connection = DataSourceFactory.getDataSource().getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement("DELETE FROM study_group WHERE id = ?;");
            preparedStatement.setLong(1, group.getId());

            count = preparedStatement.executeUpdate();
            preparedStatement.close();
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return count;
    }
}
