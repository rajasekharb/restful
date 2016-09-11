package com.egen.solutions.assignment.entity;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.io.Serializable;

/**
 * @author Rajasekhar
 *         <p>
 *         Java Model Corresponding to database table "user" with columns
 *         id, first_name, middle_name, last_name, age, gender, phone, zip
 *         <p>
 *         Database naming convention is all lower case words separated by underscores
 */
@Entity
@Table(name = "user", uniqueConstraints = {@UniqueConstraint(columnNames = {"id"})})
public class User implements Serializable {

    private static final long serialVersionUID = 1L;

    /*Unique Identifier*/
    @Id
    @GeneratedValue(generator = "uuid2")
    @GenericGenerator(name = "uuid2", strategy = "uuid2")
    @Column(name = "id", unique = true, nullable = false)
    private String id;

    @Column(name = "first_name", nullable = false)
    private String firstName;

    /*Optional field, So nullable true*/
    @Column(name = "middle_name")
    private String middleName;

    @Column(name = "last_name", nullable = false)
    private String lastName;

    @Column(name = "age", nullable = false)
    private int age;

    @Column(name = "gender", nullable = false)
    private char gender;

    @Column(name = "phone", nullable = false, length = 10)
    private int phone;

    /*Optional field, So nullable true*/
    @Column(name = "zip")
    private String zip;

    /*-----------------------------------Start of Getters and Setters----------------------------------*/
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getMiddleName() {
        return middleName;
    }

    public void setMiddleName(String middleName) {
        this.middleName = middleName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public char getGender() {
        return gender;
    }

    public void setGender(char gender) {
        this.gender = gender;
    }

    public int getPhone() {
        return phone;
    }

    public void setPhone(int phone) {
        this.phone = phone;
    }

    public String getZip() {
        return zip;
    }

    public void setZip(String zip) {
        this.zip = zip;
    }
    /*-----------------------------------End of Getters and Setters----------------------------------*/

    @Override
    public String toString() {
        return "User{" +
                "id='" + id + '\'' +
                ", firstName='" + firstName + '\'' +
                ", middleName='" + middleName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", age=" + age +
                ", gender=" + gender +
                ", phone=" + phone +
                ", zip='" + zip + '\'' +
                '}';
    }
}
