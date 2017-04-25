package model.dao.interfaces;

import model.entity.User;

/**
 * Created by ivans on 20/04/2017.
 */
public interface UserDAO extends DAO<User, Long>{
    User findUserByLoginAndPassword(String login, String password);
}
