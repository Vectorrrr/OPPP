package com.vanya.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;

/**
 * Created by Hladush Ivan
 * on 04.12.16.
 */
@Document(collection = "unconfirmedUser")
public class UnconfirmedUser {
    private static int dayForConfirRegistration = 1;
    @Id
    Integer id;

    String email;

    String password;

    String name;


    Date lastDate;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getLastDate() {
        return lastDate;
    }

    public void setLastDate(Date lastDate) {
        this.lastDate = lastDate;
    }

    public static UnconfirmedUser createUnconfirmedUser(User user) {
        UnconfirmedUser unconfirmedUser = new UnconfirmedUser();
        unconfirmedUser.setId(user.getEmail().hashCode());
        unconfirmedUser.setEmail(user.getEmail());
        unconfirmedUser.setName(user.getName());
        unconfirmedUser.setPassword(user.getPassword());
        unconfirmedUser.setLastDate(new Date(new Date().getTime()+dayForConfirRegistration*360000));
        return unconfirmedUser;
    }
}
