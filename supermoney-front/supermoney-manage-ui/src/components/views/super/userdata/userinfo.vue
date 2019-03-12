<template>
<div class="conwarp">
<!-- grid -->
<div class="grid">
    <div class="toolbar">
         <el-button type="primary"  icon="el-icon-plus" @click="dialogFormVisible=true" >发抵用券</el-button>
         <!--<el-button type="primary"  icon="el-icon-plus" @click="dialogVoucherFormVisible=true" >发抵用券</el-button>-->
    </div>
    <div class="searchbar">
        <el-form :inline="true">
            <el-form-item><el-input  placeholder="用户id"   class="w18"  prefix-icon="el-icon-search" v-model="criteria.userId" ></el-input> </el-form-item>
            <el-form-item><el-input  placeholder="账号"   class="w18"  prefix-icon="el-icon-search" v-model="criteria.userName" ></el-input> </el-form-item>
            <el-form-item><el-input  placeholder="姓名"   class="w18"  prefix-icon="el-icon-search" v-model="criteria.realName" ></el-input> </el-form-item>
            <el-form-item>
                <el-date-picker  v-model="criteria.startDate" type="date" format="yyyy-MM-dd" value-format="yyyy-MM-dd HH:mm:ss"  placeholder="开始日期"> </el-date-picker>
            </el-form-item>
            <el-form-item>
                <el-date-picker  v-model="criteria.endDate" type="date" format="yyyy-MM-dd" value-format="yyyy-MM-dd HH:mm:ss"  placeholder="结束日期"> </el-date-picker>
            </el-form-item>

            <el-form-item>
                <el-select v-model="criteria.userStatus" placeholder="任务状态">
                    <el-option v-for="item in criteria.status" :key="item.value"  :label="item.label" :value="item.value"> </el-option>
                 </el-select>
            </el-form-item>
            <el-form-item><el-button type="primary" icon="el-icon-search" @click="search">搜索</el-button></el-form-item>
        </el-form>
    </div>
    <div class="gmain">
            <el-table :data="tableData"  ref="multipleTable"  tooltip-effect="dark"  @select-all="handleSelectionChange"  @selection-change="handleSelectionChange" stripe style="width: 100%">
            <el-table-column type="selection" width="55"></el-table-column>
             <el-table-column  prop="id"   label="用户ID" ></el-table-column>
            <el-table-column  prop="userName"   label="账号名称" ></el-table-column>
            <el-table-column  prop="mobile"   label="手机号码" ></el-table-column>
            <el-table-column  prop="realName"   label="真实姓名" ></el-table-column>
            <el-table-column  prop="email"   label="email" ></el-table-column>
            <el-table-column  prop="creditScore"   label="信用分数" ></el-table-column>
            <el-table-column  prop="needAmount"   label="资金需求" ></el-table-column>
            <el-table-column  prop="needCycle"   label="需求周长" ></el-table-column>
            <el-table-column  prop="userLevel"   label="用户级别" >
                <template slot-scope="scope">
                     <span v-if="scope.row.userLevel==0"> 普通</span>
                     <span v-if="scope.row.userLevel==1"  >会员</span>
                     <span v-if="scope.row.userLevel==2"  >钻石会员</span>
                </template>
            </el-table-column>
            <el-table-column  prop="userStatus"   label="状态" >
                <template slot-scope="scope">
                     <span v-if="scope.row.userStatus==0"> 使用</span>
                     <span v-if="scope.row.userStatus==1" >停止</span>
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
<el-dialog title="发放抵用券" :visible.sync="dialogFormVisible" @close="close" :width="dialogWidth">
    <el-form :model="voucher" :rules="rules" ref="voucher">
        <el-form-item label="发放方式" :label-width="formLabelWidth">
            <el-select v-model="voucher.method" placeholder="请选择方式">
                <el-option label="全部用户" value="0"></el-option>
                <el-option label="选中用户" value="1"></el-option>
            </el-select>
        </el-form-item>
        <el-form-item label="抵用券" :label-width="formLabelWidth">
            <el-select v-model="voucher.voucherId" placeholder="请选择抵用券">
               <el-option v-for="item in voucher.list" :key="item.value"  :label="item.text" :value="item.value"> </el-option>
            </el-select>
        </el-form-item>
    </el-form>
    <div slot="footer" class="dialog-footer">
        <el-button @click="dialogFormVisible=false">取 消</el-button>
        <el-button type="primary" @click="voucherToUser('voucher')">确 定</el-button>
    </div>
