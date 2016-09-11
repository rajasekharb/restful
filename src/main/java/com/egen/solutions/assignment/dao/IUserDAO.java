package com.egen.solutions.assignment.dao;

import com.egen.solutions.assignment.entity.User;

import java.util.List;

/**
 * @author Rajasekhar
 */
public interface IUserDAO {

    boolean isUserExists(User user);

    /* Create */
    void createUser(User user);

    /* Read */
    User getUserById(String id);

    List<User> getAllUsers();

    /* Update */
    void updateUser(User user);

    /* Delete */
    void deleteUserById(String id);
}
