import axios from 'axios'

const Axios = axios.create({
  timeout: 20000
})

//POST传参序列化(添加请求拦截器)
Axios.interceptors.request.use(config => {
    console.log("Axios");
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

//返回状态判断(添加响应拦截器)
Axios.interceptors.response.use(res =>{
   // console.log("res",res);
    //需要登陆
    if(res.data.code == 401 || res.data.code == 402 || res.data.code == 403) {
      window.location = "/login";
    }
    return res
}, error => {
    return Promise.reject(error)
})

Axios.install = (Vue) => {
  Vue.prototype.$http = Axios
}

export default Axios