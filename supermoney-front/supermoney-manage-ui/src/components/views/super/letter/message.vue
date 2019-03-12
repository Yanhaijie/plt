<template>
<div class="conwarp">
<!-- grid -->
<div class="grid">
    <div class="toolbar">
        <el-button type="primary"  icon="el-icon-plus" @click="dialogFormVisible=true" >添加</el-button>
    </div>
    <div class="searchbar">
        <el-form :inline="true">
            <el-form-item><el-input  placeholder="请输入编号"   class="w18"  prefix-icon="el-icon-search" v-model="criteria.id" ></el-input> </el-form-item>
            <el-form-item><el-input  placeholder="请输入标题"   class="w18"  prefix-icon="el-icon-search" v-model="criteria.title" ></el-input> </el-form-item>
            <el-form-item>
              <el-select v-model="criteria.messageTypeId" placeholder="消息类别">
                <el-option v-for="item in criteria.messageTypes" :key="item.value"  :label="item.text" :value="item.value"> </el-option>
              </el-select>
            </el-form-item>
            <el-form-item>
                <el-select v-model="criteria.useStatus" placeholder="使用状态">
                    <el-option v-for="item in criteria.status" :key="item.value"  :label="item.label" :value="item.value"> </el-option>
                 </el-select>
            </el-form-item>
         <!--    <el-form-item>
                  <el-date-picker  v-model="criteria.startTime" type="date"  placeholder="开始日期"> </el-date-picker>
              </el-form-item>
              <el-form-item>
                  <el-date-picker  v-model="criteria.endTime" type="date"  placeholder="结束日期"> </el-date-picker>
              </el-form-item>-->
            <el-form-item><el-button type="primary" icon="el-icon-search" @click="search">搜索</el-button></el-form-item>
        </el-form>



    </div>
    <div class="gmain">
          <el-table :data="tableData"  ref="multipleTable"  tooltip-effect="dark"  @selection-change="handleSelectionChange" stripe style="width: 100%">
                 <el-table-column type="selection" width="55"></el-table-column>
                 <el-table-column  prop="id"   label="编号"  width="80"></el-table-column>
                 <el-table-column  prop="title"   label="标题" ></el-table-column>
                 <el-table-column  prop="content"   label="内容" ></el-table-column>
                 <el-table-column  prop="parentId"   label="父编号"  width="80"></el-table-column>
      <!--           <el-table-column  prop="img"   label="图片内容"  ></el-table-column>-->
                 <el-table-column  prop="updateTime"   label="更新时间" ></el-table-column>
                 <!--<el-table-column  prop="startTime"   label="生效时间" ></el-table-column>-->
                 <!--<el-table-column  prop="endTime"   label="失效时间" ></el-table-column>-->
                 <el-table-column  prop="url"   label="链接"  ></el-table-column>
                 <el-table-column  prop="messageTypeName"   label="消息类别"  ></el-table-column>
                 <el-table-column  prop="useStatus"  label="使用状态" >
                     <template slot-scope="scope">
                          <span v-if="scope.row.useStatus==0"> 使用</span>
                          <span v-if="scope.row.useStatus==1" style="color:green;" >停用</span>
                     </template>
                 </el-table-column>
                <!-- <el-table-column  prop="status_describe"   label="状态描述"  ></el-table-column>-->
                 <el-table-column label="操作"  width="300">
                      <template slot-scope="scope">
                         <el-button size="small" type="primary"  @click="handleEdit(scope.$index, scope.row)">编辑</el-button>
                         <el-button size="small" type="danger"   @click="handleDelete(scope.$index, scope.row)">删除</el-button>
                         <el-button size="small" type="danger"   @click="handleSend(scope.$index, scope.row)">推送消息</el-button>
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
<el-dialog title="消息信息" :visible.sync="dialogFormVisible" @close="close" :width="dialogWidth">
    <el-form :model="form" :rules="rules" ref="form">
        <el-form-item label="标题" :label-width="formLabelWidth" prop="title">
            <input type="hidden" v-model="form.id"/>
            <el-input v-model="form.title" auto-complete="off"></el-input>
        </el-form-item>

        <el-form-item label="消息类别" :label-width="formLabelWidth">
          <el-select v-model="form.messageTypeId" placeholder="消息类别" size="50">
            <el-option v-for="item in criteria.messageTypes" :key="item.value"  :label="item.text" :value="item.value"> </el-option>
          </el-select>
        </el-form-item>

        <el-form-item label="内容" :label-width="formLabelWidth" prop="content">
            <el-input v-model="form.content" auto-complete="off"></el-input>
        </el-form-item>
        <el-form-item label="父消息编号" :label-width="formLabelWidth" prop="parentId">
            <el-input v-model="form.parentId" auto-complete="off"></el-input>
        </el-form-item>
  <!--      <el-form-item label="图片内容" :label-width="formLabelWidth" prop="img">
            <el-input v-model="form.img" auto-complete="off"></el-input>
        </el-form-item>-->
      <el-form-item label="链接" :label-width="formLabelWidth" prop="url">
        <el-input v-model="form.url" auto-complete="off"></el-input>
      </el-form-item>
      <el-form-item label="使用状态" :label-width="formLabelWidth">
          <el-select v-model="form.useStatus" placeholder="请选择状态"  size="50">
              <el-option label="使用" value="0"></el-option>
              <el-option label="停用" value="1"></el-option>
          </el-select>
      </el-form-item>
      <el-form-item label="使用状态描述" :label-width="formLabelWidth" prop="statusDescribe">
        <el-input v-model="form.statusDescribe" auto-complete="off"></el-input>
      </el-form-item>
      <el-form-item label="推送群体SQL" :label-width="formLabelWidth" prop="targetSql">
        <el-input type="textarea" v-model="form.targetSql"></el-input>
      </el-form-item>
      <el-form-item label="生效时间" :label-width="formLabelWidth" prop="startTime">
        <el-date-picker v-model="form.startTime" type="datetime" format="yyyy-MM-dd HH:mm:ss" value-format="yyyy-MM-dd HH:mm:ss" placeholder="选择日期时间"></el-date-picker>
      </el-form-item>
      <el-form-item label="失效时间" :label-width="formLabelWidth" prop="endTime">
        <el-date-picker v-model="form.endTime" type="datetime" format="yyyy-MM-dd HH:mm:ss" value-format="yyyy-MM-dd HH:mm:ss" placeholder="选择日期时间"></el-date-picker>
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
import {getMessageList,addMessage,updateMessage,delMessage,getMessagetTypeDrop,sendMessage} from '@/api'
import MomentPlugin from '@/libs/MomentPlugin'
export default {
  name: 'message',
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
                 title:'',
                 useStatus:'',
                 startTime:'',
                 endTime:'',
                 status:[
                     {value: '', label: '所有状态'},
                     {value: '0', label: '使用'},
                     {value: '1', label: '停用'}
                 ],
                 messageTypeId:'',
                 messageTypes:[
                   {value: '', label: '消息类别'},
                 ]
             },
             multipleSelection: [],
             bountyList:[],
             bountySel:'',
             form:{
                 id:'',
                 title:'',
                 content:'',
               /*  img:'',*/
                 url:'',
                 parentId:'',
                 messageTypeId:'',
                 useStatus:'0',
                 statusDescribe:'',
                 pushSchedule:'',
                 targetSql:'',
                 startTime:'',
                 endTime:'',
                /* pushTime:'',
                 createTime:'',
                 updateTime:'',*/
                 opt:''

             },
             rules:{
                title:[
                     { required: true, message: '请输入标题', trigger: 'blur' }
                    ]
             },
             dialogFormVisible: false,
             dialogWidth:'500px',
             formLabelWidth: '110px',
             dialogSendMessageVisible:false,

      }
  },
  mounted(){
     var self=this;
     self.reload();
     self.getMessageTypeList();
  },
  methods:{
     //分页数据加载
     loadData: function(criteria, pageNum, pagesize){
          console.log("load");
        var self=this;
        var params={page:pageNum,size:pagesize,search:""};
        if(criteria!=null){
            params.search=JSON.stringify({id:criteria.id,title:criteria.title,messageTypeId:criteria.messageTypeId,useStatus:criteria.useStatus,startTime:criteria.startTime,endTime:criteria.endTime});
        }
        getMessageList(params).then(result => {
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
        self.form={id:'',title:'',content:'',url:'', parentId:'', messageTypeId:'',useStatus:'0', statusDescribe:'',pushSchedule:'', targetSql:'', opt:'',startTime:'', endTime:''};
        self.dialogFormVisible=false;
       self.dialogSendMessageVisible=false;
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
    //获取消息类别列表
    getMessageTypeList:function(){
      var self=this;
      getMessagetTypeDrop({}).then(result => {
        console.log("drop",result);
        if(result.code!="200")return;
        self.criteria.messageTypes=result.data;
        self.criteria.messageTypes.splice(0,0,{text:'--',value:''});
      });
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
            detail.title=row.title;
            detail.content=row.content;
          /*  detail.img=row.img;*/
            detail.url=row.url;
            detail.parentId=row.parentId;
            detail.messageTypeId=row.messageTypeId == null ? "":row.messageTypeId.toString();
            detail.useStatus=row.useStatus.toString();
            detail.statusDescribe=row.statusDescribe;
            detail.targetSql=row.targetSql;
            detail.startTime=row.startTime;
            detail.endTime=row.endTime;
       /*     detail.pushSchedule=row.pushSchedule;
            detail.pushTime=row.pushTime;

           */
            detail.targetSql=row.targetSql;
            self.dialogFormVisible=true;
         },
     //删除
     handleDelete: function(index, row) {
          console.log("d:",index, row);
          var self=this;
          if(!confirm("你确定要删除吗？"))return;
          delMessage({id:row.id}).then(result => {
              console.log('del:',result);
             if(result.code!="200"){
                alert(result.message);
                return;
             }
             self.reload();
          });
     },
    //推送消息
    handleSend: function(index, row) {
      console.log("d:",index, row);
      var self=this;
      if(!confirm("你确定要推送吗？"))return;
      //接受消息人群不能为空
      var targetSql= row.targetSql.toString().trim();
      if(targetSql == null || targetSql == ''){
        confirm("消息推送群体SQL不能为空，请先编辑！");
        return;
      }else{
        sendMessage({id:row.id}).then(result => {
          if(result.code!="200"){
            alert(result.message);
            return;
          }else{
            alert("推送成功！");
          }
          self.reload();
        });
      }

    },
     //保存表单
     formSave(formName){
        var self=this;
        self.$refs[formName].validate((valid) => {
            console.log("valid:",valid);
            if(!valid)return;
            if(self.form.id==""){
                //add
                addMessage(self.form).then(result => {
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
                updateMessage(self.form).then(result => {
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
