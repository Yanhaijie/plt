<template>
<div class="homewarp">
<el-container>
  <el-aside width="220px">
      <div class="logo">
        <span style="color:#F51212;">GateWay</span>-Admin
      </div>
      <cm-Siderbar></cm-Siderbar>
  </el-aside>
  <el-container>
    <el-header>
      <cm-Header></cm-Header>
    </el-header>
    <el-main>
        <router-view></router-view>
    </el-main>
  </el-container>
</el-container>

</div>
</template>
<script>

import cmSiderbar from './common/Sidebar.vue'
import cmHeader from './common/Header.vue'

export default {
  name: 'Home',
  components:{
   'cm-Siderbar':cmSiderbar,
   'cm-Header':cmHeader
  },
  data () {
    return {
      msg: 'Welcome to Your Vue.js App'
    }
  },
  methods:{
      

  }
}
</script>

<style scoped>
  .homewarp,.el-container{
    height:100%;
  }
  .logo{
     display:block;
     width:100%;
     height:80px;
    background-color: #1f262d;
    color:#fff;
    font-size:20px;
    height: 40px;
    line-height: 40px;
    margin-bottom: 40px; 
  }
  .el-aside{
        background: #2E363F;
}
  

</style>