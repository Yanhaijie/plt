package com.supermoney.loan.mg.configurer;

import org.apache.log4j.Logger;

public class DataSourceContextHolder {
    public static final Logger log = Logger.getLogger(DataSourceContextHolder.class);


    private static final ThreadLocal<String> contextHolder = new ThreadLocal<>();

    // 设置数据源名
    public static void setDB(String dbType) {
        log.info("切换到{ "+ dbType +" }数据源");
        contextHolder.set(dbType);
    }

    // 获取数据源名
    public static String getDB() {
        return (contextHolder.get());
    }

    // 清除数据源名
    public static void clearDB() {
        contextHolder.remove();
    }
}
