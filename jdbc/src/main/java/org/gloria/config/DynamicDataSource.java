package org.gloria.config;

import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;

/**
 * Create on 2016/12/7 22:51.
 *
 * @author : gloria.
 */
public class DynamicDataSource extends AbstractRoutingDataSource {
    @Override
    protected Object determineCurrentLookupKey() {
        return DSContextHolder.getContext();
    }
}