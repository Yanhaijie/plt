<template>
<div class="siderbar">
  <h5>{{bear}}</h5>
  <el-tree :data="items" :props="defaultProps"  :render-content="rederNode" :load="itemLoad" @node-click="itemNodeClick"></el-tree>
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
           self.$store.state.userinfo.menu=result.data.menus;
           self.items=self.menuToItems(result.data.menus);
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
         if(data.url&&data.url!=""){
           self.$router.push("/home"+data.url);
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
        console.log("h2");
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

<style>

 .el-tree-node__content{
   border-top: 1px solid #37414b;
   border-bottom: 1px solid #1e242b !important;
   height:40px !important;
   background: #2E363F !important;
 }
.el-tree-node__children,.el-tree-node__children .el-tree-node__content {
  background:#1f262d !important;
  border:0px !important;
}
 
 

</style>