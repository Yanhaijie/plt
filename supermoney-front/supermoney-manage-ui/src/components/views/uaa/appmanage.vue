<template>
<div class="conwarp">
<!-- grid -->
<div class="grid">
    <div class="toolbar">
        <el-button type="primary"  icon="el-icon-plus" @click="dialogFormVisible=true" >添加</el-button>
        <!--<el-button type="danger"  icon="el-icon-close">刪除</el-button>-->
    </div>
    <div class="searchbar">
        <el-row>
            <el-col :span="4"><el-input  placeholder="请输入名称"   class="w18"  prefix-icon="el-icon-search" v-model="criteria.appName" ></el-input> </el-col>
            <el-col :span="4">
                <el-date-picker  v-model="criteria.startDate" type="date"  placeholder="开始日期"> </el-date-picker>
            </el-col>
            <el-col :span="4">
                <el-date-picker  v-model="criteria.endDate" type="date"  placeholder="结束日期"> </el-date-picker>
            </el-col>
            <el-col :span="4">
                 <el-select v-model="criteria.appType" placeholder="应用类型">
                    <el-option v-for="item in criteria.appTypes" :key="item.value"  :label="item.label" :value="item.value"> </el-option>
                 </el-select>
            </el-col>
            <el-col :span="4">  
                <el-select v-model="criteria.appStatus" placeholder="应用状态">
                    <el-option v-for="item in criteria.status" :key="item.value"  :label="item.label" :value="item.value"> </el-option>
                 </el-select>
            </el-col>
            <el-col :span="4"><el-button type="primary" icon="el-icon-search" @click="search">搜索</el-button></el-col>
        </el-row>
        
        

    </div>
    <div class="gmain">
            <el-table :data="tableData"  ref="multipleTable"  tooltip-effect="dark"  @selection-change="handleSelectionChange" stripe style="width: 100%">
            <el-table-column type="selection" width="55"></el-table-column>
            <el-table-column  prop="appName"   label="应用名称" ></el-table-column>
            <el-table-column  prop="appRoute"   label="应用路由" ></el-table-column>
            <el-table-column  prop="appType"   label="应用类型"  >
                 <template slot-scope="scope">
                    <span v-if="scope.row.appType==0" >面向外部</span>
                    <span v-if="scope.row.appType==1" >面向内部</span>
                </template>
            </el-table-column>
            <el-table-column  prop="appStatus"   label="应用状态" >
                <template slot-scope="scope">
                    <span v-if="scope.row.appStatus==0" style="color:red;" >未启用</span>
                    <span v-if="scope.row.appStatus==1" style="color:green;" >启用</span>
                </template>
            </el-table-column>
            <el-table-column label="操作"  > 
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
<!-- form -->
<el-dialog title="应用信息" :visible.sync="dialogFormVisible" @close="close" :width="dialogWidth">
    <el-form :model="form" :rules="rules" ref="form">
        <el-form-item label="应用编号" :label-width="formLabelWidth" prop="appCode">
            <input type="hidden" v-model="form.id"/>
            <el-input v-model="form.appCode" auto-complete="off"></el-input>
        </el-form-item>
        <el-form-item label="应用名称" :label-width="formLabelWidth" prop="appName">
            <el-input v-model="form.appName" auto-complete="off" size="small"></el-input>
        </el-form-item>
        <el-form-item label="应用路由" :label-width="formLabelWidth" prop="appRoute">
            <el-input v-model="form.appRoute" auto-complete="off"></el-input>
        </el-form-item>
        <el-form-item label="应用类型" :label-width="formLabelWidth">
            <el-select v-model="form.appType" placeholder="请选择应用类型">
                <el-option label="面向外部" value="0"></el-option>
                <el-option label="面向内部" value="1"></el-option>
            </el-select>
        </el-form-item>
        <el-form-item label="应用状态" :label-width="formLabelWidth">
            <el-select v-model="form.appStatus" placeholder="请选择应用状态">
                <el-option label="未启用" value="0"></el-option>
                <el-option label="已启用" value="1"></el-option>
            </el-select>
        </el-form-item>
    </el-form>
    <div slot="footer" class="dialog-footer">
        <el-button @click="dialogFormVisible=false">取 消</el-button>
        <el-button type="primary" @click="formSave('form')">确 定</el-button>
    </div>

</el-dialog>
</div>
</template>

<script>
import {getAppList,addApp,updateApp,delApp} from '@/api'

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
                 appName:'',
                 startDate:'',
                 endDate:'',
                 appType:'',
                 appTypes:[
                     {value: '', label: '所有类型'},
                     {value: '0', label: '面向外部'},
                     {value: '1', label: '面向内部'}
                 ],
                 appStatus:'',
                 status:[
                     {value: '', label: '所有状态'},
                     {value: '0', label: '未启用'},
                     {value: '1', label: '已启用'}
                 ]
             },
             multipleSelection: [],
             form:{
                 id:'',
                 appCode:'',
                 appName:'',
                 appRoute:'',
                 appType:'0',
                 appStatus:'0'
             },
             rules:{
                appCode:[
                     { required: true, message: '请输入编号', trigger: 'blur' },
                     { min: 11, max:25, message: '长度在 11 到 25 个字符', trigger: 'blur' }
                    ],
                appName:[ 
                     { required: true, message: '请输入应用名称', trigger: 'blur' },
                     {  max:30, message: '长度限制为30 个字符', trigger: 'blur' }
                    ],
                appRoute:[ 
                    { required: true, message: '请输入路由', trigger: 'blur' },
                     {  max:100, message: '长度限制为100个字符', trigger: 'blur' }
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
  },
  methods:{
     //分页数据加载
     loadData: function(criteria, pageNum, pagesize){ 
          console.log("load");
        var self=this;
        var params={page:pageNum,size:pagesize,search:""};
        if(criteria!=null){
            params.search=JSON.stringify({appName:criteria.appName,appType:criteria.appType,appStatus:criteria.appStatus,startDate:criteria.startDate,endDate:criteria.endDate});    
        }
        getAppList(params).then(result => {
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
        self.form={ id:'',appCode:'',appName:'',appRoute:'',appType:'0',appStatus:'0'};
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
        var detail=self.form;
        detail.id=row.id;
        detail.appCode=row.appCode;
        detail.appName=row.appName;
        detail.appRoute=row.appRoute;
        detail.appType=row.appType.toString();
        detail.appStatus=row.appStatus.toString();
        self.dialogFormVisible=true;
     },
     //删除
     handleDelete: function(index, row) {
          console.log("d:",index, row);
          var self=this;
          if(!confirm("你确定要删除吗？"))return;
          delApp({id:row.id}).then(result => {  
              console.log('del:',result);
             if(result.code!="200"){
                alert(result.message);
                return;
             }        
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
                addApp(self.form).then(result => {
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
                updateApp(self.form).then(result => {      
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