<template>
<div class="conwarp">
<!-- grid -->
<div class="grid">
    <div class="toolbar">
          <el-button type="primary"  icon="el-icon-plus" @click="dialogInterfaceFormVisible=true" >添加</el-button>
    </div>
    <div class="searchbar">
        <el-row>
            <el-col :span="4">  
                <el-select v-model="criteria.interfaceType">
                    <el-option v-for="item in interfaceTypeList" :key="item.value"  :label="item.label" :value="item.value"> </el-option>
                </el-select>
            </el-col>
            <el-col :span="4"><el-input  placeholder="请输入接口名称"   class="w18"  prefix-icon="el-icon-search" v-model="criteria.interfaceName" ></el-input> </el-col>
            <el-col :span="4"><el-input  placeholder="请输入url"   class="w18"  prefix-icon="el-icon-search" v-model="criteria.interfaceUrl" ></el-input> </el-col>
            <el-col :span="4">  
                <el-select v-model="criteria.source">
                    <el-option v-for="item in sourceList" :key="item.value"  :label="item.label" :value="item.value"> </el-option>
                </el-select>
            </el-col>
            <el-col :span="4">  
                <el-select v-model="criteria.useStatus" placeholder="状态">
                    <el-option v-for="item in useStatusList" :key="item.value"  :label="item.label" :value="item.value"> </el-option>
                 </el-select>
            </el-col>
            <el-col :span="4"><el-button type="primary" icon="el-icon-search" @click="search">搜索</el-button></el-col>
        </el-row>       
    </div>
    <div class="gmain">
        <el-table :data="tableData"  ref="multipleTable"  tooltip-effect="dark"  @select-all="handleSelectionChange"  @selection-change="handleSelectionChange"  stripe style="width: 100%">
            <el-table-column type="selection" width="55"></el-table-column>
            <el-table-column  prop="interfaceType"   label="接口类型">
                <template scope="scope">
                    <span v-if="scope.row.interfaceType==1">黑名单</span>
                    <span v-if="scope.row.interfaceType==2">ocr</span>
                    <span v-if="scope.row.interfaceType==3">人脸对比</span>
                    <span v-if="scope.row.interfaceType==4">活体</span>
                </template>
            </el-table-column>
            <el-table-column  prop="interfaceName"   label="接口名称" ></el-table-column>
            <el-table-column  prop="interfaceDesc"   label="描述" ></el-table-column>
            <el-table-column  prop="interfaceUrl"   label="URL"></el-table-column>
            <el-table-column  prop="interfaceVersion"   label="版本" ></el-table-column>
            <el-table-column  prop="source"   label="接口类来源">
                <template scope="scope">
                    <span v-if="scope.row.source==1">Advance.AI</span>
                    <span v-if="scope.row.source==2">百度</span>
                    <span v-if="scope.row.source==3">科大讯飞</span>
                </template>
            </el-table-column>
            <el-table-column  prop="useStatus"   label="状态" width="90">
                <template scope="scope">
                    <span v-if="scope.row.useStatus==0" style="color:green;" > 启用中 </span>
                    <span v-if="scope.row.useStatus==1" style="color:red;" >禁用</span>
                </template>
            </el-table-column>
            <el-table-column label="操作"  width="150" >
                 <template slot-scope="scope">
                    <el-button size="small" type="primary"  @click="handleEdit(scope.$index, scope.row)">编辑</el-button>
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

