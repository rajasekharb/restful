package com.egen.solutions.assignment.dao.impl;

import com.egen.solutions.assignment.dao.IUserDAO;
import com.egen.solutions.assignment.entity.User;
import com.egen.solutions.assignment.utils.Status;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author Rajasekhar
 */
@Component("userDAO")
public class UserDAOImpl implements IUserDAO {

    private final SessionFactory sessionFactory;

    @Autowired
    public UserDAOImpl(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public boolean isUserExists(User user) {
        return false;
    }

    @Override
    public Status createUser(User user) {
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
