package com.supermoney.loan.api.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.supermoney.loan.api.dao.SBountyLoanMapper;
import com.supermoney.loan.api.dao.SBountyMapper;
import com.supermoney.loan.api.dao.SUserMapper;
import com.supermoney.loan.api.dao.SVoucherUserRecordMapper;
import com.supermoney.loan.api.entity.SBounty;
import com.supermoney.loan.api.entity.SBountyLoan;
import com.supermoney.loan.api.entity.SExchangeRate;
import com.supermoney.loan.api.entity.SUser;
import com.supermoney.loan.api.entity.vo.CalculationVo;
import com.supermoney.loan.api.entity.vo.DropVo;
import com.supermoney.loan.api.entity.vo.SBountyVo;
import com.supermoney.loan.api.entity.vo.SVoucherVo;
import com.supermoney.loan.api.service.SBountyService;
import com.supermoney.loan.api.service.SExchangeRateService;
import com.supermoney.loan.api.utils.*;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.math.BigDecimal;
import java.util.*;
import java.util.concurrent.TimeUnit;

import static com.supermoney.loan.api.utils.NomalUntil.*;


/**
 * Created by xionghuifeng on 2018/01/04.
 */
@Service
@Transactional
public class SBountyServiceImpl extends AbstractService<SBounty> implements SBountyService {

    @Resource
    private SBountyMapper sBountyMapper;
    @Resource
    private AppProperties appProperties;
    @Resource
    private SUserMapper sUserMapper;
    @Resource
    private SBountyLoanMapper sBountyLoanMapper;
    @Resource
    private SVoucherUserRecordMapper sVoucherUserRecordMapper;
    @Resource
    private SExchangeRateService sExchangeRateService;
    @Resource
    private RedisTemplate redisTemplate;

    private static final Logger logger = LoggerFactory.getLogger(SBountyServiceImpl.class);
    /**
     * 查询应用
     * @param param
     * @return
     */
    public List<SBounty> getList(Map<String,Object> param)
    {
        return  sBountyMapper.selectList(param);
    }

    /**
     * 分页获取
     * @param page
     * @param size
     * @param param
     * @return
     */
    public PageInfo getByPage(int page, int size, Map<String,Object> param){
        PageHelper.startPage(page, size);
        List<SBounty> list =getList(param);
        PageInfo pageInfo = new PageInfo(list);
        return pageInfo;
    }

    /**
     * 获取赏金任务列表
     * @param page
     * @param size
     * @param userId
     * @return
     */
    public Result getBountyList(int page, int size,Integer userId){

        if(userId==null){
            userId=Constants.PLATFORM_USERID;
        }
        // PageHelper.startPage(page, size);
        List<SBountyVo> vo=new ArrayList<>();
        Map<String,Object> param= getPageMap(page,size);
        param.put("bountyStatus",1);//1 发布中
        //加入国家编码查询
        Map<String,String> reqHeaders= RequestUntil.getUserAgentParams();
        String areaCode=reqHeaders.get("COUNTRYID");
        if(StringUtils.isNotEmpty(areaCode)){
            param.put("areaCode",areaCode);
        }else{
            param.put("areaCode","480");//默认是菲律宾
        }
        List<SBounty> list =getList(param);
        //List<SVoucherVo> voucher=getCanVoucher(userId);
        SExchangeRate sExchangeRate = sExchangeRateService.getUsdByCountry(Constants.Country.PHP_CR);
        BigDecimal exChangeVal = sExchangeRate.getExchangeVal();
        List<SVoucherVo> voucher=new ArrayList<>();
        for(SBounty item:list){
            SBountyVo bounty=doBountyVo(item,userId,Constants.SOURCE_APP,voucher,reqHeaders);
            bounty.setExChangeVal(exChangeVal);
            vo.add(bounty);
        }
        return ResultGenerator.genSuccessResult(vo);
    }

