package org.gloria.dao;


import org.bson.types.ObjectId;
import org.gloria.entity.User;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Create on 2016/12/6 18:15.
 *
 * @author : gloria.
 *
 * 集成自定义mongoTemolate进行mongo操作
 *
 */
@Repository(value = "customUserDao")
public class CustomUserDao extends CustomMongoTemplate {

    public void save(User user) {

        super.save(user);
    }

    public User findById(String id) {
        Query query = Query.query(Criteria.where("_id").is(new ObjectId(id)));
        return super.findOne(query, User.class);
    }

    public User findByName(String name) {
        Query query = Query.query(Criteria.where("name").is(name));
        return super.findOne(query, User.class);
    }

    public List<User> findAll() {
        return find(new Query(), User.class);
    }

}