<el-dialog title="编辑接口" :visible.sync="dialogInterfaceFormVisible" :before-close="close" :width="dialogWidth" class="demo-ruleForm">
    <el-form :model="interfaceFrom" ref="interfaceFrom" :rules="rules" label-width="80px" >
        <el-form-item label="接口来源" prop="source" align="left">
            <el-select v-model="interfaceFrom.source">
                <el-option v-for="item in sourceList" :key="item.value"  :label="item.label" :value="item.value"> </el-option>
            </el-select>
        </el-form-item>
        <el-form-item label="接口类型" prop="interfaceType" align="left">
            <el-select v-model="interfaceFrom.interfaceType">
                <el-option v-for="item in interfaceTypeList" :key="item.value"  :label="item.label" :value="item.value"> </el-option>
            </el-select>
        </el-form-item>
        <el-form-item label="接口名称" prop="interfaceName">
            <el-input v-model="interfaceFrom.interfaceName"></el-input>
        </el-form-item>
        <el-form-item label="描述" prop="interfaceDesc">
            <el-input v-model="interfaceFrom.interfaceDesc"></el-input>
        </el-form-item>
        <el-form-item label="URL" prop="interfaceUrl">
            <el-input v-model="interfaceFrom.interfaceUrl"></el-input>
        </el-form-item>
        <el-form-item label="版本号" prop="interfaceVersion">
            <el-input v-model="interfaceFrom.interfaceVersion"></el-input>
        </el-form-item>
        <el-form-item label="全局提示" prop="noteGlobal">
            <el-input v-model="interfaceFrom.noteGlobal"></el-input>
        </el-form-item>
        <el-form-item label="请求提示" prop="noteRequest">
            <el-input v-model="interfaceFrom.noteRequest"></el-input>
        </el-form-item>
        <el-form-item label="返回提示" prop="noteRespond">
            <el-input v-model="interfaceFrom.noteRespond"></el-input>
        </el-form-item>
        <el-form-item label="请求示例" prop="requestExample">
            <el-input v-model="interfaceFrom.requestExample"></el-input>
        </el-form-item>
        <el-form-item label="返回示例" prop="respondExample">
            <el-input v-model="interfaceFrom.respondExample"></el-input>
        </el-form-item>
        <el-form-item label="使用状态" prop="useStatus" align="left">
            <el-select v-model="interfaceFrom.useStatus">
                <el-option v-for="item in useStatusList" :key="item.value"  :label="item.label" :value="item.value"> </el-option>
            </el-select>
        </el-form-item>
    </el-form>
    <div slot="footer" class="dialog-footer">
        <el-button @click="close()">取 消</el-button>
        <el-button type="primary" @click="addOrUpdateInterface()">保 存</el-button>
    </div>
</el-dialog>

</div>
</template>

