
//保留两位小数(不能为空)
export var decimal_10_2 = (rule, value, callback) => {
    if(value == null || value == '' || value == undefined){
        return callback(new Error('不能为空'));
    }
    else{
      var RegStr = /^\d{0,16}\.?\d{0,2}$/;
      value += '';
      if(RegStr.test(value) == false){
        return callback(new Error('请输入整数或小数（最多保留两位）'));
      }
    }

    return callback();
};


//保留两位小数(不能为空,包含负数)
export var negative_decimal_10_2 = (rule, value, callback) => {
  if(value == null || value == '' || value == undefined){
    return callback(new Error('不能为空'));
  }
  else{
    var RegStr = /^-?\d{0,16}\.?\d{0,2}$/;
    value += '';
    if(RegStr.test(value) == false){
      return callback(new Error('请输入整数或小数（最多保留两位）'));
    }
  }

  return callback();
};


//纯数字 11 位
export var number_11_null = (rule, value, callback) => {
    if(value == null || value == '' || value == undefined){
    }
    else{
      var RegStr = /^\d{0,16}$/;
      value += '';
      if(RegStr.test(value) == false){
        return callback(new Error('请输入整数'));
      }
    }

    return callback();
};

// 百分比区间 10%~12%
export var percent_scoop = (rule, value, callback) => {
    if(value == null || value == '' || value == undefined){
    }
    else{
      var RegStr = /^\d{1,3}%~\d{1,3}%$/;
      value += '';
      if(RegStr.test(value) == false){
        return callback(new Error('请输入百分比范围如：12%~18%'));
      }
    }

    return callback();
};

//身份证
export var idcardRule = (rule, value, callback) => {
    if(value == null || value == '' || value == undefined){
        return callback(new Error('不能为空'));
    }
    else{
      var RegStr = /(^\d{15}$)|(^\d{18}$)|(^\d{17}(\d|X|x)$)/;
      value += '';
      if(RegStr.test(value) == false){
        return callback(new Error('请输入正确身份证'));
      }
    }

    return callback();
};

//手机号码
export var mobileRule = (rule, value, callback) => {
    if(value == null || value == '' || value == undefined){
        return callback(new Error('不能为空'));
    }
    else{
      var RegStr = /^1[3|4|5|7|8]\d{9}$/;
      value += '';
      if(RegStr.test(value) == false){
        return callback(new Error('请输入正确手机号'));
      }
    }

    return callback();
};

//邮箱
export var emailRule = (rule, value, callback) => {
    if(value == null || value == '' || value == undefined){
        return callback(new Error('不能为空'));
    }
    else{
      var RegStr = /^[a-zA-Z0-9_-]+@[a-zA-Z0-9_-]+(\.[a-zA-Z0-9_-]+)+$/;
      value += '';
      if(RegStr.test(value) == false){
        return callback(new Error('请输入正确邮箱'));
      }
    }

    return callback();
};
