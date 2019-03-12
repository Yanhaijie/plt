<template>
<div class="homewarp">
<el-container>
   <el-header>
      <cm-Header></cm-Header>
    </el-header>
  <el-container>
   <el-aside width="220px">
      <cm-Siderbar ref="siderBar"></cm-Siderbar>
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
import { setTimeout } from 'timers';

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
  .el-aside{
      background: #2E363F;
      width: 180px !important;
  }
</style>