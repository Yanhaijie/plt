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
                    <el-button size="small" type="success"   @click="rolePermision(scope.$index, scope.row)">权限</el-button>
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
<el-dialog title="角色信息" :visible.sync="dialogFormVisible" :before-close="close" :width="dialogWidth">
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
<!--角色权限-->
<el-dialog title="权限设置" :visible.sync="dialogPermisionVisible" :before-close="roleClose" :width="dialogRoleWidth"  >
    <div class="grid" >
    <div class="searchbar">
        <el-row>
            <el-col :span="4"><el-input  placeholder="请输入名称"   class="w18"  prefix-icon="el-icon-search" v-model="criteriaRole.routeName" ></el-input> </el-col>
            <el-col :span="4"> 
                 <el-select v-model="criteriaRole.appCode" class="w18" placeholder="应用">
                    <el-option v-for="item in criteriaRole.appList" :key="item.value"  :label="item.text" :value="item.value"> </el-option>
                 </el-select>
            </el-col>
            <el-col :span="4">
                 <el-select v-model="criteriaRole.routeType" class="w18" placeholder="路由类型">
                    <el-option v-for="item in criteriaRole.routeTypes" :key="item.value"  :label="item.label" :value="item.value"> </el-option>
                 </el-select>
            </el-col>
            <el-col :span="4">  
                <el-select v-model="criteriaRole.routeStatus" class="w18" placeholder="路由状态">
                    <el-option v-for="item in criteriaRole.status" :key="item.value"  :label="item.label" :value="item.value"> </el-option>
                 </el-select>
            </el-col>
            <el-col :span="4">
                 <el-select v-model="criteriaRole.routeMethod" class="w18"  placeholder="使用方式">
                    <el-option v-for="item in criteriaRole.routeMethods" :key="item.value"  :label="item.label" :value="item.value"> </el-option>
                 </el-select>
            </el-col>

            <el-col :span="2">
                <el-button type="primary" icon="el-icon-search" @click="loadRoleData">搜索</el-button>
            </el-col>
            <el-col :span="2">
                <el-button type="success"   @click="savePermissions" >保存权限</el-button>
            </el-col>
        </el-row>

    </div>
    <div class="gmain">
            <el-table :data="tableRoleData"  ref="multipleRoleTable"  tooltip-effect="dark" @select-all="handleRoleSelectionChange" @select="handleRoleSelectionChange" height="500" stripe style="width: 100%">
            <el-table-column type="selection" width="55"></el-table-column>
            <el-table-column  prop="appCode"   label="应用" ></el-table-column>
            <el-table-column  prop="routeName"   label="路由名称" ></el-table-column>
            <el-table-column  prop="routeUrl"   label="路由地址" ></el-table-column>
            <el-table-column  prop="routeType"   label="路由类型"  >
                 <template slot-scope="scope">
                    <span v-if="scope.row.routeType==0" >根路由</span>
                    <span v-if="scope.row.routeType==1" >普通路由</span>
                </template>
            </el-table-column>
              <el-table-column  prop="routeMethod"   label="使用方式"  >
                 <template slot-scope="scope">
                    <span v-if="scope.row.routeMethod==0" >菜单</span>
                    <span v-if="scope.row.routeMethod==1" >页面</span>
                    <span v-if="scope.row.routeMethod==2" >API</span>
                    <span v-if="scope.row.routeMethod==3" >资源</span>
                </template>
            </el-table-column>
            <el-table-column  prop="routeStatus"   label="状态" >
                <template slot-scope="scope">
                    <span v-if="scope.row.routeStatus==0" style="color:red;" >启用</span>
                    <span v-if="scope.row.routeStatus==1" style="color:green;" >关闭</span>
                </template>
            </el-table-column>
        </el-table>
    </div>
    </div>

</el-dialog>
</div>
</template>

<script>
import {getRoleList,addRole,updateRole,delRole,getAppDrop,getRouteList,getRolePermission,saveRolePermission} from '@/api'

