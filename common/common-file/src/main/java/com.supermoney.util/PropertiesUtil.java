package com.supermoney.util;

import java.io.InputStreamReader;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
/**
 * @ClassName: PropertiesUtil
 * @Author: yanhaijie
 * @CreateDate: 2019-02-23 15:43
 * @Version: 1.0
 */
public class PropertiesUtil {

        private static Properties props;

//    public PropertiesUtil(String fileName) {
//        readProperties(fileName);
//    }

        static{
            readProperties("application.properties");
        }

        /**
         * 加载配置文件
         *
         * @param fileName
         */
        private static void readProperties(String fileName) {
            try {
                props = new Properties();
                InputStreamReader inputStream = new InputStreamReader(
                        PropertiesUtil.class.getClassLoader().getResourceAsStream(fileName), "UTF-8");
                props.load(inputStream);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        /**
         * 根据key读取对应的value
         *
         * @param key
         * @return
         */
        public static String get(String key) {
            return props.getProperty(key);
        }

        /**
         * 得到所有的配置信息
         *
         * @return
         */
        public static  Map<?, ?> getAll() {
            Map<String, String> map = new HashMap<String, String>();
            Enumeration<?> enu = props.propertyNames();
            while (enu.hasMoreElements()) {
                String key = (String) enu.nextElement();
                String value = props.getProperty(key);
                map.put(key, value);
            }
            return map;
        }

}
