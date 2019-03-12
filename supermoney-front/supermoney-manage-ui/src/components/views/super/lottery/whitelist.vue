<template>
<div class="conwarp">
<!-- grid -->
<div class="grid">
    <div class="toolbar">
          <el-button type="primary"  icon="el-icon-plus" @click="dialogWhiteFormVisible=true" >添加</el-button>
          <el-button type="primary"  @click="lotteryCountClick()" >用户抽奖次数统计</el-button>
    </div>
    <div class="searchbar">
        <el-form :inline="true">
            <el-form-item><el-input  placeholder="请输入用户ID"   class="w18"  prefix-icon="el-icon-search" v-model="criteria.userId" ></el-input> </el-form-item>
            <el-form-item><el-input  placeholder="请输入用户名"   class="w18"  prefix-icon="el-icon-search" v-model="criteria.userName" ></el-input> </el-form-item>

            <!-- <el-form-item :span="3.6">
                <el-date-picker  v-model="criteria.startDate" type="date"  placeholder="开始日期"> </el-date-picker>
            </el-form-item>
            <el-form-item :span="3.7">
                <el-date-picker  v-model="criteria.endDate" type="date"  placeholder="结束日期"> </el-date-picker>
            </el-form-item> -->

            <!-- <el-form-item :span="4">  
                <el-select v-model="criteria.giftId" placeholder="礼物">
                    <el-option v-for="item in criteria.giftList" :key="item.value"  :label="item.label" :value="item.value"> </el-option>
                 </el-select>
            </el-form-item> -->

            <el-form-item>  
                <el-select v-model="criteria.isSent" placeholder="中奖状态">
                    <el-option v-for="item in criteria.isSentList" :key="item.value"  :label="item.label" :value="item.value"> </el-option>
                 </el-select>
            </el-form-item>
            <el-form-item><el-button type="primary" icon="el-icon-search" @click="search">搜索</el-button></el-form-item>
        </el-form>
    </div>
    <div class="gmain">
            <el-table :data="tableData"  ref="multipleTable"  tooltip-effect="dark"  @select-all="handleSelectionChange"  @selection-change="handleSelectionChange"  stripe style="width: 100%">
            <el-table-column type="selection" width="55"></el-table-column>
            <el-table-column  prop="userId"   label="用户ID" ></el-table-column>
            <el-table-column  prop="holdingPhone"   label="用户名" ></el-table-column>
            <el-table-column  prop="giftName"   label="礼物名" ></el-table-column>
            <el-table-column  prop="giftCount"   label="礼物数量" ></el-table-column>
            <el-table-column  prop="lotteryCount"   label="抽奖次数"></el-table-column>
            <el-table-column  prop="doLotteryCount"   label="已抽次数"></el-table-column>
            <el-table-column  prop="lotteryTime"   label="中奖时间" :formatter="dateFormat"></el-table-column>
            <el-table-column  prop="isSent"   label="中奖状态"></el-table-column>
            <el-table-column label="操作"  >
                 <template slot-scope="scope">
                    <el-button size="small" type="primary" v-if="scope.row.isSentStatus==0" @click="handleEdit(scope.$index, scope.row)">编辑</el-button>
                    <el-button size="small" type="danger" v-if="scope.row.isSentStatus==0"  @click="handleDelete(scope.$index, scope.row)">删除</el-button>
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

<!-- form -->
<el-dialog title="添加白名单" :visible.sync="dialogWhiteFormVisible" @close="close" :width="dialogWidth" class="demo-ruleForm">
    <el-form :model="whitelistFrom" ref="whitelistFrom" :rules="rules" label-width="80px">
        <el-form-item label="选择礼物" prop="giftId">
            <el-select v-model="whitelistFrom.giftId" placeholder="礼物">
                    <el-option v-for="item in giftList" :key="item.value"  :label="item.label" :value="item.value"> </el-option>
            </el-select>
        </el-form-item>
        <el-form-item label="用户id" prop="userId">
            <el-input type="number" v-model="whitelistFrom.userId"></el-input>
        </el-form-item>
        <el-form-item label="抽奖次数" prop="lotteryCount">
            <el-input type="number" v-model="whitelistFrom.lotteryCount"></el-input>
        </el-form-item>
    </el-form>
    <div slot="footer" class="dialog-footer">
        <el-button @click="close()">取 消</el-button>
        <el-button type="primary" @click="saveWhiteList()">保 存</el-button>
    </div>
