package org.gloria.mongodb.dao;


import org.bson.types.ObjectId;
import org.gloria.mongodb.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Create on 2016/12/6 18:15.
 *
 * @author : gloria.
 */
@Repository(value = "mongoUserDao")
public class UserDao {

    @Autowired
    private MongoTemplate mongoTemplate;

    public void save(User user) {
        mongoTemplate.save(user);
    }

    public User findById(String id) {
        return mongoTemplate.findById(new ObjectId(id), User.class);
    }

    public List<User> findAll() {
        return mongoTemplate.findAll(User.class);
    }
}
