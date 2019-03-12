<template>
<div class="conwarp">
<!-- grid -->
<div class="grid">
    <div class="toolbar">
          <el-button type="primary"  icon="el-icon-plus" @click="dialogProductFormVisible=true" >添加</el-button>
          <el-button type="danger"  icon="el-icon-plus" @click="cacheRest" >缓存重置</el-button>
    </div>
    <div class="searchbar">
        <el-row>
            <el-col :span="4"><el-input  placeholder="产品名称"   class="w18"  prefix-icon="el-icon-search" v-model="criteria.productName" ></el-input> </el-col>

            <el-col :span="4">  
                <el-select v-model="criteria.productStatus" placeholder="状态">
                    <el-option v-for="item in  productStatusKV" :key="item.value"  :label="item.label" :value="item.value"> </el-option>
                 </el-select>
            </el-col>
            <el-col :span="2.5"><el-button type="primary" icon="el-icon-search" @click="search">搜索</el-button></el-col>
        </el-row>
    </div>
    <div class="gmain">
            <el-table :data="tableData"  ref="multipleTable"  tooltip-effect="dark"  @select-all="handleSelectionChange"  @selection-change="handleSelectionChange"  stripe style="width: 100%">
            <el-table-column type="selection" width="55"></el-table-column>

            <el-table-column  prop="productFlag"   label="产品标识" ></el-table-column>
            <el-table-column  prop="productName"   label="产品名称" ></el-table-column>            
            <el-table-column  prop="productStatus"   label="状态" >
                <template scope="scope">
                    <span v-if="scope.row.productStatus == 0"> 可用</span>
                    <span v-if="scope.row.productStatus == 1"> 不可用</span>
                </template>
            </el-table-column>
            <el-table-column  prop="routePre"   label="路由前缀" ></el-table-column>
            <el-table-column  prop="routeName"   label="路由名称" ></el-table-column>
            <el-table-column  prop="signType"   label="校验方式" >
                <template scope="scope">
                    <span v-if="scope.row.signType == 0"> 秘钥校验</span>
                    <span v-if="scope.row.signType == 1"> 对称校验</span>
                    <span v-if="scope.row.signType == 2"> 非对称校验</span>
                    <span v-if="scope.row.signType == 3"> 对称校验+非对称校验</span>
                </template>
            </el-table-column>
            <el-table-column  prop="createTime"   label="创建时间"></el-table-column>
            
            <el-table-column label="操作"  width="220px">
                 <template slot-scope="scope">
                    <el-button size="small" type="primary" @click="handleEdit(scope.$index, scope.row)">编辑</el-button>
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
<el-dialog title="产品详情" :visible.sync="dialogProductFormVisible" :before-close="close" :width="dialogWidth" class="demo-ruleForm">
    <el-form :model="form" ref="form" :rules="rules" label-width="80px" :label-position="labelPosition">
        <el-form-item label="产品标识" prop="productFlag">
            <el-input v-model="form.productFlag"></el-input>
        </el-form-item>
        <el-form-item label="产品名称" prop="productName">
            <el-input v-model="form.productName"></el-input>
        </el-form-item>
        <el-form-item label="路由前缀" prop="routePre">
            <el-input v-model="form.routePre"></el-input>
        </el-form-item>
        <el-form-item label="路由名称" prop="routeName">
            <el-input v-model="form.routeName"></el-input>
        </el-form-item>
        <el-form-item label="校验方式" prop="signType" align="left">
            <el-col :span="18"> 
                <el-select v-model="form.signType" >
                        <el-option v-for="item in signTypeKV" :key="item.value"  :label="item.label" :value="item.value"> </el-option>
                </el-select>
            </el-col>
        </el-form-item>
        <el-form-item label="缴费类型" prop="feeType" align="left">
            <el-col :span="18"> 
                <el-select v-model="form.feeType" >
                        <el-option v-for="item in feeTypeKV" :key="item.value"  :label="item.label" :value="item.value"> </el-option>
                </el-select>
            </el-col>
        </el-form-item>
        <el-form-item label="状态" prop="productStatus" align="left">
            <el-col :span="18"> 
                <el-select v-model="form.productStatus" >
                        <el-option v-for="item in productStatusKV" :key="item.value"  :label="item.label" :value="item.value"> </el-option>
                </el-select>
            </el-col>
        </el-form-item>
        <el-form-item label="结算地址" prop="closeUrl">
            <el-input v-model="form.closeUrl"></el-input>
        </el-form-item>
    </el-form>
    <div slot="footer" class="dialog-footer">
        <el-button @click="close()">取 消</el-button>
        <el-button  @click="saveFrom()" type="primary"  >保 存</el-button>
    </div>
