<template>
<div class="conwarp">
<!-- grid -->
<div class="grid">
    <!-- <div class="toolbar">
          <el-button type="primary"  icon="el-icon-plus" @click="checkClick()" >审核</el-button>
    </div> -->
    <div class="searchbar">
        <el-form :inline="true">
            <el-form-item><el-input  placeholder="请输入用户帐号"   class="w18"  prefix-icon="el-icon-search" v-model="criteria.userName" ></el-input> </el-form-item>
            <el-form-item><el-input  placeholder="请输入用户姓名"   class="w18"  prefix-icon="el-icon-search" v-model="criteria.holdingName" ></el-input> </el-form-item>
            <el-form-item><el-input  placeholder="请输入用户身份证号码"   class="w18"  prefix-icon="el-icon-search" v-model="criteria.holdingCard" ></el-input> </el-form-item>
            <el-form-item><el-input  placeholder="请输入预留电话号码"   class="w18"  prefix-icon="el-icon-search" v-model="criteria.holdingPhone" ></el-input> </el-form-item>
            <el-form-item><el-input  placeholder="请输入银行卡号"   class="w18"  prefix-icon="el-icon-search" v-model="criteria.cardNumber" ></el-input> </el-form-item>
            <el-form-item>
                <el-date-picker  v-model="criteria.startDate" type="date"  placeholder="开始日期"> </el-date-picker>
            </el-form-item>
            <el-form-item>
                <el-date-picker  v-model="criteria.endDate" type="date"  placeholder="结束日期"> </el-date-picker>
            </el-form-item>
            <el-form-item><el-button type="primary" icon="el-icon-search" @click="search">搜索</el-button></el-form-item>
        </el-form>
    </div>
    <div class="gmain">
            <el-table :data="tableData"  ref="multipleTable"  tooltip-effect="dark"  @select-all="handleSelectionChange"  @selection-change="handleSelectionChange"  stripe style="width: 100%">
            <el-table-column type="selection" width="55"></el-table-column>
            <el-table-column  prop="userName"   label="用户帐号" ></el-table-column>
            <el-table-column  prop="holdingName"   label="用户姓名" ></el-table-column>
            <el-table-column  prop="holdingCard"   label="用户身份证号码" ></el-table-column>
            <el-table-column  prop="holdingPhone"   label="用户预留电话号码" ></el-table-column>
            <el-table-column  prop="bankName"   label="所属银行" ></el-table-column>
            <el-table-column  prop="cardNumber"   label="银行卡号" ></el-table-column>
            <el-table-column  prop="country"   label="所属国家" ></el-table-column>
            <el-table-column  prop="bindTime"   label="绑定时间" :formatter="dateFormat"></el-table-column>
            <!-- <el-table-column  prop="cardType"   label="卡类型" >
                <template scope="scope">
                     <span v-if="scope.row.cardType==0"> 储蓄卡</span>
                     <span v-if="scope.row.cardType==1" >信用卡</span>     
                </template>
            </el-table-column> -->
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
<!-- <el-dialog title="提现审核" :visible.sync="dialogAuditFormVisible" @close="close" :width="dialogWidth" class="demo-ruleForm">
    <el-form :model="audit" :rules="rules" ref="audit" label-width="80px">
        <el-form-item label="审核结果" prop="status">
            <el-select v-model="audit.status" placeholder="请选择">
                <el-option label="审核通过" value="1"></el-option>
                <el-option label="审核不通过" value="2"></el-option>
            </el-select>
        </el-form-item>
        <el-form-item label="审核备注" prop="remark">
            <el-input type="textarea" v-model="audit.remark"></el-input>
        </el-form-item>
    </el-form>
    <div slot="footer" class="dialog-footer">
        <el-button @click="close()">取 消</el-button>
        <el-button type="primary" @click="toAudit()">确 定</el-button>
    </div>
</el-dialog> -->

</div>
</template>

<script>
import {bindCardList} from '@/api'
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
                 holdingName:'',
                 holdingCard:'',
                 holdingPhone:'',
                 cardNumber:'',
                 startDate:'',
                 endDate:''
             },
             multipleSelection: [],
             form:{
                
             },
             audit:{
                 status:'1',
                 remark:''
             },
             rules:{
               
             },
             checkIds:[],
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
        
        var startDate = '';
        var endDate = '';
        if(criteria != null && criteria.startDate != ''&& criteria.startDate != null){
            startDate = MomentPlugin(criteria.startDate).format("YYYY-MM-DD HH:mm:ss");
        }
        if(criteria != null && criteria.endDate != '' && criteria.endDate != nul){
            endDate = MomentPlugin(criteria.endDate).format("YYYY-MM-DD HH:mm:ss");
        }
        
        if(criteria!=null){
            params.search=JSON.stringify({userName:criteria.userName,holdingName:criteria.holdingName,holdingCard:criteria.holdingCard,holdingPhone:criteria.holdingPhone,cardNumber:criteria.cardNumber,startDate:startDate,endDate:endDate});    
        }
        bindCardList(params).then(result => {
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