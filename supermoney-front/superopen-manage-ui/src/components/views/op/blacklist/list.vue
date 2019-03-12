<template>
<div class="conwarp">
<!-- grid -->
<div class="grid">
    <div class="searchbar">
        <el-row>
            <el-col :span="4"><el-input  placeholder="请输入调用记录ID"   class="w18"  prefix-icon="el-icon-search" v-model="criteria.callRecordId" ></el-input> </el-col>
            <el-col :span="4"><el-input  placeholder="请输入姓名"   class="w18"  prefix-icon="el-icon-search" v-model="criteria.name" ></el-input> </el-col>
            <el-col :span="4"><el-input  placeholder="请输入身份证号"   class="w18"  prefix-icon="el-icon-search" v-model="criteria.idNumber" ></el-input> </el-col>
            <el-col :span="4"><el-input  placeholder="请输入电话号码"   class="w18"  prefix-icon="el-icon-search" v-model="criteria.phoneNumber" ></el-input> </el-col>
            <el-col :span="4">  
                <el-select v-model="criteria.recommendation">
                    <el-option v-for="item in recommendationList" :key="item.value"  :label="item.label" :value="item.value"> </el-option>
                </el-select>
            </el-col>
            <el-col :span="4"><el-button type="primary" icon="el-icon-search" @click="search">搜索</el-button></el-col>
        </el-row>       
    </div>
    <div class="gmain">
        <el-table :data="tableData"  ref="multipleTable"  tooltip-effect="dark"  @select-all="handleSelectionChange"  @selection-change="handleSelectionChange"  stripe style="width: 100%">
            <el-table-column type="selection" width="55"></el-table-column>
            <el-table-column  prop="callRecordId"   label="调用记录ID"></el-table-column>
            <el-table-column  prop="name"   label="姓名"></el-table-column>
            <el-table-column  prop="idNumber"   label="身份证号" ></el-table-column>
            <el-table-column  prop="phoneNumber"   label="电话号码"></el-table-column>
            <el-table-column  prop="recommendation"   label="参数">
                <template scope="scope">
                    <span v-if="scope.row.recommendation=='PASS'">未命中</span>
                    <span v-if="scope.row.recommendation=='NEEDS_VERIFICATION'">电话命中</span>
                    <span v-if="scope.row.recommendation=='REJECT'">组合命中</span>
                </template>
            </el-table-column>
            <el-table-column  prop="eventTime"   label="时间"></el-table-column>
            <el-table-column  prop="hitReason"   label="资料"></el-table-column>
            <el-table-column  prop="productType"   label="数据源类型" ></el-table-column>
            <el-table-column  prop="reasonCode"   label="原因"></el-table-column>
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

</div>
</template>

<script>
import {opBlacklistList} from '@/api'
import MomentPlugin from '@/libs/MomentPlugin'
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
                 callRecordId:'',
                 name:'',
                 idNumber: '',
                 phoneNumber: '',
                 recommendation:'',
             },
             recommendationList:[
                {value: '', label: '请选择'},
                {value: 'PASS', label: '未命中'},
                {value: 'NEEDS_VERIFICATION', label: '电话命中'},
                {value: 'REJECT', label: '组合命中'},
             ],
             multipleSelection: [],
             dialogWidth:'600px',
             formLabelWidth:'80px',
             dialogInterfaceFormVisible:false,
      }
  },
  mounted(){
     var self=this;
     self.reload();
     this.$refs.multipleTable.multipleSelection;
  },
  methods:{
     //分页数据加载
     loadData: function(criteria, pageNum, pagesize){ 
          console.log("load");
        var self=this;
        var params={page:pageNum,size:pagesize,search:""};

        if(criteria!=null){
            params.search=JSON.stringify({
                 callRecordId:self.criteria.callRecordId,
                 name:self.criteria.name,
                 idNumber:self.criteria.idNumber,
                 phoneNumber:self.criteria.phoneNumber,
                 recommendation:self.criteria.recommendation,
            });    
        }
        else{
            params.search=JSON.stringify({});
        }
        opBlacklistList(params).then(result => {
                var data=result.data;
                self.pagesize=data.pageSize;
                self.totalCount=data.total;
                self.tableData=data.list;
        });
     },
     reload:function(){
         var self=this;
          self.loadData(self.criteria,self.currentPage,self.pagesize);
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
     handleSelectionChange(checkData) {
         console.log(checkData);
        var self=this;
        var np=[]
        checkData.forEach(p=>{ np.push(p.id) });
        self.checkIds=np;
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
      //查询
      search:function(){
          var self=this;
          this.loadData(self.criteria, this.currentPage, this.pagesize);
      },
      close:function(){
         var self=this;
         self.restForm();
      },
  }
}
</script>