package com.egen.solutions.assignment.services;

import com.egen.solutions.assignment.entity.User;
import com.egen.solutions.assignment.utils.Status;

import java.util.List;

/**
 * @author Rajasekhar
 *         <p>
 *         User service Interface that abstracts the underlying data access implementation
 */
public interface IUserService {

    boolean isUserExists(User user);

    /* Create */
    User createUser(User user);

    /* Read */
    User getUserById(String id);

    List<User> getAllUsers();

    /* Update */
    Status updateUser(User user);

    /* Delete */
    Status deleteUserById(String id);

    Status deleteAllUsers();
}
