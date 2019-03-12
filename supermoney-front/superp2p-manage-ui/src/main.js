// The Vue build version to load with the `import` command
// (runtime-only or standalone) has been set in webpack.base.conf with an alias.
import Vue from 'vue'
import App from './App'
import router from './router'
import ElementUI from 'element-ui'
import 'element-ui/lib/theme-chalk/index.css'
import './assets/css/common.css'
//import AxiosPlugin from './libs/AxiosPlugin'
import global_ from './components/tool/Global'
import store from './store'

//Vue.use(AxiosPlugin)
Vue.use(ElementUI,{size:"small"})
Vue.config.productionTip = false
Vue.prototype.GLOBAL = global_
/* router before*/
// router.beforeEach((to, from, next) => {
//   console.log("path:"+to.path);

// })

/* eslint-disable no-new */
new Vue({
  el: '#app',
  router,
  store,
  template: '<App/>',
  components: { App }
})
