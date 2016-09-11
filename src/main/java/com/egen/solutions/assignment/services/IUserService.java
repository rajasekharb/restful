package com.egen.solutions.assignment.services;

import com.egen.solutions.assignment.entity.User;

import java.util.List;

/**
 * @author Rajasekhar
 *         <p>
 *         User service Interface that abstracts the underlying data access implementation
 */
public interface IUserService {

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