    /**
     * 整理赏金任务信息
     * @param item
     * @param userId
     * @param source
     * @param voucher
     * @return
     */
    public  SBountyVo doBountyVo(SBounty item,Integer userId,String source, List<SVoucherVo> voucher,Map<String,String> reqHeaders){

        SBountyVo bounty=new SBountyVo();
        bounty.setId(item.getId());
        bounty.setAdsPic(objString(item.getAdsPic()));
        bounty.setAdsIco(objString(item.getAdsIco()));
        bounty.setAdsType(item.getAdsType());
        bounty.setAdsId(objString(item.getAdsId()));
        bounty.setUserId(userId.toString());
        bounty.setBountyName(item.getBountyName());
        bounty.setBountyDsc(item.getBountyDsc());
        String clickId=NomalUntil.encodeClickId(userId,item.getId(),item.getAdsId(),appProperties.getAdsChId(),source);
        String adsUrl=item.getAdsUrl()+"&ch_subid="+userId+"&aff_sub="+clickId;
        bounty.setAdsUrl(adsUrl);
        //赏金小数2位
        BigDecimal bm=decimalNull(item.getBountyMoney()).setScale(2,BigDecimal.ROUND_DOWN);
        bounty.setBountyMoney(bm);
        bounty.setCreateTime(item.getUpdateTime());
        //抵用券整理
        List<SVoucherVo> itemVouchers=new ArrayList<>();
//        for (SVoucherVo vh : voucher) {
//            if (StringUtils.isBlank(vh.getBountyIds())) {
//                itemVouchers.add(vh);
//            } else if (StringUtils.isNotBlank(vh.getBountyIds()) && vh.getBountyIds().indexOf(item.getId().toString()) > -1) {
//                itemVouchers.add(vh);
//            }
//        }

        bounty.setVouchers(itemVouchers);
        String code = "app:"+reqHeaders.get("PARAMS");
        String shareCode = NomalUntil.encodeShareCode(code);
        String sharUrl= appProperties.getH5Sharesms()+"?userId="+userId+"&bountyId="+item.getId()+"&shareCode="+shareCode;
        bounty.setSharUrl(sharUrl);

        bounty.setLoanMinMoney(decimalNull(item.getLoanMinMoney()));
        bounty.setLoanMaxMoney(decimalNull(item.getLoanMaxMoney()));
        bounty.setLoanMinRate(decimalNull(item.getLoanMinRate()));
        bounty.setLoanMaxRate(decimalNull(item.getLoanMaxRate()));

        bounty.setLoanLimit(integerNull( item.getLoanLimit()));
        bounty.setLoanLimitMax(integerNull(item.getLoanLimitMax()));
        bounty.setLoanLimitUnit(integerNull(item.getLoanLimitUnit()));
        bounty.setLendersLimit(integerNull(item.getLendersLimit()));
        bounty.setLendersLimitUnit(integerNull(item.getLendersLimitUnit()));

        bounty.setLimitDsc(item.getLimitDsc());
        bounty.setLendersDay(item.getLendersDsc());
        bounty.setScore(decimalNull(item.getBountyScore()));
        bounty.setDayMoney(decimalNull(item.getDayMoney()));
        bounty.setBountyCompany(objString(item.getBountyCompany()));

        bounty.setLoanSimpleDsc(objString(item.getLoanSimpleDsc()));
        bounty.setLoanDsc(objString(item.getLoanDsc()));
        bounty.setProjectDsc(objString(item.getProjectDsc()));

        bounty.setFormatBountyMoney(sExchangeRateService.usdToIndoneslaFormat(bounty.getBountyMoney()));
        bounty.setFormatDayMoney(sExchangeRateService.usdToIndoneslaFormat(bounty.getDayMoney()));


        return  bounty;
    }


    /**
     * 获取H5赏金任务介绍
     * @param userId
     * @param bountyId
     * @return
     */
    public  Result getSharesms(String userId, String bountyId){

        if(StringUtils.isBlank(userId) || "null".equals(userId) || StringUtils.isBlank(bountyId) || "null".equals(bountyId)){
            return  ResultGenerator.genFailResult(" param is null");
        }
        SUser user=sUserMapper.selectByPrimaryKey(Integer.valueOf(userId));
        if(user==null){
            return  ResultGenerator.genFailResult("user is null");
        }
        SBounty bounty=sBountyMapper.selectByPrimaryKey(Integer.valueOf(bountyId));
        if(bounty==null){
            return  ResultGenerator.genFailResult("bounty is null");
        }
        List<SVoucherVo> voucher=new ArrayList<>();
        SBountyVo vo=doBountyVo(bounty,user.getId(),Constants.SOURCE_SHAR,voucher,RequestUntil.getUserAgentParams());
        Map<String,Object>   param=new HashMap<>();
        param.put("UserPhone",user.getMobile());
        param.put("UserName",user.getUserName());
        param.put("adsUrl",vo.getAdsUrl());
        param.put("sharUrl",vo.getSharUrl());
        param.put("adsPic",vo.getAdsPic());
        param.put("adsIco",vo.getAdsIco());
        param.put("bountyMoney",vo.getBountyMoney());
        param.put("bountyDsc",vo.getBountyDsc());
        param.put("bountyName",vo.getBountyName());
        param.put("projectDsc",vo.getProjectDsc());
        return  ResultGenerator.genSuccessResult(param);
    }

    /**
     * 用户可用的券
     * @param userId
     * @return
     */
    public  List<SVoucherVo> getCanVoucher(Integer userId){
        List<SVoucherVo> vo=new ArrayList<>();
        if(userId==null || userId.compareTo(1)<0){
            return  vo;
        }
        Map<String,Object> param=new HashMap<>();
        param.put("userId",userId);
        param.put("notUse","true"); //未使用
        param.put("nowTime",new Date());
         vo=sVoucherUserRecordMapper.selectUserList(param);
        return  vo;
    }

