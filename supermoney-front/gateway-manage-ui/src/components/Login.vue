<template>
  <div>
    <img src="../assets/logo.png">
    <div class="container" style="width: 400px; margin: 0 auto;">
      <h2  style="color:#4AD0D8; text-align: center;">{{ msg }}</h2>
          <el-form ref="loginForm" :model="loginForm" :rules="rules"  label-width="80px">
          <el-form-item label="账号" prop="username">
              <el-input v-model="loginForm.username"  placeholder="请输入账号"></el-input>
          </el-form-item>
            <el-form-item label="密码" prop="password">
              <el-input type="password" v-model="loginForm.password" placeholder="请输入密码" @keyup.enter.native="onSubmit('loginForm')" ></el-input>
          </el-form-item>
            <el-form-item>
              <el-button type="primary" @click="onSubmit('loginForm')">登录</el-button>
          </el-form-item>
      </el-form>
  </div>
</div>
</template>
<script>
import {login,getUserInfo} from '../api'
export default {
  name: 'Login',
  data () {
    return {
      msg: 'GateWay-Manager',
      loginForm:{
        username:'',
        password:''
      },
      rules:{
        username: [{ required: true, message: '请输入用户名', trigger: 'blur' }],
        password: [{ required: true, message: '请输入密码', trigger: 'blur' }]
      }
    }
  },
  methods:{
    /**
     * 登录
     */
    onSubmit(formName){
       const self = this;      
       //表单验证
       self.$refs[formName].validate((valid) => {
            if(valid){
              //请求登录
              var params={username:self.loginForm.username,password:self.loginForm.password};
              login(params).then(result => {
                  if(result["access_token"]){
                      //记录tokenInfo
                      sessionStorage.setItem("access_token",result.access_token);
                      self.GLOBAL.tokenSession=result;
                      self.$store.state.userinfo.username=self.loginForm.username;
                      self.$store.state.userinfo.tokeninfo=result;
                      self.$router.push('/home');
                      //self.inside(self.$store.state.userinfo);                                           
                  }else{
                    self.err(result);
                  }
                 
              });
            }
       });
    },
    err(msg){
        this.$message.error(msg);
    },
    /**
     * 加载菜单进入Home
     */
    inside(userinfo){
      const self = this;    
      var qr="appcode="+userinfo.appcode;
      getUserInfo(qr).then(result => {
           console.log("get-user-info:",result);
           self.$store.state.userinfo.menu=result.data.menus;
           console.log('session:', self.$store);  
           self.$router.push('/home');
      });
    }
  }
}
</script>