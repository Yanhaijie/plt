import Vue from 'vue'
import Router from 'vue-router'
import HelloWorld from '@/components/HelloWorld'
import Login from '@/components/Login'
import home from '@/components/home'
import appmanage from '@/components/views/uaa/appmanage'
import usermanage from '@/components/views/uaa/usermanage'
import routermanage from '@/components/views/uaa/routermanage'
import rolemanage from '@/components/views/uaa/rolemanage'
import permisionmanage from '@/components/views/uaa/permisionmanage'


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
            path:'uaa/appmanage',
            name:"appmanage",
            component:appmanage
        },
         {
            path:'uaa/usermanage',
            name:"usermanage",
            component:usermanage
        },
         {
            path:'uaa/routermanage',
            name:"routermanage",
            component:routermanage
        },
        {
            path:'uaa/rolemanage',
            name:"rolemanage",
            component:rolemanage
        },
        {
            path:'uaa/permisionmanage',
            name:"permisionmanage",
            component:permisionmanage
        }
      ]
    }
   
  ]
})
