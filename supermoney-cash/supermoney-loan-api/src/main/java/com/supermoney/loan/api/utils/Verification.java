package com.supermoney.loan.api.utils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by tangwenchi on 2018/1/22.
 */
public class Verification {

    /**
     * 验证字符串中是否存在特殊字符
     * @param str 字符串
     * @return 存在 true 不存在 false
     */
    public static boolean isSpecialChar(String str) {
        String regEx = "[ _`~!@#$%^&*()+=|{}':;',\\[\\].<>/?~！@#￥%……&*（）——+|{}【】‘；：”“’。，、？]|\n|\r|\t";
        Pattern p = Pattern.compile(regEx);
        Matcher m = p.matcher(str);
        return m.find();
    }
}
