import Axios from '../libs/AxiosPlugin'

// 服务器地址 "http://192.168.1.103:8000"  "http://13.250.107.72"; "http://www.supermoneyshop.com";

const BASE_PATH = "http://127.0.0.1:8000";

//const BASE_PATH = "http://app.teafunr.com";

//const BASE_PATH ="http://www.supermoneyshop.com";
//const BASE_PATH ="http://10.251.251.109:8901";
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



//用户信息列表
export const getUserInfoList = params=>{ return Axios.post(`${BASE_PATH}/super/s/user/list`, params,{headers:FORM_URLENCODED}).then(res => res.data)};
//新增用户信息
export const addUserInfo = params=>{ return Axios.post(`${BASE_PATH}/super/s/user/add`, params,{headers:FORM_URLENCODED}).then(res => res.data)};
//更新用户信息
export const updateUserInfo = params=>{ return Axios.post(`${BASE_PATH}/super/s/user/update`, params,{headers:FORM_URLENCODED}).then(res => res.data)};
//用戶发抵用券
export const voucherUser= params=>{ return Axios.post(`${BASE_PATH}/super/s/user/voucher`, params,{headers:FORM_URLENCODED}).then(res => res.data)};

//获取任务列表
export const getBountyList = params=>{ return Axios.post(`${BASE_PATH}/super/s/bounty/list`, params,{headers:FORM_URLENCODED}).then(res => res.data)};
//新增任务
export const addBounty = params=>{ return Axios.post(`${BASE_PATH}/super/s/bounty/add`, params,{headers:FORM_URLENCODED}).then(res => res.data)};
//更新任务
export const updateBounty = params=>{ return Axios.post(`${BASE_PATH}/super/s/bounty/update`, params,{headers:FORM_URLENCODED}).then(res => res.data)};
//删除任务
export const delBounty = params=>{ return Axios.post(`${BASE_PATH}/super/s/bounty/delete`, params,{headers:FORM_URLENCODED}).then(res => res.data)};
//任务下拉
export const dropBounty = params=>{ return Axios.post(`${BASE_PATH}/super/s/bounty/drop`, params,{headers:FORM_URLENCODED}).then(res => res.data)};
//获取任务借款列表
export const getBountyLoan = params=>{ return Axios.post(`${BASE_PATH}/super/s/bounty/loan/bountyloan`, params,{headers:FORM_URLENCODED}).then(res => res.data)};
//新增任务借款
export const addBountyLoan = params=>{ return Axios.post(`${BASE_PATH}/super/s/bounty/loan/add`, params,{headers:FORM_URLENCODED}).then(res => res.data)};
//更新任务借款
export const updateBountyLoan = params=>{ return Axios.post(`${BASE_PATH}/super/s/bounty/loan/update`, params,{headers:FORM_URLENCODED}).then(res => res.data)};
//删除任务借款
export const deleteBountyLoan = params=>{ return Axios.post(`${BASE_PATH}/super/s/bounty/loan/delete`, params,{headers:FORM_URLENCODED}).then(res => res.data)};



//获取任务记录列表
export const getBountyRecordList = params=>{ return Axios.post(`${BASE_PATH}/super/s/bounty/record/list`, params,{headers:FORM_URLENCODED}).then(res => res.data)};
//任务审核
export const getBountyAudit = params=>{ return Axios.post(`${BASE_PATH}/super/s/bounty/record/audit`, params,{headers:FORM_URLENCODED}).then(res => res.data)};

//获取券列表
export const getVoucherList = params=>{ return Axios.post(`${BASE_PATH}/super/s/voucher/list`, params,{headers:FORM_URLENCODED}).then(res => res.data)};
//新增券
export const addVoucher = params=>{ return Axios.post(`${BASE_PATH}/super/s/voucher/add`, params,{headers:FORM_URLENCODED}).then(res => res.data)};
//更新券
export const updateVoucher = params=>{ return Axios.post(`${BASE_PATH}/super/s/voucher/update`, params,{headers:FORM_URLENCODED}).then(res => res.data)};
//删除券
export const delVoucher = params=>{ return Axios.post(`${BASE_PATH}/super/s/voucher/delete`, params,{headers:FORM_URLENCODED}).then(res => res.data)};
//下拉抵用券
export const dropVoucher = params=>{ return Axios.post(`${BASE_PATH}/super/s/voucher/drop`, params,{headers:FORM_URLENCODED}).then(res => res.data)};

