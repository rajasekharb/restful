package com.egen.solutions.assignment.controller;

import com.egen.solutions.assignment.entity.User;
import com.egen.solutions.assignment.services.IUserService;
import com.egen.solutions.assignment.utils.Status;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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

    private static final Logger logger = LoggerFactory.getLogger(UserController.class);
    //Field injection is not recommended. So using constructor injection
    private final IUserService userService;

    @Inject
    //Java CDI
    public UserController(IUserService userService) {
        this.userService = userService;
    }

    //Retrieves all users. Http Method Get
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

    //Retrieves a user. Http Method Get
    @RequestMapping(value = "/users/{id}", method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)
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

    //Creates a user. Http Method Post
    @RequestMapping(value = "/users", method = RequestMethod.POST,
            produces = {MediaType.APPLICATION_JSON_VALUE},
            consumes = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<Status> createUser(@RequestBody User user) {
        if (!this.userService.isUserExists(user)) {
            this.userService.createUser(user);
        } else {
            Status status = new Status(HttpStatus.CONFLICT.toString(), "User is already present");
            return new ResponseEntity<>(status, HttpStatus.CONFLICT);
        }

        Status status = new Status(HttpStatus.OK.toString(), "User is successfully created");
        return new ResponseEntity<>(status, HttpStatus.OK);
    }

    //Updates a user. Http Method Put
    @RequestMapping(value = "/users/{id}", method = RequestMethod.PUT,
            produces = {MediaType.APPLICATION_JSON_VALUE},
            consumes = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<Status> updateUser(@PathVariable("id") String id, @RequestBody User user) {
        User currentUser = this.userService.getUserById(id);
        if (currentUser == null) {
            if (logger.isInfoEnabled()) {
                logger.info(String.format("Couldn't find user with id %s", id));
            }
            Status status = new Status(HttpStatus.NOT_FOUND.toString(), "User not found");
            return new ResponseEntity<>(status, HttpStatus.NOT_FOUND);
        }

        this.userService.updateUser(user);

        Status status = new Status(HttpStatus.OK.toString(), "User is successfully updated");
        return new ResponseEntity<>(status, HttpStatus.OK);
    }

    //Deletes a specific user. Http Method Delete
    @RequestMapping(value = "/users/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<Status> deleteUser(@PathVariable("id") String id) {
        if (logger.isDebugEnabled()) {
            logger.debug(String.format("Received a request to delete the user with id %s", id));
        }

        this.userService.deleteUserById(id);
        Status status = new Status(HttpStatus.OK.toString(), "User deleted successfully");
        return new ResponseEntity<>(status, HttpStatus.OK);
    }
}
