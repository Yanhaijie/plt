package com.supermoney.loan.mg.service;
import com.github.pagehelper.PageInfo;
import com.supermoney.loan.mg.entity.SExchangeRate;
import com.supermoney.loan.mg.utils.Result;
import com.supermoney.loan.mg.utils.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;


/**
 * Created by xionghuifeng on 2018/04/01.
 */
public interface SExchangeRateService extends Service<SExchangeRate> {


    /**
     * 查询
     * @param param
     * @return
     */
    public List<SExchangeRate> getList(Map<String,Object> param);
    /**
     * 分页获取
     * @param page
     * @param size
     * @param param
     * @return
     */
    public PageInfo getByPage(int page, int size, Map<String,Object> param);
    /**
     * 把美元转换成对应国家的币值
     * @param currency
     * @param amount
     * @param hasFormat
     * @return
     */
    public BigDecimal usdToByCountry(String currency, BigDecimal amount , boolean hasFormat);

    /**
     * 把某国家的币值转换成美元
     * @param currency
     * @param amount
     * @return
     */
    public BigDecimal toUsdByCountry(String currency,BigDecimal amount);
    /**
     * 获取美元对应的转换汇率
     * @param currency
     * @return
     */
    public  SExchangeRate getUsdByCountry(String currency);
    /**
     * 根据汇率信息转换
     * @param rate
     * @param amount
     * @return
     */
    public BigDecimal valByRate(SExchangeRate rate,BigDecimal amount);
    /**
     * 按国家对货币进行格式化
     * @param rate
     * @param amount
     * @return
     */
    public BigDecimal valFormatCountry(SExchangeRate rate,BigDecimal amount);

    /**
     * 获取国家货币信息
     * @param county
     * @return
     */
    public Result getCurrency(String county);

    /**
     * 印尼盾取整
     * @param amount
     * @return
     */
    public   String  valToInt(BigDecimal amount);

    /**
     * 印尼币值转换成美元
     * @param amount
     * @return
     */
    public BigDecimal indoneslaToUsd(BigDecimal amount);
    /**
     * 美元值转换成印尼币
     * @param amount
     * @param hasFormat
     * @return
     */
    public  BigDecimal usdToIndonesla(BigDecimal amount,boolean hasFormat);
    /**
     * 印尼币格式
     * @param amount
     * @return
     */
    public  String indonwslaFormat(BigDecimal amount);
}