</el-dialog>

<el-dialog title="用户抽奖次数统计" :visible.sync="dialogUserLottoryVisible" :before-close="lotteryClose" :width="lottoryDialogWidth"  class="demo-ruleForm">
    <el-table :data="userLottoryTableData" style="width: 100%">
        <el-table-column align="center" prop="userId"   label="用户ID" ></el-table-column>
        <el-table-column align="center" prop="userName"   label="用户名" ></el-table-column>
        <el-table-column align="center" prop="realPrizes"   label="获得的实物礼品" ></el-table-column>
        <el-table-column align="center" prop="notWiningConut"   label="未中奖次数" ></el-table-column>
        <el-table-column align="center" prop="winingConut"   label="中奖次数" ></el-table-column>
        <el-table-column align="center" prop="totalCount"   label="总抽奖数" ></el-table-column>
    </el-table>
    <div align="center" class="gpage">
                <el-pagination
                     background
                    @size-change="lotteryHandleSizeChange"
                    @current-change="lotteryHandleCurrentChange"
                    :current-page="lotteryCurrentPage"
                    :page-sizes="[10, 20,30]"
                    :page-size="lotteryPagesize"
                    layout="total, sizes, prev, pager, next, jumper"
                    :total="lotteryTotalCount">
                </el-pagination>
        </div>
</el-dialog>

</div>
</template>

