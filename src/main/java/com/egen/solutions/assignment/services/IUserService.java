package com.egen.solutions.assignment.services;

import com.egen.solutions.assignment.entity.User;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author Rajasekhar
 *         <p>
 *         User service Interface that abstracts the underlying data access implementation
 */
@Service
public interface IUserService {

    List<User> getAllUsers();

    boolean isUserExists(User user);

    /* Create */
    void createUser(User user);

    /* Read */
    User getUserById(String id);

    /* Update */
    void updateUser(User user);

    /* Delete */
    void deleteUserById(String id);
}
