<template>
<div class="conwarp">
<!-- grid -->
<div class="grid">
    <div class="toolbar">
          <el-button type="primary"  icon="el-icon-plus" @click="dialogAuditFormVisible=true" >审核</el-button>
    </div>
    <div class="searchbar">
        <el-form :inline="true">
            <el-form-item>
                <el-input  placeholder="请输入用户ID"   class="w18"  prefix-icon="el-icon-search" v-model="criteria.userId" ></el-input>
                <!-- <el-checkbox   v-model="criteria.sysUser" true-label="4973" false-label="" checked="true">排除系统</el-checkbox> -->
            </el-form-item>
            <el-form-item>
                 <el-input  placeholder="排除用户ID"   class="w10"  prefix-icon="el-icon-search" v-model="criteria.sysUser" ></el-input>
            </el-form-item>
            <el-form-item>
               <el-input  placeholder="手机号"   class="w10"  prefix-icon="el-icon-search" v-model="criteria.mobile" ></el-input>
            </el-form-item>
            <el-form-item>
                <el-date-picker  v-model="criteria.startDate" type="date" format="yyyy-MM-dd" value-format="yyyy-MM-dd HH:mm:ss" placeholder="开始日期"> </el-date-picker>
            </el-form-item>
            <el-form-item>
                <el-date-picker  v-model="criteria.endDate" type="date"  format="yyyy-MM-dd" value-format="yyyy-MM-dd HH:mm:ss" placeholder="结束日期"> </el-date-picker>
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
            <el-table :data="tableData"  ref="multipleTable"  tooltip-effect="dark"  @select-all="handleSelectionChange"  @selection-change="handleSelectionChange"  stripe style="width: 100%">
            <el-table-column type="selection" width="55"></el-table-column>
            <el-table-column  prop="bountyName"   label="赏金任务名称" ></el-table-column>
            <el-table-column  prop="userId"   label="用户ID" ></el-table-column>
            <el-table-column  prop="mobile"   label="用户手机" ></el-table-column>
            <el-table-column  prop="bountyMoney"   label="赏金" ></el-table-column>
            <el-table-column  prop="recordStatus"   label="任务状态" >
                <template slot-scope="scope">
                     <span v-if="scope.row.recordStatus==0"> 完成</span>
                     <span v-if="scope.row.recordStatus==1" style="color:blue;" >进行中</span>
                     <span v-if="scope.row.recordStatus==2" style="color:red;" >未完成</span>
                     <span v-if="scope.row.recordStatus==3" style="color:green;" >已结算</span>
                </template>
            </el-table-column>
            <el-table-column  prop="cbEventName"   label="完成事件" ></el-table-column>
            <el-table-column  prop="updateTime"   label="更新时间" :formatter="dateFormat" ></el-table-column>
            <!--<el-table-column label="操作"  >
                 <template slot-scope="scope">
                    <el-button size="small" type="primary"  @click="handleEdit(scope.$index, scope.row)">编辑</el-button>
                 </template>
            </el-table-column>-->

        </el-table>
        <div align="center" class="gpage">
                <el-pagination
                     background
                    @size-change="handleSizeChange"
                    @current-change="handleCurrentChange"
                    :current-page="currentPage"
                    :page-sizes="[20,30,50,80,100]"
                    :page-size="pagesize"
                    layout="total, sizes, prev, pager, next, jumper"
                    :total="totalCount">
                </el-pagination>
        </div>
    </div>
</div>
<!-- form -->
<el-dialog title="审核任务" :visible.sync="dialogAuditFormVisible" @close="close" :width="dialogWidth">
    <el-form :model="audit" :rules="rules" ref="audit">
        <el-form-item label="任务结果" :label-width="formLabelWidth">
             <input type="hidden" v-model="audit.id"/>
            <el-select v-model="audit.status" placeholder="请选择">
                <el-option label="审核通过" value="3"></el-option>
                <el-option label="审核不通过" value="2"></el-option>
            </el-select>
        </el-form-item>
    </el-form>
    <div slot="footer" class="dialog-footer">
        <el-button @click="dialogAuditFormVisible=false">取 消</el-button>
        <el-button type="primary" @click="toAudit('audit')">确 定</el-button>
    </div>
</el-dialog>

</div>
</template>

<script>
import {getBountyRecordList,getBountyAudit} from '@/api'
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
                 userId:'',
                 bountyId:'',
                 startDate:'',
                 endDate:'',
                 recordStatus:'',
                 sysUser:1,
                 mobile:'',
                 status:[
                     {value: '', label: '所有状态'},
                     {value: '0', label: '完成'},
                     {value: '1', label: '进行中'},
                     {value: '2', label: '未完成'},
                     {value: '3', label: '已结算'}
                 ]
             },
             multipleSelection: [],
             form:{

             },
             audit:{
                 id:'',
                 status:'3'
             },
             rules:{

             },
             auditIds:[],
             dialogWidth:'400px',
             dialogAuditFormVisible:false,
             formLabelWidth:'80px'


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
            params.search=JSON.stringify({sysUser:criteria.sysUser,userId:criteria.userId,bountyId:criteria.bountyId,mobile:criteria.mobile,recordStatus:criteria.recordStatus,startDate:criteria.startDate,endDate:criteria.endDate});
        }
        getBountyRecordList(params).then(result => {
                var data=result.data;
                self.pagesize=data.pageSize;
                self.totalCount=data.total;
                self.tableData=data.list;
        });
     },
     reload:function(){
         var self=this;
          self.loadData(self.criteria,self.currentPage,self.pagesize);
     },
     restForm:function(){
        var self=this;
        self.form={};
        self.dialogAuditFormVisible=false;
     },
     close:function(){
         var self=this;
         self.restForm();
     },
     //审核
     toAudit:function(audit){
        var self=this;
        var ids="";
        self.auditIds.forEach(p=>{ ids+=p+"," });
        if(ids.length>0)ids=ids.substring(0,ids.length-1);

        var param={status:self.audit.status,ids:ids};
        getBountyAudit(param).then(result => {
            console.log("BountyAudit:",result);
            if(result.code!="200"){
                alert(result.message);
                return;
            }
            self.dialogAuditFormVisible=false;
            self.reload();
        });

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
     handleSelectionChange(checkData) {
        var self=this;
        var np=[]
        checkData.forEach(p=>{ np.push(p.id) });
        self.auditIds=np;
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
      //编辑
      handleEdit: function(index, row) {
        var self=this;
        //self.audit.id=row.id;
        self.dialogAuditFormVisible=true;
      }
  }
}
</script>
