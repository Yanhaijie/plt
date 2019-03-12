<template>
<div class="conwarp">
<!-- grid -->
<div class="grid">
    <div class="searchbar">
        <el-row>
          <el-col><span style="color: red">SQL语句模板(as 后面的为excel标题)：SELECT user_name as 账号 ,real_name 姓名 FROM t_user</span></el-col>
          <br/>
          <br/>
          <br/>
          <el-col>标题排序：</el-col>
          <el-col><el-input  type="textarea" placeholder="请输入excel标题排序名如：账号,姓名(默认无序)"  prefix-icon="el-icon-search" v-model="criteria.titleIndex" style="width: 600px;height: 80px" ></el-input> </el-col>
          <el-col>报表SQL:</el-col>
          <el-col><el-input  type="textarea" placeholder="请输入查询SQL语句如：SELECT user_name as 账号 ,real_name 姓名 FROM t_user"  prefix-icon="el-icon-search" v-model="criteria.sqlStr" style="width: 600px;height: 80px" ></el-input> </el-col>
          <el-col><el-button type="primary" icon="el-icon-search" @click="toExportExcel">导出Excel</el-button></el-col>

        </el-row>
    </div>
</div>
</div>
</template>

<script>
import {exportExcel,exportExcelUrl} from '@/api'
import MomentPlugin from '@/libs/MomentPlugin'

export default {
  name: 'reportutil',
  data () {
      return{
             //导出条件
             criteria: {
               titleIndex:'',
               sqlStr:''
             }

      }
  },
  mounted(){
     var self=this;
  },
  methods:{
    //分页数据加载
    toExportExcel:function(){//导出表格
      var self=this;
      var sql = self.criteria.sqlStr.trim();
      if(sql == null||sql == ''){
        alert("请输入报表SQL!")
        return false;
      }else{
        if(sql.charAt(0).toUpperCase() != 'S'){
          alert("输入非法，请输入合法查询SQL!")
          return false;
        }
      }
      //TODO:待加入严紧的校验
      var params={search:JSON.stringify({titleIndex:self.criteria.titleIndex,sql:self.criteria.sqlStr})};
      var form = document.createElement('form');
      form.name = 'form'
      document.body.appendChild(form);
      for(var obj in params) {
        if(params.hasOwnProperty(obj)) {
          var input = document.createElement('input');
          input.tpye='hidden';
          input.name = obj;
          input.value = params[obj];
          form.appendChild(input)
        }
      }
      form.method = "post";//请求方式                                            ?access_token=
      form.action = exportExcelUrl();
      form.submit();
      document.body.removeChild(form);

    }
  }
}
</script>
