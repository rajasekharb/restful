package com.egen.solutions.assignment.entity;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import org.hibernate.annotations.GenericGenerator;
import org.springframework.stereotype.Component;

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
@Component
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@SuppressWarnings("unused")
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
@Table(name = "t_user", uniqueConstraints = {@UniqueConstraint(columnNames = {"f_id"})})
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

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
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
}
