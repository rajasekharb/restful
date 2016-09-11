package com.egen.solutions.assignment.services.impl;

import com.egen.solutions.assignment.dao.IUserDAO;
import com.egen.solutions.assignment.entity.User;
import com.egen.solutions.assignment.services.IUserService;
import org.springframework.stereotype.Service;

import javax.inject.Inject;
import java.util.List;

/**
 * @author Rajasekhar
 *         IUserService.java implementation
 */
@Service("userService")
public class UserServiceImpl implements IUserService {

    private final IUserDAO userDAO;

    /**
     * Using a custom annotation, we can keep switching the implementation in CDI.
     * <p>
     * The same can be achieved in Spring using @Qualifier
     *
     * @param userDAO Implementation of DAO layer
     */
    @Inject
    public UserServiceImpl(IUserDAO userDAO) {
        this.userDAO = userDAO;
    }

    @Override
    public boolean isUserExists(User user) {
        return this.userDAO.isUserExists(user);
    }

    @Override
    public void createUser(User user) {
        this.userDAO.createUser(user);
    }

    @Override
    public User getUserById(String id) {
        return this.userDAO.getUserById(id);
    }

    @Override
    public List<User> getAllUsers() {
        return this.userDAO.getAllUsers();
    }

    @Override
    public void updateUser(User user) {
        this.userDAO.updateUser(user);
    }

    @Override
    public void deleteUserById(String id) {
        this.userDAO.deleteUserById(id);
    }
}
