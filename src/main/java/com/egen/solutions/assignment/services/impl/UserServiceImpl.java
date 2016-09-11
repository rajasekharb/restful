package com.egen.solutions.assignment.services.impl;

import com.egen.solutions.assignment.dao.IUserDAO;
import com.egen.solutions.assignment.entity.User;
import com.egen.solutions.assignment.services.IUserService;
import com.egen.solutions.assignment.utils.Status;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import javax.inject.Inject;
import javax.transaction.Transactional;
import java.util.List;

/**
 * @author Rajasekhar
 *         IUserService.java implementation
 */
@Service("userService")
@Transactional
public class UserServiceImpl implements IUserService {

    private final IUserDAO userDAO;

    @Inject
    public UserServiceImpl(@Qualifier("userDAO") IUserDAO userDAO) {
        this.userDAO = userDAO;
    }

    @Override
    public boolean isUserExists(User user) {
        return this.userDAO.isUserExists(user);
    }

    @Override
    public User createUser(User user) {
        return null;
    }

    @Override
    public User getUserById(String id) {
        return null;
    }

    @Override
    public List<User> getAllUsers() {
        return null;
    }

    @Override
    public Status updateUser(User user) {
        return null;
    }

    @Override
    public Status deleteUserById(String id) {
        return null;
    }

    @Override
    public Status deleteAllUsers() {
        return null;
    }
}
