package com.supermoney.loan.api.utils;

import java.math.BigDecimal;

/**
 * Created by admin on 2017-12-29.
 */
public class Constants {
    public  static  final  int SUCCESS_CODE=200;
    //平台用户id
    public  static  final  Integer PLATFORM_USERID=1;
    /**
     * 数据状态  0:根路由 1:普通路由
     */
    public  static  final  Integer ROWSTATE_USE=0;
    public  static  final  Integer ROWSTATE_DEL=1;
    /**
     * 用户seesion
     */
    public static  String SESSION_USERINFO="GWM_USER_SESSION";

    /**
     * redis key
     * MSGCODE_:登录短信验证码  FINDCODE_:找回验证码 GEETEST:极验key ACCOUNTKIT:account kit
     */
    public  static  String REDIS_MSGCODE_KEY="MSGCODE_";
    public  static  String REDIS_MSGCODE_ISEXPIRED="isExpired_";
    public  static  String REDIS_FINDCODE_KEY="findcode:";
    public  static  String REDIS_GEETEST="geetest:";
    public  static  String REDIS_KIT="accountkit:";
    public  static  String REDIS_H5LOGIN_KEY="H5LOGIN";
    public  static  String REDIS_LASTLOGINTIMEKEY="lastLoginTime";

    /**
     * 提现额度区间
     */
    public  static BigDecimal CASH_IDR_MIN=new BigDecimal(50000);
    public static  BigDecimal CASH_IDR_MAX=new BigDecimal(10000000);
    public  static  BigDecimal CASH_USD_MIN=new BigDecimal(1);
    public  static  BigDecimal CASH_USD_MAX=new BigDecimal(100);
    //状态
    public  static  Integer STATUS_USE=0; //使用
    public static  Integer STATUS_STOP=1; //取消 停止
    public  static  Integer STATUS_WAIT=2;//等待
    //账户类型
    public  static  Integer ACCOUNT_TYPE_MONEY=1;//资金账户
    public  static  Integer ACCOUNT_TYPE_LOAN=2;//借款账户
    //来源类型
    public  static  String SOURCE_APP="a";
    public  static  String SOURCE_SHAR="s";

    //账户业务类型
    public  static  Integer BUSS_TYPE_RECHARGE=1;//充值
    public  static  Integer BUSS_STEP_RECHARGE_INMONEY=10;//充值入账

    public  static  Integer BUSS_TYPE_BOUNTY=2;//赏金任务
    public  static  Integer BUSS_STEP_BOUNTY_CASH_FREEZE=20;//赏金提现冻结
    public  static  Integer BUSS_STEP_BOUNTY_CASH_FREEZEOUT=21;//赏金提现划出(成功)
    public  static  Integer BUSS_STEP_BOUNTY_CASH_FREEZEBACK=22;//赏金提现打回(失败)


    public static  Integer BUSS_TYPE_LOTTERY=3;//抽奖活动
    public static  Integer BUSS_TYPE_LOTTERY_EXCHANGE_COUNT=30;//兑换抽检次数出账
    public static  Integer BUSS_TYPE_LOTTERY_WINNING_COUNT=31; //中奖

    public static  Integer BUSS_TYPE_AGENCY=4;//代理
    public static  Integer BUSS_TYPE_AGENCY_PERSON=40;//代理推广人头结算


    public static  Integer BUSS_TYPE_LOAN=5;//借款业务
    public static  Integer BUSS_STEP_LOAN_ORDER_INMONEY=50;//借款订单入账
    public static  Integer BUSS_STEP_LOAN_ORDER_REPAY=51;//借款订单还款
    public static  Integer BUSS_STEP_LOAN_ORDER_OVERDUE_INMONEY=52;//借款逾期款入账
    public static  Integer BUSS_STEP_LOAN_ORDER_OVERDUE_REPAY=53;//借款逾期款还款
    public static  Integer BUSS_STEP_LOAN_ORDER_REPAY_PASS=54;//借款本息多还
    public static  Integer BUSS_STEP_LOAN_ORDER_REPAY_OVERDUE_PASS=55;//借款逾期多还
    public static  Integer BUSS_STEP_LOAN_ORDER_DAMAGED=56;//借款报损

