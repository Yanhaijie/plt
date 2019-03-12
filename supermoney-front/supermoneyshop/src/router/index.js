import Vue from 'vue'
import Router from 'vue-router'
import login from '../components/login'
import index from "../components/index";
import { flexible } from '../assets/js/flexible.js'
Vue.use(Router)

export default new Router({
	// mode: 'history',
    routes: [
	    {
	        path: '/login',
	        name: 'login',
	        component: login
	    },
	    {
	        path: '/index',
	        name: 'index',
	        component: index
	    }
    ]
})