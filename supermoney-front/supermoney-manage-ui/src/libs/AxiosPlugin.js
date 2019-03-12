import axios from 'axios'
import { Message } from 'element-ui';
const Axios = axios.create({
  timeout: 20000
})
//Axios 上传文件
Axios.uploadFile=function(url,formData,callback){
    var token=sessionStorage.access_token;
    url=url+"?access_token="+token;
    let config = {headers: {'Content-Type': 'multipart/form-data'}};
    console.log("Axios.uploadFile-1:",url);
    axios.post(url,formData,config).then(function(res){
         console.log("Axios.uploadFile:",res);
         callback(res.data);
    }).catch(function(error){
        console.log("Axios.uploadFile-err:",error);
    });
}

//POST传参序列化(添加请求拦截器)
Axios.interceptors.request.use(config => {
     var token=sessionStorage.access_token;
     //get附加Token
     if( config.url.indexOf("access_token=")<0 && token){
         config.url=config.url.indexOf("?")>-1?
                    config.url+"&&access_token="+token:
                    config.url+"?access_token="+token;                    
     }
     //post附加Token
    if(config.method  == 'post'){
        var qs = require('qs');        
        var params={};
        Object.keys(config.data).forEach(key => params[key]=config.data[key] );
        config.data = qs.stringify(params);
    }
    return config
},error =>{
    return Promise.reject(error)
})
let self = this;
//返回状态判断(添加响应拦截器)
Axios.interceptors.response.use(res =>{
    //需要登陆
    if(res.data.code == 401 || res.data.code == 402 || res.data.code == 403) {
      window.location = "/login";
    }
    return res
}, error => {
    if(error.response.status == 401) {
        Message({
            message: '请重新登录',
            type: 'warning',
            duration:800,
            onClose: function(){
            window.location = "/";
            }
        })
    }else{
        Message({
            message: error.message,
            type: 'error',
            duration:800
        })
    }
    return Promise.reject(error)
})

Axios.install = (Vue) => {
  Vue.prototype.$http = Axios
}

export default Axios