<template>
  <div>
    <el-collapse-transition>
      <header v-show="show3">
        <img class="transition-box" src="../assets/images/logo.png">
      </header>
    </el-collapse-transition>
    <input placeholder="Please enter the cell phone number" v-model="inputPhone" @click="show3 = !show3" class='inputPhone' type='number' v-on:focus="changePlho">
    <div class="send-box" v-on:click="checkPhone">
      <input placeholder="Please enter SMS authentication code" v-model="inputNum" class='inputNum' type='number' v-on:input="checkCode">
      <span class="send-code" @click="sendCode">Send</span>
    </div>
    <div class="btn" v-bind:class="{ btnActive:btnActive }">Sign in</div>
    <!-- <div class="password-login">Password login</div> -->
  </div>
</template>
<script>
  // import CollapseTransition from 'element-ui/lib/transitions/collapse-transition';
  // import Vue from 'vue';
  // Vue.component(CollapseTransition.name, CollapseTransition);
  export default {
    name: 'login',
    data() {
      return {
        show3: true,
        inputPhone: '',
        inputNum: '',
        btnActive: false
      }
    },
    methods: {
		// 获取焦点改变placehold提示语
		changePlho(){
			$('.inputPhone').attr('placeholder','08 **** ****');
		},
      // 检验手机号
      checkPhone: function (e) {
        let sMobile = this.inputPhone;
        if (!(/^1[3|4|5|8][0-9]\d{4,8}$/.test(sMobile))) {
          alert("不是完整的11位手机号或者正确的手机号前七位");
          $('.inputPhone').focus();
          return false;
        }
        this.invokeSettime('.send-code');
        $('.send-code').addClass('time-num');
        return true;
      },
      // 检验验证码是否为6位数字
      checkCode() {
        let inputNum = this.inputNum;
        if (/^\d{6}$/.test(inputNum)) {
          this.btnActive = true;
          // alert("请输入6位数字验证码");
          return false;
        }
      },
      // 验证码倒计时
      invokeSettime(obj) {
        let countdown = 60;
        settime(obj);

        function settime(obj) {
          if (countdown == 0) {
            $(obj).text("Send");
            $(obj).removeClass('time-num');
            countdown = 60;
            return;
          } else {
            $(obj).text(countdown + " s ");
            countdown--;
          }
          setTimeout(function () {
            settime(obj)
          }, 1000)
        }
      },
      // 发送验证码
      sendCode() {
        this.$ajax({
          method: 'post',
          url: 'http://13.250.107.72:8902/ut/sendcode?mobile=13678561234'
        })
      }
    }
  }

</script>
<!-- Add "scoped" attribute to limit CSS to this component only -->
<style>
  header {
    text-align: center;
    margin-top: 1.44rem/* 54px */
    ;
  }

  header img {
    display: inline-block;
    width: 1.70666667rem/* 64px */
    ;
    height: 1.70666667rem/* 64px */
    ;
    border: 1px solid #FFFFFF;
    object-fit: cover;
  }

  .inputPhone,
  .inputNum {
    -webkit-box-sizing: border-box;
    box-sizing: border-box;
    padding-left: .42666667rem;
    -web-kit-appearance: none;
    -moz-appearance: none;
    outline: none;
    display: block;
    width: 9.14666667rem;
    height: 1.06666667rem;
    font-size: .32rem/* 12px */
    ;
    border: 1px solid #E9E9E9;
    color: #333;
    letter-spacing: 0.2px;
  }

  .inputNum {
    border-top: none;
  }

  input::-webkit-input-placeholder {
    font-size: .37333333rem/* 14px */
    ;
    color: #BBBBBB;
    letter-spacing: 0.23px;
  }

  .send-box {
    position: relative;
  }

  .send-box span {
    position: absolute;
    top: 0;
    right: 0;
    width: 2rem;
    height: 1.16666667rem/* 40px */
    ;
    line-height: 1.16666667rem/* 40px */
    ;
    transform: translateX(-20%);
    font-size: .32rem/* 12px */
    ;
    color: #666666;
    letter-spacing: 0.2px;
  }

  .send-box span::before {
    content: '';
    display: inline-block;
    vertical-align: middle;
    margin-right: 5px;
    height: .66666667rem/* 25px */
    ;
    width: 1px;
    line-height: .66666667rem;
    background: #D8D8D8;
    border-radius: 4px;
  }

  .btn {
    font-size: 16px;
    color: #FFFFFF;
    letter-spacing: 0.26px;
    text-align: center;
    width: 9.14666667rem;
    height: 1.06666667rem;
    line-height: 1.06666667rem;
    background: #C4C4C4;
    border-radius: 4px;
    margin: .64rem auto .64rem;
  }

  .password-login {
    font-size: 12px;
    color: #49AFF6;
    letter-spacing: 0.2px;
  }

  span.time-num {
    color: #EE4866;
  }

  .btnActive {
    background: #EE4866;
    border-radius: 4px;
  }

</style>
