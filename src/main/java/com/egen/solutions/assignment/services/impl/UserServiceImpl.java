package com.egen.solutions.assignment.services.impl;

import com.egen.solutions.assignment.dao.IUserDAO;
import com.egen.solutions.assignment.entity.User;
import com.egen.solutions.assignment.exceptions.InvalidDataException;
import com.egen.solutions.assignment.services.IUserService;
import org.springframework.stereotype.Service;

import javax.inject.Inject;
import java.util.List;

/**
 * @author Rajasekhar
 *         IUserService.java implementation
 *         <p>
 *         Validates the domain objects and thus aware of the Business Logic. But unware of
 *         implementation logic
 *         <p>
 *         Uses {@link IUserDAO} and can have different
 *         implementations for different use cases
 */
@Service("userService")
public class UserServiceImpl implements IUserService {

    private final IUserDAO userDAO;

    @Inject
    public UserServiceImpl(IUserDAO userDAO) {
        this.userDAO = userDAO;
    }

    @Override
    public boolean isUserExists(User user) {
        return user.getPhone() != null && this.userDAO.isUserExists(user);
    }

    @Override
    public void createUser(User user) {
        if (isValidData(user)) {
            this.userDAO.createUser(user);
        } else {
            throw new InvalidDataException("One or more fields have invalid data");
        }
    }

    private boolean isAlpha(String name) {
        return name.matches("[a-zA-Z]+");
    }

    private boolean isValidData(User user) {
        String firstName = user.getFirstName();
        //Non null, only alphabet
        if (firstName == null || !isAlpha(firstName)) {
            throw new InvalidDataException("First name should have only alphabet and " +
                    "be non null");
        }

        String middleName = user.getMiddleName();
        //Optional
        if (middleName != null) {
            //Allow empty middle name but not null
            if (!isAlpha(middleName)) {
                if (!"".equals(middleName.trim())) {
                    throw new InvalidDataException("Middle name should have only alphabet");
                }
            }
        }

        String lastName = user.getLastName();
        if (lastName == null || !isAlpha(lastName)) {
            throw new InvalidDataException("Last name should have only " +
                    "alphabet and be non null");
        }

        int age = user.getAge();
        //An assumption
        //101 is too much :-)
        if (age > 101 || age <= 0) {
            throw new InvalidDataException("Invalid data for age. Please enter valid " +
                    "positive number.");
        }

        char gender = user.getGender();
        if (!(gender == 'M' || gender == 'F')) {
            throw new InvalidDataException("Accepted gender is only either M or F");
        }

        String phone = user.getPhone();

        validatePhoneNumber(phone);
        //Seems everything is okay
        return true;
    }

    /**
     * Uses Math.signum() to find positive or negative or zero
     *
     * @param phone 10 digit non zero number best represented by a String
     * @see java.lang.Math
     */
    private void validatePhoneNumber(String phone) {
        if (phone == null) {
            throw new InvalidDataException("Phone number is null");
        }

        String trim = phone.trim();

        if (trim.length() < 10) {
            throw new InvalidDataException("Phone number should be 10 digit non zero number.");
        }

        try {
            double signum = Math.signum(Double.parseDouble(trim));
            if (signum == 0 || signum == -1) {
                throw new InvalidDataException("Phone number should be 10 digit non zero number.");
            }
        } catch (NumberFormatException ex) {
            throw new InvalidDataException("Phone number should be 10 digits number");
        }
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
        if (isValidData(user)) {
            this.userDAO.updateUser(user);
        } else {
            throw new InvalidDataException("One or more fields have invalid data");
        }
    }

    @Override
    public void deleteUserById(String id) {
        this.userDAO.deleteUserById(id);
    }
}
