package services;

import model.dao.impl.UserDAOImpl;
import model.dao.interfaces.UserDAO;
import model.entity.User;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Component;

/**
 * Created by ivans on 20/04/2017.
 */
public class UserServiceImpl implements UserService{

    private static final Logger logger = Logger.getLogger(UserServiceImpl.class);
    private UserDAO userDAO;

    public UserDAO getUserDAO() {
        return userDAO;
    }

    public void setUserDAO(UserDAO userDAO) {
        this.userDAO = userDAO;
    }

    public User auth(String login, String password) {
        User user = userDAO.findUserByLoginAndPassword(login, password);
        logger.debug("user: " + user);

        if (user != null && user.isBlocked()) {
            return null;
        }
        logger.debug("user not blocked");

        return user;
    }
}