export default {
  name: 'rolemanage',
  data () {
      return{
             tableData:[],
             tableRoleData:[],
             roleCode:"",
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
            criteriaRole: {
                 appName:'',
                 appCode:'',
                 routeType:'',
                 routeTypes:[
                     {value: '', label: '所有类型'},
                     {value: '0', label: '根路由'},
                     {value: '1', label: '普通路由'}
                 ],
                 routeStatus:'',
                 status:[
                     {value: '', label: '所有状态'},
                     {value: '0', label: '启用'},
                     {value: '1', label: '关闭'}
                 ],
                 routeMethod:'',
                 routeMethods:[
                     {value: '', label: '所有方式'},
                     {value: '0', label: '菜单'},
                     {value: '1', label: '页面'},
                     {value: '2', label: 'API'},
                     {value: '3', label: '资源'}
                 ],
                 appList:[]
             },
             multipleSelection: [],
             multipleRoleSelection: [],
             hasPermision:[],
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
             formLabelWidth: '80px',
             dialogPermisionVisible:false,
             dialogRoleWidth:'70%',
             dialogRoleHeight:'800px'
             
      }
  },
  mounted(){
     var self=this;
     self.reload();
     self.getAppList();
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
     //权限数据加载
     loadRoleData:function(){
        var self=this;
        var criteria=self.criteriaRole;
       
        var params={page:1,size:1000,search:""};    
        params.search= params.search=JSON.stringify({
            routeName:criteria.routeName,
            appCode:criteria.appCode,
            routeStatus:criteria.routeStatus,
            routeType:criteria.routeType,
            routeMethod:criteria.routeMethod
        });    
        getRouteList(params).then(result => {
               console.log("load route")
                var data=result.data;
                self.tableRoleData=data.list;
              setTimeout(function(){
                  self.showPermision(self.tableRoleData);
              },1000)  
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
     roleClose:function(){
          var self=this;
          self.roleCode="";
          self.tableRoleData=[];
          self.dialogPermisionVisible=false;
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
      handleRoleSelectionChange(checkData) {
        var self=this;
        var np=[]
        checkData.forEach(p=>{ np.push(p.routeCode) });
        self.hasPermision=np;
      },
     
     handleSelectionChange(val) {
        this.multipleSelection = val;
      },
      //查询
      search:function(){
          var self=this;
          console.log("search");
          this.loadData(self.criteria, this.currentPage, this.pagesize);
      },
       //获取应用列表
      getAppList:function(){
         var self=this;
        getAppDrop({}).then(result => {
            console.log("drop",result);
            if(result.code!="200")return;
            self.criteriaRole.appList=result.data;
            self.criteriaRole.appList.splice(0,0,{text:'所有应用',value:''});
        });
      },
      //加载角色权限
      loadPermision:function(){
          var self=this;     
          getRolePermission({roleCode:self.roleCode}).then(result => {
               self.hasPermision=result.data;           
         });
      },
      //显示已选角色权限
      showPermision:function(data){
           var self=this;      
           var rt= self.$refs.multipleRoleTable;
           self.hasPermision.forEach(p=>{
                self.$refs.multipleRoleTable.toggleRowSelection(self.tableRoleData.find(d => d.routeCode==p)) 
           });
      },
      //权限设置
      rolePermision:function(index,row){
         var self=this;
         self.roleCode=row.roleCode;
         self.loadPermision();
         self.dialogPermisionVisible=true;
      },
      //保存角色权限
      savePermissions:function(){
        var self=this;
        var params={roleCode:self.roleCode,ids:""};      
        var ids="";
        self.hasPermision.forEach(p=>{ ids+=p+"," });
        if(ids.length>0)ids=ids.substring(0,ids.length-1);
        params.ids=ids;
        console.log("self.params:",params);
        saveRolePermission(params).then(result => {
            if(result.code==200){
                alert("保存成功!");
            }
        });
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