<template>
<div class="conwarp">
<!-- grid -->
<div class="grid">
    <div class="toolbar">
          <el-button type="primary"  icon="el-icon-plus" @click="dialogUserFormVisible=true" >添加</el-button>
    </div>
    <div class="searchbar">
        <el-row>
   
            <el-col :span="4"><el-input  placeholder="请输入商户名称"   class="w18"  prefix-icon="el-icon-search" v-model="criteria.merchantName" ></el-input> </el-col>

            <el-col :span="2.5"><el-button type="primary" icon="el-icon-search" @click="search">搜索</el-button></el-col>
        </el-row>
    </div>
    <div class="gmain">
            <el-table :data="tableData"  ref="multipleTable"  tooltip-effect="dark"  @select-all="handleSelectionChange"  @selection-change="handleSelectionChange"  stripe style="width: 100%">
            <el-table-column type="selection" width="55"></el-table-column>

            <el-table-column  prop="merchantId"   label="商户ID" ></el-table-column>
            <el-table-column  prop="merchantName"   label="商户名称" ></el-table-column>
            <el-table-column  prop="merchantType"   label="商户类型" >
                <template scope="scope">
                    <span v-if="scope.row.merchantType == 0"> 上游商户</span>
                    <span v-if="scope.row.merchantType == 1"> 下游商户</span>
                </template>
            </el-table-column>
            <el-table-column  prop="emial"   label="邮件" ></el-table-column>
            <el-table-column  prop="mobile"   label="电话" ></el-table-column>
            <el-table-column  prop="userStatus"   label="状态" >
                <template scope="scope">
                    <span v-if="scope.row.userStatus == 0" style="color:green;"> 可用</span>
                    <span v-if="scope.row.userStatus == 1" style="color:red;"> 不可用</span>
                </template>
            </el-table-column>
            <el-table-column  prop="createTime" :formatter="dateFormat"   label="创建时间"></el-table-column>
            
            <el-table-column label="操作"  width="300px">
                 <template slot-scope="scope">
                    <el-button size="small" type="primary" @click="handleEdit(scope.$index, scope.row)">编辑</el-button>
                    <el-button size="small" type="success" @click="handleKeyDetail(scope.$index, scope.row)">密钥</el-button>
                    <el-button size="small" type="primary" @click="openProductForm(scope.$index, scope.row)">产品</el-button>
                    <el-button size="small" type="danger"   @click="handleDelete(scope.$index, scope.row)">删除</el-button>
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
<el-dialog title="商户详情" :visible.sync="dialogUserFormVisible" :before-close="close" :width="dialogWidth" class="demo-ruleForm">
    <el-form :model="merchantFrom" ref="merchantFrom" :rules="rules" label-width="80px" :label-position="labelPosition">
        <el-form-item label="商户名称" prop="merchantName">
            <el-input v-model="merchantFrom.merchantName"></el-input>
        </el-form-item>
        <el-form-item label="商户类型" prop="merchantType" align="left">
            <el-col :span="12"> 
                <el-select v-model="merchantFrom.merchantType" >
                        <el-option v-for="item in merchantTypeKV" :key="item.value"  :label="item.label" :value="item.value"> </el-option>
                </el-select>
            </el-col>
        </el-form-item>
          <el-form-item label="账号名称" prop="userName">
            <el-input v-model="merchantFrom.userName"></el-input>
        </el-form-item>
           <el-form-item label="账号密码" prop="userPassword">
            <el-input v-model="merchantFrom.userPassword"></el-input>
        </el-form-item>     
        <el-form-item label="公司地址" prop="address">
            <el-input v-model="merchantFrom.address"></el-input>
        </el-form-item>
        <el-form-item label="邮    件" prop="emial">
            <el-input v-model="merchantFrom.emial"></el-input>
        </el-form-item>
        <el-form-item label="电话" prop="mobile">
            <el-input v-model="merchantFrom.mobile"></el-input>
        </el-form-item>
        <el-form-item label="状态" prop="userStatus" align="left">
            <el-col :span="12"> 
                <el-select v-model="merchantFrom.userStatus" >
                    <el-option v-for="item in userStatusKV" :key="item.value"  :label="item.label" :value="item.value"> </el-option>
                </el-select> 
            </el-col>
        </el-form-item>
    </el-form>
    <div slot="footer" class="dialog-footer">
        <el-button @click="close()">取 消</el-button>
        <el-button type="primary" @click="saveMerchantFrom()">保 存</el-button>
    </div>
</el-dialog>


