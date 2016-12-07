package org.gloria.web;

import org.gloria.mongodb.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.gloria.mongodb.dao.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * Create on 2016/12/6 18:29.
 *
 * @author : gloria.
 */
@RestController
@RequestMapping("/mongo")
public class MongoController {

    @Resource(name = "mongoUserDao")
    private UserDao userDao;

    @Resource(name = "userRepositoryMongo")
    private UserRepository userRepository;

    @RequestMapping(value = "/user/save", method = RequestMethod.PUT)
    public Object save(@RequestBody User user) {
        userDao.save(user);
        return "success";
    }

    @RequestMapping(value = "/user/query", method = RequestMethod.GET)
    public List<User> findAll() {
//        return userDao.findAll();
        return userRepository.findAll();
    }

    @RequestMapping(value = "/user/query/{id}", method = RequestMethod.GET)
    public User findById(@PathVariable String id) {
//        return userDao.findById(id);
        return userRepository.findById(id);
    }

    @RequestMapping(value = "/user/query", params = "name", method = RequestMethod.GET)
    public User findByName(@RequestParam String name) {
        return userDao.findByName(name);
    }
}
