package edu.school21.models;

import edu.school21.annotations.OrmColumn;
import edu.school21.annotations.OrmColumnId;
import edu.school21.annotations.OrmEntity;

import javax.persistence.GenerationType;


@OrmEntity(table = "simple_user")
public class User {

    @OrmColumnId(id = GenerationType.AUTO, name = "id")

    public Long id;
    @OrmColumn(name = "firstName", length = 10)
    public String firstName;
    @OrmColumn(name = "lastName", length = 10)
    public String lastName;
    @OrmColumn(name = "age")
    public Integer age;

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public User(String firstName, String lastName, Integer age) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.age = age;
    }
    public User() {
        this.id = 0L;
        this.firstName = null;
        this.lastName = null;
        this.age = 0;
    }


    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", age=" + age +
                '}';
    }
}
