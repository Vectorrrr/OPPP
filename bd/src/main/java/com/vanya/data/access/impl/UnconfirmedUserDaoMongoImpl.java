package com.vanya.data.access.impl;

import com.vanya.data.access.api.UnconfirmedUserDao;
import com.vanya.model.UnconfirmedUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Component;

/**
 * Created by Hladush Ivan
 * on 04.12.16.
 */
@Component
public class UnconfirmedUserDaoMongoImpl implements UnconfirmedUserDao {
    @Autowired
    private MongoOperations mongoOperation;

    @Override
    public UnconfirmedUser getUncofirmedUser(long id) {
        return mongoOperation.findOne(new Query().addCriteria(Criteria.where("id").is(id))
                , UnconfirmedUser.class);
    }

    @Override
    public void saveUncomfirmedUser(UnconfirmedUser user) {
        mongoOperation.save(user);
    }

    @Override
    public void deleteUnconfirmedUser(long id) {
        mongoOperation.remove(new Query().
                addCriteria(Criteria.where("id").is(id)), UnconfirmedUser.class);
    }
}