<script>
import {saveWhiteList,getWhiteList,getBackGiftList,getUserLottoryList} from '@/api'
import MomentPlugin from '@/libs/MomentPlugin'
export default {
  name: 'appmanage',
  data () {
      return{
             tableData:[],
             userLottoryTableData:[],
             //分页-数量
             pagesize: 10,
             //分页-页码
             currentPage: 1,
             //分页-总数
             totalCount: 100,
             //搜索条件
             criteria: {
                 userName:'',
                 userId:'',
                 startDate:'',
                 endDate:'',
                 giftId:'',
                 isSent:'',
                 isSentList:[
                     {value: '', label: '所有状态'},
                     {value: '0', label: '未中奖'},
                     {value: '1', label: '已中奖'},
                 ]
             },
             rules:{
                giftId: [{ required: true, message: '请选择礼物', trigger: 'blur' }],
                userId: [{ required: true, message: '请输入用户id', trigger: 'blur' }],
                lotteryCount: [{ required: true, message: '请输入抽奖次数', trigger: 'blur' }]
             },
             whitelistFrom:{
                 id:'',
                 giftId:'',
                 userId:'',
                 lotteryCount:'',
             },
             giftList:[],
             multipleSelection: [],
             dialogWidth:'400px',
             formLabelWidth:'80px',
             dialogWhiteFormVisible:false,
             lotteryPagesize: 10,
             lotteryCurrentPage: 1,
             lotteryTotalCount: 100,
             lottoryDialogWidth:'1200px',
             dialogUserLottoryVisible:false
      }
  },
  mounted(){
     var self=this;
     self.reload();
     this.$refs.multipleTable.multipleSelection;
     self.getGiftList();
  },
  methods:{
     //分页数据加载
     loadData: function(criteria, pageNum, pagesize){ 
          console.log("load");
        var self=this;
        var params={page:pageNum,size:pagesize,search:""};
        
        // var startDate = '';
        // var endDate = '';
        // if(criteria != null && criteria.startDate != ''&& criteria.startDate != null){
        //     startDate = MomentPlugin(criteria.startDate).format("YYYY-MM-DD HH:mm:ss");
        // }
        // if(criteria != null && criteria.endDate != '' && criteria.endDate != null){
        //     endDate = MomentPlugin(criteria.endDate).format("YYYY-MM-DD HH:mm:ss");
        // }
        
        if(criteria!=null){
            params.search=JSON.stringify({userId:criteria.userId,giftId:criteria.giftId,isSent:criteria.isSent,userName:criteria.userName});    
        }
        getWhiteList(params).then(result => {
                var data=result.data;
                self.pagesize=data.pageSize;
                self.totalCount=data.total;
                self.tableData=data.list;
        });
     },
     getGiftList:function(){
        var self=this;
        getBackGiftList({}).then(result => {
            self.giftList = result.data;
        });
     },
     //添加白名单
     saveWhiteList:function(){
        var self=this;

        //表单验证
       self.$refs['whitelistFrom'].validate((valid) => {
            if(valid){
              var self=this;
              //请求登录
              var params={id:self.whitelistFrom.id,userId:self.whitelistFrom.userId,giftId:self.whitelistFrom.giftId,lotteryCount:self.whitelistFrom.lotteryCount};
                saveWhiteList(params).then(result => {
                    // console.log("cashCheck:",result);
                    if(result.code!="200"){
                        this.$message.error(result.message);
                        return;
                    }                 
                    self.dialogAuditFormVisible=false; 
                    self.restCheckForm();
                    self.reload();
                });
            }
       });
     },
     reload:function(){
         var self=this;
          self.loadData(null,self.currentPage,self.pagesize);
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
        self.checkIds=np;
     },
     close:function(){
         var self=this;
         self.dialogWhiteFormVisible=false;
         self.restCheckForm();
     },
     //重置表单
     restCheckForm:function(){
        var self=this;
        if (self.$refs['whitelistFrom']!==undefined) {
            self.$refs['whitelistFrom'].resetFields();
            self.whitelistFrom = {
                 id:'',
                 giftId:'',
                 userId:'',
                 lotteryCount:'',
             };
        }
        self.dialogWhiteFormVisible=false;
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
        console.log("e:",index, row);
        var self=this;
        var detail=self.whitelistFrom;
        detail.id=row.id;
        detail.giftId=row.giftId;
        detail.userId=row.userId;
        detail.lotteryCount=row.lotteryCount;
        self.dialogWhiteFormVisible=true;
     },
     //删除
     handleDelete: function(index, row) {
        console.log("d:",index, row);
        var self=this;

        this.$confirm('此操作将永久删除该文件, 是否继续?', '提示', {
          confirmButtonText: '确定',
          cancelButtonText: '取消',
          type: 'warning'
        }).then(() => {
          deleteWhiteList({id:row.id}).then(result => {
              console.log('del:',result);
             if(result.code!="200"){
                this.$message.error(result.message);
                return;
             }
             this.$message({type: 'success',message: '删除成功!'});
          });
          
        }).catch(() => {
        }); 
     },
     //分页数据加载
     loadDialogData: function(pageNum, pagesize){ 
          console.log("load");
        var self=this;
        var params={page:pageNum,size:pagesize,search:""};
        getUserLottoryList(params).then(result => {
                var data=result.data;
                self.lotteryPagesize=data.pageSize;
                self.lotteryTotalCount=data.total;
                self.userLottoryTableData=data.list;
        });
     },
     lotteryCountClick: function(){
        this.loadDialogData(1,10);
        this.dialogUserLottoryVisible=true;
     },
     lotteryClose:function(){
         var self=this;
         self.dialogUserLottoryVisible=false;
     },
     //分页大小变更
     lotteryHandleSizeChange: function(val) {
        this.lotteryPagesize = val;
        this.loadDialogData(this.lotteryCurrentPage, this.lotteryPagesize);
     },
     //分页变更
     lotteryHandleCurrentChange: function(val) {
        this.lotteryCurrentPage = val;
        this.loadDialogData(this.lotteryCurrentPage, this.lotteryPagesize);
     },
  }
}
</script>