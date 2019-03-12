<template>
<div class="conwarp">
<!-- grid -->
<div class="grid">
    <div class="toolbar">
        <el-button type="primary"  icon="el-icon-plus" @click="dialogFormVisible=true" >添加</el-button>
    </div>
    <div class="searchbar">
        <el-row>
            <el-col :span="4"><el-input  placeholder="请输入名称"   class="w18"  prefix-icon="el-icon-search" v-model="criteria.roleName" ></el-input> </el-col>          
            <el-col :span="4">  
                <el-select v-model="criteria.roleStatus" placeholder="应用状态">
                    <el-option v-for="item in criteria.status" :key="item.value"  :label="item.label" :value="item.value"> </el-option>
                 </el-select>
            </el-col>
            <el-col :span="4"><el-button type="primary" icon="el-icon-search" @click="search">搜索</el-button></el-col>
            <el-col :span="12"></el-col>
        </el-row>               
    </div>
    <div class="gmain">
            <el-table :data="tableData"  ref="multipleTable"  tooltip-effect="dark"  @selection-change="handleSelectionChange" stripe style="width: 100%">
            <el-table-column type="selection" width="55"></el-table-column>
            <el-table-column  prop="roleName"   label="角色名称" ></el-table-column>
            <el-table-column  prop="roleStatus"   label="角色状态" >
                <template slot-scope="scope">
                    <span v-if="scope.row.roleStatus==0" style="color:green;" >启用</span>
                    <span v-if="scope.row.roleStatus==1" style="color:red;" >关闭</span>                
                </template>
            </el-table-column>
            <el-table-column  prop="roleFlag"   label="角色标签" ></el-table-column>
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
<el-dialog title="角色信息" :visible.sync="dialogFormVisible" @close="close" :width="dialogWidth">
    <el-form :model="form" :rules="rules" ref="form">
        <el-form-item label="角色名称" :label-width="formLabelWidth" prop="roleName">
             <input type="hidden" v-model="form.id"/>
            <el-input v-model="form.roleName" auto-complete="off" size="small"></el-input>
        </el-form-item>
        <el-form-item label="角色标签" :label-width="formLabelWidth" prop="roleFlag">
            <el-input v-model="form.roleFlag" auto-complete="off"></el-input>
        </el-form-item>
        <el-form-item label="应用状态" :label-width="formLabelWidth">
            <el-select v-model="form.roleStatus" placeholder="请选择应用状态">
                <el-option label="启用" value="0"></el-option>
                <el-option label="关闭" value="1"></el-option>
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
import {getRoleList,addRole,updateRole,delRole} from '@/api'

export default {
  name: 'rolemanage',
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
                 roleStatus:'',
                 status:[
                     {value: '', label: '所有状态'},
                     {value: '0', label: '启用'},
                     {value: '1', label: '关闭'}
                 ]
             },
             multipleSelection: [],
             form:{
                 id:'',                
                 roleName:'',
                 roleStatus:'0',
                 roleFlag:''      
             },
             rules:{
                roleName:[
                     { required: true, message: '请输入角色名称', trigger: 'blur' },
                     { min: 2, max:25, message: '长度在 2 到 25 个字符', trigger: 'blur' }
                    ],
                roleFlag:[ 
                    { required: true, message: '请输入角色标签', trigger: 'blur' },
                     {  max:20, message: '长度限制为20 个字符', trigger: 'blur' }
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
            params.search=JSON.stringify({roleName:criteria.roleName,roleStatus:criteria.roleStatus});    
        }
        getRoleList(params).then(result => {
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
        self.form={id:'',roleName:'',roleStatus:'0',roleFlag:''};
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
        var self=this;
        var detail=self.form;
        detail.id=row.id;
        detail.roleName=row.roleName;
        detail.roleStatus=row.roleStatus.toString();
        detail.roleFlag=row.roleFlag;
        self.dialogFormVisible=true;
     },
     //删除
     handleDelete: function(index, row) {
          console.log("d:",index, row);
          var self=this;
          if(!confirm("你确定要删除吗？"))return;
          delRole({id:row.id}).then(result => {  
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
                addRole(self.form).then(result => {
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
                updateRole(self.form).then(result => {      
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