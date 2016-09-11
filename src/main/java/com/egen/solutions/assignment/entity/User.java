package com.egen.solutions.assignment.entity;

import com.egen.solutions.assignment.exceptions.InvalidDataException;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.io.Serializable;

/**
 * @author Rajasekhar
 *         <p>
 *         Java Model Corresponding to database table "t_user" with columns
 *         f_id, f_first_name, f_middle_name, f_last_name, f_age, f_gender, f_phone, f_zip
 *         <p>
 *         Database naming convention is all lower case words separated by underscores.
 *         <p>
 *         Tables start with t and fields start with f
 */
@Entity
@Table(name = "t_user", uniqueConstraints = {@UniqueConstraint(columnNames = {"f_id"})})
@SuppressWarnings("unused")
public class User implements Serializable {

    private static final long serialVersionUID = 1L;

    /*Unique Identifier*/
    @Id
    @GeneratedValue(generator = "uuid2")
    @GenericGenerator(name = "uuid2", strategy = "uuid2")
    @Column(name = "f_id", unique = true, nullable = false)
    private String id;

    @Column(name = "f_first_name", nullable = false)
    private String firstName;

    /*Optional field, So nullable true*/
    @Column(name = "f_middle_name")
    private String middleName;

    @Column(name = "f_last_name", nullable = false)
    private String lastName;

    @Column(name = "f_age", nullable = false)
    private int age;

    @Column(name = "f_gender", nullable = false)
    private char gender;

    @Column(name = "f_phone", nullable = false, length = 10)
    //Chose String as Number type is too small
    private String phone;

    /*Optional field, So nullable true*/
    @Column(name = "f_zip")
    private String zip;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        //UUID
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        //Non null, only alphabet
        if (firstName == null || !isAlpha(firstName)) {
            throw new InvalidDataException("First name should be only alphabet and non null");
        }
        this.firstName = firstName;
    }

    public String getMiddleName() {
        return middleName;
    }

    public void setMiddleName(String middleName) {
        //Optional
        if (middleName != null) {
            if (!isAlpha(middleName)) {
                throw new InvalidDataException("Middle name should have only alphabet");
            }
        }
        this.middleName = middleName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        if (lastName == null || !isAlpha(lastName)) {
            throw new InvalidDataException("Last name should have only alphabet and be non null");
        }

        this.lastName = lastName;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        if (age > 150 || age <= 0) {//Even 150 is too much. :)
            throw new InvalidDataException("Invalid data for age. Please enter valid positive number.");
        }
        this.age = age;
    }

    public char getGender() {
        return gender;
    }

    public void setGender(char gender) {
        if (gender == 'M' || gender == 'F') {
            this.gender = gender;
        } else {
            throw new InvalidDataException("Accepted gender is only either M or F");
        }
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        if (phone == null) {
            throw new InvalidDataException("Phone number is null");
        }

        String trim = phone.trim();
        if ("".equals(trim)) {
            throw new IllegalArgumentException("Invalid argument for phone number");
        }

        if (!(trim.equals("0.0") || !phone.startsWith("-")) || trim.length() < 10) {
            throw new InvalidDataException("Phone number should be 10 digit non zero number.");
        }

        try {
            double signum = Math.signum(Double.parseDouble(phone));
            if (signum == 0 || signum == -1) {
                throw new InvalidDataException("Phone number should be 10 digit non zero number.");
            }
        } catch (NumberFormatException ex) {
            throw new InvalidDataException("Phone number should be 10 digits number");
        }

        this.phone = phone;
    }

    public String getZip() {
        return zip;
    }

    public void setZip(String zip) {
        this.zip = zip;
    }

    @Override
    public String toString() {
        return "User{" +
                "id='" + id + '\'' +
                ", firstName='" + firstName + '\'' +
                ", middleName='" + middleName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", age=" + age +
                ", gender=" + gender +
                ", phone='" + phone + '\'' +
                ", zip='" + zip + '\'' +
                '}';
    }

    private boolean isAlpha(String name) {
        return name.matches("[a-zA-Z]+");
    }
}