<el-dialog title="密钥" :visible.sync="dialogKeyFormVisible" :before-close="keyClose" :width="keyDialogWidth" class="demo-ruleForm">
    <el-form :model="keyFrom" ref="keyFrom" :rules="rules" label-width="120px">
        <el-form-item label="公钥（正式）" prop="publicKey">
            <el-input v-model="keyFrom.publicKey"></el-input>
        </el-form-item>
        <el-form-item label="私钥（正式）" prop="privateKey">
            <el-input v-model="keyFrom.privateKey"></el-input>
        </el-form-item>
        <el-form-item label="秘钥（正式）" prop="secretKey">
            <el-input v-model="keyFrom.secretKey"></el-input>
        </el-form-item>
        <el-form-item label="TOKEN（正式）" prop="validationoToken">
            <el-input v-model="keyFrom.validationoToken"></el-input>
        </el-form-item>
        <el-form-item label="公钥（test）" prop="testPublicKey">
            <el-input v-model="keyFrom.testPublicKey"></el-input>
        </el-form-item>
        <el-form-item label="私钥（test）" prop="testPrivateKey">
            <el-input v-model="keyFrom.testPrivateKey"></el-input>
        </el-form-item>
        <el-form-item label="秘钥（test）" prop="testSecretKey">
            <el-input v-model="keyFrom.testSecretKey"></el-input>
        </el-form-item>
        <el-form-item label="TOKEN（test）" prop="testValidationoToken">
            <el-input v-model="keyFrom.testValidationoToken"></el-input>
        </el-form-item>
    </el-form>
</el-dialog>

<el-dialog title="添加产品" :visible.sync="dialogProductFormVisible" :before-close="productClose" :width="productDialogWidth" class="demo-ruleForm">
    <el-form :model="keyFrom" ref="productform" :rules="rules" label-width="80px">
        <el-form-item label="产品名称" >
            <el-select v-model="productForm.productId" >
                        <el-option v-for="item in productKV" :key="item.value"  :label="item.label" :value="item.value"> </el-option>
                </el-select>
        </el-form-item>
        <el-form-item label="结算方式"  class="fl">
            <el-select v-model="productForm.closeMethod" placeholder="选择方式"  >
                <el-option label="月固定时间" value="0"></el-option>
                <el-option label="间隔时间" value="1"></el-option>   
                <el-option label="手动结算" value="2"></el-option>        
            </el-select>
        </el-form-item>
        <el-form-item label="结算值" >
            <el-input v-model="productForm.closeVal"></el-input>
        </el-form-item>
        <el-form-item label="回调地址" >
            <el-input v-model="productForm.callBackUrl"></el-input>
        </el-form-item>
    </el-form>
    <div slot="footer" class="dialog-footer">
        <el-button @click="productClose()">取 消</el-button>
        <el-button type="primary" @click="saveProduct()">添加</el-button>
    </div>
</el-dialog>

</div>
</template>

