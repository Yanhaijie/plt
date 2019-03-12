<template>
<div class="conwarp">
<!-- grid -->
<div class="grid">
    <div class="toolbar">
        <el-button type="primary"  icon="el-icon-plus" @click="dialogFormVisible=true" >添加</el-button>
    </div>
    <div class="searchbar">
        <el-row>
            <el-col :span="4"><el-input  placeholder="请输入名称"   class="w18"  prefix-icon="el-icon-search" v-model="criteria.routeName" ></el-input> </el-col>
            <el-col :span="4"> 
                 <el-select v-model="criteria.appCode" placeholder="应用">
                    <el-option v-for="item in criteria.appList" :key="item.value"  :label="item.text" :value="item.value"> </el-option>
                 </el-select>
            </el-col>
            <el-col :span="4">
                 <el-select v-model="criteria.routeType" placeholder="路由类型">
                    <el-option v-for="item in criteria.routeTypes" :key="item.value"  :label="item.label" :value="item.value"> </el-option>
                 </el-select>
            </el-col>
            <el-col :span="4">  
                <el-select v-model="criteria.routeStatus" placeholder="路由状态">
                    <el-option v-for="item in criteria.status" :key="item.value"  :label="item.label" :value="item.value"> </el-option>
                 </el-select>
            </el-col>
            <el-col :span="4">
                 <el-select v-model="criteria.routeMethod" placeholder="使用方式">
                    <el-option v-for="item in criteria.routeMethods" :key="item.value"  :label="item.label" :value="item.value"> </el-option>
                 </el-select>
            </el-col>

            <el-col :span="4"><el-button type="primary" icon="el-icon-search" @click="search">搜索</el-button></el-col>
        </el-row>
        
        

    </div>
    <div class="gmain">
            <el-table :data="tableData"  ref="multipleTable"  tooltip-effect="dark"  @selection-change="handleSelectionChange" stripe style="width: 100%">
            <el-table-column type="selection" width="55"></el-table-column>
            <el-table-column  prop="appCode"   label="应用" ></el-table-column>
            <el-table-column  prop="routeCode"   label="路由编号" ></el-table-column>
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
<el-dialog title="应用信息" :visible.sync="dialogFormVisible" :before-close="close" :width="dialogWidth">
    <el-form :model="form" :rules="rules" ref="form">
        <el-form-item label="路由名称" :label-width="formLabelWidth" >
             <input type="hidden" v-model="form.id"/>
            <el-input v-model="form.routeName" auto-complete="off" size="small"></el-input>
        </el-form-item>
        <el-form-item label="路由地址" :label-width="formLabelWidth" >
            <el-input v-model="form.routeUrl" auto-complete="off" size="small"></el-input>
        </el-form-item>
         <el-form-item label="父级编号" :label-width="formLabelWidth">
             <el-input v-model="form.parentCode" auto-complete="off" size="small"></el-input>
        </el-form-item>
         <el-form-item label="应用" :label-width="formLabelWidth">
            <el-select v-model="form.appCode" placeholder="请选择应用">
                <el-option v-for="item in criteria.appList" :key="item.value"  :label="item.text" :value="item.value"> </el-option>
            </el-select>
        </el-form-item>  
        <el-form-item label="路由类型" :label-width="formLabelWidth">
            <el-select v-model="form.routeType" placeholder="请选择应用类型">
                <el-option label="普通路由" value="1"></el-option>
                <el-option label="根路由" value="0"></el-option>              
            </el-select>
        </el-form-item>
          <el-form-item label="使用方式" :label-width="formLabelWidth">
                <el-select v-model="form.routeMethod" placeholder="选择使用方式">
                <el-option label="菜单" value="0"></el-option>
                <el-option label="页面" value="1"></el-option>
                <el-option label="API" value="2"></el-option>
                <el-option label="资源" value="3"></el-option>
          </el-select>
        </el-form-item>
          <el-form-item label="路由状态" :label-width="formLabelWidth">
            <el-select v-model="form.routeStatus" placeholder="选择路由状态">
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
import {getRouteList,addRoute,updateRoute,delRoute,getAppDrop} from '@/api'

export default {
  name: 'routermanage',
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
             form:{
                 id:'',
                 appCode:'',
                 parentCode:'',
                 routeName:'',
                 routeUrl:'',
                 routeType:'1',
                 routeStatus:'0',
                 routeMethod:'0'
             },
             rules:{
              
             },
           
             dialogFormVisible: false,
             dialogWidth:'400px',
             formLabelWidth: '80px'
             
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
            params.search=JSON.stringify({routeName:criteria.routeName,appCode:criteria.appCode,routeStatus:criteria.routeStatus,routeType:criteria.routeType,routeMethod:criteria.routeMethod});    
        }
        getRouteList(params).then(result => {
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
        self.form={id:'',appCode:'',parentCode:'',routeName:'',routeUrl:'',routeType:'1',routeStatus:'0',routeMethod:'0'};
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
        detail.parentCode=row.parentCode;
        detail.routeName=row.routeName;
        detail.routeUrl=row.routeUrl;
        detail.routeType=row.routeType.toString();
        detail.routeStatus=row.routeStatus.toString();
        detail.routeMethod=row.routeMethod.toString();
        self.dialogFormVisible=true;
     },
     //获取应用列表
     getAppList:function(){
         var self=this;
        getAppDrop({}).then(result => {
            console.log("drop",result);
            if(result.code!="200")return;
            self.criteria.appList=result.data;
            self.criteria.appList.splice(0,0,{text:'所有应用',value:''});
        });
     },
     //删除
     handleDelete: function(index, row) {
          console.log("d:",index, row);
          var self=this;
          if(!confirm("你确定要删除吗？"))return;
          delRoute({id:row.id}).then(result => {  
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
                addRoute(self.form).then(result => {
                    console.log("add:",result);
                    if(result.code!="200"){
                        alert(result.message);
                        return;
                    }                  
                    self.reload();
                    self.restForm();
                   
                })
            }else{
                //update
                updateRoute(self.form).then(result => {      
                     if(result.code!="200"){
                        alert(result.message);
                        return;
                     }              
                     self.reload();
                     self.restForm();
                     
                })
            }
       })
       

      }
  }
}
</script>