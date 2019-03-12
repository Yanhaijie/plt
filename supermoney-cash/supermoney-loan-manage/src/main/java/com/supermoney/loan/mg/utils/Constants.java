package com.supermoney.loan.mg.utils;

/**
 * Created by admin on 2017-12-29.
 */
public class Constants {
    //数据状态  0:根路由 1:普通路由
    public  static  final  Integer ROWSTATE_USE=0;
    public  static  final  Integer ROWSTATE_DEL=1;
    //用户seesion
    public static  String SESSION_USERINFO="GWM_USER_SESSION";

    //状态
    public  static  Integer STATUS_USE=0;
    public static  Integer STATUS_STOP=1;
    //账户类型
    public  static  Integer ACCOUNT_TYPE_MONEY=1;//资金账户
    public  static  Integer ACCOUNT_TYPE_LOAN=2;//借款账户

    //赏金任务提现
    public  static  Integer BUSS_TYPE_BOUNTY=2;//赏金任务
    public  static  Integer BUSS_STEP_BOUNTY_CASH_FREEZE=20;//赏金提现冻结
    public  static  Integer BUSS_STEP_BOUNTY_CASH_FREEZEOUT=21;//赏金提现划出(成功)
    public  static  Integer BUSS_STEP_BOUNTY_CASH_FREEZEBACK=22;//赏金提现打回(失败)
    public  static  Integer BUSS_STEP_BOUNTY_COUNT_INMONEY=23;//赏金任务结算入账
    public static  Integer BUSS_STEP_BOUNTY_AGENCY_COUNT_INMONEY=24;//赏金任务代理分成入账

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


    /**
     * 抵用券
     */
    public  static  final  class  VOUCHERS{
        //奖券类型 0单次翻倍  1项目翻倍 2期间翻倍 3随机红包
        public static  Integer TYPE_SINGLE=0;
        public static  Integer TYPE_PROJECT=1;
        public static  Integer TYPE_LIMIT=2;
        public static  Integer TYPE_RANDOM=3;
        //奖券业务类型:0赏金、1贷款
        public  static  Integer  BUSS_BOUNTY=0;
        public  static  Integer BUSS_LOAN=1;
        //奖券状态:0未使用 1使用中 2使用完
        public  static  Integer STATUS_UNDO=0;
        public  static  Integer STATUS_DOING=1;
        public  static  Integer STATUS_DONE=2;
    }

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
     * 应用默认配置
     */
    public static   final  class App {
        /**
         * MAP 最小初始化长度
         */
        public  static  Integer MAP_MIN_SIZE=16;
        /**
         * 普通站内消息类型ID
         */
        public  static  Integer MESSAGE_NOMAL_TYPEID=1000;
        /**
         * 普通消息ID
         */
        public  static  Integer MESSAGE_NOMAL_ID=10000;
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

        public  static  Integer STATUS_SAVE=0;
        public  static  Integer STATUS_AUDITWAIT=1;
        public  static  Integer STATUS_AUDITPASS=2;
        public  static  Integer STATUS_AUDITFAILD=3;
    }

    /**
     * 订单
     */
    public static  final  class  Order{
        //STATUS 订单状态 0待审核 1审核通过 2审核失败 3放款审核通过 4放款审核失败 5放款成功 6放款失败 7待还款 8还款完成 9还款延期 10还款报损
        public  static  Integer STATUS_UN_AUDIT=0;

        public  static  Integer STATUS_PASS_AUDIT=1;

        public  static  Integer STATUS_FAILD_AUDIT=2;

        public  static  Integer STATUS_LOAN_PASS_AUDIT=3;

        public  static  Integer STATUS_LOAN_FAILD_AUDIT=4;

        public  static  Integer STATUS_LOAN_SUCCESS=5;

        public  static  Integer STATUS_LOAN_FAILD=6;

        public  static  Integer STATUS_REPAYING=7;

        public  static  Integer STATUS_REPAY_SUCCESS=8;

        public  static  Integer STATUS_REPAY_DELAY=9;

        public  static  Integer STATUS_REPAY_DAMAGED=10;
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
     * 充值
     */
    public  static  final  class  Pay{
        /**
         * 登录奖金领取
         */
        public  static  Integer TYPE_LOGIN=1001;
        /**
         * 代理
         */
        public  static  Integer TYPE_AGENCY=1002;
        /**
         * 借款订单
         */
        public  static  Integer TYPE_LOAN_ORDER=1003;

        //'状态: 0 待审核  1 审核通过  2 审核失败 3 充值成功 4 充值失败
        public  static  Integer STATUS_PASS_AUDIT=1;

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

        /**
         * 国家编号
         */
        public  static  Integer INDONESIA_CODE=899;

        /**
         * 国家使用的支付平台
         */
        public static  String INDONESIA_PAY_PLATFORM="xendit";

    }

    /**
     * 借款
     */
    public static  final  class  Loan{
        public  static  Integer LIMIT_MONTH=0;
        public  static  Integer LIMIT_DAY=1;
        public  static  Integer LIMIT_HOUR=2;

        //回款状态 0待审核 1审核失败  2待打款 3回款中 4回款完成

        public  static Integer GRANT_STATUS_AUDIT_WAIT=0;
        public  static Integer GRANT_STATUS_AUDIT_FAILD=1;
        public  static Integer GRANT_STATUS_UNDO=2;
        public  static Integer GRANT_STATUS_REPAY_ING=3;
        public  static Integer GRANT_STATUS_REPAY_SUCCESS=4;
        // 放款方式 0线上 1线下
        public  static Integer GRANT_METHOD_LINE_UP=0;
        public  static Integer GRANT_METHOD_LINE_DOWN=1;

        //'还款状态 0创建 1待还款 2还款完成 3还款失败 4取消'
        public  static Integer REPAY_STATUS_CREATE=0;
        public  static Integer REPAY_STATUS_REPAYING=1;
        public  static Integer REPAY_STATUS_SUCCESS=2;
        public  static Integer REPAY_STATUS_FAILD=3;
        public  static Integer REPAY_STATUS_CANCLE=4;

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

        //还款虚拟账户类型 1:订单还款 2.订单逾期还款
        public static  Integer VRITUAL_ACCOUNT_LOAN=1;
        public static  Integer VRITUAL_ACCOUNT_OVERDUE=2;

        //报损金额类型  0所有未还 1还部分本息 2已还本息逾期未还 3已还本息还部分逾期 4所有已还
        public  static  Integer DAMAGED_AMOUNTTYPE_ALLNOTPAY=0;
        public  static  Integer DAMAGED_AMOUNTTYPE_SOMELOAN=1;
        public  static  Integer DAMAGED_AMOUNTTYPE_PAYLOAN_NOTOVERDUE=2;
        public  static  Integer DAMAGED_AMOUNTTYPE_PAYLOAN_SOMEOVERDUE=3;
        public  static  Integer DAMAGED_AMOUNTTYPE_ALLPAY=4;
        //报损类型  0还款报损 1业务测试 2系统问题
        public  static  Integer DAMAGED_TYPE_REPAY=0;
        public  static  Integer DAMAGED_TYPE_TEST=1;
        public  static  Integer DAMAGED_TYPE_ERROR=2;
    }

    public  static  final  class  Database{

        public static final String MASTER = "super_money";

        public static final String CLIENT = "super_date_clear";
    }

    public  static  final  class  Google{
        // 0设备推送 1标签推送 2推送所有用户
        public static  Integer PUSH_DIVICE=0;
        public static  Integer PUSH_FLAG=1;
        public static  Integer PUSH_CONDITION=2;
        public static  Integer PUSH_ALL_USER=3;

    }
}
