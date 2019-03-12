package com.supers.p2p.assets.utils;

import com.supers.p2p.assets.entity.vo.CurrentInfo;
import io.swagger.models.auth.In;
import org.apache.commons.lang.StringUtils;

public class UserUtils {
    public static final String SESSION_USER = "user";
    private  static  String key="s1p2m3o4n5e6y7";

    /**
     * 生成AppSecret
     * @param userId
     * @param companyId
     * @param userType
     * @return
     */
    public  static String toAppSecret(Integer userId,Integer companyId,Integer userType){
        try {
            String appStr=userId.toString()+"|"+companyId.toString()+"|"+userType.toString();
            EncryptUtil des = new EncryptUtil(key, "utf-8");
            appStr=des.encode(appStr);
            appStr=NomalUntil.symbolEncoder(appStr);
            return  appStr;
        }catch (Exception ex){
            ex.printStackTrace();
        }
        return  null;
    }

    /**
     * 获取AppSecret中用户信息
     * @param appSecret
     * @return 0:userId 1:mobile
     */
    public  static  String[] deAppSecret(String appSecret){
        try {
            EncryptUtil des = new EncryptUtil(key, "utf-8");
            appSecret= NomalUntil.symbolDencoder(appSecret);
            String  deStr=des.decode(appSecret);
            return  deStr.split("\\|");
        }catch (Exception ex){
            ex.printStackTrace();
        }
        return  null;
    }

    /**
     * 获取当前用户信息
     *
     * @return
     */
    public static CurrentInfo getCurrentInfo() {
        try {
            String appSecret= RequestUntil.request().getParameter("appSecret");
            if(StringUtils.isBlank(appSecret)){
                return  null;
            }
            appSecret=appSecret.indexOf("%")>-1? NomalUntil.decoderStr(appSecret):appSecret;
            String[] appInfo=deAppSecret(appSecret);
            CurrentInfo info=new CurrentInfo();
            info.setUserId(Integer.valueOf(appInfo[0]));
            info.setCompanyId(Integer.valueOf(appInfo[1]));
            info.setUserType(Integer.valueOf(appInfo[2]));
            return info;

        }catch (Exception ex){
            ex.printStackTrace();

        }
        return  null;
    }

    /**
     * 获取当前AccessToken
     * @return
     */
    public  static  String getCurrentAccessToken(){
        String accssToken= RequestUntil.request().getParameter("access_token");
        return  accssToken;
    }

}