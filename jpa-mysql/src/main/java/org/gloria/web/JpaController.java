package org.gloria.web;


import org.gloria.dao.UserCustomRepository;
import org.gloria.dao.UserRepository;
import org.gloria.entity.JpaUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.format.annotation.NumberFormat;
import org.springframework.web.bind.annotation.*;


/**
 * Created by gloria on 2016/12/5.
 */
@RestController
@RequestMapping("/jpa")
public class JpaController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserCustomRepository userCustomRepository;


    @RequestMapping(value = "/user/query/{id}", method = RequestMethod.GET)
    public JpaUser findOneByJpa(@PathVariable @NumberFormat Long id) {
        return userRepository.findById(id);
    }

    /**
     * 分页列出所有记录
     * @param pageable
     * @return
     */
    @RequestMapping(value = "/user/query/", method = RequestMethod.GET)
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
