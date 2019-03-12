package com.supermoney.loan.mg.configurer;

import com.supermoney.loan.mg.utils.Constants;
import org.apache.log4j.Logger;
import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;

public class DynamicDataSource extends AbstractRoutingDataSource {
    private static final Logger log = Logger.getLogger(DynamicDataSource.class);

    @Override
    protected Object determineCurrentLookupKey() {
        String satasource = DataSourceContextHolder.getDB() == null ? Constants.Database.MASTER : DataSourceContextHolder.getDB();
        log.info("数据源为{ " + satasource + " }");

        return DataSourceContextHolder.getDB();
    }

}