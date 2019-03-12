<template>
  <div id="index" class="index">
    <div class="search-box">
      <div>资产类型<img src="../assets/images/trun-down.png"></div>
      <div>利率<img src="../assets/images/trun-down.png"></div>
      <div>期限<img src="../assets/images/trun-down.png"></div>
      <span>更多</span>
    </div>
    <ul class="pro-list-box">
      <li v-for="key in proList" :key="key"  class="pro-list">
        <div class="pro-title">
          <div class="pro-name">{{key.item_title}}{{key.asset_info}}</div>
          <div class="pro-tag">信</div>
          <div class="pro-tag">3A</div>
        </div>
        <div class="pro-main">
          <div class="pro-mainL">
            <div>利率</div>
            <span>{{key.borrow_rate}}%</span>
          </div>
          <div class="pro-mainR">
            <div><img src="../assets/images/1.png"><span>¥ {{key.borrow_amount}}元</span></div>
            <div><img src="../assets/images/2.png"><span>期限：{{key.borrow_deadline}}</span></div>
            <div><img src="../assets/images/3.png"><span>还款方式：{{key.repay_way}}</span></div>
          </div>
        </div>
      </li>
    </ul>
    <div class="show-box">
    </div>
  </div>
</template>
<script>
export default {
  name: 'index',
  data() {
    return {
      proList:[]
    }
  },
  mounted(){
    this.init();
  },
  methods:{
    init() {
      var vm = this;
        vm.axios.post('http://www.loansface.com/p2p/ut/getAssetItemPoolList').then(function(res){
          console.log(res.data.data.list[0]);
        vm.proList = res.data.data.list;
})
.catch(function(err){
  console.log(err);
});
      }
  }
}

</script>
<!-- Add "scoped" attribute to limit CSS to this component only -->
<style scoped>
.index {
  background: #F5F5F5;
  width: 100%;
  height: 100%;
}


/*顶部搜索*/

.search-box {
  width: 100%;
  height: 1.70666667rem/* 64px */
  ;
  display: flex;
  justify-content: center;
  align-items: center;
  background: #FFFFFF;
}

.search-box div {
  padding: 0 .32rem/* 12px */
  ;
  height: .96rem/* 36px */
  ;
  display: flex;
  justify-content: center;
  align-items: center;
  border: 1px solid #FEB201;
  border-radius: 1.12rem/* 42px */
  ;
  font-family: PingFangSC-Regular;
  font-size: .37333333rem/* 14px */
  ;
  color: #666666;
  letter-spacing: 0;
  margin-left: .42666667rem;
}

.search-box img {
  width: .29333333rem/* 11px */
  ;
  object-fit: cover;
  margin-left: .21333333rem/* 8px */
  ;
}

.search-box span {
  display: block;
  width: .8rem;
  height: .53333333rem/* 20px */
  ;
  margin-left: .74666667rem/* 28px */
  ;
  margin-right: .42666667rem;
  font-family: PingFangSC-Regular;
  font-size: .37333333rem/* 14px */
  ;
  color: #FEB101;
  letter-spacing: 0;
}


/*产品列表*/

.pro-list-box {
  margin-top: .26666667rem/* 10px */
  ;
  display: flex;
  flex-direction: column;
  justify-content: center;
  align-items: center;
  background: #FFFFFF;
  box-sizing: border-box;
  padding: .42666667rem 0;
}

.pro-list {
  width: 9.14666667rem/* 343px */
  ;
  height: 4.72rem/* 177px */
  ;
  list-style: none;
  background: #FFFFFF;
  box-shadow: 0 1px 4px 1px rgba(0, 0, 0, 0.16);
  border-radius: 6px;
  box-sizing: border-box;
  padding: .42666667rem;
  margin-bottom: .26666667rem/* 10px */;
}

.pro-title {
  display: flex;
  width: 100%;
  padding-bottom: .42666667rem;
  border-bottom: solid 1px #F3F3F3;
  align-items: center;
}

.pro-name {
  font-family: PingFangSC-Medium;
  font-size: .48rem/* 18px */
  ;
  color: #333333;
}

.pro-tag {
  padding: 0 .24rem/* 9px */
  ;
  height: .53333333rem/* 20px */
  ;
  line-height: .53333333rem/* 20px */
  ;
  margin-left: .21333333rem/* 8px */
  ;
  font-family: PingFangSC-Regular;
  font-size: .32rem/* 12px */
  ;
  color: #FEB101;
  border: 1px solid #FEB101;
  border-radius: 2px;
}

.pro-main {
  display: flex;
  align-items: center;
  height: 2.4rem/* 90px */;
  margin-top: .42666667rem;
}

.pro-mainL{
  display: flex;
  flex-direction: column;
  align-items: center;
  background: #FFFFFF;
  box-sizing: border-box;
  padding: .3rem;
  height: 2.4rem/* 90px */;
  width: 3.33333333rem/* 125px */;
  border-right: 1px solid #F3F3F3;
  justify-content: center;
}
.pro-mainL div{
  font-family: PingFangSC-Regular;
font-size: .37333333rem/* 14px */;
color: #666666;
}
.pro-mainL span{
  display: block;
  height: 1.28rem/* 48px */;
  line-height: 1.28rem/* 48px */;
  font-family: PingFangSC-Regular;
font-size: .90666667rem/* 34px */;
color: #FEB101;
}
.pro-mainR{
  display: flex;
  flex-direction: column;
  justify-content: center;
  background: #FFFFFF;
  box-sizing: border-box;
  padding: .3rem;
  height: 2.4rem/* 90px */;
  justify-content: flex-start;
}
.pro-mainR div{
  display: flex;
  align-items: center;
  height: .48rem/* 18px */;
  margin-bottom: .26666667rem/* 10px */;
}
.pro-mainR div img{
  width: .37333333rem/* 14px */;
  object-fit: cover;
  margin-right: .26666667rem/* 10px */;
}
.pro-mainR div span{
  font-family: PingFangSC-Regular;
font-size: .32rem/* 12px */;
color: #666666;
}
</style>
