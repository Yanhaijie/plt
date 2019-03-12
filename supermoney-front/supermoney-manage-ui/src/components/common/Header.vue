<template>
    <div class="header">
      <div class="logo">
        <span style="color:#F51212;">Super</span>-Money
      </div>
      <span style="float:right;margin-right:26px;color:#fff;margin-top:30px;"><span style="color:#ffd04b">{{userName}}</span>，<a @click="logOut" class="log-out"> 退出</a></span>
      
    </div>
</template>
<script>
export default {
  name: 'Header',
  data () {
    return {
    msg:"header content"
    }
  },
  computed:{
    userName(){
      return this.$store.getters.userName
    }
  },
  methods:{
        logOut(){
          window.location = "/";
          sessionStorage.clear();
        }

  }
}
</script>

<style>
.el-header{
    background-color: #1f262d !important;
    padding:0 !important;
    margin:0 !important;
    height:auto  !important;
   
}
.header{
  height:60px ;
  /* border-left: 2px solid #000000 !important; */
}
.logo{
     display:block;
     width: 220px;
    background-color: #1f262d;
    color:#fff;
    font-size:20px;
    /* height: 60px; */
    line-height: 60px;
    /* margin-bottom: 40px;  */
    float: left;
  }
  .log-out:hover{
      cursor: pointer;
      color: rgb(245, 18, 18);
  }
</style>