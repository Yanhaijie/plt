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
            <el-col :span="4"><el-input  placeholder="用户名称"   class="w18"  prefix-icon="el-icon-search" v-model="criteria.userName" ></el-input> </el-col>
             <el-col :span="4">
                 <el-select v-model="criteria.userType" placeholder="用户类型">
                    <el-option v-for="item in criteria.userTypes" :key="item.value"  :label="item.label" :value="item.value"> </el-option>
                 </el-select>
            </el-col>
            <el-col :span="4">  
                <el-select v-model="criteria.userRole" placeholder="用户角色">
                    <el-option v-for="item in criteria.userRoles" :key="item.value"  :label="item.text" :value="item.value"> </el-option>
                 </el-select>
            </el-col>
            
            <el-col :span="4">
                <el-date-picker  v-model="criteria.startDate" type="date"  placeholder="开始日期"> </el-date-picker>
            </el-col>
            <el-col :span="4">
                <el-date-picker  v-model="criteria.endDate" type="date"  placeholder="结束日期"> </el-date-picker>
            </el-col>


            <el-col :span="4"><el-button type="primary" icon="el-icon-search" @click="search">搜索</el-button></el-col>
        </el-row>
        
        

    </div>
    <div class="gmain">
            <el-table :data="tableData"  ref="multipleTable"  tooltip-effect="dark"  @selection-change="handleSelectionChange" stripe style="width: 100%">
            <el-table-column type="selection" width="55"></el-table-column>
            <el-table-column  prop="roleName"   label="角色" ></el-table-column>
            <el-table-column  prop="userType"   label="类型" >
                <template slot-scope="scope">
                    <span v-if="scope.row.userType==0" style="color:green;" >对外用户</span>
                    <span v-if="scope.row.userType==1" style="color:red;" >对内用户</span>
                </template>
            </el-table-column>
            <el-table-column  prop="userName"   label="名称" ></el-table-column>
            <el-table-column  prop="regSource"   label="注册来源" ></el-table-column>
            <el-table-column  prop="regIp"   label="注册IP" ></el-table-column>
            <el-table-column  prop="createDate"   label="注册时间" :formatter="dateFormat" ></el-table-column>
            <el-table-column  prop="rowStatus"   label="状态" >
                <template slot-scope="scope">
                    <span v-if="scope.row.rowStatus==0" style="color:#E6A23C;" >未启用</span>
                    <span v-if="scope.row.rowStatus==1" style="color:green;" >启用</span>
                    <span v-if="scope.row.rowStatus==2" style="color:red;" >冻结</span>
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
<el-dialog title="用户信息" :visible.sync="dialogFormVisible" @close="close" :width="dialogWidth">
    <el-form :model="form" :rules="rules" ref="form">
        <el-form-item label="用户类型" :label-width="formLabelWidth">
            <el-select v-model="form.userType" placeholder="请选择应用类型">
                <el-option label="面向外部" value="0"></el-option>
                <el-option label="面向内部" value="1"></el-option>
            </el-select>
        </el-form-item>
         <el-form-item label="用户状态" :label-width="formLabelWidth">
            <el-select v-model="form.rowStatus" placeholder="用户状态">
                <el-option label="未启用" value="0"></el-option>
                <el-option label="启用" value="1"></el-option>
                <el-option label="冻结" value="2"></el-option>
            </el-select>
        </el-form-item>
        <el-form-item label="用户角色" :label-width="formLabelWidth">
            <el-select v-model="form.roleCode" placeholder="用户角色">
                <el-option v-for="item in criteria.userRoles" :key="item.value"  :label="item.text" :value="item.value"> </el-option>
            </el-select>
         </el-form-item>
        <el-form-item label="用户名称" :label-width="formLabelWidth" prop="userName">
            <input type="hidden" v-model="form.id"/>
            <input type="hidden" v-model="form.userCode"/>
            <el-input v-model="form.userName" auto-complete="off" size="small"></el-input>
        </el-form-item>
        <el-form-item label="用户密码" :label-width="formLabelWidth" prop="password">
            <el-input v-model="form.password" auto-complete="off"></el-input>
        </el-form-item>
        <el-form-item label="用户昵称" :label-width="formLabelWidth" prop="nickName">
            <el-input v-model="form.nickName" auto-complete="off" size="small"></el-input>
        </el-form-item>
         <el-form-item label="用户资料:" :label-width="formLabelWidth">
         </el-form-item>
         <el-form-item label="电子邮件" :label-width="formLabelWidth" prop="email">
              <el-input v-model="form.email" auto-complete="off" size="small"></el-input>
         </el-form-item>
         <el-form-item label="电话" :label-width="formLabelWidth" prop="phone" >
              <el-input v-model="form.phone" auto-complete="off" size="small"></el-input>
         </el-form-item>
         <el-form-item label="身份证号" :label-width="formLabelWidth" prop="idCard">
              <el-input v-model="form.idCard" auto-complete="off" size="small"></el-input>
         </el-form-item>
         <el-form-item label="性别" :label-width="formLabelWidth">
            <el-select v-model="form.gender" placeholder="性别">
                <el-option label="男" value="0"></el-option>
                <el-option label="女" value="1"></el-option>
            </el-select>
         </el-form-item>
        <!--<el-form-item label="出生年月" :label-width="formLabelWidth" >
            <el-date-picker  v-model="form.birthdayStr" type="datetime"  placeholder="选择日期"> </el-date-picker>
        </el-form-item>-->
         

    </el-form>
    <div slot="footer" class="dialog-footer">
        <el-button @click="dialogFormVisible=false">取 消</el-button>
        <el-button type="primary" @click="formSave('form')">确 定</el-button>
    </div>

