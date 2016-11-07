package com.vanya.data.access.impl;

import com.vanya.data.access.api.UserDao;
import com.vanya.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author Ivan Gladush
 * @since 07.11.16.
 */
@Component
public class UserDaoMongoImpl implements UserDao {
    @Autowired
    private MongoOperations mongoOperation;

    @Override
    public void deleteUser(long id) {
        mongoOperation.remove(new Query().
                addCriteria(Criteria.where("id").is(id)), User.class);
    }

    @Override
    public  void saveUser(User user){
        mongoOperation.save(user);
    }

    @Override
    public List<User> getAllUsers() {
        return mongoOperation.find(new Query(),User.class);
    }

    @Override
    public User getUser(long id) {
        return mongoOperation.findOne(new Query().addCriteria(Criteria.where("id").is(id))
                , User.class);
    }

    @Override
    public void changeUser(User user, long id) {
        Update update=new Update();
        update.set("name",user.getName());
        update.set("password",user.getPassword());
        update.set("email",user.getEmail());
        mongoOperation.updateFirst(new Query().addCriteria(Criteria.where("id").is(id)),update,User.class);
    }


}
