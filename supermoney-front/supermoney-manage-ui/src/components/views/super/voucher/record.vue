<template>
<div class="conwarp">
<!-- grid -->
<div class="grid">
    <div class="toolbar">
    </div>
    <div class="searchbar">
        <el-form :inline="true">
            <el-form-item><el-input  placeholder="请输入用户ID"   class="w18"  prefix-icon="el-icon-search" v-model="criteria.userId" ></el-input> </el-form-item>
            <el-form-item>
                <el-date-picker  v-model="criteria.startDate" type="date" format="yyyy-MM-dd" value-format="yyyy-MM-dd HH:mm:ss"  placeholder="开始日期"> </el-date-picker>
            </el-form-item>
            <el-form-item>
                <el-date-picker  v-model="criteria.endDate" type="date" format="yyyy-MM-dd" value-format="yyyy-MM-dd HH:mm:ss" placeholder="结束日期"> </el-date-picker>
            </el-form-item>

            <el-form-item>
                <el-select v-model="criteria.recordStatus" placeholder="任务状态">
                    <el-option v-for="item in criteria.status" :key="item.value"  :label="item.label" :value="item.value"> </el-option>
                 </el-select>
            </el-form-item>
            <el-form-item><el-button type="primary" icon="el-icon-search" @click="search">搜索</el-button></el-form-item>
        </el-form>



    </div>
    <div class="gmain">
            <el-table :data="tableData"  ref="multipleTable"  tooltip-effect="dark"  @selection-change="handleSelectionChange" stripe style="width: 100%">
            <el-table-column type="selection" width="55"></el-table-column>
            <el-table-column  prop="userId"   label="用户ID" ></el-table-column>
            <el-table-column  prop="voucherId"   label="奖券ID" ></el-table-column>
            <el-table-column  prop="useId"   label="业务ID" ></el-table-column>
            <el-table-column  prop="voucherName"   label="奖券名称" ></el-table-column>
            <el-table-column  prop="startTime"   label="开始" :formatter="dateFormat" ></el-table-column>
            <el-table-column  prop="endTime"   label="结束" :formatter="dateFormat" ></el-table-column>
            <el-table-column  prop="voucherBussType"   label="奖券业务类型" >
                <template slot-scope="scope">
                     <span v-if="scope.row.voucherBussType==0"> 赏金</span>
                     <span v-if="scope.row.voucherBussType==1" >贷款</span>
                </template>
            </el-table-column>
            <el-table-column  prop="voucherType"   label="兑换类型" >
                <template slot-scope="scope">
                     <span v-if="scope.row.voucherType==0"> 金钱</span>
                     <span v-if="scope.row.voucherType==1" >利率</span>
                    <span v-if="scope.row.voucherType==2" >积分</span>
                </template>
            </el-table-column>
            <el-table-column  prop="voucherVal"   label="兑换值" ></el-table-column>
            <el-table-column  prop="useVal"   label="使用值" ></el-table-column>
            <el-table-column  prop="recordStatus"   label="状态" >
                <template slot-scope="scope">
                     <span v-if="scope.row.recordStatus==0"> 未使用</span>
                     <span v-if="scope.row.recordStatus==1" >使用中</span>
                     <span v-if="scope.row.recordStatus==2" >使用完</span>
                </template>
            </el-table-column>
            <el-table-column  prop="updateTime"   label="更新时间" :formatter="dateFormat" ></el-table-column>
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
<!-- form -->
</div>
</template>

<script>
import {getVoucherRecordList,updateVoucherRecord} from '@/api'
import MomentPlugin from '@/libs/MomentPlugin'
export default {
  name: 'voucherrecord',
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
                 userId:'',
                 bountyId:'',
                 startDate:'',
                 endDate:'',
                 recordStatus:'',
                 status:[
                     {value: '', label: '所有状态'},
                     {value: '0', label: '打开'},
                     {value: '1', label: '完成'},
                     {value: '2', label: '未完成'}
                 ]
             },
             multipleSelection: [],
             form:{

             },
             rules:{

             }


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
            params.search=JSON.stringify({userId:criteria.userId,recordStatus:criteria.recordStatus,startDate:criteria.startDate,endDate:criteria.endDate});
        }
        getVoucherRecordList(params).then(result => {
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
        self.form={};
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
      }
  }
}
</script>
