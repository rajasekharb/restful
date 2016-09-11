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
 *         <p>
 *         Provides services for
 *         creating user -- /users POST Method with JSON data
 *         Reads all users -- /users GET
 *         Reads a single user -- /users/{id} GET
 *         updating user -- /users PUT Method with JSON data
 *         creating user -- /users POST Method with JSON data
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
    //Searches by using id
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

    //Searches for user based on phone number provided. If found returns Conflict status
    //Otherwise creates a new user and returns Ok status
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

    //Finds user by id and if found, updates
    //Updates a user. Http Method Put
    @RequestMapping(value = "/users", method = RequestMethod.PUT,
            produces = {MediaType.APPLICATION_JSON_VALUE},
            consumes = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<Status> updateUser(@RequestBody User user) {
        String id = user.getId();
        if (id == null) {
            Status status = new Status(HttpStatus.BAD_REQUEST.toString(), "Missing user id");
            return new ResponseEntity<>(status, HttpStatus.BAD_REQUEST);
        }

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
