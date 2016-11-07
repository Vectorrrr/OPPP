package com.vanya.data.access.api;

import com.vanya.model.User;

import java.util.List;

/**
 * @author Ivan Gladush
 * @since 07.11.16.
 */
public interface UserDao {
    void deleteUser(long id);

    void saveUser(User user);

    List<User> getAllUsers();

    User getUser(long id);


    void changeUser(User user, long id);
    
}
