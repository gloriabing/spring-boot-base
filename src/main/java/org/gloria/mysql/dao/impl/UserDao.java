package org.gloria.mysql.dao.impl;

import main.org.gloria.mysql.dao.IUserDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementSetter;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.gloria.mysql.entity.*;
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

    @Override
    public List<User> findAll() {
        String sql = "select id,name,pwd from user";
        return jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(User.class));
    }

    @Override
    public void save(User user) {
        String sql = "insert into user(name,pwd) values(?,?)";
        jdbcTemplate.update(sql, preparedStatement -> {
            preparedStatement.setString(1, user.getName());
            preparedStatement.setString(2, user.getPwd());
        });

    }

    @Override
    public User saveAndReturnKey(User user) {
        String sql = "insert into user(name,pwd) values(?,?)";

        KeyHolder keyHolder = new GeneratedKeyHolder();

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
        return new NamedParameterJdbcTemplate(jdbcTemplate).query(sql, params, new BeanPropertyRowMapper<>(User.class)).get(0);
    }

}
