package org.gloria.web;


import org.gloria.dao.IUserDao;
import org.gloria.entity.User;
import org.springframework.format.annotation.NumberFormat;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by gloria on 2016/12/5.
 */
@RestController
@RequestMapping("/jdbc")
public class JdbcController {

    @Resource(name = "userDao")
    private IUserDao userDao;


    /**
     * 保存
     * @param user
     *      put传递body如：{"name":"gloria","pwd":"gloria"}
     *      注意Content-Type必须是application/json
     * @return
     */
    @RequestMapping(value = "/user/save" , method = RequestMethod.PUT)
    public Object save(@RequestBody User user) {

        userDao.saveAndReturnKey(user);

        return user;
    }

    @RequestMapping(value = "/user/update", method = RequestMethod.PUT)
    public Object update(@RequestBody User user) {
        return userDao.saveOrUpdate(user);
    }

    @RequestMapping(value = "/user/query", method = RequestMethod.GET)
    public List<User> findAll() {
        return userDao.findAll();
    }

    @RequestMapping(value = "/user/query/{id}", method = RequestMethod.GET)
    public User findOne(@PathVariable @NumberFormat Long id) {
        return userDao.findById(id);
    }
}
