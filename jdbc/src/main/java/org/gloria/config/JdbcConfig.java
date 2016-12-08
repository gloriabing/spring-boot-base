package org.gloria.config;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.jdbc.DataSourceBuilder;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by gloria on 2016/12/5.
 */
@Configuration
public class JdbcConfig {

    @Bean(name = "base_datasource")
    @Qualifier("base_datasource")
    @ConfigurationProperties(prefix = "mysql.datasource")
    public DataSource dataSource() {
        return DataSourceBuilder.create().build();
    }


    @Bean(name = "second_datasource")
    @Qualifier("second_datasource")
    @ConfigurationProperties(prefix="mysql.second_datasource")
    public DataSource secondDataSource() {
        return DataSourceBuilder.create().build();
    }


    @Bean
    @Primary
    public DynamicDataSource  dynamicDataSource() {
        DynamicDataSource dds=new DynamicDataSource();
        Map<Object, Object> targetDataSources =new HashMap<Object, Object>();
        targetDataSources.put("base_datasource", dataSource());
        targetDataSources.put("second_datasource", secondDataSource());
        dds.setTargetDataSources(targetDataSources);
        return dds;
    }

    @Bean(name = "jdbcTemplate")
    public JdbcTemplate jdbcTemplate() {
        return new JdbcTemplate(dynamicDataSource());
    }


}
