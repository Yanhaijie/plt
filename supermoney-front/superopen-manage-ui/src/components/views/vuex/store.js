import Vue from 'vue';
import Vuex from 'vuex'
  ;
Vue.use(Vuex);

const state = {
    currentMenu:"sss",                //当前路径
};

const store = new Vuex.Store({
    state,
    mutations:{
        setCurrentMenu(state,params){
            state.currentMenu = params.currentMenu;
        }
    }
});

export default store;
