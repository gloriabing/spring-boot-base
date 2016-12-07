package org.gloria.mysql.dao;

import org.gloria.mysql.entity.JpaUser;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;

import javax.transaction.Transactional;

/**
 * Create on 2016/12/7 15:49.
 *
 * @author : gloria.
 */
public interface UserCustomRepository extends PagingAndSortingRepository<JpaUser, Integer> {

    /**
     * from后面一定要为自定义的实体类，实体类中映射对应的table
     * @param pageable
     * @return
     */
    @Query("select u from JpaUser u")
    Page<JpaUser> findAll(Pageable pageable);

    @Query("select u from JpaUser u where u.name=:name")
    JpaUser findByName(@Param("name") String name);

    @Query("select u from JpaUser u where u.name=?")
    JpaUser findByName1(String name);

    @Transactional
    @Modifying
    @Query("delete from JpaUser u where u.id=:id")
    Integer deleteById(@Param("id") Long id);



}
