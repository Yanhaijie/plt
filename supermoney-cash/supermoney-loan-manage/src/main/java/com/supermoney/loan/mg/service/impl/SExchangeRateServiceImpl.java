package com.supermoney.loan.mg.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.supermoney.loan.mg.dao.SExchangeRateMapper;
import com.supermoney.loan.mg.entity.SExchangeRate;
import com.supermoney.loan.mg.service.SExchangeRateService;
import com.supermoney.loan.mg.utils.AbstractService;
import com.supermoney.loan.mg.utils.Constants;
import com.supermoney.loan.mg.utils.Result;
import com.supermoney.loan.mg.utils.ResultGenerator;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * Created by xionghuifeng on 2018/04/01.
 */
@Service
@Transactional
public class SExchangeRateServiceImpl extends AbstractService<SExchangeRate> implements SExchangeRateService {
    @Resource
    private SExchangeRateMapper sExchangeRateMapper;

    private SExchangeRate indonesiaExchange = null;

    /**
     * 查询应用
     *
     * @param param
     * @return
     */
    public List<SExchangeRate> getList(Map<String, Object> param) {
        return sExchangeRateMapper.selectList(param);
    }

    /**
     * 分页获取
     *
     * @param page
     * @param size
     * @param param
     * @return
     */
    public PageInfo getByPage(int page, int size, Map<String, Object> param) {
        PageHelper.startPage(page, size);
        List<SExchangeRate> list = getList(param);
        PageInfo pageInfo = new PageInfo(list);
        return pageInfo;
    }

    /**
     * 获取印尼兑美元汇率
     *
     * @return
     */
    public SExchangeRate getIndonesiaExchange() {
        if (indonesiaExchange == null) {
            indonesiaExchange = getUsdByCountry(Constants.Country.INDONESIA_CR);
        }
        return indonesiaExchange;
    }

    /**
     * 把美元转换成对应国家的币值
     *
     * @param currency
     * @param amount
     * @param hasFormat
     * @return
     */
    @Override
    public BigDecimal usdToByCountry(String currency, BigDecimal amount, boolean hasFormat) {
        SExchangeRate rate = getUsdByCountry(currency);
        if (rate == null) {
            return new BigDecimal(0);
        }
        BigDecimal val = amount.multiply(rate.getExchangeVal()).setScale(2, BigDecimal.ROUND_HALF_UP);
        return hasFormat ? valFormatCountry(rate, val) : val;
    }

    /**
     * 把某国家的币值转换成美元
     *
     * @param currency
     * @param amount
     * @return
     */
    @Override
    public BigDecimal toUsdByCountry(String currency, BigDecimal amount) {
        SExchangeRate rate = getUsdByCountry(currency);
        if (rate == null) {
            return new BigDecimal(0);
        }

        BigDecimal usd = amount.divide(rate.getExchangeVal(), 6, BigDecimal.ROUND_HALF_UP);
        return usd;
    }

    /**
     * 印尼币值转换成美元
     *
     * @param amount
     * @return
     */
    @Override
    public BigDecimal indoneslaToUsd(BigDecimal amount) {
        SExchangeRate rate = getIndonesiaExchange();
        if (rate == null) {
            return new BigDecimal(0);
        }
        BigDecimal usd = amount.divide(rate.getExchangeVal(), 6, BigDecimal.ROUND_HALF_UP);
        return usd;
    }

    /**
     * 美元值转换成印尼币
     *
     * @param amount
     * @param hasFormat
     * @return
     */
    @Override
    public BigDecimal usdToIndonesla(BigDecimal amount, boolean hasFormat) {
        SExchangeRate rate = getIndonesiaExchange();
        if (rate == null) {
            return new BigDecimal(0);
        }
        BigDecimal val = amount.multiply(rate.getExchangeVal()).setScale(2, BigDecimal.ROUND_HALF_UP);
        return hasFormat ? valFormatCountry(rate, val) : val;
    }

