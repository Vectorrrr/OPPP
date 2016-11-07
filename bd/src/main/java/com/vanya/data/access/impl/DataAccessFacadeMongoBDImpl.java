package com.vanya.data.access.impl;

import com.vanya.data.access.api.DataAccessFacade;
import com.vanya.data.access.api.JalousieDao;
import com.vanya.data.access.api.UserDao;
import com.vanya.model.Jalousie;
import com.vanya.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author Ivan Gladush
 * @since 07.11.16.
 */
@Component
public class DataAccessFacadeMongoBDImpl implements DataAccessFacade {
    @Autowired
    private UserDao userDao;

    @Override
    public void deleteUser(long id) {
        userDao.deleteUser(id);
    }

    @Override
    public void saveUser(User user) {
        userDao.saveUser(user);
    }

    @Override
    public List<User> getAllUsers() {
        return userDao.getAllUsers();
    }

    @Override
    public User getUser(long id) {
        return userDao.getUser(id);
    }

    @Override
    public void changeUser(User user, long id) {
        userDao.changeUser(user,id);
    }


    @Autowired
    private JalousieDao jalousieDao;

    @Override
    public void addJalousieForUser(long userId, Jalousie jalousie) {
        jalousieDao.addJalousieForUser(userId, jalousie);
    }

    @Override
    public void removeJalousie(long userId, long jalId) {
        jalousieDao.removeJalousie(userId,jalId);
    }

    @Override
    public List<Jalousie> getAllJalousies(long userId) {
        return jalousieDao.getAllJalousies(userId);
    }

    @Override
    public void changeJalousies(long userId, long id, Jalousie jalousie) {
        jalousieDao.changeJalousies(userId,id,jalousie);
    }

}