</el-dialog>
</div>
</template>

<script>
import {getUserInfoList,addUserInfo,updateUserInfo,voucherUser,dropVoucher} from '@/api'
import MomentPlugin from '@/libs/MomentPlugin'

export default {
  name: 'userinfomanage',
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
                 userName:'',
                 realName:'',
                 startDate:'',
                 endDate:'',
                 userStatus:'',
                 status:[
                     {value: '', label: '所有状态'},
                     {value: '0', label: '待发布'},
                     {value: '1', label: '发布'},
                     {value: '2', label: '停止'}
                 ]
             },
             multipleSelection: [],
             hasUserIds:[],
             form:{

             },
             voucher:{
                 method:'1',
                voucherId:'',
                list:[]
             },
             rules:{
                userName:[
                     { required: true, message: '请输入名称', trigger: 'blur' },
                     {  max:30, message: '长度限制为25个字符', trigger: 'blur' }
                    ]
             },
             dialogFormVisible: false,
             dialogWidth:'400px',
             formLabelWidth: '80px'

      }
  },
  mounted(){
     var self=this;
     self.reload();
     self.getVoucherList();
  },
  methods:{
     //分页数据加载
     loadData: function(criteria, pageNum, pagesize){
          console.log("load");
        var self=this;
        var params={page:pageNum,size:pagesize,search:""};
        if(criteria!=null){
            params.search=JSON.stringify({userId:criteria.userId,userName:criteria.userName,realName:criteria.realName,userStatus:criteria.userStatus,startDate:criteria.startDate,endDate:criteria.endDate});
        }
        getUserInfoList(params).then(result => {
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

      //查询
      search:function(){
          var self=this;
          this.loadData(self.criteria, this.currentPage, this.pagesize);
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
    //编辑
     handleEdit: function(index, row) {
        console.log("e:",index, row);
        var self=this;
        var detail=self.form;
        detail.id=row.id;
        detail.userName=row.bountyName;
        detail.userStatus=row.userStatus.toString();
        self.dialogFormVisible=true;
     },
     //获取抵用券列表
     getVoucherList:function(){
         var self=this;
        dropVoucher({}).then(result => {
            console.log("drop",result);
            if(result.code!="200")return;
            self.voucher.list=result.data;
            console.log("vlist",result.data);
        });
     },
      //复选框
     handleSelectionChange(checkData) {
        var self=this;
        var np=[]
        checkData.forEach(p=>{ np.push(p.id) });
        self.hasUserIds=np;
     },
     voucherToUser(){
        var self=this;
        var ids="";
        if(self.voucher.method=="1"){
            self.hasUserIds.forEach(p=>{ ids+=p+"," });
            if(ids.length>0)ids=ids.substring(0,ids.length-1);
        }
        var param={method:self.voucher.method,voucherId:self.voucher.voucherId,ids:ids};
        voucherUser(param).then(result => {
            console.log("voucherUser:",result);
            if(result.code!="200"){
                alert(result.message);
                return;
            }
            self.dialogFormVisible=false;
            self.reload();
        });

     },
     //保存表单
     formSave(formName){
        var self=this;
        self.$refs[formName].validate((valid) => {
            console.log("valid:",valid);
            if(!valid)return;

            if(self.form.id==""){
                //add
                addUserInfo(self.form).then(result => {
                    console.log("add:",result);
                    if(result.code!="200"){
                        alert(result.message);
                        return;
                    }
                    self.reload();
                    self.restForm();

                });
            }else{
                //update
                updateUserInfo(self.form).then(result => {
                     if(result.code!="200"){
                        alert(result.message);
                        return;
                     }
                     self.reload();
                     self.restForm();

                });
            }
       });


      }
  }
}

</script>
