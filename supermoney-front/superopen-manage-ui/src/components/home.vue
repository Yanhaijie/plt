<template>
<div class="homewarp">
<el-container>
  <el-header>
     <div class="logo">
          <span style="margin:0; color:#509ee2; border-bottom: 4px solid; padding-bottom: 4px;" >OPEN-PLATFORM</span>
      </div>
      <cm-Header></cm-Header>
  </el-header>
  <el-container>
     <el-aside width="220px">
      <cm-Siderbar></cm-Siderbar>
      <div class="btip">
        <i class="el-icon-back" @click="toLogin"></i>
        <el-tooltip content="Email:superp2p@superads.com" placement="top" effect="light">
          <i class="el-icon-message"></i>
        </el-tooltip>
        <el-tooltip content="superP2P best p2p! 2018-05-16" placement="top" effect="light">
          <i class="el-icon-question"></i>
        </el-tooltip>
      </div>
    </el-aside>
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
      msg: 'Welcome to SuperP2p App',
      logo: require("../assets/lp.png")
    }
  },
  methods:{
    toLogin:function(){
      console.log("quit");
      this.$router.push('/');
    }

  }
}
</script>

<style scoped>
  .homewarp,.el-container{
    height:100%;
    
  }
  .logo{
    display:block;
    width:220px;
    background-color: #1C81D9;
    color:#fff;
    font-size:20px;
    height: 60px;
    line-height: 60px;
    float:left;
  }
  .el-aside{
    background: #fff;
    border-right:solid 1px #eee;
    -webkit-box-shadow: 1px 0px 20px rgba(0, 0, 0, 0.08);
    box-shadow: 1px 0px 20px rgba(0, 0, 0, 0.08);

  }
  .btip{
    background:#f2f6f8;
    width:219px;
    height:54px;
    position: fixed;
    z-index: 10;
    bottom: 0px;
    left: 0px;
    border-top: 1px solid rgba(120, 130, 140, 0.13);
    transition: 0.2s ease-out;
  }
  .btip i{
    font-size: 20px;
    margin: 18px;
    color: #607d8b;
  }
  .btip i:hover{
    color:#ddd; 
    cursor: pointer;
  }

@import './assets/css/common.css';
</style>
