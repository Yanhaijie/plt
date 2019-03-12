<template>
<div class="conwarp">
<!-- grid -->
<div class="grid">
    <div class="toolbar">
        <el-button type="primary"  icon="el-icon-plus" @click="dialogFormVisible=true" >添加</el-button>
        <!--<el-button type="danger"  icon="el-icon-close">刪除</el-button>-->
    </div>
    <div class="searchbar">
        <el-form :inline="true">
            <el-form-item>
                <el-input  placeholder="请输入名称"   class="w18"  prefix-icon="el-icon-search" v-model="criteria.bountyName" ></el-input> 
            </el-form-item>
            <el-form-item>
                <el-date-picker  v-model="criteria.startDate" type="date"  placeholder="开始日期"> </el-date-picker>
            </el-form-item>
            <el-form-item>
                <el-date-picker  v-model="criteria.endDate" type="date"  placeholder="结束日期"> </el-date-picker>
            </el-form-item>
            <el-form-item>  
                <el-select v-model="criteria.bountyStatus" placeholder="任务状态">
                    <el-option v-for="item in criteria.status" :key="item.value"  :label="item.label" :value="item.value"> </el-option>
                 </el-select>
            </el-form-item>
            <el-form-item><el-button type="primary" icon="el-icon-search" @click="search">搜索</el-button></el-form-item>
        </el-form>
        
        

    </div>
    <div class="gmain">
            <el-table :data="tableData"  ref="multipleTable"  tooltip-effect="dark"  @selection-change="handleSelectionChange" stripe style="width: 100%">
            <el-table-column type="selection" width="55"></el-table-column>
             <el-table-column  prop="id"   label="赏金ID" ></el-table-column>
            <el-table-column  prop="bountyName"   label="赏金任务名称" ></el-table-column>
            <el-table-column  prop="bountyMoney"   label="任务赏金" ></el-table-column>
            <el-table-column  prop="bountyDsc"   label="任务说明"  ></el-table-column>
            <el-table-column  prop="bountyStatus"   label="任务状态" >
                <template slot-scope="scope">
                     <span v-if="scope.row.bountyStatus==0"> 待发布</span>
                     <span v-if="scope.row.bountyStatus==1" style="color:green;" >发布</span>
                     <span v-if="scope.row.bountyStatus==2" style="color:red;" >未启用</span>                   
                </template>
            </el-table-column>
            <el-table-column  prop="adsUrl"   label="广告地址"  ></el-table-column>
            <el-table-column label="操作"  min-width="200" align="center"> 
                 <template slot-scope="scope">
                    <el-button size="mini" type="primary"  @click="handleEdit(scope.$index, scope.row)">编辑</el-button>
                    <el-button size="mini" type="success"  @click="loanMg(scope.$index, scope.row)">产品</el-button>
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
<el-dialog title="项目信息" :visible.sync="dialogFormVisible" @close="close" :width="dialogWidth">
    <el-form :model="form" :rules="rules" ref="form">
        <el-tabs type="border-card">
            <el-tab-pane label="任务信息">
                <el-form-item label="发布状态" :label-width="formLabelWidth">
                    <el-select v-model="form.bountyStatus" placeholder="请选择发布状态">
                        <el-option label="待发布" value="0"></el-option>
                        <el-option label="发布" value="1"></el-option>
                        <el-option label="停止" value="2"></el-option>
                    </el-select>
                </el-form-item>
                <el-form-item label="任务名称" :label-width="formLabelWidth" prop="bountyName">
                    <input type="hidden" v-model="form.id"/>
                    <el-input v-model="form.bountyName" auto-complete="off" size="small"></el-input>
                </el-form-item>
                <el-form-item label="评分" :label-width="formLabelWidth" prop="bountyScore">
                    <el-input type="number" v-model="form.bountyScore" auto-complete="off"></el-input>
                </el-form-item>
                <el-form-item label="任务赏金" :label-width="formLabelWidth" prop="bountyMoney">
                    <el-input type="number" v-model="form.bountyMoney" auto-complete="off"></el-input>
                </el-form-item>
                <el-form-item label="每天赏金" :label-width="formLabelWidth" prop="dayMoney">
                    <el-input type="number" v-model="form.dayMoney" auto-complete="off"></el-input>
                </el-form-item>
                <el-form-item label="Colt说明" :label-width="formLabelWidth" prop="bountyCompany">
                    <el-input v-model="form.bountyCompany" auto-complete="off"></el-input>
                </el-form-item>
                <el-form-item label="赏金说明" :label-width="formLabelWidth" prop="bountyDsc">
                    <el-input v-model="form.bountyDsc" auto-complete="off"></el-input>
                </el-form-item>
                 <el-form-item label="项目说明" :label-width="formLabelWidth" prop="projectDsc">
                    <el-input v-model="form.projectDsc" auto-complete="off"></el-input>
                </el-form-item>
            </el-tab-pane>
            <el-tab-pane label="广告信息">
                 <el-form-item label="广告类型" :label-width="formLabelWidth">
                    <el-select v-model="form.adsType" placeholder="请选广告类型">
                        <el-option label="普通" value="0"></el-option>
                        <el-option label="贷款" value="1"></el-option>
                    </el-select>
                </el-form-item>
                <el-form-item label="APP排序" :label-width="formLabelWidth" prop="frontSort">
                      <el-input type="number" v-model="form.frontSort" auto-complete="off"></el-input>
                </el-form-item>
                 <el-form-item label="广告图标" :label-width="formLabelWidth" prop="adsIco">
                      <img :src="form.adsIco" width="20%" alt="front" />
                      <input type="file"  name="file" @change="getFile($event,1)" />
                </el-form-item>
                <el-form-item label="广告图片" :label-width="formLabelWidth" prop="adsPic">
                    <img :src="form.adsPic" width="20%" alt="front" />
                    <input type="file"  name="file" @change="getFile($event,2)" />
                </el-form-item>
                <el-form-item label="广告ID" :label-width="formLabelWidth" prop="adsId">
                    <el-input  v-model="form.adsId" auto-complete="off"></el-input>
                </el-form-item>
                <el-form-item label="广告地址" :label-width="formLabelWidth" prop="adsUrl">
                    <el-input v-model="form.adsUrl" auto-complete="off">
                    </el-input>
                </el-form-item>
                <el-form-item label="回掉地址" :label-width="formLabelWidth" prop="adsCallbackUrl">
                    <el-input v-model="form.adsCallbackUrl" auto-complete="off"></el-input>
                </el-form-item>
            </el-tab-pane>
            <el-tab-pane label="借贷信息">
                <el-form-item label="额度" :label-width="formLabelWidth" >
                    <el-col :span="10">
                        <el-input type="number" class="w8" v-model="form.loanMinMoney" auto-complete="off"></el-input>
                    </el-col>
                    <el-col :span="2">-</el-col>
                    <el-col :span="10">
                        <el-input type="number" class="w8" v-model="form.loanMaxMoney" auto-complete="off"></el-input>
                    </el-col>
                </el-form-item>
                <el-form-item label="利率" :label-width="formLabelWidth" >
                    <el-col :span="10">
                        <el-input type="number" class="w8" v-model="form.loanMinRate" auto-complete="off"></el-input>
                    </el-col>
                    <el-col :span="2">-</el-col>
                    <el-col :span="10">
                        <el-input type="number" class="w8" v-model="form.loanMaxRate" auto-complete="off"></el-input>
                    </el-col>
                </el-form-item>
                <el-form-item label="借款期限" :label-width="formLabelWidth" >
                    <el-col :span="6">
                        <el-input type="number" class="w8" v-model="form.loanLimit" auto-complete="off"></el-input>
                    </el-col>
                    <el-col :span="2">-</el-col>
                    <el-col :span="6">
                        <el-input type="number" class="w8" v-model="form.loanLimitMax" auto-complete="off"></el-input>
                    </el-col>
                     <el-col :span="2">/</el-col>
                    <el-col :span="6">
                    <el-select v-model="form.loanLimitUnit"  placeholder="单位">
                        <el-option v-for="item in selone" :key="item.value"  :label="item.label" :value="item.value"> </el-option>            
                    </el-select>
                    </el-col>
                </el-form-item>
                <el-form-item label="放款期限" :label-width="formLabelWidth" prop="lendersLimit">
                    <el-col :span="8">
                        <el-input type="number" class="w8" v-model="form.lendersLimit" auto-complete="off"></el-input>
                    </el-col>
                        <el-col :span="2"> /</el-col>
                    <el-col :span="10">
                        <el-select v-model="form.lendersLimitUnit"  placeholder="单位">
                            <el-option v-for="item in seltwo" :key="item.value"  :label="item.label" :value="item.value"> </el-option>
                        </el-select>
                    </el-col>
                </el-form-item>
            </el-tab-pane>
            <el-tab-pane label="借贷介绍">
                <el-form-item label="借款简介" :label-width="formLabelWidth" prop="projectDsc">
                    <el-input v-model="form.loanSimpleDsc" auto-complete="off"></el-input>
                </el-form-item>
                <el-form-item label="">
                     <el-button type="success" @click="getDetailTmp" >初始化模板</el-button>
                </el-form-item>
                 <el-form-item label="借款详情" :label-width="formLabelWidth" >
                    <textarea v-model="form.loanDsc"  style="width:100%; height:260px;"></textarea>
                </el-form-item>
            </el-tab-pane>
        </el-tabs>
    </el-form>
    <div slot="footer" class="dialog-footer">
        <el-button @click="dialogFormVisible=false">取 消</el-button>
        <el-button type="primary" @click="formSave('form')">确 定</el-button>
    </div>

