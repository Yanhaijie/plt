<template>
  <div>
   <div class="left-tip">
      <span class="label" id="currentMenu">当前页面：{{currentMenu}}</span>
    </div>
    <div class="right-tip">
        <img :src="picUrl" class="pic" >
        <span  class="label">{{realName}} </span>
    </div>
  </div>
</template>
<script>
import eventVue from '@/libs/event'
export default {
  name: 'Header',
  data () {
    return {
    msg:"welcome to superP2p manage!",
    realName:"Jhoncena",
    picUrl:"",
    boy:require("../../assets/2.png"),
    gril:require("../../assets/1.png"),
    currentMenu:"",
    }
  },

  computed:{
     showInfo(){
     },

  },
/*  created() {
    this.$on('blur', (arg) => {
      this.currentMenu = arg; // 接收
    });
  },*/
  mounted(){
     var self=this;
     console.log("sex:",sessionStorage.sex);
     self.picUrl=sessionStorage.sex=="1" ? self.gril:self.boy;
     self.realName=sessionStorage.realName;

  },
  created:function(){
       this.bbtn();
   },
  methods:{
        bbtn:function(){
            eventVue .$on("myFun",(message)=>{   //这里最好用箭头函数，不然this指向有问题
                this.currentMenu = message;
            })
       }
  }
}
</script>

<style>
.el-header{
    background-color: #1e88e5 !important;
    padding:0 !important;
    margin:0 !important;
    -webkit-box-shadow: 5px 0px 10px rgba(0, 0, 0, 0.5);
    box-shadow:5px 0px 10px rgba(0, 0, 0, 0.5);


}
.right-tip{
 height:60px;
 width:160px;
 float:right;

 margin:0 20px 0 0;
}

.left-tip{
  height:60px;
  width:700px;
  float:left;
  margin:0 20px 0 0;
}

.pic{

    width: 40px;
    height: 40px;
    border-radius: 25px;
    border: 1.5px solid #fff;
    float: left;
    margin: 8px 0 0;

}

.label{
    float:left;
    padding:0 0 0 12px;
    margin:18px 0 0 0;
    font-size:18px;
    color:#fff;
    font-weight:bold;
}

</style>
