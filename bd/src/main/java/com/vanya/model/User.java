package com.vanya.model;

//import org.springframework.data.annotation.Id;
//import org.springframework.data.mongodb.core.mapping.Document;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;

/**
 * @author Ivan Gladush
 * @since 07.11.16.
 */
@Document(collection = "user")
public class User {
    //todo add validation
    @Id
    private long id;
    private String name;
    private String password;
    private String email;

    public User( String name, String password, String email) {
        this.id=new Date().getTime();
        this.name = name;
        this.password = password;
        this.email = email;
    }

    public User() {
        id=new Date().getTime();
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public static User getUser(UnconfirmedUser unconfirmedUser){
        User user = new User();
        user.setName(unconfirmedUser.getName());
        user.setEmail(unconfirmedUser.getEmail());
        user.setPassword(unconfirmedUser.getPassword());
        return user;
    }
    @Override
    public String toString() {
        return "User{" +
                "email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", name='" + name + '\'' +
                ", id='" + id + '\'' +
                '}';
    }
}
