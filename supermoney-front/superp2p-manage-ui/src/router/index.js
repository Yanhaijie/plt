import Vue from 'vue'
import Router from 'vue-router'
import HelloWorld from '@/components/HelloWorld'
import Login from '@/components/Login'
import home from '@/components/home'


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
         
      ]
    }
  ]
})
