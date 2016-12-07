package org.gloria.mysql.dao;

import org.gloria.mysql.entity.JpaUser;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

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

}