//获取券记录列表
export const getVoucherRecordList = params=>{ return Axios.post(`${BASE_PATH}/super/s/voucher/user/record/list`, params,{headers:FORM_URLENCODED}).then(res => res.data)};
//更新券记录
export const updateVoucherRecord = params=>{ return Axios.post(`${BASE_PATH}/super/s/voucher/user/record/update`, params,{headers:FORM_URLENCODED}).then(res => res.data)};


//获取身份认证
export const getIdentifyList = params=>{ return Axios.post(`${BASE_PATH}/super/s/at/identity/list`, params,{headers:FORM_URLENCODED}).then(res => res.data)};
//更新身份认证
export const updateIdentify = params=>{ return Axios.post(`${BASE_PATH}/super/s/at/identity/update`, params,{headers:FORM_URLENCODED}).then(res => res.data)};
//审核身份认证
export const updateIdentifyStatus = params=>{ return Axios.post(`${BASE_PATH}/super/s/at/identity/status`, params,{headers:FORM_URLENCODED}).then(res => res.data)};
//删除身份认证
export const delIdentify = params=>{ return Axios.post(`${BASE_PATH}/super/s/at/identity/delete`, params,{headers:FORM_URLENCODED}).then(res => res.data)};
//ocr检查
export const OCRCheck = params=>{ return Axios.post(`${BASE_PATH}/super/s/at/identity/OCR/check`, params,{headers:FORM_URLENCODED}).then(res => res.data)};


//获取配置信息
export const getBussLableList = params=>{ return Axios.post(`${BASE_PATH}/super/s/buss/label/list`, params,{headers:FORM_URLENCODED}).then(res => res.data)};
//更新配置信息
export const updateBussLable = params=>{ return Axios.post(`${BASE_PATH}/super/s/buss/label/update`, params,{headers:FORM_URLENCODED}).then(res => res.data)};
//新增配置信息
export const addBussLable= params=>{ return Axios.post(`${BASE_PATH}/super/s/buss/label/add`, params,{headers:FORM_URLENCODED}).then(res => res.data)};
//删除配置信息
export const delBussLable = params=>{ return Axios.post(`${BASE_PATH}/super/s/buss/label/delete`, params,{headers:FORM_URLENCODED}).then(res => res.data)};
//根据名称获取
export const getBussLableByName = params=>{ return Axios.post(`${BASE_PATH}/super/s/buss/label/bussname`, params,{headers:FORM_URLENCODED}).then(res => res.data)};
//获取banner
export const getBussBanner = params=>{ return Axios.post(`${BASE_PATH}/super/s/buss/label/banners`, params,{headers:FORM_URLENCODED}).then(res => res.data)};
//保存Banner配置
export const  bannerBussLable = params=>{ return Axios.post(`${BASE_PATH}/super/s/buss/label/banner`, params,{headers:FORM_URLENCODED}).then(res => res.data)};
//上传文件 \r\n  multipart/form-data; boundary=----WebKitFormBoundaryNoyJJHqjFeqBmpQQ
// export const  uploadFile = params=>{  return Axios.post(`${BASE_PATH}/super/s/upload/file`, params,{headers:FORM_FILE}).then(res => res.data)};
 export const  uploadFile = params=>{  return Axios.uploadFile(uploadFileUrl,params.formData,params.callback)};
//提现审核列表
export const  cashCheckList = params=>{ return Axios.post(`${BASE_PATH}/super/s/user/cash/list`, params,{headers:FORM_URLENCODED}).then(res => res.data)};
//提现审核
export const  doCashCheck = params=>{ return Axios.post(`${BASE_PATH}/super/s/user/cash/check`, params,{headers:FORM_URLENCODED}).then(res => res.data)};
//用户账户
export const  userAccountList = params=>{ return Axios.post(`${BASE_PATH}/super/s/user/account/list`, params,{headers:FORM_URLENCODED}).then(res => res.data)};
//银行绑卡列表
export const  bindCardList = params=>{ return Axios.post(`${BASE_PATH}/super/s/user/bind/list`, params,{headers:FORM_URLENCODED}).then(res => res.data)};
//充值|奖金审核列表
export const  payList = params=>{ return Axios.post(`${BASE_PATH}/super/s/user/pay/list`, params,{headers:FORM_URLENCODED}).then(res => res.data)};
//充值|奖金审核
export const  doPayCheck = params=>{ return Axios.post(`${BASE_PATH}/super/s/user/pay/check`, params,{headers:FORM_URLENCODED}).then(res => res.data)};


