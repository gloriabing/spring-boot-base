package org.gloria.mysql.dao;

import org.gloria.mongodb.entity.User;
import org.gloria.mysql.entity.JpaUser;
import org.springframework.data.repository.CrudRepository;

import javax.transaction.Transactional;

/**
 * Create on 2016/12/7 10:25.
 *
 * @author : gloria.
 */
@Transactional
public interface UserRepository extends CrudRepository<JpaUser, String> {

    public User findById(Long id);

}
