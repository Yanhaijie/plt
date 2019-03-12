import Axios from '../libs/AxiosPlugin'

// 服务器地址 "http://192.168.1.103:8000"  "http://13.250.107.72"; "http://www.supermoneyshop.com";
const BASE_PATH ="http://www.supermoneyshop.com";
//const BASE_PATH ="http://192.168.0.125:8000";
//Content-type
const FORM_URLENCODED={'Content-Type': 'application/x-www-form-urlencoded'};
const FORM_FILE={'Content-Type': 'multipart/form-data; boundary=----WebKitFormBoundaryNoyJJHqcFeqBmpQQ'}; //multipart/form-data
const AJAX_UTF8={'Content-Type':'application/json;charset=UTF-8'};
const STREAM={'Content-Type':'application/octet-stream'}
export var  uploadFileUrl=BASE_PATH+"/super/s/upload/file";
// 用户登录
export const login = params => { return Axios.post(`${BASE_PATH}/uaa/login`, params,{headers:FORM_URLENCODED}).then(res => res.data);};
//用户信息
export const getUserInfo = params=>{ return Axios.get(`${BASE_PATH}/uaa/usermenu?${params}`); };



