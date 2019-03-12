<template>
<div class="siderbar">
  <!-- <h5>{{bear}}</h5>
  <el-tree :data="items" :props="defaultProps" :render-content="rederNode" :load="itemLoad" accordion highlight-current @node-click="itemNodeClick"></el-tree> -->
  <el-menu 
  unique-opened 
  router
  :default-active="onRoutes"
   background-color="#2E363F"
    text-color="#fff"
    active-text-color="#ffd04b"
  >
    <el-submenu v-for="pitem in items" :key="pitem.id" :index="pitem.id" popper-class="submenu-item">
      <template slot="title">
         {{pitem.name}}
      </template>
        <el-menu-item v-for="citem in pitem.children" :key="citem.id" :index="citem.url">{{citem.name}}</el-menu-item>
    </el-submenu>
  </el-menu>
</div>
</template>
<script>

import {getUserInfo} from '@/api'

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
      var qr="appcode="+ self.$store.state.userinfo.appcode;
      getUserInfo(qr).then(result => {
           console.log("get-user-info:",result);
           //self.$store.state.userinfo.menu=result.data.menus;
            self.$store.commit('SET_MENUS',result.data.menus);
            self.$store.commit('SET_USERNAME',result.data.userName);
           self.items=self.menuToItems(result.data.menus);
           //self.$router.push(self.items[0].children[0].url);
      }); 
  },
  computed:{
     onRoutes(){
        return this.$route.path;
     }
  },
  methods:{
      itemNodeClick(data){
        var self=this;
        //点击菜单
         console.log(data);
         if(data.url&&data.url!=""){
           self.$router.push("/home"+data.url);
         }
        
      },
      itemLoad(node, resolve){
      },
      rederNode(createElement, { node, data, store }){
        return (  
              <span >{node.label}</span>             
          );
      },
      open(store,data){
      },
      isNull(el){
        return typeof(el) == "undefined" ||el==null || el=="";
      },
      /**
       * 递归菜单数据
       */
      menuToItems(menu){
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
                jitem.url = "/home"+ jitem.url ;
                jitems.push(jitem);
             }
           }
           return jitems;
         };
         var items=[];
         for(var i=0;i<menu.length;i++){
             var pid=menu[i]["parentId"];
             if(self.isNull(pid)){
               var item=menu[i];
               item.children=eachFn(menu[i]["id"],menu);
               items.push(item);
             }
          }
       return items;
    }
  }
}
</script>

<style>

 /* .el-tree-node__content{
   border-top: 1px solid #37414b;
   border-bottom: 1px solid #1e242b !important;
   height:40px !important;
   background: #2E363F !important;
   color: #ffffff;
 }
.el-tree-node__children,.el-tree-node__children .el-tree-node__content {
  background:#1f262d !important;
  border:0px !important;
}

.el-tree--highlight-current>.el-tree-node :hover{
  background:#ee4c4c !important;
}
 .el-tree--highlight-current .el-tree-node .is-current>.el-tree-node__content{
   background:#ee4c4c !important;
 } */
 
 .el-menu-item{
   font-size: 12px;
   background: #1f262d !important;
 }
.el-submenu{
  border-bottom: 1px solid #1e242b !important;
  width: 180px !important;
}
.el-menu-item{
  min-width: 180px !important;
}
</style>