</el-dialog>
<!--loan -->
<el-dialog title="借贷产品" :visible.sync="dialogLoanVisible"  :width="dialogLoanWidth">
    <div class="toolbar" style="text-align:left;">
         <el-button type="primary"  icon="el-icon-plus" @click="dialogLoanProductVisible=true" >添加</el-button>
     </div>
     
     <div class="gmain">
            <el-table :data="loanData"   tooltip-effect="dark"  stripe style="width: 100%">
            <el-table-column  prop="loanLimit"   label="借款期限" ></el-table-column>
            <el-table-column  prop="loanRate"   label="借款利率" ></el-table-column>
            <el-table-column  prop="feeMoeny"   label="固定手续费" ></el-table-column>
            <el-table-column  prop="feeRate"   label="手续费利率"  ></el-table-column>
            <el-table-column   prop="isDeductFee"   label="先扣手续费" >
                <template slot-scope="scope">
                     <span v-if="scope.row.isDeductFee==0" style="color:green;" >否</span>
                     <span v-if="scope.row.isDeductFee==1" style="color:red;" >是</span>                   
                </template>
            </el-table-column>
            <el-table-column  prop="isDeductInterest"  label="先扣利息" >
                <template slot-scope="scope">
                     <span v-if="scope.row.isDeductInterest==0" style="color:green;" >否</span>
                     <span v-if="scope.row.isDeductInterest==1" style="color:red;" >是</span>                   
                </template>
            </el-table-column>
            <el-table-column  prop="bountyLoanStatus"  label="状态" >
                <template slot-scope="scope">
                     <span v-if="scope.row.bountyLoanStatus==0" style="color:green;" >不启用</span>
                     <span v-if="scope.row.bountyLoanStatus==1" style="color:red;" >启用</span>                   
                </template>
            </el-table-column>
            <el-table-column label="操作" min-width="120"> 
                 <template slot-scope="scope">
                    <el-button size="mini" type="primary"  @click="handleLoanEdit(scope.$index, scope.row)" style="width:40px; padding:4px 2px;">编辑</el-button>
                    <el-button size="mini" type="danger"   @click="handleLoanDelete(scope.$index, scope.row)"  style="width:40px;  padding:4px 2px;" >删除</el-button>
                 </template>
            </el-table-column>
        </el-table>
     </div>
