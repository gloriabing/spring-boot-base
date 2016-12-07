package org.gloria.config;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.orm.jpa.EntityScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * Create on 2016/12/7 10:26.
 *
 * @author : gloria.
 */
@Configuration
@EnableAutoConfiguration
@EntityScan(basePackages = {"org.gloria.mysql.entity"})
@EnableJpaRepositories(basePackages = {"org.gloria.mysql.dao"})
@EnableTransactionManagement
public class JpaConfig {
}