//=================资源管理======================
//查询汇率转换列表
export const getExchangeRateList = params=>{ return Axios.post(`${BASE_PATH}/super/s/exchange/rate/list`, params,{headers:FORM_URLENCODED}).then(res => res.data)};
//删除汇率转换
export const delExchangeRate = params=>{ return Axios.post(`${BASE_PATH}/super/s/exchange/rate/delete`, params,{headers:FORM_URLENCODED}).then(res => res.data)};
//添加汇率转换
export const addExchangeRate = params=>{ return Axios.post(`${BASE_PATH}/super/s/exchange/rate/add`, params,{headers:FORM_URLENCODED}).then(res => res.data)};
//更新汇率转换
export const updateExchangeRate = params=>{ return Axios.post(`${BASE_PATH}/super/s/exchange/rate/update`, params,{headers:FORM_URLENCODED}).then(res => res.data)};

//=================战略管理======================
/*  消息类型  */
//查询消息类型
export const getMessageTypeList = params=>{ return Axios.post(`${BASE_PATH}/super/s/message/type/list`, params,{headers:FORM_URLENCODED}).then(res => res.data)};
//删除消息类型
export const delMessageType = params=>{ return Axios.post(`${BASE_PATH}/super/s/message/type/delete`, params,{headers:FORM_URLENCODED}).then(res => res.data)};
//添加消息类型
export const addMessageType = params=>{ return Axios.post(`${BASE_PATH}/super/s/message/type/add`, params,{headers:FORM_URLENCODED}).then(res => res.data)};
//更新消息类型
export const updateMessageType = params=>{ return Axios.post(`${BASE_PATH}/super/s/message/type/update`, params,{headers:FORM_URLENCODED}).then(res => res.data)};
//消息类型下拉
export const getMessagetTypeDrop= params=>{ return Axios.post(`${BASE_PATH}/super/s/message/type/drop`, params,{headers:FORM_URLENCODED}).then(res => res.data)};

/*  消息页面  */
//查询消息
export const getMessageList = params=>{ return Axios.post(`${BASE_PATH}/super/s/message/list`, params,{headers:FORM_URLENCODED}).then(res => res.data)};
//删除消息
export const delMessage = params=>{ return Axios.post(`${BASE_PATH}/super/s/message/delete`, params,{headers:FORM_URLENCODED}).then(res => res.data)};
//添加消息
export const addMessage = params=>{ return Axios.post(`${BASE_PATH}/super/s/message/add`, params,{headers:FORM_URLENCODED}).then(res => res.data)};
//更新消息
export const updateMessage = params=>{ return Axios.post(`${BASE_PATH}/super/s/message/update`, params,{headers:FORM_URLENCODED}).then(res => res.data)};
//下发消息
export const sendMessage = params=>{ return Axios.post(`${BASE_PATH}/super/s/message/sendMessage`, params,{headers:FORM_URLENCODED}).then(res => res.data)};


/*  消息用户  */
//查询消息用户
export const getMessageUserList = params=>{ return Axios.post(`${BASE_PATH}/super/s/message/user/list`, params,{headers:FORM_URLENCODED}).then(res => res.data)};
//删除消息
export const delMessageUser = params=>{ return Axios.post(`${BASE_PATH}/super/s/message/user/delete`, params,{headers:FORM_URLENCODED}).then(res => res.data)};
//移除消息(逻辑删除)
export const removeMessageUser = params=>{ return Axios.post(`${BASE_PATH}/super/s/message/user/remove`, params,{headers:FORM_URLENCODED}).then(res => res.data)};

export const exportExcelUrl = params=>{ return `${BASE_PATH}/super/s/report/util/exportExcel?access_token=`+sessionStorage.access_token};



