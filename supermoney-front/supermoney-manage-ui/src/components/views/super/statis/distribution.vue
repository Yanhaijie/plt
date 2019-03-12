<template>
<div class="conwarp">
<!-- grid -->
<div class="grid">
    <div class="searchbar">
        <el-form :inline="true">
            <el-form-item>
                <el-input  placeholder="请输入id"   class="w18"  prefix-icon="el-icon-search" v-model="criteria.id" ></el-input> 
            </el-form-item>
            <el-form-item>  
                <el-input  placeholder="请输入电话号码"   class="w18"  prefix-icon="el-icon-search" v-model="criteria.mobile" ></el-input> 
            </el-form-item>
            <el-form-item><el-button type="primary" icon="el-icon-search" @click="search">搜索</el-button></el-form-item>
        </el-form>
        
        

    </div>
    <div class="gmain">
        <el-table :data="tableData"  ref="multipleTable" stripe style="width: 100%" v-loading="loading">
            <el-table-column  prop="id"   label="id" ></el-table-column>
            <el-table-column  prop="mobile"   label="电话" ></el-table-column>
            <el-table-column  prop="subCount"   label="下级数量" ></el-table-column>
            <el-table-column  prop="regSource"   label="注册来源"  ></el-table-column>
            <el-table-column  prop="regIp"   label="注册IP"  ></el-table-column>
            <el-table-column  prop="country"   label="国家"  ></el-table-column>
            <el-table-column label="操作"  min-width="200" align="center"> 
                 <template slot-scope="scope">
                    <el-button size="mini" type="success"  @click="viewSub(scope.row.id)">查看下级</el-button>
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
<!--loan -->
<el-dialog title="下级列表" :visible.sync="dialogLoanVisible" :before-close="closeSub" >
     <div class="gmain">
        <el-table :data="subData" stripe style="width: 100%" v-loading="subLoading">
            <el-table-column  prop="id"   label="id" ></el-table-column>
            <el-table-column  prop="mobile"   label="电话" ></el-table-column>
            <el-table-column  prop="subCount"   label="下级数量" ></el-table-column>
            <el-table-column  prop="regSource"   label="注册来源"  ></el-table-column>
            <el-table-column  prop="regIp"   label="注册IP"  ></el-table-column>
            <el-table-column  prop="country"   label="国家"  ></el-table-column>
        </el-table>
        <div align="center" class="subGpage">
                <el-pagination
                    background
                    @size-change="subHandleSizeChange"
                    @current-change="subHandleCurrentChange"
                    :current-page="subCriteria.currentPage"
                    :page-sizes="[10, 20,30]"
                    :pager-count="5"
                    :page-size="subCriteria.pagesize"
                    layout="total, sizes, prev, pager, next, jumper"
                    :total="subCriteria.totalCount">
                </el-pagination>
        </div>
     </div>
</el-dialog>
</div>
</template>

<script>
import {getDistributionList} from '@/api'

export default {
  data () {
      return{
             tableData:[],
             subData:[], //下级类别数据
             //分页-数量
             pagesize: 10,
             //分页-当前页码
             currentPage: 1,
             //分页-总数
             totalCount:0,
             //搜索条件
             criteria: {
                 id:'',
                 mobile:'',
             },
             subCriteria:{
                 pagesize:10,
                 currentPage:1,
                 totalCount:0
             },
             dialogLoanVisible:false,
             currPid:'',
             loading:true,
             subLoading:false
      }
  },
  mounted(){
     this.reload();
  },
  methods:{
     //分页数据加载
     loadData: function(criteria, pageNum, pagesize){ 
        var self=this;
        var params={pageNo:pageNum,pageSize:pagesize};
        try{
            if(criteria.id){
                params.id = criteria.id;
            }
            if(criteria.mobile){
                params.mobile = criteria.mobile;
            }
        }catch(err){
            
        }
        //获取分销统计列表
        getDistributionList(params).then(result => {
                var data=result.data;
                 self.pagesize=data.pageSize;
                 self.totalCount=data.dataTotalCount;
                 self.currentPage = data.pageNo;
                 self.tableData=data.distributions;
                 self.loading = false;
        }).catch(err => {
            this.loading = false;
        });
     },
     reload:function(){
          this.loadData(null,this.currentPage,this.pagesize);
     },
     //分页大小变更
     handleSizeChange: function(val) {
        this.loading = true;
        this.pagesize = val;
        this.loadData(this.criteria, this.currentPage, this.pagesize);
     },
     //分页变更
     handleCurrentChange: function(val) {
        this.loading = true;
        this.currentPage = val;
        this.loadData(this.criteria, this.currentPage, this.pagesize);
     },
      //查询
      search:function(){
          this.loading = true;
          this.currentPage = 1;
          this.loadData(this.criteria, this.currentPage, this.pagesize);
      },
     //查看下级列表
      viewSub: function(id) {
        var self=this;
        this.currPid = id;
        var params={'pid':this.currPid,pageNo:self.subCriteria.currentPage,pageSize:self.subCriteria.pagesize};
        this.subLoading = true;
        getDistributionList(params).then(result => {
            var data=result.data;
            self.subData = data.distributions;
            self.subCriteria.totalCount = data.dataTotalCount;
            self.subLoading = false;
        });
        self.dialogLoanVisible=true;
      },
      //下级列表分页大小变更
     subHandleSizeChange: function(val) {
        this.subLoading = true;
        this.subCriteria.pagesize = val;
        this.viewSub(this.currPid);
     },
     //下级列表分页变更
     subHandleCurrentChange: function(val) {
        this.subLoading = true;
        this.subCriteria.currentPage = val;
        this.viewSub(this.currPid);
     },
      //关闭下级列表弹框
      closeSub(){
          this.dialogLoanVisible = false;
          this.subCriteria = {
               pagesize:10,
                currentPage:1,
                totalCount:0
          }
      }
  }
}
</script>
<style>
    .el-dialog{
       display: inline-block;
        width: auto !important;
        min-width: 60% !important;
    }
</style>
