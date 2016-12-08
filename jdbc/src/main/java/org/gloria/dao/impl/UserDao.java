package org.gloria.dao.impl;


import org.gloria.config.DSContextHolder;
import org.gloria.dao.IUserDao;
import org.gloria.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.sql.PreparedStatement;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by gloria on 2016/12/5.
 */
@Repository
public class UserDao implements IUserDao {

    @Autowired
    private JdbcTemplate jdbcTemplate;
    private final static String DATASOURCE="base_datasource";

    @Override
    public List<User> findAll() {
        String sql = "select id,name,pwd from user";
        DSContextHolder.setContext(DATASOURCE);
        return jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(User.class));
    }

    @Override
    public void save(User user) {
        String sql = "insert into user(name,pwd) values(?,?)";
        DSContextHolder.setContext(DATASOURCE);
        jdbcTemplate.update(sql, preparedStatement -> {
            preparedStatement.setString(1, user.getName());
            preparedStatement.setString(2, user.getPwd());
        });

    }

    @Override
    public User saveOrUpdate(User user) {
        User u = findByName(user.getName());
        if (null != u) {
            //更新
            String sql = "update user set name = :name, pwd = :pwd where id = :id";
            Map<String, Object> params = new HashMap<>();
            params.put("name", user.getName());
            params.put("pwd", user.getPwd());
            params.put("id", u.getId());

            DSContextHolder.setContext(DATASOURCE);
            new NamedParameterJdbcTemplate(jdbcTemplate).update(sql, params);
            user.setId(u.getId());

            return user;
        } else {
            //保存
            return saveAndReturnKey(user);
        }

    }

    @Override
    public User saveAndReturnKey(User user) {
        String sql = "insert into user(name,pwd) values(?,?)";

        KeyHolder keyHolder = new GeneratedKeyHolder();

        DSContextHolder.setContext(DATASOURCE);
        jdbcTemplate.update(connection -> {
            PreparedStatement ps = connection.prepareStatement(sql, new String[]{"id"});
            ps.setString(1, user.getName());
            ps.setString(2, user.getPwd());
            return ps;
        }, keyHolder);

        List<Map<String, Object>> keyList = keyHolder.getKeyList();
        user.setId((Long) keyList.get(0).get("GENERATED_KEY"));
        return user;
    }

    @Override
    public User findById(Long id) {
        String sql = "select id,name,pwd from user where id = :id";
        Map<String, Long> params = new HashMap<>();
        params.put("id", id);
        DSContextHolder.setContext(DATASOURCE);
        return new NamedParameterJdbcTemplate(jdbcTemplate).query(sql, params, new BeanPropertyRowMapper<>(User.class)).get(0);
    }

    @Override
    public User findByName(String name) {
        String sql = "select id,name,pwd from user where name = :name";
        Map<String, String> params = new HashMap<>();
        params.put("name", name);
        DSContextHolder.setContext(DATASOURCE);
        return new NamedParameterJdbcTemplate(jdbcTemplate).query(sql, params, new BeanPropertyRowMapper<>(User.class)).get(0);
    }

}
