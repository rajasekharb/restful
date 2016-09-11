package com.egen.solutions.assignment.dao.impl;

import com.egen.solutions.assignment.dao.IUserDAO;
import com.egen.solutions.assignment.entity.User;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import javax.inject.Inject;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Rajasekhar
 */
@Repository("userDAO")
public class UserDAOImpl implements IUserDAO {

    private static final Logger logger = LoggerFactory.getLogger(UserDAOImpl.class);
    private final SessionFactory sessionFactory;

    @Inject
    public UserDAOImpl(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public boolean isUserExists(User user) {
        Session currentSession = this.sessionFactory.getCurrentSession();
        Query query = currentSession.createQuery("from User where User.id = :userId");
        query.setParameter("userId", user.getId());
        Object uniqueResult = query.uniqueResult();
        if (logger.isDebugEnabled()) {
            logger.debug("Result of query ", uniqueResult);
        }
        return (uniqueResult != null);
    }

    @Override
    public void createUser(User user) {
        Session currentSession = this.sessionFactory.getCurrentSession();
        currentSession.persist(user);
    }

    @Override
    public void updateUser(User user) {
        Session currentSession = this.sessionFactory.getCurrentSession();
        currentSession.update(user);

        if (logger.isDebugEnabled()) {
            logger.debug("Updated user successfully");
        }
    }

    @Override
    public List<User> getAllUsers() {
        Session currentSession = this.sessionFactory.getCurrentSession();
        List<User> list = currentSession.createQuery("from User").list();

        if (logger.isDebugEnabled()) {
            logger.debug("Returning details of all users ", list);
        }

        if (list != null) {
            return list;
        } else {
            return new ArrayList<>();
        }
    }

    @Override
    public User getUserById(String id) {
        Session currentSession = this.sessionFactory.getCurrentSession();
        User user = (User) currentSession.load(User.class, id);
        if (logger.isDebugEnabled()) {
            logger.debug("Found the user with id ", id);
        }

        return user;
    }

    @Override
    public void deleteUserById(String id) {
        Session currentSession = this.sessionFactory.getCurrentSession();
        User user = (User) currentSession.load(User.class, new Integer(id));
        if (user != null) {
            currentSession.delete(user);
        }

        if (logger.isDebugEnabled()) {
            logger.info("User deleted successfully, user details are " + user);
        }
    }
}
