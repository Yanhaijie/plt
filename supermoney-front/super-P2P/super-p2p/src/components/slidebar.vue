<template>
    <div id="container">
        <p></p>
        <xiongda></xiongda>
        <hr>
        <xionger></xionger>
    </div>
</template>
<script>
import Vue from 'vue'
    /*借助于一个公共的Vue的实例对象，不同的组件可以通过该对象完成事件的绑定和触发*/
//new一个对象，兄弟间的通信，将借助他事件绑定和触发来实现
   var bus = new Vue();
   //熊大发送消息给熊二
        //xiongda组件
        Vue.component("xiongda",{
            methods:{
                sendToXiongEr:function(){
                //给熊二发送消息
                //触发msgToXiongEr事件
                    bus.$emit("msgToXiongEr","哈哈，光头强来了");
                }
            },
            template:`
                <div>
                    <h1>我是熊大</h1>
                    <button @click="sendToXiongEr">Click Me</button>
                </div>
            `
        })
//熊二组件    
        Vue.component("xionger",{
            template:`
                <div>
                    <h1>我是熊二</h1>
                </div>
            `,
            mounted:function(){
//            给该组件绑定一个自定义事件和对应的处理函数    
                //调用bus中的on方法
                //事件的触发一般是接收数据然后处理
                var that = this;
                    bus.$on("msgToXiongEr",function(msg){
                        alert("自定义的事件触发，接收到的数据"+msg);
                    })
            }
        })
    
</script>