<script>
import {spaUserList,spaUserDel,spaUserSave,spaproductDrop,spaSetUserProduct} from '@/api'
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
                 merchantStatus:'',
                 merchantName:'',
                 merchantId:'',
             },
             merchantTypeKV:[
                {value: '0', label: '上游商户'},
                {value: '1', label: '下游商户'},
             ],
             userStatusKV:[
                {value: '0', label: '可用'},
                {value: '1', label: '不可用'},
             ],
             productKV:[],
             productForm:{
                productId:'',
                userId:'',
                closeMethod:'0',
                closeVal:'',
                callBackUrl:'',
             },
             rules:{
                merchantName: [{ required: true, message: '请输入商户名称', trigger: 'blur'}],
                merchantType: [{ required: true, message: '请选择商户类型', trigger: 'blur'}],
                merchantStatus: [{ required: true, message: '请选择商户状态', trigger: 'blur'}],
             },
             merchantFrom:{
                 id:'',
                 merchantName:'',
                 merchantType:'0',
                 address:'',
                 emial:'',
                 mobile:'',
                 userStatus:'0',
                 userPassword:'',
                 userName:'',
             },
             keyFrom:{
                publicKey:'',
                privateKey:'',
                secretKey:'',
                validationoToken:'',
                testPublicKey:'',
                testPrivateKey:'',
                testSecretKey:'',
                testValidationoToken:'',
             },
             labelPosition:'right',
             multipleSelection: [],
             dialogWidth:'400px',
             keyDialogWidth:'600px',
             formLabelWidth:'80px',
              productDialogWidth:"300px",
             dialogUserFormVisible:false,
             dialogKeyFormVisible:false,
             dialogProductFormVisible:false,
            

          }
  },
  mounted(){
     var self=this;
     self.reload();
     self.getProductKV();
     this.$refs.multipleTable.multipleSelection;
  },
  methods:{
     //分页数据加载
     loadData: function(criteria, pageNum, pagesize){ 
          console.log("load");
        var self=this;
        var params={page:pageNum,size:pagesize,search:""};

        if(criteria!=null){
            params.search=JSON.stringify({merchantStatus:criteria.merchantStatus,merchantName:criteria.merchantName,merchantId:criteria.merchantId});    
        }
        spaUserList(params).then(result => {
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
      close:function(){
         var self=this;
         self.restForm();
     },
     getProductKV:function(){
        var self=this;
        spaproductDrop({}).then(result => {
            self.productKV=result.data;
            self.productKV.splice(0,0,{value:'',label:'选择产品'});
        });
     },
     keyClose:function(){
         var self=this;
         var self=this;
        if (self.$refs['keyFrom']!==undefined) {
            self.$refs['keyFrom'].resetFields();
            self.keyFrom = {
                publicKey:'',
                privateKey:'',
                secretKey:'',
                validationoToken:'',
                testPublicKey:'',
                testPrivateKey:'',
                testSecretKey:'',
                testValidationoToken:'',
             };
        }
        self.dialogKeyFormVisible=false;
     },

     //重置表单
     restForm:function(){
        var self=this;
        if (self.$refs['merchantFrom']!==undefined) {
            self.$refs['merchantFrom'].resetFields();
            self.merchantFrom = {
                 id:'',
                 merchantName:'',
                 merchantType:'0',
                 address:'',
                 emial:'',
                 mobile:'',
                 userStatus:'0',
                 userPassword:'',
                 userName:'',
             };
        }
        self.dialogUserFormVisible=false;
     },
      //编辑
     handleEdit: function(index, row) {
        console.log("e:",index, row);
        var self=this;
        var detail=self.merchantFrom;
        detail.id=row.id;
        detail.merchantName=row.merchantName;
        detail.merchantType=row.merchantType + '';
        detail.address=row.address;
        detail.emial=row.emial;
        detail.mobile=row.mobile;
        detail.userStatus=row.userStatus + '';
        detail.userPassword=row.userPassword;
        detail.userName=row.userName;
        self.dialogUserFormVisible=true;
     },
     saveMerchantFrom:function(){
        var self=this;
        console.log("test111111111");
       //表单验证
       self.$refs['merchantFrom'].validate((valid) => {
           if(valid){
               console.log("self.merchantFrom",self.merchantFrom);
                spaUserSave(self.merchantFrom).then(result => {
                     console.log("cashCheck:",result);
                    if(result.code!="200"){
                        this.$message.error(result.message);
                        return;
                    }                 
                    self.restForm();
                    self.reload();
                });
           }
        });
     },
     //编辑
     handleKeyDetail: function(index, row) {
        console.log("e:",index, row);
        var self=this;
        var detail=self.keyFrom;
        detail.id=row.id;
        detail.publicKey=row.publicKey;
        detail.privateKey=row.privateKey;
        detail.secretKey=row.secretKey;
        detail.validationoToken=row.validationoToken;
        detail.testPublicKey=row.testPublicKey;
        detail.testPrivateKey=row.testPrivateKey;
        detail.testSecretKey=row.testSecretKey;
        detail.testValidationoToken=row.testValidationoToken;
        self.dialogKeyFormVisible=true;
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
          spaUserDel({id:row.id}).then(result => {
              console.log('del:',result);
             if(result.code!="200"){
                this.$message.error(result.message);
                return;
             }
             self.reload();
             this.$message({type: 'success',message: '删除成功!'});
          });
          
        }).catch(() => {
        }); 
     },
     openProductForm:function(index, row){
         var self=this;
         self.productForm.userId=row.id;
         self.dialogProductFormVisible=true;
     },
     saveProduct:function(){
           var self=this;

         spaSetUserProduct({
             userId:self.productForm.userId,
             productId:self.productForm.productId,
             closeMethod:self.productForm.closeMethod,
             closeVal:self.productForm.closeVal,
             callBackUrl:self.productForm.callBackUrl,
             }).then(result => {
            console.log("setProduct:",result);
            if(result.code!="200"){
                self.$message.error(result.message);
            }else{
                self.$message.success({ message: result.message,   center: true});
                self.productClose();
            }              
        });


     },
     productClose:function(){
        var self=this;
        self.dialogProductFormVisible=false;
        self.productForm.userId='';
        self.productForm.productId='';
        self.productForm.closeMethod='';
        self.productForm.closeVal='';
        self.productForm.callBackUrl='';
        }
  }
}
</script>