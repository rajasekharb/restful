package com.egen.solutions.assignment.controller;

import com.egen.solutions.assignment.entity.User;
import com.egen.solutions.assignment.exceptions.UserExistsException;
import com.egen.solutions.assignment.services.IUserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.inject.Inject;
import java.util.List;

/**
 * @author Rajasekhar
 */
@RestController
public class UserController {

    //Field injection is not recommended. So using constructor injection
    private final IUserService userService;

    private static final Logger logger = LoggerFactory.getLogger(UserController.class);

    //Using Contexts and Dependency Injection (CDI), instead of Spring Autowired
    @Inject
    //Using qualifier (Useful if we have multiple implementations)
    //Using CDI to qualify an implementation, requires a custom annotation.
    public UserController(@Qualifier(value = "userService") IUserService userService) {
        this.userService = userService;
    }

    //-------------------Retrieve All Users--------------------------------------------------------
    @RequestMapping(value = "/users", method = RequestMethod.GET)
    public ResponseEntity<List<User>> getAllUsers() {
        if (logger.isDebugEnabled()) {
            logger.debug("Received request to fetch all users list");
        }

        List<User> users = this.userService.getAllUsers();
        if (users.isEmpty()) {
            if (logger.isInfoEnabled()) {
                logger.info("The list of users is empty. Returning no content.");
            }
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(users, HttpStatus.OK);
    }

    //-------------------Retrieve Single User--------------------------------------------------------
    @RequestMapping(value = "/users/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<User> getUser(@PathVariable("id") String id) {
        if (logger.isDebugEnabled()) {
            logger.debug(String.format("Received request to fetch user with id %s", id));
        }

        User user = this.userService.getUserById(id);
        if (user == null) {
            if (logger.isInfoEnabled()) {
                logger.info(String.format("User with id %s is not found", id));
            }
        }

        return new ResponseEntity<>(user, HttpStatus.OK);
    }

    @RequestMapping(value = "/users", method = RequestMethod.POST,
            produces = {MediaType.APPLICATION_JSON_UTF8_VALUE},
            consumes = {MediaType.APPLICATION_JSON_UTF8_VALUE})
    public User createUser(@RequestBody User user) {

        if (!this.userService.isUserExists(user)) {
            return this.userService.createUser(user);
        } else {
            throw new UserExistsException(String.format("User with id %s already exists!", user.getId()));
        }
    }
}
