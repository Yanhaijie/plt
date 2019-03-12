<template>
<div class="conwarp">
<!-- grid -->
<div class="grid">
    <div class="searchbar">
        <el-row>
            <el-col :span="4"><el-input  placeholder="请输入证件号"   class="w18"  prefix-icon="el-icon-search" v-model="criteria.nik" ></el-input> </el-col>
            <el-col :span="4"><el-input  placeholder="请输入姓名"   class="w18"  prefix-icon="el-icon-search" v-model="criteria.name" ></el-input> </el-col>
            <el-col :span="4"><el-button type="primary" icon="el-icon-search" @click="search">搜索</el-button></el-col>
        </el-row>       
    </div>
    <div class="gmain">
        <el-table :data="tableData"  ref="multipleTable"  tooltip-effect="dark"  @select-all="handleSelectionChange"  @selection-change="handleSelectionChange"  stripe style="width: 100%">
            <el-table-column type="selection" width="55"></el-table-column>
            <el-table-column  prop="nik"   label="证件号"></el-table-column>
            <el-table-column  prop="name"   label="姓名"></el-table-column>
            <el-table-column  prop="bloodType"   label="血型" ></el-table-column>
            <el-table-column  prop="religion"   label="宗教"></el-table-column>
            <el-table-column  prop="gender"   label="性別"> </el-table-column>
            <el-table-column  prop="placeDateBirth"   label="出生地点和日期"></el-table-column>
            <el-table-column  prop="rtRw"   label="RT/RW"></el-table-column>
            <el-table-column  prop="occupation"   label="职业" ></el-table-column>
            <el-table-column  prop="expiryDate"   label="到期日期"></el-table-column>
            <el-table-column  prop="maritalStatus"   label="婚姻状况"></el-table-column>
            <el-table-column  prop="address"   label="地址"></el-table-column>
            <el-table-column  prop="nationality"   label="国籍"></el-table-column>
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
import {opOcrList} from '@/api'
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
                 nik:'',
                 name:'',
             },
             multipleSelection: [],
             dialogWidth:'600px',
             formLabelWidth:'80px',
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
                 nik:self.criteria.nik,
                 name:self.criteria.name,
            });    
        }
        else{
            params.search=JSON.stringify({});
        }
        opOcrList(params).then(result => {
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