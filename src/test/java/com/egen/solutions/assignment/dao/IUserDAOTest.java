package com.egen.solutions.assignment.dao;

import com.egen.solutions.assignment.dao.impl.UserDAOImpl;
import com.egen.solutions.assignment.entity.User;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.context.annotation.Configuration;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.Arrays;
import java.util.List;

/**
 * @author Rajasekhar
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration
public class IUserDAOTest {
    @Mock
    private SessionFactory sessionFactory;

    @Mock
    private Session session;

    @Mock
    private Query query;

    @InjectMocks
    private IUserDAO userDAO = new UserDAOImpl();

    private User user;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        Mockito.when(this.sessionFactory.getCurrentSession()).thenReturn(this.session);

        user = new User();
        user.setId("38400000-8cf0-11bd-b23e-10b96e4ef00d");
        user.setFirstName("Tom");
        user.setMiddleName("Dick");
        user.setLastName("Harry");
        user.setAge(27);
        user.setGender('M');
        user.setPhone("8500070740");
        user.setZip("500068");
    }

    @Test
    public void testCreateUser() {
        userDAO.createUser(user);
        Mockito.verify(session).persist(user);
    }

    @Test
    public void testUpdateUser() {
        userDAO.updateUser(user);
        Mockito.verify(session).update(user);
    }

    @Test
    public void testGetAllUsers() {
        List<User> expected = Arrays.asList(user);
        Mockito.when(session.createQuery("from User")).thenReturn(query);
        Mockito.when(query.list()).thenReturn(expected);
        List<User> actual = userDAO.getAllUsers();
        Assert.assertEquals(expected, actual);
    }

    @Configuration
    public static class TestConfig {
    }
}