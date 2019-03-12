package com.wi.data.clear.utils;

import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class CalculateUtil {

    /**
     * 数据类型
     *  0 字符串 1 整数  2 小数  3 日期  4 时间 5 日期+时间
     */
    final public static Integer VALUE_TYPE_STRING = 0;
    final public static Integer VALUE_TYPE_Long = 1;
    final public static Integer VALUE_TYPE_DECIMAL= 2;
    final public static Integer VALUE_TYPE_DATE = 3;
    final public static Integer VALUE_TYPE_DATE_TIME = 4;

    /**
     * 条件
     *  = 等于、!= 不等于、> 大于、  >=大于等于 、 < 小于、 <= 小于等于、 包含 、 不包含
     */
    final public static Integer VALUE_CONDITION_EQ = 0;
    final public static Integer VALUE_CONDITION_NEQ = 1;
    final public static Integer VALUE_CONDITION_GT = 2;
    final public static Integer VALUE_CONDITION_GT_EQ = 3;
    final public static Integer VALUE_CONDITION_LT = 4;
    final public static Integer VALUE_CONDITION_LT_EQ = 5;
    final public static Integer VALUE_CONDITION_CT = 6;
    final public static Integer VALUE_CONDITION_NCT = 7;


    /**
     * 计算
     * @param val1          用户数据
     * @param val2          对比数据1
     * @param valType       值类型
     * @param valCondition  计算条件
     * @return
     */
    public static boolean cal(Object val1, Object val2 ,Integer valType , Integer valCondition) {

        boolean result = false;

        try {
            if (valType == VALUE_TYPE_STRING){
                result = val1 == null ? false : calString(val1.toString(),val2.toString(),valCondition);
            }
            else if (valType == VALUE_TYPE_Long){
                result = val1 == null ? false : calLong(Long.parseLong(val1.toString()) , val2,valCondition);
            }
            else if (valType == VALUE_TYPE_DECIMAL){
                result = val1 == null ? false : calDecimal(new BigDecimal(val1.toString()), val2,valCondition);
            }
            else if (valType == VALUE_TYPE_DATE ||  valType == VALUE_TYPE_DATE_TIME){
                result = val1 == null ? false : calDate(val1,val2,valType,valCondition);
            }
        }
        catch (Exception e){
            throw new RuntimeException(e.getMessage());
        }

        return result;
    }

    private static boolean calString(String val1, String val2 ,Integer valCondition){
        if (valCondition == VALUE_CONDITION_EQ){
            return val1.equals(val2);
        }
        else if (valCondition == VALUE_CONDITION_NEQ){
            return !val1.equals(val2);
        }
        else if (valCondition == VALUE_CONDITION_CT){
            return contain(val1,val2);
        }
        else if (valCondition == VALUE_CONDITION_NCT){
            return !contain(val1,val2);
        }

        return false;
    }

    private static boolean calLong(Long val1, Object val2 , Integer valCondition){
        if (valCondition == VALUE_CONDITION_EQ){
            return val1.equals(Long.parseLong(val2.toString()));
        }
        else if (valCondition == VALUE_CONDITION_NEQ){
            return !val1.equals(Long.parseLong(val2.toString()));
        }
        else if (valCondition == VALUE_CONDITION_GT){
            return val1.compareTo(Long.parseLong(val2.toString())) > 0;
        }
        else if (valCondition == VALUE_CONDITION_GT_EQ){
            return val1.compareTo(Long.parseLong(val2.toString())) >= 0;
        }
        else if (valCondition == VALUE_CONDITION_LT){
            return val1.compareTo(Long.parseLong(val2.toString())) < 0;
        }
        else if (valCondition == VALUE_CONDITION_LT_EQ){
            return val1.compareTo(Long.parseLong(val2.toString())) <= 0;
        }
        else if (valCondition == VALUE_CONDITION_CT){
            return contain(val1,val2.toString());
        }
        else if (valCondition == VALUE_CONDITION_NCT){
            return !contain(val1,val2.toString());
        }
        return false;
    }

    private static boolean calDecimal(BigDecimal val1, Object val2 , Integer valCondition){
        if (valCondition == VALUE_CONDITION_EQ){
            return val1.equals(new BigDecimal(val2.toString()));
        }
        else if (valCondition == VALUE_CONDITION_NEQ){
            return !val1.equals(new BigDecimal(val2.toString()));
        }
        else if (valCondition == VALUE_CONDITION_GT){
            return val1.compareTo(new BigDecimal(val2.toString())) > 0;
        }
        else if (valCondition == VALUE_CONDITION_GT_EQ){
            return val1.compareTo(new BigDecimal(val2.toString())) >= 0;
        }
        else if (valCondition == VALUE_CONDITION_LT){
            return val1.compareTo(new BigDecimal(val2.toString())) < 0;
        }
        else if (valCondition == VALUE_CONDITION_LT_EQ){
            return val1.compareTo(new BigDecimal(val2.toString())) <= 0;
        }
        else if (valCondition == VALUE_CONDITION_CT){
            return contain(val1,val2.toString());
        }
        else if (valCondition == VALUE_CONDITION_NCT){
            return !contain(val1,val2.toString());
        }
        return false;
    }

    private static boolean calDate(Object val1, Object val2 ,Integer valType , Integer valCondition) throws ParseException {
        SimpleDateFormat dateTimeFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

        Date date1 = null;
        Date date2 = null;
        if (valType == VALUE_TYPE_DATE){
            date1 = getDate(val1.toString());
            date2 = getDate(val2.toString());
        }
        else if (valType == VALUE_TYPE_DATE_TIME){
            date1 = dateTimeFormat.parse(val1.toString());
            date2 = dateTimeFormat.parse(val2.toString());
        }

        if (valCondition == VALUE_CONDITION_EQ){
            return date1.equals(date2);
        }
        else if (valCondition == VALUE_CONDITION_NEQ){
            return !date1.equals(date2);
        }
        else if (valCondition == VALUE_CONDITION_GT){
            return date1.compareTo(date2) > 0;
        }
        else if (valCondition == VALUE_CONDITION_GT_EQ){
            return date1.compareTo(date2) >= 0;
        }
        else if (valCondition == VALUE_CONDITION_LT){
            return date1.compareTo(date2) < 0;
        }
        else if (valCondition == VALUE_CONDITION_LT_EQ){
            return date1.compareTo(date2) <= 0;
        }
        else if (valCondition == VALUE_CONDITION_CT){
            return false;
        }
        else if (valCondition == VALUE_CONDITION_NCT){
            return false;
        }
        return false;
    }

    private static Date getDate(String val){
        String[] str1 = val.split(" ");
        String[] str2 = str1[0].split("-");
        Calendar calendar1 = Calendar.getInstance();
        calendar1.set(Integer.parseInt(str2[0]),Integer.parseInt(str2[1]) - 1,Integer.parseInt(str2[2]));
        return calendar1.getTime();
    }

    private static boolean contain(Object val1 , String val2){
        if (val2 != null){
            String[] vals = val2.split(",");
            for (String val :vals) {
                if (val.equals(val1.toString())){
                    return true;
                }
            }
        }
        return false;
    }








}