    /**
     * 获取下拉数据
     * @return
     */
    public  List<DropVo> getDrop(){
        List<DropVo> result=new ArrayList<>();
        List<SBounty> list =getList(null);
        for(SBounty app:list){
            DropVo item=new DropVo();
            item.setText(app.getBountyName());
            item.setValue(app.getId().toString());
            result.add(item);
        }
        return  result;
    }

    /**
     * 分享superMoney
     * @param userId
     * @return
     */
    public  Result sharSuperMoney(Integer userId){
        SBounty bounty=getSuperMoneyBounty();
        if(bounty==null){
            return  ResultGenerator.genFailResult("have not hipesoBounty");
        }
        if(userId==null){
            //为空设置为平台用户
            userId=Constants.PLATFORM_USERID;
        }
        List<SVoucherVo> vouchers=new ArrayList<>();
        SBountyVo vo=doBountyVo(bounty,userId,Constants.SOURCE_APP,vouchers,RequestUntil.getUserAgentParams());
        return  ResultGenerator.genSuccessResult(vo);
    }

    /**
     * 获取superMoney任务
     * @return
     */
    public SBounty getSuperMoneyBounty(){
        SBounty bounty=new SBounty();
        bounty.setBountyName("hipesoBounty");
        bounty= sBountyMapper.selectOne(bounty);
        return  bounty;
    }

    /**
     * 计算还款明细
     * @param bountyId
     * @param money
     * @param limit
     * @param limitUnit
     * @return
     */
    public Result calculation(Integer bountyId, Integer money,Integer limit, Integer limitUnit){
        if(bountyId==null){
            return  ResultGenerator.genFailResult("bountyId is null");
        }
        List<SBountyLoan> loans=getBountyLoan(bountyId);
        if(loans==null || loans.size()<1){
            return  ResultGenerator.genFailResult("plase set loan product!");
        }
        //按期限排序: 大到小
        loans.sort((SBountyLoan h1, SBountyLoan h2)-> h1.getLoanLimit()-h2.getLoanLimit() );
        loans.forEach((loan)-> System.out.println(loan.getLoanLimit()));
        SBountyLoan loanCal=null;//大
        SBountyLoan smallCal=null;//小
        for (SBountyLoan item:loans){
            if(limit.compareTo(item.getLoanLimit())>-1){
                loanCal=item;
            }
            if(limit.compareTo(item.getLoanLimit())==-1){
                smallCal=item;
            }
        }
        loanCal=loanCal==null? smallCal:loanCal;
        if(loanCal==null){
            return  ResultGenerator.genFailResult("plase set loan limit!");
        }
        if(loanCal.getLoanRate()==null){
            return  ResultGenerator.genFailResult("plase set loanRate !");
        }
        //计算
        CalculationVo vo=new CalculationVo();
        BigDecimal m=new BigDecimal(money);
        BigDecimal lm=new BigDecimal(limit);
        BigDecimal b=new BigDecimal(100);
        //本金
        vo.setPrincipal(m);
        //利息
        BigDecimal interest=m.multiply(loanCal.getLoanRate().divide(b)).multiply(lm);
        vo.setInterest(interest);
        //服务费
        BigDecimal fee=loanCal.getFeeMoeny();
        if(loanCal.getFeeRate()!=null && loanCal.getFeeRate().compareTo(BigDecimal.ZERO)>0){
            BigDecimal rateFee=m.multiply(loanCal.getFeeRate().divide(b)).multiply(lm);
            fee=fee.add(rateFee);
        }
        vo.setFee(fee);
        //还款金额
        vo.setRepayMoney(m.add(interest).add(fee));
        //到手金额
        BigDecimal gm=m;
        if(loanCal.getIsDeductInterest().compareTo(1)==0){
            gm=gm.subtract(interest);
        }
        if(loanCal.getIsDeductFee().compareTo(1)==0){
            gm=gm.subtract(fee);
        }
        vo.setGotMoney(gm);

        return  ResultGenerator.genSuccessResult(vo);
    }

    /**
     * 获取借款产品
     * @param bountyId
     * @return
     */
    public  List<SBountyLoan> getBountyLoan(Integer bountyId){
        Map param=new HashMap();
        param.put("bountyId",bountyId);
        param.put("bountyLoanStatus",1);
        return  sBountyLoanMapper.selectList(param);
    }

    /**
     * 获取现金贷产品
     * @param bountyId
     * @return
     */
    public  SBountyLoan getBountyCashLoan(Integer bountyId){
        List<SBountyLoan> list=getBountyLoan(bountyId);
        return  list.size()>0 ?list.get(0):null;
    }
}
