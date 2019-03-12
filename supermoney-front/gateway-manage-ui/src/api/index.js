import Axios from '../libs/AxiosPlugin'

// 服务器地址	http://13.250.107.72:8000  http://13.250.107.72
const BASE_PATH ="http://www.supermoneyshop.com";
//const BASE_PATH ="http://192.168.0.119:8000";
//Content-type
const FORM_URLENCODED={'Content-Type': 'application/x-www-form-urlencoded'};
const AJAX_UTF8={'Content-Type':'application/json;charset=UTF-8'};
// 用户登录
export const login = params => { return Axios.post(`${BASE_PATH}/uaa/login`, params,{headers:FORM_URLENCODED}).then(res => res.data);};
//用户信息
export const getUserInfo = params=>{ return Axios.get(`${BASE_PATH}/uaa/usermenu?${params}`); };

//获取应用列表
export const getAppList = params=>{ return Axios.post(`${BASE_PATH}/gwm/w/application/list`, params,{headers:FORM_URLENCODED}).then(res => res.data)};
//新增应用
export const addApp = params=>{ return Axios.post(`${BASE_PATH}/gwm/w/application/add`, params,{headers:FORM_URLENCODED}).then(res => res.data)};
//更新应用
export const updateApp = params=>{ return Axios.post(`${BASE_PATH}/gwm/w/application/update`, params,{headers:FORM_URLENCODED}).then(res => res.data)};
//删除应用
export const delApp = params=>{ return Axios.post(`${BASE_PATH}/gwm/w/application/delete`, params,{headers:FORM_URLENCODED}).then(res => res.data)};
//应用下拉
export const getAppDrop = params=>{ return Axios.post(`${BASE_PATH}/gwm/w/application/drop`, params,{headers:FORM_URLENCODED}).then(res => res.data)};


//获取角色列表
export const getRoleList = params=>{ return Axios.post(`${BASE_PATH}/gwm/w/role/list`, params,{headers:FORM_URLENCODED}).then(res => res.data)};
//新增角色
export const addRole = params=>{ return Axios.post(`${BASE_PATH}/gwm/w/role/add`, params,{headers:FORM_URLENCODED}).then(res => res.data)};
//更新角色
export const updateRole = params=>{ return Axios.post(`${BASE_PATH}/gwm/w/role/update`, params,{headers:FORM_URLENCODED}).then(res => res.data)};
//删除角色
export const delRole= params=>{ return Axios.post(`${BASE_PATH}/gwm/w/role/delete`, params,{headers:FORM_URLENCODED}).then(res => res.data)};
//角色下拉
export const getRoleDrop= params=>{ return Axios.post(`${BASE_PATH}/gwm/w/role/drop`, params,{headers:FORM_URLENCODED}).then(res => res.data)};
//获取角色权限
export const getRolePermission= params=>{ return Axios.post(`${BASE_PATH}/gwm/w/role/permission`, params,{headers:FORM_URLENCODED}).then(res => res.data)};
//保存角色权限
export const saveRolePermission= params=>{ return Axios.post(`${BASE_PATH}/gwm/w/role/savepermission`, params,{headers:FORM_URLENCODED}).then(res => res.data)};


//获取路由列表
export const getRouteList = params=>{ return Axios.post(`${BASE_PATH}/gwm/w/access/route/list`, params,{headers:FORM_URLENCODED}).then(res => res.data)};
//新增路由
export const addRoute = params=>{ return Axios.post(`${BASE_PATH}/gwm//w/access/route/add`, params,{headers:FORM_URLENCODED}).then(res => res.data)};
//更新路由
export const updateRoute = params=>{ return Axios.post(`${BASE_PATH}/gwm/w/access/route/update`, params,{headers:FORM_URLENCODED}).then(res => res.data)};
//删除路由
export const delRoute= params=>{ return Axios.post(`${BASE_PATH}/gwm/w/access/route/delete`, params,{headers:FORM_URLENCODED}).then(res => res.data)};


//获取用户列表
export const getUserList = params=>{ return Axios.post(`${BASE_PATH}/gwm/w/user/list`, params,{headers:FORM_URLENCODED}).then(res => res.data)};
//新增用户
export const addUser = params=>{ return Axios.post(`${BASE_PATH}/gwm/w/user/add`, params,{headers:FORM_URLENCODED}).then(res => res.data)};
//更新用户
export const updateUser = params=>{ return Axios.post(`${BASE_PATH}/gwm/w/user/update`, params,{headers:FORM_URLENCODED}).then(res => res.data)};
//删除用户
export const delUser= params=>{ return Axios.post(`${BASE_PATH}/gwm/w/user/delete`, params,{headers:FORM_URLENCODED}).then(res => res.data)};
