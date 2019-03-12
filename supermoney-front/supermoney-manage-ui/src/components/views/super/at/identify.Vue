<template>
<div class="conwarp">
<!-- grid -->
<div class="grid">
    <div class="toolbar">
        <!--<el-button type="primary"  icon="el-icon-plus" @click="dialogFormVisible=true" >添加</el-button>-->
        <!--<el-button type="danger"  icon="el-icon-close">刪除</el-button>-->
          <el-button type="primary"  icon="el-icon-plus" @click="dialogFormVisible=true" >批量审核</el-button>
          <el-button type="primary"  icon="el-icon-view" @click="OCRCheckUpdate" >OCR校验</el-button>
    </div>
    <div class="searchbar">
        <el-form :inline="true">
            <el-form-item><el-input  placeholder="请输入姓名"   class="w18"  prefix-icon="el-icon-search" v-model="criteria.realName" ></el-input> </el-form-item>
            <el-form-item>
                <el-date-picker  v-model="criteria.startDate" type="date"  placeholder="开始日期"> </el-date-picker>
            </el-form-item>
            <el-form-item>
                <el-date-picker  v-model="criteria.endDate" type="date"  placeholder="结束日期"> </el-date-picker>
            </el-form-item>
      
            <el-form-item>  
                <el-select v-model="criteria.identityStatus" placeholder="任务状态">
                    <el-option v-for="item in criteria.status" :key="item.value"  :label="item.label" :value="item.value"> </el-option>
                 </el-select>
            </el-form-item>
            <el-form-item>  
                <el-select v-model="criteria.checkStatus" placeholder="OCR校验状态">
                    <el-option v-for="item in criteria.checkStatusList" :key="item.value"  :label="item.label" :value="item.value"> </el-option>
                 </el-select>
            </el-form-item>
            <el-form-item><el-button type="primary" icon="el-icon-search" @click="search">搜索</el-button></el-form-item>
        </el-form>
        
        

    </div>
    <div class="gmain">
            <el-table :data="tableData"  ref="multipleTable"  tooltip-effect="dark"  @selection-change="handleSelectionChange" stripe style="width: 100%">
            <el-table-column type="selection" width="55"></el-table-column>
            <el-table-column  prop="realName"   label="真实名称" ></el-table-column>
            <el-table-column  prop="idNumber"   label="证件号码" ></el-table-column>
            <el-table-column  prop="imgFront"   label="正面图">
                <template slot-scope="scope">
                    <img :src="scope.row.imgFront" width="60%" alt="front" />
                </template>
            </el-table-column>
            <el-table-column  prop="imgHold"   label="背面图"  >
                 <template slot-scope="scope">
                    <img :src="scope.row.imgHold" width="60%" alt="imgHold" />
                </template>
            </el-table-column>
            <!--<el-table-column  prop="mobile"   label="联系电话"  ></el-table-column>-->
            <!--<el-table-column  prop="infoSource"   label="资料来源"  ></el-table-column>
            <el-table-column  prop="identityScore"   label="评分"  ></el-table-column>-->
            <el-table-column  prop="remark"   label="备注"  ></el-table-column>
            <el-table-column  prop="identityStatus"   label="状态" >
                <template slot-scope="scope">
                     <span v-if="scope.row.identityStatus==0"> 待审核</span>
                     <span v-if="scope.row.identityStatus==1" style="color:green;" >审核通过</span>
                     <span v-if="scope.row.identityStatus==2" style="color:red;" >审核失败</span>                   
                </template>
            </el-table-column>
            <el-table-column  prop="checkStatus"   label="OCR校验" >
                <template slot-scope="scope">
                     <span v-if="scope.row.checkStatus==0"> 未校验 </span>
                     <span v-if="scope.row.checkStatus==1" style="color:green;" >校验成功</span>
                     <span v-if="scope.row.checkStatus==2" style="color:red;" >名字校验不通过</span>     
                     <span v-if="scope.row.checkStatus==3" style="color:red;" >ID号校验不通过</span>     
                     <span v-if="scope.row.checkStatus==4" style="color:red;" >ID和名字校验不通过</span>     
                     <span v-if="scope.row.checkStatus==5" style="color:red;" >三方校验失败</span>      
                     <span v-if="scope.row.checkStatus==6" style="color:red;" >证件照获取失败</span>               
                </template>
            </el-table-column>
             <el-table-column  prop="createTime"   label="时间"  ></el-table-column>
            <el-table-column label="操作"  min-width="160"> 
                 <template slot-scope="scope">
                     <el-button size="mini" type="primary"  @click="handleEdit(scope.$index, scope.row)">编辑</el-button>
                     <span v-if="scope.row.identityStatus==0 || scope.row.identityStatus==2 ">
                       <el-button size="mini" type="danger"   @click="handleDelete(scope.$index, scope.row)">删除</el-button>
                    </span>
                   
                    
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
<el-dialog title="身份信息" :visible.sync="dialogFormVisible" @close="close" :width="dialogWidth">
    <el-form :model="form" :rules="rules" ref="form">
        <el-form-item label="真实名称" :label-width="formLabelWidth" prop="realName">
            <input type="hidden" v-model="form.id"/>
            <input type="hidden" v-model="form.userId"/>
            <el-input v-model="form.realName" auto-complete="off" size="small"></el-input>
        </el-form-item>
        <el-form-item label="证件号码" :label-width="formLabelWidth" prop="idNumber">
            <el-input v-model="form.idNumber" auto-complete="off"></el-input>
        </el-form-item>
        <el-form-item label="证件图片" :label-width="formLabelWidth" prop="imgFront">
              <img :src="form.imgFront" width="40%" alt="front" />
               <img :src="form.imgHold" width="40%" alt="front" />
        </el-form-item>
        <el-form-item label="评分" :label-width="formLabelWidth" prop="identityScore">
            <el-input type="number" v-model="form.identityScore" auto-complete="off"></el-input>
        </el-form-item>
        <el-form-item label="联系电话" :label-width="formLabelWidth" prop="mobile">
            <el-input v-model="form.mobile" auto-complete="off"></el-input>
        </el-form-item>
        <el-form-item label="资料来源" :label-width="formLabelWidth" prop="infoSource">
            <el-input v-model="form.infoSource" auto-complete="off"></el-input>
        </el-form-item>
         <el-form-item label="备注" :label-width="formLabelWidth" prop="remark">
            <el-input v-model="form.remark" auto-complete="off"></el-input>
        </el-form-item>
        <el-form-item label="状态" :label-width="formLabelWidth">
            <el-select v-model="form.identityStatus" placeholder="请选择状态">
                <el-option label="待审核" value="0"></el-option>
                <el-option label="审核通过" value="1"></el-option>
                <el-option label="审核失败" value="2"></el-option>
            </el-select>
        </el-form-item>
    </el-form>
    <div slot="footer" class="dialog-footer">
        <el-button @click="dialogFormVisible=false">取 消</el-button>
        <el-button type="primary" @click="formSave('form')">确 定</el-button>
    </div>