</el-dialog>
</div>
</template>

<script>
import {getUserList,addUser,updateUser,delUser,getRoleDrop} from '@/api'
import MomentPlugin from '@/libs/MomentPlugin'
export default {
  name: 'usermanage',
  data () {
      return{
             tableData:[],
             //分页-数量
             pagesize: 10,
             //分页-页码
             currentPage: 1,
             //分页-总数
             totalCount: 0,
             //搜索条件
             criteria: {
                 userName:'',
                 startDate:'',
                 endDate:'',
                 userType:'',
                 userTypes:[
                     {value: '', label: '所有类型'},
                     {value: '0', label: '面向外部'},
                     {value: '1', label: '面向内部'}
                 ],
                 userRole:'',
                 userRoles:[
                     {value: '', label: '所有角色'},
                 ]
             },
             multipleSelection: [],
             form:{                
                 id:'',
                 regSource:'ADMIN_CREATE',
                 userCode:'',
                 roleCode:'',
                 userType:'0',
                 userName:'',
                 password:'',
                 nickName:'',
                 rowStatus:'1',
                 email:'',
                 phone:'',
                 idCard:'',
                 gender:'0'
                // birthdayStr:'2017-12-29 16:44:35'
             },
             rules:{
                userName:[
                     { required: true, message: '请输入名称号', trigger: 'blur' },
                     { min: 6, max:20, message: '长度在 6 到 20 个字符', trigger: 'blur' }
                    ],
                password:[ 
                     {   max:20, message:'长度限制为 20  个字符', trigger: 'blur' }
                    ],
                nickName:[ 
                     {    max:20, message:'长度限制为 20  个字符', trigger: 'blur' }
                    ],
                email:[ 
                     {    max:30, message:'长度限制为 40  个字符', trigger: 'blur' }
                    ],
                phone:[ 
                     {    max:18, message:'长度限制为 18  个字符', trigger: 'blur' }
                    ],
                idCard:[ 
                     {    max:20, message:'长度限制为 20  个字符', trigger: 'blur' }
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
     self.getRoleList();
  },
  methods:{
     //分页数据加载
     loadData: function(criteria, pageNum, pagesize){ 
          console.log("load");
        var self=this;
        var params={page:pageNum,size:pagesize,search:""};
        if(criteria!=null){
            params.search=JSON.stringify({
                 userName:criteria.userName,
                 startDate:criteria.startDate,
                 endDate:criteria.endDate,
                 userType:criteria.userType,
                 userRole:criteria.userRole
            });    
        }
        getUserList(params).then(result => {
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
        self.form={id:'',userCode:'',regSource:'ADMIN_CREATE',roleCode:'',userType:'0',userName:'',password:'',nickName:'', rowStatus:'1',email:'',phone:'',idCard:'',gender:'0'};
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
      //时间格式
      dateFormat:function(row, column) {  
           var self=this;
          var date = row[column.property];  
          if (date == undefined) {  
             return "";  
          }  
          return MomentPlugin(date).format("YYYY-MM-DD HH:mm:ss"); 
     },
     //获取角色列表
      getRoleList:function(){
         var self=this;
        getRoleDrop({}).then(result => {
            console.log("drop",result);
            if(result.code!="200")return;
            self.criteria.userRoles=result.data;
            self.criteria.userRoles.splice(0,0,{text:'所有角色',value:''});
        });
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
        detail.userCode=row.userCode;
        detail.regSource=row.regSource;        
        detail.roleCode=row.roleCode;
        detail.userType=row.userType.toString();
        detail.userName=row.userName;
        detail.password='';
        detail.nickName=row.nickName;
        detail.rowStatus=row.rowStatus.toString();
        detail.email=row.email;
        detail.phone=row.phone;
        detail.idCard=row.idCard;
        detail.gender=row.gender==null? '0':row.gender.toString();
        self.dialogFormVisible=true;
     },
     //删除
     handleDelete: function(index, row) {
          console.log("d:",index, row);
          var self=this;
          if(!confirm("你确定要删除吗？"))return;
          delUser({id:row.id}).then(result => {  
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
                addUser(self.form).then(result => {
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
                updateUser(self.form).then(result => {      
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