<script>
import {opInterfaceList,opInterfaceSave,opInterfaceDelete} from '@/api'
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
                 interfaceType: '',
                 interfaceName: '',
                 interfaceUrl: '',
                 source: '',
                 useStatus: '',
             },
             interfaceFrom: {
                 id:'',
                 source:'',
                 interfaceType:'',
                 interfaceName:'',
                 interfaceDesc:'',
                 interfaceUrl:'',
                 interfaceVersion:'',
                 noteGlobal:'',
                 noteRequest:'',
                 noteRespond:'',
                 requestExample:'',
                 respondExample:'',
                 useStatus:'',
             },
             interfaceTypeList:[
                {value: '', label: '请选择'},
                {value: '1', label: '黑名单'},
                {value: '2', label: 'ocr'},
                {value: '3', label: '人脸对比'},
                {value: '4', label: '活体'},
             ],
             sourceList:[
                {value: '', label: '请选择'},
                {value: '1', label: 'Advance.AI'},
                {value: '2', label: '百度'},
                {value: '3', label: '科大讯飞'},
             ],
             useStatusList:[
                {value: '', label: '请选择'},
                {value: '0', label: '启用中'},
                {value: '1', label: '禁用'},
             ],
             multipleSelection: [],
             dialogWidth:'600px',
             formLabelWidth:'80px',
             dialogInterfaceFormVisible:false,
             rules:{
                source: [{ required: true, message: '请选择接口来源', trigger: 'blur' }],
                interfaceType: [{ required: true, message: '请选择接口类型', trigger: 'blur'}],
                interfaceName: [{ required: true, message: '请填写接口名称', trigger: 'blur'}],
                useStatus: [{ required: true, message: '请选择使用状态', trigger: 'blur'}],
             }
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
            params.search=JSON.stringify({
                 interfaceType:self.criteria.interfaceType,
                 interfaceName:self.criteria.interfaceName,
                 interfaceUrl:self.criteria.interfaceUrl,
                 source:self.criteria.source,
                 useStatus:self.criteria.useStatus,
            });    
        }
        else{
            params.search=JSON.stringify({});
        }
        opInterfaceList(params).then(result => {
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
         console.log(checkData);
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
          this.loadData(self.criteria, this.currentPage, this.pagesize);
      },
      close:function(){
         var self=this;
         self.restForm();
      },
      //重置表单
      restForm:function(){
        var self=this;
        if (self.$refs['interfaceFrom']!==undefined) {
            self.$refs['interfaceFrom'].resetFields();
            this.parentMenuList = [];
            self.interfaceFrom = {
                id:'',
                 source:'',
                 interfaceType:'',
                 interfaceName:'',
                 interfaceDesc:'',
                 interfaceUrl:'',
                 interfaceVersion:'',
                 noteGlobal:'',
                 noteRequest:'',
                 noteRespond:'',
                 requestExample:'',
                 respondExample:'',
                 useStatus:'',
            };
        }
        self.dialogInterfaceFormVisible=false;
      },
      //编辑菜单
     addOrUpdateInterface:function(){
        var self=this;
       //表单验证
       self.$refs['interfaceFrom'].validate((valid) => {
           if(valid){
                var params={
                    id:self.interfaceFrom.id,
                    source:self.interfaceFrom.source,
                    interfaceType:self.interfaceFrom.interfaceType,
                    interfaceName:self.interfaceFrom.interfaceName,
                    interfaceDesc:self.interfaceFrom.interfaceDesc,
                    interfaceUrl:self.interfaceFrom.interfaceUrl,
                    interfaceVersion:self.interfaceFrom.interfaceVersion,
                    noteGlobal:self.interfaceFrom.noteGlobal,
                    noteRequest:self.interfaceFrom.noteRequest,
                    noteRespond:self.interfaceFrom.noteRespond,
                    requestExample:self.interfaceFrom.requestExample,
                    respondExample:self.interfaceFrom.respondExample,
                    useStatus:self.interfaceFrom.useStatus,
                    };
                opInterfaceSave(params).then(result => {
                    if(result.code != 200){
                        this.$message.error(result.message);
                        return;
                    }                 
                    self.dialogInterfaceFormVisible=false; 
                    self.restForm();
                    self.reload();
                });
           }
        });
     },
     //编辑
     handleEdit: function(index, row) {
        var self=this;
        self.interfaceFrom.id = row.id,
        self.interfaceFrom.source = row.source + '',
        self.interfaceFrom.interfaceType = row.interfaceType + '',
        self.interfaceFrom.interfaceName = row.interfaceName,
        self.interfaceFrom.interfaceDesc = row.interfaceDesc,
        self.interfaceFrom.interfaceUrl = row.interfaceUrl,
        self.interfaceFrom.interfaceVersion = row.interfaceVersion,
        self.interfaceFrom.noteGlobal = row.noteGlobal,
        self.interfaceFrom.noteRequest = row.noteRequest,
        self.interfaceFrom.noteRespond = row.noteRespond,
        self.interfaceFrom.requestExample = row.requestExample,
        self.interfaceFrom.respondExample = row.respondExample,
        self.interfaceFrom.useStatus = row.useStatus + '',

        self.dialogInterfaceFormVisible=true;
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
          opInterfaceDelete({id:row.id}).then(result => {
              console.log('del:',result);
             if(result.code != 200){
                this.$message.error(result.msg);
                return;
             }
             self.reload();
             this.$message({type: 'success',message: '删除成功!'});
          });
          
        }).catch(() => {
        }); 
     },
  }
}
</script>