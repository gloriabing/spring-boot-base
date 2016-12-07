package org.gloria.mongodb.dao;


import org.gloria.mongodb.entity.User;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Create on 016/12/6 17:53.
 * @author : gloria.
 * @since
 * @version
 */
@Repository(value = "userRepository")
public interface UserRepository extends MongoRepository<User, String> {

    @Query(value = "{}")
    List<User> findAll();

    @Query(value = "{'_id' : '?0'}")
    User findById(String id);

}
