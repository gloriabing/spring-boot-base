package org.gloria.mongodb.dao;


import com.mongodb.BasicDBObject;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;
import org.bson.types.ObjectId;
import org.gloria.mongodb.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Create on 2016/12/6 18:15.
 *
 * @author : gloria.
 *
 * 直接用原生mongoTemplate进行mongo操作
 *
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

    public User findById1(String id) {
        return mongoTemplate.findOne(Query.query(Criteria.where("_id").is(new ObjectId(id))), User.class);
    }

    public User findByName(String name) {
        DBCollection collection = mongoTemplate.getCollection("db.user");

        BasicDBObject basicDBObject = new BasicDBObject("name", name);

        DBCursor cursor = collection.find(basicDBObject);

        User user = new User();
        while (cursor.hasNext()) {
            DBObject dbObject = cursor.next();
            user.setId((ObjectId) dbObject.get("_id"));
            user.setName(dbObject.get("name").toString());
            user.setPwd(dbObject.get("pwd").toString());

        }
        return user;
    }

    public List<User> findAll() {
        return mongoTemplate.findAll(User.class);
    }
}
