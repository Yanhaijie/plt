import Vue from 'vue'
import Router from 'vue-router'
import HelloWorld from '@/components/HelloWorld'
import Login from '@/components/Login'
import home from '@/components/home'


import spauser from '@/components/views/opa/mg/aisle/user'
import spaproduct from '@/components/views/opa/mg/aisle/product'
import spauserproduct from '@/components/views/opa/mg/aisle/userproduct'
import spaflowproduct from '@/components/views/opa/mg/aisle/flowproduct'
import spaflowuser from '@/components/views/opa/mg/aisle/flowuser'

import opinterface from '@/components/views/op/interface/list'
import opcallrecord from '@/components/views/op/callrecord/list'
import opblacklist from '@/components/views/op/blacklist/list'
import opocr from '@/components/views/op/ocr/list'


Vue.use(Router)

export default new Router({
  routes: [
    {
      path: '/',
      name: 'HelloWorld',
      component: Login
    },
    {
      path: '/Account/Login',
      name: 'Login',
      component: Login
    },
    {
      path: '/home',
      name: 'home',
      component: home,
      children:[

        {
          path:'opa/mg/aisle/user',
          name:"spauser",
          component:spauser
        },
        {
          path:'opa/mg/aisle/product',
          name:"spaproduct",
          component:spaproduct
        },
        {
          path:'opa/mg/aisle/userproduct',
          name:"spauserproduct",
          component:spauserproduct
        },
        {
          path:'opa/mg/aisle/flowproduct',
          name:"spaflowproduct",
          component:spaflowproduct
        },
        {
          path:'opa/mg/aisle/flowuser',
          name:"spaflowuser",
          component:spaflowuser
        },
        {
          path:'op/mg/interface/list',
          name:"opinterface",
          component:opinterface
        },
        {
          path:'op/mg/call/record/list',
          name:"opcallrecord",
          component:opcallrecord
        },
        {
          path:'op/mg/black/list/list',
          name:"opblacklist",
          component:opblacklist
        },
        {
          path:'op/mg/ocr/list',
          name:"opocr",
          component:opocr
        },
      ]
    }

  ]
})
