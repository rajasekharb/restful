package com.egen.solutions.assignment.services;

import com.egen.solutions.assignment.dao.IUserDAO;
import com.egen.solutions.assignment.entity.User;
import com.egen.solutions.assignment.exceptions.InvalidDataException;
import com.egen.solutions.assignment.services.impl.UserServiceImpl;
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

/**
 * @author Rajasekhar
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration
public class IUserServiceTest {

    @Mock
    private IUserDAO userDAO;

    @InjectMocks
    private IUserService userService = new UserServiceImpl();

    private User user;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);

        user = new User();
        user.setId("38400000-8cf0-11bd-b23e-10b96e4ef00d");
        user.setFirstName("Somen");
        user.setMiddleName("Kumar");
        user.setLastName("Sarkar");
        user.setAge(31);
        user.setGender('M');
        user.setPhone("9494136460");
        user.setZip("500068");
    }

    @Test(expected = InvalidDataException.class)
    public void createUserException() {
        Mockito.when(userDAO.isUserExists(user)).thenReturn(true);
        user.setLastName(null);//Last name can't be null. So exception
        userService.createUser(user);
        Mockito.verify(userDAO).createUser(user);
    }

    @Test(expected = InvalidDataException.class)
    public void updateUserException() {
        Mockito.when(userDAO.isUserExists(user)).thenReturn(true);
        user.setPhone("94941Abcdf");
        userService.createUser(user);
        Mockito.verify(userDAO).createUser(user);
    }

    @Configuration
    public static class TestConfig {
    }
}