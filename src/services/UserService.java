package services;

import model.entity.User;

/**
 * Created by ivans on 20/04/2017.
 */
public interface UserService {
    User auth(String login, String password);

}