    public static  Integer BUSS_TYPE_ACCOUNT_BILLING=6;//账户之间划账业务
    public static  Integer BUSS_STEP_ACCOUNT_BILLING_LOAN_OUT_TOMONEY=60;//借款账户出账到资金账户
    public static  Integer BUSS_STEP_ACCOUNT_BILLING_MONEY_IN_FROMLOAN=61;//资金账户入账来自借款账户

    public static  Integer BUSS_TYPE_TOPUP=7;//冲话费
    public  static  Integer BUSS_STEP_TOPUP_CASH_FREEZE=71;//充话费冻结


    public static  Integer PAY_ACOUNT=100001; // 充值账号id

    /**
     *  赏金任务
     */
    public static   final  class BountyRecord{
        /**
         * 赏金任务状态  0 完成 1进行中 2 未完成 3已结算
         */
        public  static  Integer STATUS_FINSH=0;
        public  static  Integer STATUS_DOING=1;
        public  static  Integer STATUS_UNFINSH=2;
        public  static  Integer STATUS_COUNT=3;
    }


    /**
     *  充值领取状态
     */
    public static   final  class PayGetStatus{
        /**
         * 领取状态  0 未领取 1已经领取
         */
        public  static  Integer STATUS_UNDO=0;
        public  static  Integer STATUS_DONE=1;
    }

    /**
     *  充值审核状态
     */
    public static   final  class PayStatus{
        /**
         *  状态: 0 待审核  1 审核通过  2 审核失败 3 充值成功 4 充值失败
         */
        public  static  Integer STATUS_AUDIT_WAIT=0;
        public  static  Integer STATUS_AUDIT_PASS=1;
        public  static  Integer STATUS_AUDIT_FAILURE=1;
        public  static  Integer STATUS_PAY_SCCUSS=1;
        public  static  Integer STATUS_PAY_FAILURE=1;
    }
    /**
     *  充值类型
     */
    public static   final  class PayType{
        /**
         *  状态: 0 现金充值
         */
        public  static  Integer STATUS_GET_AWARD=1001;



    }


    /**
     * 应用默认配置
     */
    public static   final  class App {
        /**
         * MAP 最小初始化长度
         */
        public  static  Integer MAP_MIN_SIZE=16;
        public  static  Integer NUM_ONE=1;

        // 错误业务记录 1 打款 2还款
        public static  Integer BUSS_ERROR_DIS=1;
        public static  Integer BUSS_ERROR_REPAY=2;
    }

    /**
     * 国家
     * Indonesia:印度尼西亚
     */
    public  static  final  class  Country{
        /**
         * 国家名称
         */
        public  static  String INDONESIA="Indonesia";
        public  static  String AMERICA="America";

        /**
         * 国家货币单位
         */
        public  static  String INDONESIA_CR="IDR";
        public  static  String AMERICA_CR="USD";
        public  static  String PHP_CR="PHP";

        /**
         * 国家编号
         */
        public  static  Integer INDONESIA_CODE=899;
        public  static  Integer PHP_CODE=480;

        /**
         * 国家使用的支付平台
         */
        public static  String INDONESIA_PAY_PLATFORM="xendit";

    }

    /**
     * 奖券
     */
    public  static  final class   Voucher{
        /**
         * 奖券业务类型:0赏金、1贷款
         */
        public  static  Integer  BUSS_BOUNTY=0;
        public  static  Integer BUSS_LOAN=1;
    }

    /**
     * 提现
     */
    public  static  final class   Cash{
        /**
         * 状态: 0 待审核  1 审核通过  2 审核失败 3提现中  4提现成功 5 提现失败
         */
        public  static  Integer STATUS_TOADUIT=0;
        public  static  Integer STATUS_AUDIT=1;
        public  static  Integer STATUS_UNAUDIT=2;
        public  static  Integer STATUS_CASHING=3;
        public  static  Integer STATUS_CASHSUCCESS=4;
        public  static  Integer STATUS_CASHFAILD=5;
        public  static  Integer STATUS_EXPECTION=6;

    }

    /**
     * 活动
     */
    public  static  final class Activity{

        // 51 抽奖活动
        public  static  String LOTTERY_51="lottery_51";
    }


