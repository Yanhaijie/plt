<template>
<div class="siderbar">
  <h5>{{bear}}</h5>
  <el-tree id="menu" :data="items" :props="defaultProps"  :render-content="rederNode" :load="itemLoad" @node-click="itemNodeClick"></el-tree>
</div>
</template>
<script>

import {getUserMenus} from '@/api'
import eventVue from '@/libs/event'
import store from './../views/vuex/store';

export default {
  name: 'Sidebar',
   components:{

   },
  data () {
    return {
      msg: 'Welcome to Your Vue.js App',
      items: [],
      defaultProps: {
        children: 'children',
        label: 'name'
      }
    }
  },
  props:['bear'],
  beforeMount() {
     console.log("beforeMount");
  },
  mounted(){
      var self=this;
      console.log("appcode:", self.$store.state.userinfo);
      var qr="appcode="+ self.$store.state.userinfo.appcode;
      getUserMenus(qr).then(result => {
           console.log("get-menu-info:",result);
           self.$store.state.userinfo.menu=result.data;
           self.items=self.menuToItems(result.data.menus);
           self.$store.state.userinfo.username=result.data.userName;
           sessionStorage.realName=result.data.userName;                   
      });

  },
  computed:{
     onRoutes(){
        console.log(this.$route.path);
        return this.$route.path;
     }
  },
  methods:{
      itemNodeClick(data){
        var self=this;
        //点击菜单
         console.log(data);
         if(data.parentId&&data.url&&data.url!=""){
           self.$router.push("/home"+data.url);
           eventVue.$emit("myFun",data.name);
         }

      },
      itemLoad(node, resolve){
          console.log("itemLoad:",node);
      },
      rederNode(createElement, { node, data, store }){
        return (
              <span >{node.label}</span>
          );
      },
      open(store,data){
          console.log("open:",data);
      },
      isNull(el){
        return typeof(el) == "undefined" ||el==null || el=="";
      },
      /**
       * 递归菜单数据
       */
      menuToItems(menu){
       
        console.log("h2",menu);
         
         const self = this;
         if(self.isNull(menu)){
           return [];
         }
         var eachFn=function(pid,data){
           var jitems=[];
           for(var i=0;i<data.length;i++){
             var npid=menu[i]["parentId"];
             if(npid==pid){
                var jitem=menu[i];
                jitem.children=eachFn(menu[i]["id"],menu);
                jitems.push(jitem);
             }
           }
           return jitems;
         };
         var items=[];
         for(var i=0;i<menu.length;i++){
             var pid=menu[i]["parentId"];
             if(self.isNull(pid)){
               console.log("pid",menu[i]);
               var item=menu[i];
               item.children=eachFn(menu[i]["id"],menu);
               items.push(item);
             }
          }
       console.log("items:",items);
       return items;
    }
  }
}
</script>

<style >

#menu .el-tree-node__content{
  font-size:14px;
  font-weight:bold;
  color:#607d8b;
  line-height:50px;
  height:50px;

}
#menu .el-tree-node__children .el-tree-node__content {
  font-size:14px;
  font-weight:300;
  line-height:32px;
  height:32px;
  border-bottom:solid 1px #eee;
}


</style>
