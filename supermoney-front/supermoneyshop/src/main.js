
import Vue from 'vue'
import ElementUI from 'element-ui'
import locale from 'element-ui/lib/locale/lang/en'
import App from './App'
import router from './router'
import axios from 'axios'
import { flexible } from './assets/js/flexible.js'
import CollapseTransition from 'element-ui/lib/transitions/collapse-transition';

Vue.use(ElementUI, { locale })
// 引入 axios 之后，修改原型链,直接在组件的 methods 中使用 $ajax 命令
Vue.prototype.$ajax = axios
Vue.config.productionTip = false

Vue.component(CollapseTransition.name, CollapseTransition);

/* eslint-disable no-new */
new Vue({
  el: '#app',
  router,
  template: '<App/>',
  components: { App }
})