    /**
     * 站内充值
     */
    public  static  final  class  BussPay{
        public  static  Integer PAYTYPE_LOTTERY=1001;
        public  static  Integer PAYTYPE_AGENCY=1002;
        public  static  Integer PAYTYPE_ORDER=1003;
        //状态: 0 待审核  1 审核通过  2 审核失败 3 充值成功 4 充值失败 10打款中 11 打款异常
        public  static  Integer PAY_STATUS_WAIT=0;
        public static  Integer PAY_STATUS_AUDIT=1;
        public static  Integer PAY_STATUS_FAILD=2;
        public static  Integer PAY_STATUS_TO_SUCCESS=3;
        public static  Integer PAY_STATUS_TO_FAILD=4;
        public static  Integer PAY_STATUS_TO_ING=10;
        public static  Integer PAY_STATUS_TO_ERROR=11;
        //0提现打款 1借款打款
        public static Integer DIS_BUSS_CASH=0;
        public static Integer DIS_BUSS_LOAN=1;

    }

    /**
     * 征信
     */
    public static  final  class  Credit{

        public  static  Integer CREDIT_P_NULL=0;
        public  static  Integer CREDIT_P_WHITE=1;
        public  static  Integer CREDIT_P_BUSINESSMAN=2;
        public  static  Integer CREDIT_P_WITHOUT =3;
        public  static  Integer CREDIT_P_STUDENT =4;
        //状态:0暂存 1待审核 2 审核通过 3审核失败
        public  static  Integer STATUS_SAVE=0;
        public  static  Integer STATUS_AUDITWAIT=1;
        public  static  Integer STATUS_AUDITPASS=2;
        public  static  Integer STATUS_AUDITFAILD=3;
    }

    /**
     * 借款
     */
    public static  final  class  Loan{
        public  static  Integer LIMIT_MONTH=0;
        public  static  Integer LIMIT_DAY=1;
        public  static  Integer LIMIT_HOUR=2;

        // order_status: 订单状态 0待审核 1审核通过 2审核失败 3放款审核通过 4放款审核失败 5放款成功 6放款失败 7待还款 8还款完成 9还款延期 10还款报损
        public static  Integer STATUS_AUDIT_UN=0;
        public static  Integer STATUS_AUDIT_PASS=1;
        public static  Integer STATUS_AUDIT_FAILD=2;
        public static  Integer STATUS_LOAN_AUDIT_PASS=3;
        public static  Integer STATUS_LOAN_AUDIT_FAILD=4;
        public static  Integer STATUS_LOAN_TO_PASS=5;
        public static  Integer STATUS_LOAN_TO_FAILD=6;
        public static  Integer STATUS_LOAN_REPAY_ING=7;
        public static  Integer STATUS_LOAN_REPAY_SUCCESS=8;
        public static  Integer STATUS_LOAN_REPAY_WAR=9;
        public static  Integer STATUS_LOAN_REPAY_BAD=10;

        //回款状态 0待审核 1审核失败  2待打款 3回款中 4回款完成
        public  static Integer GRANT_STATUS_AUDIT_WAIT=0;
        public  static Integer GRANT_STATUS_AUDIT_FAILD=1;
        public  static Integer GRANT_STATUS_UNDO=2;
        public  static Integer GRANT_STATUS_REPAY_ING=3;
        public  static Integer GRANT_STATUS_REPAY_SUCCESS=4;

        //还款状态 0创建 1待还款 2还款完成 3还款失败
        public static  Integer REPAY_STATU_CREATE=0;
        public static  Integer REPAY_STATU_REPAYING=1;
        public static  Integer REPAY_STATU_REPAYSUCCESS=2;
        public static  Integer REPAY_STATU_REPAYFAILD=3;

        //还款虚拟账户类型 1:订单还款 2.订单逾期还款
        public static  Integer VRITUAL_ACCOUNT_LOAN=1;
        public static  Integer VRITUAL_ACCOUNT_OVERDUE=2;
    }


    /**
     * 信用等级
     */
    public static  final  class  CreditGrade{
        public  static  Integer GRADE_A = 10;   //A
        public  static  Integer GRADE_B = 11;   //B
        public  static  Integer GRADE_C = 12;   //C
        public  static  Integer GRADE_D = 13;   //D
        public  static  Integer GRADE_E = 14;   //E
        public  static  Integer GRADE_F = 15;   //F
    }

}
