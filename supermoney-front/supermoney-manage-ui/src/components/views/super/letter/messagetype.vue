<template>
<div class="conwarp">
<!-- grid -->
<div class="grid">
    <div class="toolbar">
        <el-button type="primary"  icon="el-icon-plus" @click="dialogFormVisible=true" >添加</el-button>
    </div>
    <div class="searchbar">
        <el-form :inline="true">
            <el-form-item><el-input  placeholder="请输入名称"   class="w18"  prefix-icon="el-icon-search" v-model="criteria.name" ></el-input> </el-form-item>
            <el-form-item>
                <el-select v-model="criteria.useStatus" placeholder="使用状态">
                    <el-option v-for="item in criteria.status" :key="item.value"  :label="item.label" :value="item.value"> </el-option>
                 </el-select>
            </el-form-item>
            <el-form-item><el-button type="primary" icon="el-icon-search" @click="search">搜索</el-button></el-form-item>
        </el-form>



    </div>
    <div class="gmain">
            <el-table :data="tableData"  ref="multipleTable"  tooltip-effect="dark"  @selection-change="handleSelectionChange" stripe style="width: 100%">
            <el-table-column type="selection" width="55"></el-table-column>
            <el-table-column  prop="name"   label="名称" ></el-table-column>
            <el-table-column  prop="img"   label="图标url" ></el-table-column>
            <el-table-column  prop="sort"   label="排序序号" ></el-table-column>
            <el-table-column  prop="useStatus"  label="使用状态" >
                <template slot-scope="scope">
                     <span v-if="scope.row.useStatus==0"> 使用</span>
                     <span v-if="scope.row.useStatus==1" style="color:green;" >停用</span>
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
<el-dialog title="消息类型" :visible.sync="dialogFormVisible" @close="close" :width="dialogWidth">
    <el-form :model="form" :rules="rules" ref="form">
        <el-form-item label="名称" :label-width="formLabelWidth" prop="name">
            <input type="hidden" v-model="form.id"/>
            <el-input v-model="form.name" auto-complete="off"></el-input>
        </el-form-item>
        <el-form-item label="图标url" :label-width="formLabelWidth" prop="img">
            <el-input v-model="form.img" auto-complete="off"></el-input>
        </el-form-item>
        <el-form-item label="排序序号" :label-width="formLabelWidth" prop="sort">
           <el-input type="number" v-model="form.sort" auto-complete="off"></el-input>
       </el-form-item>
        <el-form-item label="使用状态" :label-width="formLabelWidth">
            <el-select v-model="form.useStatus" placeholder="请选择状态" size="50">
                <el-option label="使用" value="0"></el-option>
                <el-option label="停用" value="1"></el-option>
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
import {getMessageTypeList,addMessageType,updateMessageType,delMessageType} from '@/api'
import MomentPlugin from '@/libs/MomentPlugin'
export default {
  name: 'messagetype',
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
                 name:'',
                 useStatus:'',
                 status:[
                     {value: '', label: '所有状态'},
                     {value: '0', label: '使用'},
                     {value: '1', label: '停用'}
                 ]
             },
             multipleSelection: [],
             form:{
                 id:'',
                 name:'',
                 img:'',
                 useStatus:'0',
                 sort:'0',
        /*         createTime:'',
                 updateTime:'',*/
                 opt:''

             },
             rules:{
                name:[
                     { required: true, message: '请输入名称', trigger: 'blur' }
                    ]
             },
             dialogFormVisible: false,
             dialogWidth:'500px',
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
            params.search=JSON.stringify({name:criteria.name,useStatus:criteria.useStatus});
        }
        getMessageTypeList(params).then(result => {
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
        self.form={id:'',name:'',img:'',useStatus:'0'};
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
        detail.name=row.name;
        detail.img=row.img;
       detail.sort=row.sort;
        detail.useStatus=row.useStatus.toString();
        self.dialogFormVisible=true;
     },
     //删除
     handleDelete: function(index, row) {
          console.log("d:",index, row);
          var self=this;
          if(!confirm("你确定要删除吗？"))return;
          delMessageType({id:row.id}).then(result => {
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
                addMessageType(self.form).then(result => {
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
                updateMessageType(self.form).then(result => {
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
