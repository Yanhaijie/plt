<template>
<div class="conwarp">
<!-- grid -->
<div class="grid">
    <div class="toolbar">
          <el-button type="primary"  icon="el-icon-plus" @click="dialogUserFormVisible=true" >添加</el-button>
    </div>
    <div class="searchbar">
        <el-row>
            <el-col :span="4"><el-input  placeholder="用户名称"   class="w18"  prefix-icon="el-icon-search" v-model="criteria.merchantName" ></el-input> </el-col>
            <el-col :span="5">        
                <el-select v-model="criteria.productId" >
                    <el-option v-for="item in productKV" :key="item.value"  :label="item.label" :value="item.value"> </el-option>
                </el-select>
            </el-col>
            <el-col :span="2.5"><el-button type="primary" icon="el-icon-search" @click="search">搜索</el-button></el-col>
        </el-row>
    </div>
    <div class="gmain">
            <el-table :data="tableData"  ref="multipleTable"  tooltip-effect="dark"  @select-all="handleSelectionChange"  @selection-change="handleSelectionChange"  stripe style="width: 100%">
            <el-table-column type="selection" width="55"></el-table-column>
            <el-table-column  prop="userName"   label="用户名称" ></el-table-column>
            <el-table-column  prop="productName"   label="产品名称" ></el-table-column>
            <el-table-column   label="产品状态" >
                <template scope="scope">
                    <span v-if="scope.row.productUseStatus == 0"> 可用</span>
                    <span v-if="scope.row.productUseStatus == 1"> 不可用</span>
                </template>
            </el-table-column>
            <el-table-column  prop="accountMoney"   label="产品余额" ></el-table-column>
            <el-table-column  prop="closeMoney"   label="结算金额" ></el-table-column>
            <el-table-column   label="结算方式" >
                <template scope="scope">
                    <span v-if="scope.row.closeMethod == 0"> 月固定结算</span>
                    <span v-if="scope.row.closeMethod == 1"> 间隔结算</span>
                    <span v-if="scope.row.closeMethod == 2"> 手动结算</span>
                </template>
            </el-table-column>
            <el-table-column  prop="lastCloseTime" :formatter="dateFormat"   label="上次结算"></el-table-column>      
            <el-table-column label="操作"  width="300px">
                 <template slot-scope="scope">
                    <el-button size="small" type="primary" @click="openProductForm(scope.$index, scope.row)">编辑</el-button>
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


<el-dialog title="产品详情" :visible.sync="dialogProductFormVisible" :before-close="productClose" :width="productDialogWidth" class="demo-ruleForm">
    <el-form :model="productForm" ref="productform" :rules="rules" label-width="80px">
        <el-form-item label="产品名称" >
            <el-select v-model="productForm.productId" :disabled="true" >
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
              <el-input type="number"  v-model="productForm.closeVal" auto-complete="off"  ></el-input>
        </el-form-item>
        <el-form-item label="账户余额"   >
             <el-input type="number" v-model="productForm.accountMoney" auto-complete="off"  ></el-input>
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
import {spaUserProductList,spaUserProductSave,spaUserProductDel,spaproductDrop} from '@/api'
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
                id:'',
                productId:'',
                userId:'',
                closeMethod:'0',
                closeVal:'',
                callBackUrl:'',
                accountMoney:'',
             },
             rules:{
                merchantName: [{ required: true, message: '请输入商户名称', trigger: 'blur'}],
                merchantType: [{ required: true, message: '请选择商户类型', trigger: 'blur'}],
                merchantStatus: [{ required: true, message: '请选择商户状态', trigger: 'blur'}],
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
        spaUserProductList(params).then(result => {
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
     getProductKV:function(){
        var self=this;
        spaproductDrop({}).then(result => {
            self.productKV=result.data;
            self.productKV.splice(0,0,{value:'',label:'选择产品'});
        });
     },
      //删除
     handleDelete: function(index, row) {
        console.log("d:",index, row);
        var self=this;

        this.$confirm('确定要删除?', '提示', {
          confirmButtonText: '确定',
          cancelButtonText: '取消',
          type: 'warning'
        }).then(() => {
          spaUserProductDel({id:row.id}).then(result => {
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
        self.productForm.id=row.id;
        self.productForm.userId=row.userId;
        self.productForm.productId=row.productId;
        self.productForm.closeMethod=row.closeMethod+'';
        self.productForm.closeVal=row.closeVal;
        self.productForm.callBackUrl=row.callBackUrl;
        self.productForm.accountMoney=row.accountMoney;
        self.dialogProductFormVisible=true;
     },
     saveProduct:function(){
         var self=this;
         spaUserProductSave({
             id:self.productForm.id,
             userId:self.productForm.userId,
             productId:self.productForm.productId,
             closeMethod:self.productForm.closeMethod,
             closeVal:self.productForm.closeVal,
             callBackUrl:self.productForm.callBackUrl,
             accountMoney:self.productForm.accountMoney,
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
        self.productForm.id='';
        self.productForm.userId='';
        self.productForm.productId='';
        self.productForm.closeMethod='';
        self.productForm.closeVal='';
        self.productForm.callBackUrl='';
        self.productForm.accountMoney='';
        
        self.reload();
    }
  }
}
</script>