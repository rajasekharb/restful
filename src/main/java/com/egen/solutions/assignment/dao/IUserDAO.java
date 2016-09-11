package com.egen.solutions.assignment.dao;

import com.egen.solutions.assignment.entity.User;
import com.egen.solutions.assignment.utils.Status;

import java.util.List;

/**
 * @author Rajasekhar
 */
public interface IUserDAO {

    boolean isUserExists(User user);

    /* Create */
    Status createUser(User user);

    /* Read */
    User getUserById(String id);

    List<User> getAllUsers();

    /* Update */
    Status updateUser(User user);

    /* Delete */
    Status deleteUserById(String id);

    Status deleteAllUsers();

}
