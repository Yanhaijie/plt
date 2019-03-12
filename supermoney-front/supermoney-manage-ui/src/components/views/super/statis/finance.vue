<template>
<div class="conwarp">
<!-- grid -->
<div class="grid">
    <div class="totalTable" style="margin-bottom:20px" >
        <el-table :data="totalAmount" border style="width: 100%" :row-class-name="tableRowClassName">
            <el-table-column  prop="bountyTotalAmount"   label="赏金总金额(总)" ></el-table-column>
            <el-table-column  prop="selfBountyTotalAmount"   label="任务赏金(总)" ></el-table-column>
            <el-table-column  prop="secBountyTotalAmount"   label="提成赏金(总)" ></el-table-column>
            <el-table-column  prop="retailTotalAmount"   label="话费充值总金额(总)" ></el-table-column>
            <el-table-column  prop="wholeSaleTotalAmount"   label="话费成本总金额(总)" ></el-table-column>
            <el-table-column  prop="freezeBountyTotalAmount"   label="赏金冻结总金额(总)" ></el-table-column>
            <el-table-column  prop="freezeOutBountyTotalAmount"   label="赏金提现成功总金额(总)" ></el-table-column>
            <el-table-column  prop="freezeBackBountyTotalAmount"   label="赏金提现失败总金额(总)" ></el-table-column>
        </el-table>
    </div>
    <div class="">
        <el-form :inline="true">
            <el-form-item>
                <el-input  placeholder="请输入id"   class="w18"  prefix-icon="el-icon-search" v-model="criteria.userId" ></el-input> 
            </el-form-item>
            <el-form-item>  
                <el-input  placeholder="请输入电话号码"   class="w18"  prefix-icon="el-icon-search" v-model="criteria.mobile" ></el-input> 
            </el-form-item>
            <el-form-item><el-button type="primary" icon="el-icon-search" @click="search">搜索</el-button></el-form-item>
        </el-form>
    </div>
    <div class="gmain">
        <el-table :data="tableData" stripe border style="width: 100%" v-loading="loading">
            <el-table-column  prop="userId"   label="用户id" ></el-table-column>
            <el-table-column  prop="mobile"   label="手机号" ></el-table-column>
            <el-table-column  prop="bountyAmount"   label="赏金总金额" ></el-table-column>
            <el-table-column  prop="selfBountyAmount"   label="任务赏金"  ></el-table-column>
            <el-table-column  prop="secBountyAmount"   label="提成赏金"  ></el-table-column>
            <el-table-column  prop="retailAmount"   label="话费充值总金额"  ></el-table-column>
            <el-table-column  prop="wholeSaleAmount"   label="话费成本总金额"  ></el-table-column>
            <el-table-column  prop="freezeOutBountyAmount"   label="赏金冻结总金额"  ></el-table-column>
            <el-table-column  prop="freezeBountyAmount"   label="赏金提现成功总金额"  ></el-table-column>
            <el-table-column  prop="freezeBackBountyAmount"   label="赏金提现失败总金额"  ></el-table-column>
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
import {getFinanceList} from '@/api'

export default {
  data () {
      return{
             tableData:[],
             totalAmount:[], // 总量统计
             //分页-数量
             pagesize: 10,
             //分页-当前页码
             currentPage: 1,
             //分页-总数
             totalCount:0,
             //搜索条件
             criteria: {
                 userId:'',
                 mobile:'',
             },
             loading:true,
             
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
            if(criteria.userId){
                params.userId = criteria.userId;
            }
            if(criteria.mobile){
                params.mobile = criteria.mobile;
            }
        }catch(err){
            
        }
        //获取财务统计列表
        getFinanceList(params).then(result => {
                var data=result.data;
                 self.pagesize=data.pageSize;
                 self.totalCount=data.dataTotalCount;
                 self.currentPage = data.pageNo;
                 self.tableData=data.reportData;
                 self.loading = false;
                 self.totalAmount =[data.sBountyTotalReport] ;
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
       tableRowClassName({row, rowIndex}) {
        return 'success-row';
      }
  }
}
</script>
<style>
    .el-table .success-row {
        background: #f0f9eb;
    }
</style>

