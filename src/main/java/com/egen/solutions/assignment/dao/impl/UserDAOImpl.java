package com.egen.solutions.assignment.dao.impl;

import com.egen.solutions.assignment.dao.IUserDAO;
import com.egen.solutions.assignment.entity.User;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.inject.Inject;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Rajasekhar
 */
@Repository("userDAO")
public class UserDAOImpl implements IUserDAO {

    private static final Logger logger = LoggerFactory.getLogger(UserDAOImpl.class);
    private SessionFactory sessionFactory;

    @Inject
    public UserDAOImpl(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    //For mockito
    public UserDAOImpl() {
    }

    @Override
    @Transactional
    public boolean isUserExists(User user) {
        Session currentSession = this.sessionFactory.getCurrentSession();
        Query query = currentSession.createQuery("from User where phone = :phoneNumber");
        List phoneNumberList = query.setParameter("phoneNumber", user.getPhone()).list();

        if (phoneNumberList.size() != 0) {
            return true;
        } else {
            return false;
        }
    }

    @Override
    @Transactional
    public void createUser(User user) {
        Session currentSession = this.sessionFactory.getCurrentSession();
        currentSession.persist(user);

        if (logger.isDebugEnabled()) {
            logger.debug("Created user successfully");
        }
    }

    @Override
    @Transactional
    public void updateUser(User user) {
        Session currentSession = this.sessionFactory.getCurrentSession();
        currentSession.update(user);

        if (logger.isDebugEnabled()) {
            logger.debug("Updated user successfully");
        }
    }

    @Override
    @Transactional
    public List<User> getAllUsers() {
        Session currentSession = this.sessionFactory.getCurrentSession();
        @SuppressWarnings("unchecked") List<User> list = currentSession.createQuery("from User").list();
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
    @Transactional
    public User getUserById(String id) {
        Session currentSession = this.sessionFactory.getCurrentSession();
        return (User) currentSession.get(User.class, id);
    }

    @Override
    @Transactional
    public void deleteUserById(String id) {
        Session currentSession = this.sessionFactory.getCurrentSession();
        User user = (User) currentSession.get(User.class, id);
        if (user != null) {
            currentSession.delete(user);
            if (logger.isDebugEnabled()) {
                logger.info("User deleted successfully, user details are " + user);
            }
        }
    }
}
