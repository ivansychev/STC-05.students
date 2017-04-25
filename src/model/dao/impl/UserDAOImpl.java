package model.dao.impl;

import model.ConnectionPool;
import model.dao.interfaces.UserDAO;
import model.entity.User;
import org.apache.log4j.Logger;

import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collection;

/**
 * Created by ivans on 20/04/2017.
 */
public class UserDAOImpl implements UserDAO {

    private static final Logger logger = Logger.getLogger(UserDAOImpl.class);

    public Collection<User> getAll() {
        throw new NotImplementedException();
    }

    public User save(User entity) {
        return null;
    }

    public User getById(Long id) {
        throw new NotImplementedException();
    }

    public Long insert(User entity) {
        throw new NotImplementedException();
    }

    public int update(User entity) {
        throw new NotImplementedException();
    }

    public int delete(User entity) {
        throw new NotImplementedException();
    }

    public User findUserByLoginAndPassword(String login, String password) {
        User user = null;

        try (Connection connection = ConnectionPool.getInstance().getConnection();
             PreparedStatement statement = connection
                     .prepareStatement( "SELECT * FROM public.user WHERE login = ? AND password = ?")) {

            statement.setString(1, login);
            statement.setString(2, password);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                user = createEntity(resultSet);
            }

            logger.debug("user " + user);
        } catch (SQLException e) {
            logger.error(e);
        }

        return user;
    }

    private User createEntity(ResultSet resultSet) throws SQLException {
        return new User(resultSet.getLong("id"),
                resultSet.getString("login"),
                resultSet.getString("password"),
                resultSet.getBoolean("is_blocked"));
    }
}
