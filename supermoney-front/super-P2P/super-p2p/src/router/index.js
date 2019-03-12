import Vue from 'vue'
import Router from 'vue-router'
import index from '@/components/index'
import Login from '@/components/Login'
import slidebar from '@/components/slidebar'
import header from '@/components/header'
Vue.use(Router)

export default new Router({
    routes: [{
            path: '/',
            name: 'index',
            component: index
        },
        {
            path: '/Login',
            name: 'Login',
            component: Login
        },
        {
            path: '/slidebar',
            name: 'slidebar',
            component: slidebar
        },
        {
            path: '/header',
            name: 'header',
            component: header
        }
    ]
})