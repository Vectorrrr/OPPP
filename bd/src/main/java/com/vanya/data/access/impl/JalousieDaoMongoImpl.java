package com.vanya.data.access.impl;

import com.vanya.data.access.api.JalousieDao;
import com.vanya.model.Jalousie;
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
public class JalousieDaoMongoImpl implements JalousieDao {
    @Autowired
    private MongoOperations mongoOperation;

    @Override
    public void addJalousieForUser(long userId, Jalousie jalousie) {
        //todo think about add jalousies only for exist user
        mongoOperation.save(jalousie);
    }
    @Override
    public Jalousie getJalousie(long userId, long jalousieId){
        return mongoOperation.findOne(new Query().
                        addCriteria(Criteria.where("userId").is(userId)).
                        addCriteria(Criteria.where("id").is(jalousieId)),
                Jalousie.class);
    }
    @Override
    public void removeJalousie(long userId, long jalId) {
        mongoOperation.remove(new Query().
                        addCriteria(Criteria.where("id").is(jalId)).
                        addCriteria(Criteria.where("userId").is(userId)),
                Jalousie.class);
    }

    @Override
    public List<Jalousie> getAllJalousies(long userId) {
        return mongoOperation.find(new Query().
                        addCriteria(Criteria.where("userId").is(userId)),
                        Jalousie.class);
    }

    @Override
    public void changeJalousies(long userId, long id, Jalousie jalousie) {
        addJalousieForUser(userId,jalousie);
    }
}
