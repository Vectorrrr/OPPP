package com.vanya.data.access.api;

import com.vanya.model.Jalousie;
import com.vanya.model.User;

import java.util.List;

/**
 * @author Ivan Gladush
 * @since 07.11.16.
 */
public interface DataAccessFacade {
    void deleteUser(long id);

    void saveUser(User user);

    List<User> getAllUsers();

    User getUser(long id);

    void changeUser(User user,long id);

    void addJalousieForUser(long userId, Jalousie jalousie);

    void removeJalousie(long userId, long jalId);

    List<Jalousie> getAllJalousies(long userId);

    void changeJalousies(long userId, long id, Jalousie jalousie);
    
}
