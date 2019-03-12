<template>
<div class="conwarp">
<!-- grid -->
<div class="grid">
    <div class="searchbar">
        <el-form :inline="true">
            <el-form-item><el-input  placeholder="请输入消息编号"   class="w18"  prefix-icon="el-icon-search" v-model="criteria.messageId" ></el-input> </el-form-item>
            <el-form-item><el-input  placeholder="请输入消息标题"   class="w18"  prefix-icon="el-icon-search" v-model="criteria.messageTitle" ></el-input> </el-form-item>
            <el-form-item><el-input  placeholder="请输入用户编号"   class="w18"  prefix-icon="el-icon-search" v-model="criteria.userId" ></el-input> </el-form-item>
            <el-form-item><el-input  placeholder="请输入用户名称"   class="w18"  prefix-icon="el-icon-search" v-model="criteria.userName" ></el-input> </el-form-item>
            <el-form-item><el-button type="primary" icon="el-icon-search" @click="search">搜索</el-button></el-form-item>
        </el-form>
    </div>
    <div class="gmain">
            <el-table :data="tableData"  ref="multipleTable"  tooltip-effect="dark"  @selection-change="handleSelectionChange" stripe style="width: 100%">
            <el-table-column type="selection" width="55"></el-table-column>
            <el-table-column  prop="messageId"   label="消息编号" ></el-table-column>
            <el-table-column  prop="messageTitle"   label="消息标题" ></el-table-column>
            <el-table-column  prop="userId"   label="用户编号" ></el-table-column>
            <el-table-column  prop="userName"   label="用户名" ></el-table-column>
            <el-table-column  prop="readStatus"  label="阅读状态" >
                <template slot-scope="scope">
                     <span v-if="scope.row.readStatus==0">未读</span>
                     <span v-if="scope.row.readStatus==1" style="color:green;" >已读</span>
                </template>
            </el-table-column>
            <el-table-column  prop="deleteStatus"  label="移除状态" >
                <template slot-scope="scope">
                     <span v-if="scope.row.deleteStatus==0">未移除</span>
                     <span v-if="scope.row.deleteStatus==1" style="color:green;" >已移除</span>
                </template>
            </el-table-column>
            <el-table-column label="操作"  >
                 <template slot-scope="scope">
                    <el-button size="small" type="danger"   @click="handleDelete(scope.$index, scope.row)">删除</el-button>
                    <el-button size="small" type="danger"  v-if="scope.row.deleteStatus==0"  @click="handleRemove(scope.$index, scope.row)">移除</el-button>
                    <el-button size="small" type="danger"  v-if="scope.row.deleteStatus==1" disabled="true" >已移除</el-button>
                 </template>
            </el-table-column>
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
import {getMessageUserList,delMessageUser,removeMessageUser} from '@/api'
import MomentPlugin from '@/libs/MomentPlugin'
export default {
  name: 'messagetuser',
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
                 messageId:'',
                 messageTitle:'',
                 userId:'',
                 userName:''
             },
             multipleSelection: [],
             dialogFormVisible: false,
             dialogWidth:'500px',
             formLabelWidth: '80px'

      }
  },
  mounted(){
     var self=this;
     self.reload();
  },
  methods:{
     //分页数据加载
     loadData: function(criteria, pageNum, pagesize){
          console.log("load");
        var self=this;
        var params={page:pageNum,size:pagesize,search:""};
        if(criteria!=null){
            params.search=JSON.stringify({userId:criteria.userId,userName:criteria.userName,messageId:criteria.messageId,messageTitle:criteria.messageTitle});
        }
        getMessageUserList(params).then(result => {
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
     restForm:function(){
        var self=this;
        self.form={id:'',name:'',img:'',useStatus:'0'};
        self.dialogFormVisible=false;
     },
     close:function(){
         var self=this;
         self.restForm();
     },
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
     handleSelectionChange(val) {
        this.multipleSelection = val;
      },
      //查询
      search:function(){
          var self=this;
          console.log("search");
          this.loadData(self.criteria, this.currentPage, this.pagesize);
      },
     //删除
     handleDelete: function(index, row) {
          console.log("d:",index, row);
          var self=this;
          if(!confirm("你确定要删除吗？"))return;
          delMessageUser({id:row.id}).then(result => {
              console.log('del:',result);
             if(result.code!="200"){
                alert(result.message);
                return;
             }
             self.reload();
          });
     },
    //删除
    handleRemove: function(index, row) {
      console.log("d:",index, row);
      var self=this;
      if(!confirm("你确定要移除吗？"))return;
      removeMessageUser({id:row.id}).then(result => {
        console.log('del:',result);
        if(result.code!="200"){
          alert(result.message);
          return;
        }
        self.reload();
      });
    }
  }
}
</script>