</el-dialog>
<!--loan product -->
<el-dialog title="产品详情" :visible.sync="dialogLoanProductVisible"  :before-close="closeLoan" :width="dialogLoanProductWidth">
 <el-form :model="formLoan" :rules="rulesLoan" ref="formLoan">
       <el-form-item label="状态" :label-width="formLoanLabelWidth">
                <input type="hidden" v-model="formLoan.id"/>
                <input type="hidden" v-model="formLoan.bountyId"/>
                <el-select v-model="formLoan.bountyLoanStatus" placeholder="请选状态">
                    <el-option label="不启用" value="0"></el-option>
                    <el-option label="启用" value="1"></el-option>
                </el-select>
        </el-form-item>
         <el-form-item label="固定手续费" :label-width="formLoanLabelWidth" >
                <el-input type="number" v-model="formLoan.feeMoeny" auto-complete="off"></el-input>
        </el-form-item>
          <el-form-item label="借款期限" :label-width="formLoanLabelWidth" >
                <el-input type="number" v-model="formLoan.loanLimit" auto-complete="off"></el-input>
        </el-form-item>
        <el-form-item label="借款利率" :label-width="formLoanLabelWidth" >
                <el-col :span="8">
                    <el-input type="number" class="w8" v-model="formLoan.loanRate" auto-complete="off"></el-input>
                </el-col>
                <el-col :span="2">/ </el-col>
                <el-col :span="10">
                <el-select v-model="formLoan.loanRateUnit"  placeholder="单位">
                    <el-option v-for="item in selthree" :key="item.value"  :label="item.label" :value="item.value"> </el-option>            
                </el-select>
                </el-col>
        </el-form-item>
        <el-form-item label="手续费利率" :label-width="formLoanLabelWidth" >
                <el-col :span="8">
                    <el-input type="number" class="w8" v-model="formLoan.feeRate" auto-complete="off"></el-input>
                </el-col>
                <el-col :span="2">/ </el-col>
                <el-col :span="10">
                <el-select v-model="formLoan.feeRateUnit"  placeholder="单位">
                    <el-option v-for="item in selthree" :key="item.value"  :label="item.label" :value="item.value"> </el-option>            
                </el-select>
                </el-col>
        </el-form-item>
        <el-form-item label="先扣除手续费" :label-width="formLoanLabelWidth">
                <el-select v-model="formLoan.isDeductFee" placeholder="请选择">
                    <el-option label="否" value="0"></el-option>
                    <el-option label="是" value="1"></el-option>
                </el-select>
        </el-form-item>
         <el-form-item label="先扣利息" :label-width="formLoanLabelWidth">
              <el-select v-model="formLoan.isDeductInterest" placeholder="请选择">
                    <el-option label="否" value="0"></el-option>
                    <el-option label="是" value="1"></el-option>
              </el-select>
        </el-form-item>
 </el-form>
 <div slot="footer" class="dialog-footer">
    <el-button @click="closeLoan">取 消</el-button>
    <el-button type="primary" @click="formLoanSave('formLoan')">确 定</el-button>
