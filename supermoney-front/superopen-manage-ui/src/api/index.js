import Axios from '../libs/AxiosPlugin'

// 服务器地址

const BASE_PATH ="http://127.0.0.1:8000";

const SPA_BASE_PATH="http://127.0.0.1:8000";

const OP_BASE_PATH="http://127.0.0.1:8000";

//const BASE_PATH ="http://192.168.0.129:8000";
//Content-type
const FORM_URLENCODED={'Content-Type': 'application/x-www-form-urlencoded'};
const FORM_FILE={'Content-Type': 'multipart/form-data; boundary=----WebKitFormBoundaryNoyJJHqcFeqBmpQQ'}; //multipart/form-data
const AJAX_UTF8={'Content-Type':'application/json;charset=UTF-8'};
const STREAM={'Content-Type':'application/octet-stream'}
export var  uploadFileUrl=BASE_PATH+"/p2p/s/upload/file";
// 用户登录
export const login = params => { return Axios.post(`${SPA_BASE_PATH}/uaa/login`, params,{headers:FORM_URLENCODED}).then(res => res.data);};
//用户菜单列表
export const getUserMenus = params=>{ return Axios.get(`${SPA_BASE_PATH}/uaa/usermenu?${params}`); };

export const getUserInfo = params=>{ return Axios.get(`${SPA_BASE_PATH}/uaa/usermenu?${params}`); };

//获取菜单
export const getMenus = params=>{ return Axios.post(`${BASE_PATH}/p2p/ut/menu`,params,{headers:FORM_URLENCODED}).then(res => res.data)};

//=================文件上传=========================
export const  uploadFile = params=>{  return Axios.uploadFile(uploadFileUrl,params.formData,params.callback)};
export const  uploadCompanyExcel = params=>{  return Axios.uploadFile(`${BASE_PATH}/p2p/s/company/uploadCompanyExcel`,params.formData,params.callback)};


//=========================================================================================
//================================== spa manage ===========================================
//=========================================================================================

//====spa 用户管理===
export const spaUserSave = params=>{ return Axios.post(`${SPA_BASE_PATH}/opa/mg/user/save`, params,{headers:FORM_URLENCODED}).then(res => res.data)};
export const spaUserDel  = params=>{ return Axios.post(`${SPA_BASE_PATH}/opa/mg/user/delete`, params,{headers:FORM_URLENCODED}).then(res => res.data)};
export const spaUserList = params=>{ return Axios.post(`${SPA_BASE_PATH}/opa/mg/user/list`, params,{headers:FORM_URLENCODED}).then(res => res.data)};
export const spaSetUserProduct = params=>{ return Axios.post(`${SPA_BASE_PATH}/opa/mg/user/setproduct`, params,{headers:FORM_URLENCODED}).then(res => res.data)};

//====spa 产品管理===
export const spaProductAdd = params=>{ return Axios.post(`${SPA_BASE_PATH}/opa/mg/product/add`, params,{headers:FORM_URLENCODED}).then(res => res.data)};
export const spaproductDel = params=>{ return Axios.post(`${SPA_BASE_PATH}/opa/mg/product/delete`, params,{headers:FORM_URLENCODED}).then(res => res.data)};
export const spaproductUpdate = params=>{ return Axios.post(`${SPA_BASE_PATH}/opa/mg/product/update`, params,{headers:FORM_URLENCODED}).then(res => res.data)};
export const spaproductList = params=>{ return Axios.post(`${BASE_PATH}/opa/mg/product/list`, params,{headers:FORM_URLENCODED}).then(res => res.data)};
export const spaproductDrop = params=>{ return Axios.post(`${BASE_PATH}/opa/mg/product/drop`, params,{headers:FORM_URLENCODED}).then(res => res.data)};

export const spaProductCache = params=>{ return Axios.post(`${BASE_PATH}/opa/mg/product/reset`, params,{headers:FORM_URLENCODED}).then(res => res.data)};
//====spa 用户产品===
export const spaUserProductList = params=>{ return Axios.post(`${SPA_BASE_PATH}/opa/mg/user/product/list`, params,{headers:FORM_URLENCODED}).then(res => res.data)};
export const spaUserProductSave = params=>{ return Axios.post(`${SPA_BASE_PATH}/opa/mg/user/product/save`, params,{headers:FORM_URLENCODED}).then(res => res.data)};
export const spaUserProductDel = params=>{ return Axios.post(`${SPA_BASE_PATH}/opa/mg/user/product/delete`, params,{headers:FORM_URLENCODED}).then(res => res.data)};


//====op 接口列表===
export const opInterfaceList = params=>{ return Axios.post(`${OP_BASE_PATH}/op/mg/interface/list`, params,{headers:FORM_URLENCODED}).then(res => res.data)};
export const opInterfaceSave = params=>{ return Axios.post(`${OP_BASE_PATH}/op/mg/interface/save`, params,{headers:FORM_URLENCODED}).then(res => res.data)};
export const opInterfaceDelete = params=>{ return Axios.post(`${OP_BASE_PATH}/op/mg/interface/delete`, params,{headers:FORM_URLENCODED}).then(res => res.data)};

//====op 调用记录===
export const opCallRecordList = params=>{ return Axios.post(`${OP_BASE_PATH}/op/mg/call/record/list`, params,{headers:FORM_URLENCODED}).then(res => res.data)};
export const opInterfaceListAll = params=>{ return Axios.post(`${OP_BASE_PATH}/op/mg/interface/list/all`, params,{headers:FORM_URLENCODED}).then(res => res.data)};

//====op 黑名单===
export const opBlacklistList = params=>{ return Axios.post(`${OP_BASE_PATH}/op/mg/black/list/list`, params,{headers:FORM_URLENCODED}).then(res => res.data)};

//====op ocr===
export const opOcrList = params=>{ return Axios.post(`${OP_BASE_PATH}/op/mg/ocr/list`, params,{headers:FORM_URLENCODED}).then(res => res.data)};