</el-dialog>

</div>
</template>

<script>
import {spaproductList,spaproductUpdate,spaproductDel,spaProductAdd,spaProductCache} from '@/api'
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
             signTypeKV:[
                {value: '0', label: '秘钥校验'},
                {value: '1', label: '对称校验'},
                {value: '2', label: '非对称校验'},
                {value: '3', label: '对称校验+非对称校验'},
             ],
             feeTypeKV:[
                {value: '0', label: '使用后结算费用'},
                {value: '1', label: '先充值后使用'},
             ],
             productStatusKV:[
                {value: '', label: '所有状态'},
                {value: '0', label: '可用'},
                {value: '1', label: '不可用'},
             ],
             rules:{

             },
             form:{
                 id:'',
                 productFlag:'',
                 productName:'',
                 routePre:'',
                 routeName:'',
                 signType:'0',
                 feeType:'0',
                 productStatus:'0',
                 closeUrl:'',
             },
             labelPosition:'right',
             multipleSelection: [],
             dialogWidth:'400px',
             formLabelWidth:'80px',
             dialogProductFormVisible:false,
             dialogKeyFormVisible:false,

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

        if(criteria!=null){
            params.search=JSON.stringify({merchantStatus:criteria.merchantStatus,merchantName:criteria.merchantName,merchantId:criteria.merchantId});    
        }
        spaproductList(params).then(result => {
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
     //重置表单
     restForm:function(){
        var self=this;
        if (self.$refs['form']!==undefined) {
            self.form = { 
                 id:'',
                 productFlag:'',
                 productName:'',
                 routePre:'',
                 routeName:'',
                 signType:'0',
                 feeType:'0',
                 productStatus:'0',
                 closeUrl:'',
             };
        }
        self.dialogProductFormVisible=false;
     },

      //编辑
     handleEdit: function(index, row) {
        console.log("e:",index, row);
        var self=this;
        var detail=self.form;
        detail.id=row.id;
        detail.productFlag=row.productFlag;
        detail.productName=row.productName;
        detail.routePre=row.routePre;
        detail.routeName=row.routeName;
        detail.closeUrl=row.closeUrl;
        detail.signType=row.signType+ '';
        detail.feeType=row.feeType + '';
        detail.productStatus=row.productStatus + '';
        self.dialogProductFormVisible=true;
     },
     saveFrom:function(){
        var self=this;
       //表单验证
          console.log("valid222:");
          self.$refs['form'].validate((valid) => {
            console.log("valid:",valid);
            if(!valid)return;
            if(self.form.id==""){
                //add
                spaProductAdd(self.form).then(result => {
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
                spaproductUpdate(self.form).then(result => {
                     if(result.code!="200"){
                        alert(result.message);
                        return;
                     }
                     self.reload();
                     self.restForm();

                });
            }
         });
     },
      //删除
     handleDelete: function(index, row) {
        console.log("d:",index, row);
        var self=this;
        this.$confirm('删除此产品, 是否继续?', '提示', {
          confirmButtonText: '确定',
          cancelButtonText: '取消',
          type: 'warning'
        }).then(() => {
          spaproductDel({id:row.id}).then(result => {
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
   cacheRest:function(){
       spaProductCache({s:"xionghuifeng"}).then(result => {
             this.$message({type: 'success',message: result.message});
       });
   }
  }
}
</script>