</div>

</el-dialog>

</div>
</template>

<script>
import {getBountyList,addBounty,updateBounty,delBounty,getBussLableByName,getBountyLoan,addBountyLoan,updateBountyLoan,deleteBountyLoan,uploadFile} from '@/api'

export default {
  name: 'appmanage',
  data () {
      return{
             tableData:[],
             loanData:[],
             //分页-数量
             pagesize: 10,
             //分页-页码
             currentPage: 1,
             //分页-总数
             totalCount: 100,
             //搜索条件
             criteria: {
                 bountyName:'',
                 startDate:'',
                 endDate:'',
                 bountyStatus:'',
                 status:[
                     {value: '', label: '所有状态'},
                     {value: '0', label: '待发布'},
                     {value: '1', label: '发布'},
                     {value: '2', label: '停止'}
                 ]
             },
             multipleSelection: [],
             selone:[
                {value: '0', label: 'Month'},
                {value: '1', label: 'Day'},
                {value: '2', label: 'Hour'}
             ],
             seltwo:[
                {value: '0', label: 'Month'},
                {value: '1', label: 'Day'},
                {value: '2', label: 'Hour'}
             ],
             selthree:[
                {value: '0', label: 'MonthRate'},
                {value: '1', label: 'DayRate'}
             ],
             form:{
                id:'',
                bountyName:'',
                bountyMoney:'0',
                dayMoney:'0',
                bountyScore:'0',
                bountyDsc:'',
                adsUrl:'',
                adsCallbackUrl:'',
                bountyStatus:'0',
                adsType:'0',
				adsPic:'',
				adsIco:'',
				adsId:'',
				loanMinMoney:'0',
				loanMaxMoney:'0',
				loanMinRate:'0',
				loanMaxRate:'0',
				loanLimit:'0',
                loanLimitMax:'0',
				loanLimitUnit:'0',
                lendersLimit:'0',
				lendersLimitUnit:'0',
                lendersDsc:'',
                limitDsc:'',
                bountyCompany:'',
                projectDsc:'',
                loanDsc:'',
                loanSimpleDsc:'',
                frontSort:''
             },
             loan:{
                 

             },
             rules:{
                adsUrl:[
                     { required: true, message: '请输入广告地址', trigger: 'blur' }
                    ],
                bountyame:[ 
                     { required: true, message: '请输入名称', trigger: 'blur' },
                     {  max:30, message: '长度限制为25个字符', trigger: 'blur' }
                    ],
                bountyMoney:[ 
                    { required: true, message: '请输入赏金', trigger: 'blur' }
                    ]
             },
             dialogFormVisible: false,
             dialogWidth:'600px',
             formLabelWidth: '80px',
             formLoanLabelWidth:'100px',
             dialogLoanVisible:false,
             dialogLoanWidth:'840px',
             dialogLoanProductVisible:false,
             dialogLoanProductWidth:'420px',
             formLoan:{
                    id:'',
                    bountyId:'',
                    loanRate:'',
                    loanRateUnit:'0',
                    feeMoeny:'',
                    feeRate:'',
                    feeRateUnit:'0',
                    isDeductFee:'0',
                    isDeductInterest:'0',
                    bountyLoanStatus:'0',
                    loanLimit:''
             },
             rulesLoan:{

             },
             curBountyId:'0',
             file:''
             
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
            params.search=JSON.stringify({bountyName:criteria.bountyName,bountyStatus:criteria.bountyStatus,startDate:criteria.startDate,endDate:criteria.endDate});    
        }
        getBountyList(params).then(result => {
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
                bountyName:'',
                bountyMoney:'0',
                dayMoney:'0',
                bountyScore:'0',
                bountyDsc:'',
                adsUrl:'',
                adsCallbackUrl:'',
                bountyStatus:'0',
                adsType:'0',
				adsPic:'',
				adsIco:'',
				adsId:'',
				loanMinMoney:'0',
				loanMaxMoney:'0',
				loanMinRate:'0',
				loanMaxRate:'0',
				loanLimit:'0',
                loanLimitMax:'0',
				loanLimitUnit:'0',
                lendersLimit:'0',
				lendersLimitUnit:'0',
                lendersDsc:'',
                limitDsc:'',
                bountyCompany:'',
                projectDsc:'',
                loanDsc:'',
                loanSimpleDsc:'',
                frontSort:''
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
    //编辑
     handleEdit: function(index, row) {
        console.log("e:",index, row);
		var self=this;
		var detail=self.form;
		detail.id=row.id;
		detail.bountyName=row.bountyName;
		detail.bountyMoney=row.bountyMoney;
		detail.bountyDsc=row.bountyDsc;
		detail.adsUrl=row.adsUrl;
		detail.adsCallbackUrl=row.adsCallbackUrl;
		detail.bountyStatus=row.bountyStatus.toString();
		detail.adsType=row.adsType.toString();
		detail.adsPic=row.adsPic;
		detail.adsIco=row.adsIco;
		detail.adsId=row.adsId;
		detail.loanMinMoney=row.loanMinMoney;
		detail.loanMaxMoney=row.loanMaxMoney;
		detail.loanMinRate=row.loanMinRate;
		detail.loanMaxRate=row.loanMaxRate;
		detail.loanLimit=row.loanLimit;
        detail.loanLimitMax=row.loanLimitMax;        
		detail.loanLimitUnit=row.loanLimitUnit==null? "":row.loanLimitUnit.toString();
        detail.lendersLimit=row.lendersLimit;
        detail.lendersLimitUnit=row.lendersLimitUnit==null? "":row.lendersLimitUnit.toString();
        detail.dayMoney=row.dayMoney;
        detail.bountyScore=row.bountyScore;
        detail.limitDsc=row.limitDsc;
        detail.lendersDsc=row.lendersDsc;
        detail.bountyCompany=row.bountyCompany;
        detail.projectDsc=row.projectDsc;
        detail.loanDsc=row.loanDsc;
        detail.loanSimpleDsc=row.loanSimpleDsc;  
        detail.frontSort=row.frontSort;      
		self.dialogFormVisible=true;
     },
     getDetailTmp:function(){
        var self=this;
          getBussLableByName({bussName:'loanDetail'}).then(result => {      
                     if(result.code!="200"){
                        alert(result.message);
                        return;
                     }          
                     self.form.loanDsc=result.data.bussVal;    
                });
     },
     //删除
     handleDelete: function(index, row) {
          console.log("d:",index, row);
          var self=this;
          if(!confirm("你确定要删除吗？"))return;
          delBounty({id:row.id}).then(result => {  
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
        console.log("formName");
        self.form.limitDsc=self.form.loanLimit+"/"+self.selLabel(self.selone,self.form.loanLimitUnit);
        self.form.lendersDsc=self.form.lendersLimit+"/"+self.selLabel(self.seltwo,self.form.lendersLimitUnit);
        console.log('save form:',self.form);
        self.$refs[formName].validate((valid) => {
            console.log("valid:",valid);
            if(!valid)return;
          
            if(self.form.id==""){
                //add
                addBounty(self.form).then(result => {
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
                updateBounty(self.form).then(result => {      
                     if(result.code!="200"){
                        alert(result.message);
                        return;
                     }              
                     self.reload();
                     self.restForm();
                     
                });
            }
       });
       

      },//产品管理
      loanMg: function(index, row) {
        var self=this;
        self.loadLoanData(row.id);
        self.dialogLoanVisible=true;
        self.curBountyId=row.id;
      },//加载任务产品
      loadLoanData:function(id){
          var self=this;
          getBountyLoan({bountyId:id}).then(result => {
                var data=result.data;
                self.loanData=data;
        });
      },//产品编辑
     handleLoanEdit: function(index, row) {
        var self=this;
		var detail=self.formLoan;
		detail.id=row.id;
		detail.bountyId=self.curBountyId;
		detail.loanRate=row.loanRate;
        detail.loanRateUnit=row.loanRateUnit==null? "":row.loanRateUnit.toString();
        detail.feeMoeny=row.feeMoeny;
		detail.feeRate=row.feeRate;
        detail.loanLimit=row.loanLimit;       
        detail.feeRateUnit=row.feeRateUnit==null? "":row.feeRateUnit.toString();
        detail.isDeductFee=row.isDeductFee.toString();
        detail.isDeductInterest=row.isDeductInterest.toString();
        detail.bountyLoanStatus=row.bountyLoanStatus.toString(); 
		self.dialogLoanProductVisible=true;

     },//产品删除
     handleLoanDelete: function(index, row) {
          console.log("d:",index, row);
          var self=this;
          if(!confirm("你确定要删除吗？"))return;
          deleteBountyLoan({id:row.id}).then(result => {  
              console.log('del:',result);
             if(result.code!="200"){
                alert(result.message);
                return;
             }        
             self.loadLoanData(self.curBountyId);  
          });
     },//产品保存
     formLoanSave:function(formName){
         var self=this;
          self.$refs[formName].validate((valid) => {
            console.log("valid:",valid);
            if(!valid)return;
          
            if(self.formLoan.id==""){
                //add 
                self.formLoan.bountyId= self.curBountyId;
                addBountyLoan(self.formLoan).then(result => {
                    console.log("add:",result);
                    if(result.code!="200"){
                        this.$message({message: result.message,type: 'warning',duration:800});
                        return;
                    }                  
                    self.loadLoanData(self.curBountyId);  
                    self.restLoanForm();
                   
                });
            }else{
                //update
                updateBountyLoan(self.formLoan).then(result => {      
                    if(result.code!="200"){
                        this.$message({message: result.message,type: 'warning',duration:800});
                        return;
                    }              
                    self.loadLoanData(self.curBountyId);  
                    self.restLoanForm();
                     
                });
            }
         });


     },
     closeLoan:function(){
         var self=this;
         self.restLoanForm();
        
     },
     restLoanForm:function(){
        var self=this;
        self.formLoan={
            id:'',
            bountyId:'',
            loanRate:'',
            loanRateUnit:'0',
            feeMoeny:'',
            feeRate:'',
            feeRateUnit:'0',
            isDeductFee:'0',
            isDeductInterest:'0',
            bountyLoanStatus:'0',
            loanLimit:''
        };
         self.dialogLoanProductVisible=false;
     },
     getFile(event,iconIndex) {
         var file = event.target.files[0];
         if(file != undefined){
            this.uploadFile(file,iconIndex);
         }         
     },
     uploadFile(file,iconIndex){
         event.preventDefault();
         var self=this;
         let formData = new FormData();
         formData.append("file",file);
         uploadFile({formData:formData,callback:function(result){
            console.log("uploadFile",result);
             if(result.code!="200"){
                alert(result.message);
                return;
             }        
             if(iconIndex == 1){
                self.form.adsIco=result.data.url;
             }
             else{
                self.form.adsPic=result.data.url;
             }
             
        }});
      }
  }
}
</script>