/* 活动抽奖*/
//抽奖记录
export const getLotteryRecordList = params=>{ return Axios.post(`${BASE_PATH}/super/s/activity/lottery/record/list`, params,{headers:FORM_URLENCODED}).then(res => res.data)};
//用户抽奖记录统计
export const getUserLottoryList = params=>{ return Axios.post(`${BASE_PATH}/super/s/activity/lottery/record/user/lottery/count`, params,{headers:FORM_URLENCODED}).then(res => res.data)};
//全部礼物列表
export const getAllGiftList = params=>{ return Axios.post(`${BASE_PATH}/super/s/activity/gift/list`, params,{headers:FORM_URLENCODED}).then(res => res.data)};
//后台配置礼物列表
export const getBackGiftList = params=>{ return Axios.post(`${BASE_PATH}/super/s/activity/gift/backList`, params,{headers:FORM_URLENCODED}).then(res => res.data)};
//搜索栏礼物列表
export const searchAllGiftList = params=>{ return Axios.post(`${BASE_PATH}/super/s/activity/gift/searchAllList`, params,{headers:FORM_URLENCODED}).then(res => res.data)};
//礼物保存
export const saveGift = params=>{ return Axios.post(`${BASE_PATH}/super/s/activity/gift/save`, params,{headers:FORM_URLENCODED}).then(res => res.data)};
//删除礼物
export const deleteGift = params=>{ return Axios.post(`${BASE_PATH}/super/s/activity/gift/delete`, params,{headers:FORM_URLENCODED}).then(res => res.data)};
//白名单列表
export const getWhiteList = params=>{ return Axios.post(`${BASE_PATH}/super/s/activity/white/list/list`, params,{headers:FORM_URLENCODED}).then(res => res.data)};
//白名单保存
export const saveWhiteList = params=>{ return Axios.post(`${BASE_PATH}/super/s/activity/white/list/save`, params,{headers:FORM_URLENCODED}).then(res => res.data)};
//删除白名单
export const deleteWhiteList = params=>{ return Axios.post(`${BASE_PATH}/super/s/activity/white/list/delete`, params,{headers:FORM_URLENCODED}).then(res => res.data)};

//权限管理
//路由管理
export const getRouteList = params=>{ return Axios.post(`${BASE_PATH}/gwm/w/access/route/list`, params,{headers:FORM_URLENCODED}).then(res => res.data)};
//添加路由
export const addRoute = params=>{ return Axios.post(`${BASE_PATH}/gwm/w/access/route/add`, params,{headers:FORM_URLENCODED}).then(res => res.data)};
//修改路由
export const updateRoute = params=>{ return Axios.post(`${BASE_PATH}/gwm/w/access/route/update`, params,{headers:FORM_URLENCODED}).then(res => res.data)};
//删除路由
export const delRoute = params=>{ return Axios.post(`${BASE_PATH}/gwm/w/access/route/delete`, params,{headers:FORM_URLENCODED}).then(res => res.data)};
//获取应用列表
export const getAppDrop = params=>{ return Axios.post(`${BASE_PATH}/gwm//w/application/drop`, params,{headers:FORM_URLENCODED}).then(res => res.data)};
//角色管理
export const getRoleList = params=>{ return Axios.post(`${BASE_PATH}/gwm/w/role/list`, params,{headers:FORM_URLENCODED}).then(res => res.data)};
//添加角色
export const addRole = params=>{ return Axios.post(`${BASE_PATH}/gwm/w/role/add`, params,{headers:FORM_URLENCODED}).then(res => res.data)};
//修改角色
export const updateRole = params=>{ return Axios.post(`${BASE_PATH}/gwm/w/role/update`, params,{headers:FORM_URLENCODED}).then(res => res.data)};
//删除角色
export const delRole = params=>{ return Axios.post(`${BASE_PATH}/gwm/w/role/delete`, params,{headers:FORM_URLENCODED}).then(res => res.data)};
//用户管理
export const getUserList = params=>{ return Axios.post(`${BASE_PATH}/gwm/w/user/list`, params,{headers:FORM_URLENCODED}).then(res => res.data)};
//添加用户
export const addUser = params=>{ return Axios.post(`${BASE_PATH}/gwm/w/user/add`, params,{headers:FORM_URLENCODED}).then(res => res.data)};
//修改用户
export const updateUser = params=>{ return Axios.post(`${BASE_PATH}/gwm/w/user/update`, params,{headers:FORM_URLENCODED}).then(res => res.data)};
//删除用户
export const delUser = params=>{ return Axios.post(`${BASE_PATH}/gwm/w/user/delete`, params,{headers:FORM_URLENCODED}).then(res => res.data)};
//获取用户角色列表
export const getRoleDrop = params=>{ return Axios.post(`${BASE_PATH}/gwm/w/role/drop`, params,{headers:FORM_URLENCODED}).then(res => res.data)};
//应用管理
export const getAppList = params=>{ return Axios.post(`${BASE_PATH}/gwm/w/application/list`, params,{headers:FORM_URLENCODED}).then(res => res.data)};
/*数据统计*/
//分销统计
export const getDistributionList = params=>{ return Axios.post(`${BASE_PATH}/super/s/report/distribution`, params,{headers:FORM_URLENCODED}).then(res => res.data)};
//财务统计
export const getFinanceList = params=>{ return Axios.post(`${BASE_PATH}/super/s/report/bountyReport`, params,{headers:FORM_URLENCODED}).then(res => res.data)};
