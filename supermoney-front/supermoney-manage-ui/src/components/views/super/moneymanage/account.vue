<template>
<div class="conwarp">
<!-- grid -->
<div class="grid">
    <!-- <div class="toolbar">
          <el-button type="primary"  icon="el-icon-plus" @click="checkClick()" >审核</el-button>
    </div> -->
    <div class="searchbar">
        <el-form :inline="true">
            <el-form-item><el-input  placeholder="请输入账号"   class="w18"  prefix-icon="el-icon-search" v-model="criteria.userName" ></el-input> </el-form-item>
            <el-form-item><el-input  placeholder="请输入姓名"   class="w18"  prefix-icon="el-icon-search" v-model="criteria.realName" ></el-input> </el-form-item>
            <el-form-item><el-button type="primary" icon="el-icon-search" @click="search">搜索</el-button></el-form-item>
        </el-form>
    </div>
    <div class="gmain">
        <el-table :data="tableData"  ref="multipleTable"  tooltip-effect="dark"  @select-all="handleSelectionChange"  @selection-change="handleSelectionChange"  stripe style="width: 100%">
            <el-table-column type="selection" width="55"></el-table-column>
            <el-table-column  prop="userName"   label="用户账号" ></el-table-column>
            <el-table-column  prop="realName"   label="用户姓名" ></el-table-column>
            <el-table-column  prop="availableAmount"   label="可用余额" ></el-table-column>
            <el-table-column  prop="freezeAmount"   label="冻结余额" ></el-table-column>
        </el-table>
        <div align="center" class="gpage">
                <el-pagination
                     background
                    @size-change="handleSizeChange"
                    @current-change="handleCurrentChange"
                    :current-page="currentPage"
                    :page-sizes="[10, 20,30]"
                    :page-size="pagesize"
                    layout="total, sizes, prev, pager, next, jumper"
                    :total="totalCount">
                </el-pagination>
        </div>
    </div>
</div>
</div>
</template>

<script>
import {userAccountList} from '@/api'
import MomentPlugin from '@/libs/MomentPlugin'
export default {
  name: 'appmanage',
  data () {
      return{
             tableData:[],
             //分页-数量
             pagesize: 10,
             //分页-页码
             currentPage: 1,
             //分页-总数
             totalCount: 100,
             //搜索条件
             criteria: {
                 userName:'',
                 realName:''
             },
             multipleSelection: [],
             rules:{
               
             },
             dialogWidth:'400px',
             dialogAuditFormVisible:false,
             formLabelWidth:'80px'
      }
  },
  mounted(){
     var self=this;
     self.reload();
     this.$refs.multipleTable.multipleSelection;
  },
  methods:{
     //分页数据加载
     loadData: function(criteria, pageNum, pagesize){ 
          console.log("load");
        var self=this;
        var params={page:pageNum,size:pagesize,search:""};
        if(criteria!=null){
            params.search=JSON.stringify({userName:criteria.userName,realName:criteria.realName});    
        }
        userAccountList(params).then(result => {
                var data=result.data;
                self.pagesize=data.pageSize;
                self.totalCount=data.total;
                self.tableData=data.list;
        });
     },
     reload:function(){
         var self=this;
          self.loadData(null,self.currentPage,self.pagesize);
     },
     checkClick:function(){
         var self=this;
         if(self.checkIds.length == 0){
            alert('请选择条目');
            return;
        }
         self.dialogAuditFormVisible=true
     }
     ,
     //分页大小变更
     handleSizeChange: function(val) {
        this.pagesize = val;
        this.loadData(this.criteria, this.currentPage, this.pagesize);
     },
     //分页变更
     handleCurrentChange: function(val) {
        this.currentPage = val;
        this.loadData(this.criteria, this.currentPage, this.pagesize);
     },
         //复选框
     handleSelectionChange(checkData) {
        var self=this;
        var np=[]
        checkData.forEach(p=>{ np.push(p.id) });
        self.checkIds=np;
     },
        //时间格式
      dateFormat:function(row, column) {  
           var self=this;
          var date = row[column.property];  
          if (date == undefined) {  
             return "";  
          }  
          return MomentPlugin(date).format("YYYY-MM-DD HH:mm:ss"); 
     },
      //查询
      search:function(){
          var self=this;
          console.log("search");
          this.loadData(self.criteria, this.currentPage, this.pagesize);
      },
  }
}
</script>