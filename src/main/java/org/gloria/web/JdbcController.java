package org.gloria.web;


import org.gloria.mysql.dao.IUserDao;
import org.gloria.mysql.dao.UserCustomRepository;
import org.gloria.mysql.dao.UserRepository;
import org.gloria.mysql.entity.JpaUser;
import org.gloria.mysql.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
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

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserCustomRepository userCustomRepository;

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

    @RequestMapping(value = "/user/query", method = RequestMethod.GET)
    public List<User> findAll() {
        return userDao.findAll();
    }

    @RequestMapping(value = "/user/query/{id}", method = RequestMethod.GET)
    public User findOne(@PathVariable @NumberFormat Long id) {
        return userDao.findById(id);
    }

    @RequestMapping(value = "/user/query/jpa/{id}", method = RequestMethod.GET)
    public JpaUser findOneByJpa(@PathVariable @NumberFormat Long id) {
        return userRepository.findById(id);
    }

    /**
     * 分页列出所有记录
     * @param pageable
     * @return
     */
    @RequestMapping(value = "/user/query/jpa", method = RequestMethod.GET)
    public Iterable<JpaUser> findAllByJpa(@PageableDefault(value = 15, sort = {"id"}, direction = Sort.Direction.DESC)
                                                  Pageable pageable) {
        return userCustomRepository.findAll(pageable);

    }

    /**
     * 根据条件查询
     * @param name
     * @return
     */
    @RequestMapping(value = "/user/query", params = "name", method = RequestMethod.GET)
    public JpaUser findByName(@RequestParam String name) {
        return userCustomRepository.findByName(name);
    }

    /**
     * 根据id删除记录
     * @param id
     * @return
     */
    @RequestMapping(value = "/user/delete/{id}", method = RequestMethod.DELETE)
    public Object deleteById(@PathVariable @NumberFormat Long id) {
        return userCustomRepository.deleteById(id);
    }
}
