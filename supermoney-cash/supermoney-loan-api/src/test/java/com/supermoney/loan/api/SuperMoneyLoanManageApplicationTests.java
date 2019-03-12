package com.supermoney.loan.api;

import com.supermoney.loan.api.entity.SAccountBalance;
import com.supermoney.loan.api.entity.SLoanOrder;
import com.supermoney.loan.api.entity.SUser;
import com.supermoney.loan.api.entity.vo.UserInfoVo;
import com.supermoney.loan.api.entity.vo.XenditCallBackVo;
import com.supermoney.loan.api.service.*;
import com.supermoney.loan.api.utils.ResultGenerator;
import com.supermoney.loan.api.utils.ResultT;
import com.supermoney.loan.api.utils.TransferToUtil;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SuperMoneyLoanManageApplicationTests {

	private static final Logger LOGGER = LoggerFactory.getLogger(SuperMoneyLoanManageApplicationTests.class);


	@Autowired
	private SUserAccountService sUserAccountService;
	@Autowired
	private SAccountBalanceService sAccountBalanceService;
	@Autowired
	private SLoanOrderService sLoanOrderService;
	@Autowired
	private SExchangeRateService sExchangeRateService;

	@Autowired
	private  SUserService sUserService;
	@Autowired
	private  TransferToUtil transferToUtil;

	@Resource
	private STransfertoTopupHisService sTransfertoTopupHisService;


	@Test
	public void contextLoads() {

	}

	//@Test
	public  void  resultT(){
		UserInfoVo vo=new UserInfoVo();
		vo.setAge("16");
		vo.setCashAmount(new BigDecimal(2.55));
		vo.setEmail("hello@126.com");
		vo.setCountry("china");
		com.supermoney.loan.api.utils.Result rs= ResultGenerator.genSuccessResult(vo);

		ResultT<UserInfoVo> rst=new ResultT<UserInfoVo>(rs);

	}

	/**
	 * 下单测试
	 */
	//@Test
	public  void  toOrderBuss(){
		sLoanOrderService.toOrder(73,620000,9,"hari","test reason","899", 5016);
	}

	/**
	 * 订单回调业务测试
	 */
	//@Test
	public  void  orderBuss(){
		XenditCallBackVo vo=new XenditCallBackVo();
		vo.setStatus("COMPLETED");
		vo.setExternal_id("20180606135657956");
		Map<String,Object> maps=new HashMap<>();
		maps.put("orderSn",vo.getExternal_id());
		SAccountBalance balance = sAccountBalanceService.getAccountBalance( maps);
		sUserAccountService.loanOrderCallBackBuss(vo,balance);
	}

	/**
	 * 还款操作业务测试
	 */
	//@Test
	public  void  orderRepayBuss(){
		//sLoanOrderService.orderRepay("121",new BigDecimal(50000));
			SLoanOrder order=new SLoanOrder();
			order.setOrderStatus(9);
			order.setLoanAmount( new BigDecimal(900000));
			order.setOverdueAmount(new BigDecimal(243000));
			order.setRepaymentTotal(BigDecimal.ZERO);
			order.setPayOverdueAmount(BigDecimal.ZERO);
			BigDecimal amount=new BigDecimal(900400);
			sLoanOrderService.oneceRepay(order,amount,BigDecimal.ZERO);
	}

	/**
	 * 逾期费用计算业务测试
	 */
	//@Test
	public  void  overdueMoneyBuss(){
		sLoanOrderService.overdueMoneyCount("1020180611155320684");
	}

	/**
	 * 汇率转换测试
	 */
	//@Test
	public  void  exchangeRateBuss(){
		print(new BigDecimal(500000));
		print(new BigDecimal(50001));
		print(new BigDecimal(50002));
		print(new BigDecimal(50003));
		print(new BigDecimal(50004));
		print(new BigDecimal(50005));
		print(new BigDecimal(50006));
		print(new BigDecimal(50007));
		print(new BigDecimal(50008));
		print(new BigDecimal(50009));
		print(new BigDecimal(500010));
		print(new BigDecimal(333333));
		print(new BigDecimal(373737));
		print(new BigDecimal(393939));
		print(new BigDecimal(393939));
		print(new BigDecimal(124789121));
		print(new BigDecimal(713971519));
	}

	public  void print(BigDecimal je){
		BigDecimal jeUsd=sExchangeRateService.indoneslaToUsd(je);
		BigDecimal jeIdr=sExchangeRateService.usdToIndonesla(jeUsd,false);
		LOGGER.info("je:"+je+" usd:"+jeUsd+" idr:"+jeIdr);

	}

	//@Test
	public  void  testMoneyFormatBuss(){
		printFormat(new BigDecimal(1234));
		printFormat(new BigDecimal(12345));
		printFormat(new BigDecimal(123456));
		printFormat(new BigDecimal(1234567));
		printFormat(new BigDecimal(12345678));
		printFormat(new BigDecimal(123456789));
		printFormat(new BigDecimal(1234567891));
	}

	public  void  printFormat(BigDecimal je){
		String jeFm=sExchangeRateService.indonwslaFormat(je);
		LOGGER.info("je:"+je+"  jeFm: "+jeFm);
	}

	//@Test
	public  void  errorUser(){
	  SUser user= sUserService.getUserByMobile("081114447772");

	}

	@Test
	public  void  testMD5_hex(){
 	/*	System.out.println("=======================1111");
 		while (true){
			long keyL = System.currentTimeMillis();
			long paramKeyL = 1531912000000L;
			System.out.println("topupkeyL ===================>"+keyL);
			int key =(int)(keyL - paramKeyL);
			System.out.println("topupkey ==================="+key);

		}*/

	//	sTransfertoTopupHisService.topup(11,1,"+6281294224800",5000,"+8615915365053","yes","click: www.baidu.com","yes","充值一波");
		//Map<String,String> resultMap1 = transferToUtil.topUp(11,"+6281386388542",5000,"+8615915365053","yes","click: www.baidu.com","yes","充值一波","55");
//		Map<Integer,BigDecimal> resultMap = sTransfertoTopupHisService.topUpService(11,"+6281386388542");
//		for (Integer tt : resultMap.keySet()){
//            System.out.println(resultMap.get(tt));
//        }
//
//		System.out.println(resultMap.get(5000));
//		System.out.println("2222222");
		/*		Map<String,String> resultMap = transferToUtil.msisdnInfo("+6281294224800");
		for (String str : resultMap.keySet()){
			System.out.println(str+"="+resultMap.get(str));
			String product = resultMap.get("product_list");

			Map<String,String> resultMap1 = transferToUtil.topUp("+8618320008137",product,"+8615915365053","yes","click: www.baidu.com","yes","充值一波");

		}*/
	//	Map<String,String> resultMap1 = transferToUtil.topUp("+6281386388542","5000","supershop","yes","click: www.baidu.com");
	//	Map<String,String> resultMap2 = transferToUtil.reserveId();
	//	String reservedId = resultMap2.get("reserved_id");
	//	Map<String,String> resultMap1 = transferToUti
		//
		// asdfl.topUp("+8618320008137","1","+8615915365053","yes","click: www.baidu.com","yes","充值一波");
/*
		for (String str : resultMap1.keySet()){
			System.out.println(str+"="+resultMap1.get(str));
		}
		System.out.println("---------------");*/
//		sTransfertoTopupHisService.topup(11,"+6281294224800","5000","+8615915365053","yes","click: www.baidu.com","yes","充值一波");
//		System.out.println("==============");
	}




}