    /**
     * 获取美元对应的转换汇率
     *
     * @param currency
     * @return
     */
    @Override
    public SExchangeRate getUsdByCountry(String currency) {
        return getExchangeRate(Constants.Country.AMERICA_CR, currency);
    }

    /**
     * 获取汇率转换
     *
     * @param first
     * @param second
     * @return
     */
    public SExchangeRate getExchangeRate(String first, String second) {
        SExchangeRate rate = new SExchangeRate();
        rate.setFirstCurrency(first);
        rate.setSecondCurrency(second);
        rate.setExchangeStatus(0);
        return sExchangeRateMapper.selectOne(rate);
    }

    /**
     * 获取所有汇率信息
     *
     * @return
     */
    public List<SExchangeRate> getAllRate() {
        return null;
    }

    /**
     * 根据汇率信息转换
     *
     * @param rate
     * @param amount
     * @return
     */
    @Override
    public BigDecimal valByRate(SExchangeRate rate, BigDecimal amount) {
        if (rate == null) {
            return new BigDecimal(0);
        }
        BigDecimal val = amount.multiply(rate.getExchangeVal()).setScale(2, BigDecimal.ROUND_HALF_UP);
        return valFormatCountry(rate, val);
    }

    /**
     * 按国家对货币进行格式化
     *
     * @param rate
     * @param amount
     * @return
     */
    @Override
    public BigDecimal valFormatCountry(SExchangeRate rate, BigDecimal amount) {
        //印尼盾转换格式: 去除小数,除于1000,浮点三位数
        if (rate.getSecondCurrency().equals(Constants.Country.INDONESIA_CR)) {
            BigDecimal thousand = new BigDecimal(1000);
            amount.setScale(1, BigDecimal.ROUND_HALF_UP);
            amount = amount.divide(thousand).setScale(3, BigDecimal.ROUND_HALF_UP);
        }
        return amount;
    }

    /**
     * 获取国家货币信息
     *
     * @param county
     * @return
     */
    @Override
    public Result getCurrency(String county) {
        SExchangeRate exchangeRate = getUsdByCountry(county);
        Map<String, Object> result = new HashMap<>(Constants.App.MAP_MIN_SIZE);
        if (exchangeRate != null) {
            result.put("symbol", exchangeRate.getSymbol());
            result.put("currency", exchangeRate.getSecondCurrency());
            result.put("exchange", exchangeRate.getExchangeVal());
        }

        return ResultGenerator.genSuccessResult(result);
    }

    /**
     * 印尼盾取整
     *
     * @param amount
     * @return
     */
    @Override
    public String valToInt(BigDecimal amount) {
        BigDecimal one = new BigDecimal(1);
        if (amount.compareTo(one) < 0) {
            if (!(amount.compareTo(BigDecimal.ZERO) == 0)) {
                BigDecimal thousand = new BigDecimal(1000);
                amount = amount.multiply(thousand);
            }
            return String.valueOf(amount.intValue());
        }
        return amount.toString();
    }

    /**
     * 印尼币格式
     *
     * @param amount
     * @return
     */
    @Override
    public String indonwslaFormat(BigDecimal amount) {
        SExchangeRate rate = getIndonesiaExchange();
        return moneyFormatCountry(rate, amount);
    }

    /**
     * 金钱格式转换
     *
     * @param rate
     * @param amount
     * @return
     */
    public String moneyFormatCountry(SExchangeRate rate, BigDecimal amount) {

        if (rate.getSecondCurrency().equals(Constants.Country.INDONESIA_CR)) {
            amount.setScale(1, BigDecimal.ROUND_HALF_UP);
            String as = amount.toString();
            StringBuffer sb = new StringBuffer(as);
            int gs = as.length() / 3;
            for (int i = 1; i <= gs; i++) {
                int idx = (as.length() - i * 3);
                if (idx > 0) {
                    sb.insert((as.length() - i * 3), ".");
                }
            }
            return sb.toString();
        } else {

            return amount.toString();

        }
    }
}
