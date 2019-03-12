import Vue from 'vue'
import Router from 'vue-router'
import HelloWorld from '@/components/HelloWorld'
import Login from '@/components/Login'
import home from '@/components/home'
import bountytask from '@/components/views/super/bounty/task'
import bountyrecord from '@/components/views/super/bounty/record'
import vouchermanage from '@/components/views/super/voucher/manage'
import voucherrecord from '@/components/views/super/voucher/record'
import userdatauserinfo from '@/components/views/super/userdata/userinfo'
import atidentify from '@/components/views/super/at/identify'

import setingall from '@/components/views/super/seting/all'
import setingbanner from '@/components/views/super/seting/banner'
import setingtestuser from '@/components/views/super/seting/testuser'
import setingupload from '@/components/views/super/seting/upload'
import fundCash from '@/components/views/super/moneymanage/cashcheck'
import fundPay from '@/components/views/super/moneymanage/paycheck'
import fundAccount from '@/components/views/super/moneymanage/account'
import fundBind from '@/components/views/super/moneymanage/bindcard'
import exchangerate from '@/components/views/super/fund/exchangerate'
import messagetype from '@/components/views/super/letter/messagetype'
import message from '@/components/views/super/letter/message'
import messageuser from '@/components/views/super/letter/messageuser'
import reportutil from '@/components/views/super/report/reportutil'

import lotteryGift from '@/components/views/super/lottery/gift'
import lotteryRecord from '@/components/views/super/lottery/record'
import lotteryWhitelist from '@/components/views/super/lottery/whitelist'
//菜单权限管理
import routermanage from '@/components/views/uaa/routermanage' //路由管理
import rolemanage from '@/components/views/uaa/rolemanage' //角色管理
import usermanage from '@/components/views/uaa/usermanage' //用户管理
import appmanage from '@/components/views/uaa/appmanage' //应用管理
// 统计
import distribution from '@/components/views/super/statis/distribution' //分销统计
import finance from '@/components/views/super/statis/finance' //财务统计
Vue.use(Router)

const router = new Router({
  routes: [
    {
      path: '/',
      name: 'HelloWorld',
      component: Login,
      meta: {
        title: '登录'
      },
    },
    {
      path: '/Account/Login',
      name: 'Login',
      component: Login,
    },
    {
      path: '/home',
      name: 'home',
      component: home,
      meta: {
        title: '管理后台'
      },
      children:[
         {
            path:'super/bounty/task',
            name:"bountytask",
            component:bountytask,
            meta: {
              title: '赏金任务'
            }
        },
         {
            path:'super/bounty/record',
            name:"bountyrecord",
            component:bountyrecord,
            meta: {
              title: '任务记录'
            }
        },
         {
            path:'super/voucher/manage',
            name:"vouchermanage",
            component:vouchermanage,
            meta: {
              title: '抵用券管理'
            }
        },
        {
            path:'super/voucher/record',
            name:"voucherrecord",
            component:voucherrecord,
            meta: {
              title: '使用记录'
            }
          },
        {
            path:'super/userdata/userinfo',
            name:"userdatauserinfo",
            component:userdatauserinfo,
            meta: {
              title: '用户记录'
            }
        },
        {
            path:'super/at/identify',
            name:"atidentify",
            component:atidentify,
            meta: {
              title: '身份认证'
            }
        },
        {
          path:'super/seting/all',
          name:"setingall",
          component:setingall,
          meta: {
            title: '配置信息'
          }
        },
        {
          path:'super/seting/banner',
          name:"setingbanner",
          component:setingbanner,
          meta: {
            title: 'banner配置'
          }
        },
        {
          path:'super/seting/testuser',
          name:"setingtestuser",
          component:setingtestuser,
          meta: {
            title: '测试账号配置'
          }
        },
        {
          path:'super/seting/upload',
          name:"setingupload",
          component:setingupload,
          meta: {
            title: '上传文件'
          }
        },
        {
          path:'super/fund/currency',
          name:"exchangerate",
          component:exchangerate,
          meta: {
            title: '汇率管理'
          }
        }
        ,
        {
          path:'super/fund/cash',
          name:"fundCash",
          component:fundCash,
          meta: {
            title: '用户提现'
          }
        }
        ,
        {
          path:'super/fund/account',
          name:"fundAccount",
          component:fundAccount,
          meta: {
            title: '用户账户'
          }
        }
        ,
        {
          path:'super/fund/bind',
          name:"fundBind",
          component:fundBind,
          meta: {
            title: '银行绑定'
          }
        }
        ,
        {
          path:'super/fund/pay',
          name:"fundPay",
          component:fundPay
        }
        ,
        {
          path:'super/letter/messagetype',
          name:"messagetype",
          component:messagetype,
          meta: {
            title: '消息类型'
          }
        }
        ,
        {
          path:'super/letter/message',
          name:"message",
          component:message,
          meta: {
            title: '消息'
          }
        }
        ,
        {
          path:'super/letter/messageuser',
          name:"messageuser",
          component:messageuser,
          meta: {
            title: '消息用户绑定'
          }
        }
        ,
        {
          path:'super/report/reportutil',
          name:"reportutil",
          component:reportutil,
          meta: {
            title: '自定义报表'
          }
        }
        ,
        {
          path:'super/lottery/lotteryGift',
          name:"lotteryGift",
          component:lotteryGift,
          meta: {
            title: '礼物配置'
          }
        }
        ,
        {
          path:'super/lottery/lotteryRecord',
          name:"lotteryRecord",
          component:lotteryRecord,
          meta: {
            title: '抽奖记录'
          }
        }
        ,
        {
          path:'super/lottery/lotteryWhitelist',
          name:"lotteryWhitelist",
          component:lotteryWhitelist,
          meta: {
            title: '白名单'
          }
        },
        //路由管理
        {
          path:'uaa/routermanage',
          name:"routermanage",
          component:routermanage,
          meta: {
            title: '路由管理'
          }
        },
        // //角色管理
        {
          path:'uaa/rolemanage',
          name:"rolemanage",
          component:rolemanage,
          meta: {
            title: '角色管理'
          }
        },
        //用户管理
        {
          path:'uaa/usermanage',
          name:"usermanage",
          component:usermanage,
          meta: {
            title: '用户管理'
          }
        },
        //应用管理
        {
          path:'uaa/appmanage',
          name:"appmanage",
          component:appmanage,
          meta: {
            title: '应用管理'
          }
        },
        //数据统计
        {
          path:'super/statis/distribution',
          name:'distribution',
          component:distribution,
          meta:{
            title:'分销统计'
          }
        },
        {
          path:'super/statis/finance',
          name:'finance',
          component:finance,
          meta:{
            title:'财务统计'
          }
        }
      ]
    }
  ]
})
router.beforeEach((to, from, next) => {
  /* 路由发生变化修改页面title */
  if (to.meta.title) {
    document.title = to.meta.title
  }else{
    document.title = "管理后台"
  }
  next()
 })
export default router;
