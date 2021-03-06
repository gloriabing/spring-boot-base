package org.gloria.dao;


import org.gloria.entity.User;

import java.util.List;

/**
 * Created by gloria on 2016/12/5.
 */
public interface IUserDao {

    List<User> findAll();

    void save(User user);

    User saveOrUpdate(User user);

    User saveAndReturnKey(User user);

    User findById(Long id);

    User findByName(String name);

}
