<template>
<div class="conwarp">
<!-- grid -->
<div class="grid">
    <div class="toolbar">
        <el-button type="primary"  icon="el-icon-plus" @click="dialogFormVisible=true" >添加</el-button>
    </div>
    <div class="searchbar">
        <el-form :inline="true">
            <el-form-item><el-input  placeholder="请输入名称"   class="w18"  prefix-icon="el-icon-search" v-model="criteria.voucherName" ></el-input> </el-form-item>
            <el-form-item>
                <el-date-picker  v-model="criteria.startDate" type="date" format="yyyy-MM-dd" value-format="yyyy-MM-dd HH:mm:ss" placeholder="开始日期"> </el-date-picker>
            </el-form-item>
            <el-form-item>
                <el-date-picker  v-model="criteria.endDate" type="date" format="yyyy-MM-dd" value-format="yyyy-MM-dd HH:mm:ss" placeholder="结束日期"> </el-date-picker>
            </el-form-item>

            <el-form-item>
                <el-select v-model="criteria.voucherStatus" placeholder="任务状态">
                    <el-option v-for="item in criteria.status" :key="item.value"  :label="item.label" :value="item.value"> </el-option>
                 </el-select>
            </el-form-item>
            <el-form-item><el-button type="primary" icon="el-icon-search" @click="search">搜索</el-button></el-form-item>
        </el-form>



    </div>
    <div class="gmain">
            <el-table :data="tableData"  ref="multipleTable"  tooltip-effect="dark"  @selection-change="handleSelectionChange" stripe style="width: 100%">
            <el-table-column type="selection" width="55"></el-table-column>
            <el-table-column  prop="voucherName"   label="奖券名称" ></el-table-column>
            <el-table-column  prop="voucherDsc"   label="奖券介绍" ></el-table-column>
            <el-table-column  prop="voucherVal"   label="兑换值"  ></el-table-column>
            <el-table-column  prop="voucherType"   label="奖券类型" >
                <template slot-scope="scope">
                     <span v-if="scope.row.voucherType==0">单次翻倍</span>
                     <span v-if="scope.row.voucherType==1">项目翻倍</span>
                     <span v-if="scope.row.voucherType==2">期间翻倍</span>
                     <span v-if="scope.row.voucherType==3">随机红包</span>
                </template>
            </el-table-column>
           <el-table-column  prop="startTime"   label="有效期-开始" :formatter="dateFormat" ></el-table-column>
           <el-table-column  prop="startTime"   label="有效期-结束" :formatter="dateFormat" ></el-table-column>
           <el-table-column  prop="voucherStatus"   label="任务状态" >
                <template slot-scope="scope">
                     <span v-if="scope.row.voucherStatus==0"> 不可用</span>
                     <span v-if="scope.row.voucherStatus==1" style="color:green;" >可用</span>
                </template>
            </el-table-column>
            <el-table-column label="操作"  :min-width="180">
                 <template slot-scope="scope">
                    <el-button size="mini" type="primary"  @click="handleEdit(scope.$index, scope.row)">编辑</el-button>
                    <el-button size="mini" type="danger"   @click="handleDelete(scope.$index, scope.row)">删除</el-button>
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
<el-dialog title="奖券信息" :visible.sync="dialogFormVisible" @close="close" :width="dialogWidth">
    <el-form :model="form" :rules="rules" ref="form">
        <el-form-item label="奖券名称" :label-width="formLabelWidth" prop="voucherName">
            <input type="hidden" v-model="form.id"/>
            <el-input v-model="form.voucherName" auto-complete="off" size="small"></el-input>
        </el-form-item>
        <el-form-item label="奖券介绍" :label-width="formLabelWidth" prop="voucherDsc">
            <el-input v-model="form.voucherDsc" auto-complete="off"></el-input>
        </el-form-item>
        <el-form-item label="兑换值" :label-width="formLabelWidth" prop="voucherVal">
            <el-input type="number" v-model="form.voucherVal" auto-complete="off"></el-input>
        </el-form-item>
         <el-form-item label="奖券类型" :label-width="formLabelWidth">
            <el-select v-model="form.voucherType" placeholder="请选择类型">
                <el-option label="单次翻倍" value="0"></el-option>
                <el-option label="项目翻倍" value="1"></el-option>
                <el-option label="期间翻倍" value="2"></el-option>
                <el-option label="随机红包" value="3"></el-option>
            </el-select>
        </el-form-item>

        <el-form-item label="开始" :label-width="formLabelWidth">
               <el-date-picker  v-model="form.startTime" type="date"  placeholder="开始日期" value-format="yyyy-MM-dd HH:mm:ss"> </el-date-picker>
        </el-form-item>
        <el-form-item label="结束" :label-width="formLabelWidth">
               <el-date-picker  v-model="form.endTime" type="date"  placeholder="结束日期" value-format="yyyy-MM-dd HH:mm:ss"> </el-date-picker>
        </el-form-item>
        <el-form-item label="状态" :label-width="formLabelWidth">
            <el-select v-model="form.voucherStatus" placeholder="请选择状态">
                <el-option label="不可用" value="0"></el-option>
                <el-option label="可用" value="1"></el-option>
            </el-select>
        </el-form-item>
        <el-form-item label="绑定任务" :label-width="formLabelWidth">
            <el-select v-model="bountySel" multiple placeholder="请选择">
            <el-option
                v-for="item in bountyList"
                :key="item.value"
                :label="item.text"
                :value="item.value">
            </el-option>
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
import {getVoucherList,addVoucher,updateVoucher,delVoucher,dropBounty} from '@/api'
import MomentPlugin from '@/libs/MomentPlugin'
export default {
  name: 'vouchermanage',
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
                 voucherName:'',
                 startDate:'',
                 endDate:'',
                 voucherStatus:'',
                 status:[
                     {value: '', label: '所有状态'},
                     {value: '0', label: '不可用'},
                     {value: '1', label: '可用'}
                 ]
             },
             multipleSelection: [],
             bountyList:[],
             bountySel:'',
             form:{
                 id:'',
                 voucherName:'',
                 voucherDsc:'',
                 voucherVal:'0',
                 voucherType:'0',
                 voucherStatus:'0',
                 startTime:'',
                 endTime:'',
                 bountyIds:''

             },
             rules:{
                voucherName:[
                     { required: true, message: '请输入奖券名称', trigger: 'blur' },
                     {  max:30, message: '长度限制为30个字符', trigger: 'blur' }
                    ],
                voucherDsc:[
                     { required: true, message: '请输入奖券描述', trigger: 'blur' },
                     {  max:50, message: '长度限制为50个字符', trigger: 'blur' }
                    ],
                startTime:[
                     { required: true, message: '时间不能为空', trigger: 'blur' }
                    ],
                endTime:[
                     { required: true, message: '时间不能为空', trigger: 'blur' }
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
     self.loadBountyDrop();
  },
  methods:{
     //分页数据加载
     loadData: function(criteria, pageNum, pagesize){
          console.log("load");
        var self=this;
        var params={page:pageNum,size:pagesize,search:""};
        if(criteria!=null){
            params.search=JSON.stringify({voucherName:criteria.voucherName,voucherStatus:criteria.voucherStatus,startDate:criteria.startDate,endDate:criteria.endDate});
        }
        getVoucherList(params).then(result => {
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
        self.form={id:'',voucherName:'',voucherDsc:'',voucherVal:'0',voucherType:'0',voucherStatus:'0',startTime:'',endTime:''};
        self.bountySel=[];
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
       //时间格式
      dateFormat:function(row, column) {
           var self=this;
          var date = row[column.property];
          if (date == undefined) {
             return "";
          }
          return MomentPlugin(date).format("YYYY-MM-DD HH:mm:ss");
     },
     //加载下拉
     loadBountyDrop(){
        //获取任务列表
        var self=this;
        dropBounty({}).then(result=>{
            self.bountyList=result.data;
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
        detail.voucherName=row.voucherName;
        detail.voucherDsc=row.voucherDsc;
        detail.voucherVal=row.voucherVal;
        detail.voucherType=row.voucherType.toString();
        detail.voucherStatus=row.voucherStatus.toString();
        detail.startTime=row.startTime;
        detail.endTime=row.endTime;
        if(row.bountyIds.length>0){
            self.bountySel=row.bountyIds.split(",");
        }

        self.dialogFormVisible=true;
     },
     //删除
     handleDelete: function(index, row) {
          console.log("d:",index, row);
          var self=this;
          if(!confirm("你确定要删除吗？"))return;
          delVoucher({id:row.id}).then(result => {
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
          console.log(self.bountySel);
             var selIds='';
             self.bountySel.forEach((item)=>{ selIds+=item+','; });
             selIds=selIds.length>0 ? selIds.substring(0,selIds.length-1):selIds;
             self.form.bountyIds=selIds;
             console.log('selIds',selIds);
            if(self.form.id==""){
                //add
                addVoucher(self.form).then(result => {
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
                updateVoucher(self.form).then(result => {
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