</el-dialog>
<!-- audit -->
<!-- <el-dialog title="状态审核" :visible.sync="dialogFormAuditVisible" @close="close" :width="dialogAuditWidth">
    <el-form :model="audit" :rules="rules" ref="audit">
        <el-form-item label="状态" :label-width="formLabelAuditWidth">
            <el-select v-model="status" placeholder="请选择状态">
                <el-option label="待审核" value="0"></el-option>
                <el-option label="审核通过" value="1"></el-option>
                <el-option label="审核失败" value="2"></el-option>
            </el-select>
        </el-form-item>
    </el-form>
    <div slot="footer" class="dialog-footer">
        <el-button @click="dialogFormAuditVisible=false">取 消</el-button>
        <el-button type="primary" @click="auditUser('audit')">确 定</el-button>
    </div>
</el-dialog> -->

</div>
</template>

<script>
import {getIdentifyList,updateIdentify,updateIdentifyStatus,delIdentify,OCRCheck} from '@/api'

export default {
  name: 'at-identify',
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
                 realName:'',
                 startDate:'',
                 endDate:'',
                 identityStatus:'',
                 checkStatus:'',
                 status:[
                     {value: '', label: '所有状态'},
                     {value: '0', label: '待审核'},
                     {value: '1', label: '审核通过'},
                     {value: '2', label: '审核失败'}
                 ],
                 checkStatusList:[
                     {value: '', label: '所有状态'},
                     {value: '0', label: '未校验'},
                     {value: '1', label: '校验成功'},
                     {value: '2', label: '名字校验不通过'},
                     {value: '3', label: 'ID号校验不通过'},
                     {value: '4', label: 'ID和名字校验不通过'},
                     {value: '5', label: '三方校验失败'},
                     {value: '6', label: '证件照获取失败'},
                 ]
             },
             multipleSelection: [],
             form:{
                id:'',
                userId:'',
                realName:'',
                idNumber:'',
                imgFront:'',
                imgHold:'',
                identityScore:'',
                mobile:'',
                remark:'',
                infoSource:'',
                identityStatus:'0'			
             },
             
             rules:{

             },
             audit:{
                 status:'0'
             },
             dialogFormVisible: false,
             dialogWidth:'600px',
             formLabelWidth: '80px',
             dialogFormAuditVisible:false,
             formLabelAuditWidth:'80px',
             dialogAuditWidth:'600px'
      };
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
            params.search=JSON.stringify({realName:criteria.realName,checkStatus:criteria.checkStatus,identityStatus:criteria.identityStatus,startDate:criteria.startDate,endDate:criteria.endDate});    
        }
        getIdentifyList(params).then(result => {
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
        self.form={
                id:'',
                realName:'',
                idNumber:'',
                imgFront:'',
                imgHold:'',
                identityScore:'',
                mobile:'',
                remark:'',
                infoSource:'',
                identityStatus:'0'			
             };
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
      selLabel(data,val){       
        var lb="";
        data.forEach((item)=>{
            if(item.value==val){
                lb=item.label;
            }
        });
        return lb;
      },
      //查询
      search:function(){
          var self=this;
          console.log("search");
          this.loadData(self.criteria, this.currentPage, this.pagesize);
      },
      OCRCheckUpdate: function(){
          var self = this;
          OCRCheck({}).then(result => {
                if(result.code!="200"){
                    this.$message({message: result.message,type: 'warning'});
                    return;
                } else{
                    this.$message({message: '已发送校验请求，',type: 'success'});
                }        
          }).catch(function(err){
              self.$message({message: err,type: 'warning'});
          });
      },
    //编辑
     handleEdit: function(index, row) {
        console.log("e:",index, row);
		var self=this;
		var detail=self.form;
		detail.id=row.id;
        detail.userId=row.userId;
		detail.realName=row.realName;
		detail.idNumber=row.idNumber;
		detail.imgFront=row.imgFront;
		detail.imgHold=row.imgHold;
        detail.identityScore=row.identityScore;
		detail.mobile=row.mobile;
        detail.remark=row.remark;
	    detail.infoSource=row.infoSource;
		detail.identityStatus=row.identityStatus.toString();
        
		self.dialogFormVisible=true;
     },
     //删除
     handleDelete: function(index, row) {
          console.log("d:",index, row);
          var self=this;
          if(!confirm("你确定要删除吗？"))return;
          delIdentify({id:row.id}).then(result => {  
              console.log('del:',result);
             if(result.code!="200"){
                alert(result.message);
                return;
             }        
             self.reload();  
          });
     },
     //批量审核
     auditUser(formName){

     },
     //保存表单
     formSave(formName){
        var self=this;
        self.$refs[formName].validate((valid) => {
            console.log("valid:",valid);
            if(!valid)return;
          
            if(self.form.id==""){

            }else{
                //update
                updateIdentify(self.form).then(result => {      
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