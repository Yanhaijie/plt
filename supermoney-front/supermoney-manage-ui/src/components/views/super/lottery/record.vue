<template>
<div class="conwarp">
<!-- grid -->
<div class="grid">
    <div class="searchbar">
        <el-form :inline="true">
            <el-form-item><el-input  placeholder="请输入用户ID"   class="w18"  prefix-icon="el-icon-search" v-model="criteria.userId" ></el-input> </el-form-item>
            <el-form-item><el-input  placeholder="请输入用户名"   class="w18"  prefix-icon="el-icon-search" v-model="criteria.userName" ></el-input> </el-form-item>
            <el-form-item>
                <el-date-picker  v-model="criteria.startDate" type="date"  placeholder="开始日期"> </el-date-picker>
            </el-form-item>
            <el-form-item>
                <el-date-picker  v-model="criteria.endDate" type="date"  placeholder="结束日期"> </el-date-picker>
            </el-form-item>

            <el-form-item>  
                <el-select v-model="criteria.giftId" placeholder="礼物">
                    <el-option v-for="item in criteria.giftList" :key="item.value"  :label="item.label" :value="item.value"> </el-option>
                 </el-select>
            </el-form-item>
            <el-form-item>  
                <el-select v-model="criteria.isWinning" placeholder="中奖结果">
                    <el-option v-for="item in criteria.isWinningList" :key="item.value"  :label="item.label" :value="item.value"> </el-option>
                 </el-select>
            </el-form-item>
            <el-form-item><el-button type="primary" icon="el-icon-search" @click="search">搜索</el-button></el-form-item>
        </el-form>
    </div>
    <div class="gmain">
            <el-table :data="tableData"  ref="multipleTable"  tooltip-effect="dark"  @select-all="handleSelectionChange"  @selection-change="handleSelectionChange"  stripe style="width: 100%">
            <el-table-column type="selection" width="55"></el-table-column>
            <el-table-column  prop="userId"   label="用户ID" ></el-table-column>
            <el-table-column  prop="holdingPhone"   label="用户名" ></el-table-column>
            <el-table-column  prop="isWinning"   label="中奖结果" ></el-table-column>
            <el-table-column  prop="giftName"   label="礼物名" ></el-table-column>
            <el-table-column  prop="giftCount"   label="礼物数量" ></el-table-column>
            <el-table-column  prop="createTime"   label="抽奖时间" :formatter="dateFormat"></el-table-column>
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
import {getLotteryRecordList,searchAllGiftList} from '@/api'
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
                 userName:'',
                 userId:'',
                 startDate:'',
                 endDate:'',
                 giftId:'',
                 isWinning:'',
                 isWinningList:[
                     {value: '', label: '所有状态'},
                     {value: '0', label: '未中奖'},
                     {value: '1', label: '中奖'},
                 ],
                 giftList:[]
             },
             multipleSelection: [],
             checkIds:[],
             dialogWidth:'400px',
             formLabelWidth:'80px'
    
             
      }
  },
  mounted(){
     var self=this;
     self.reload();
     this.$refs.multipleTable.multipleSelection;
     self.getGiftList();
  },
  methods:{
     //分页数据加载
     loadData: function(criteria, pageNum, pagesize){ 
          console.log("load");
        var self=this;
        var params={page:pageNum,size:pagesize,search:""};
        
        var startDate = '';
        var endDate = '';
        if(criteria != null && criteria.startDate != ''&& criteria.startDate != null){
            startDate = MomentPlugin(criteria.startDate).format("YYYY-MM-DD HH:mm:ss");
        }
        if(criteria != null && criteria.endDate != '' && criteria.endDate != null){
            endDate = MomentPlugin(criteria.endDate).format("YYYY-MM-DD HH:mm:ss");
        }
        
        if(criteria!=null){
            params.search=JSON.stringify({userId:criteria.userId,giftId:criteria.giftId,isWinning:criteria.isWinning,startDate:startDate,endDate:endDate,userName:criteria.userName});    
        }
        else{
            params.search=JSON.stringify({});
        }
        getLotteryRecordList(params).then(result => {
                var data=result.data;
                self.pagesize=data.pageSize;
                self.totalCount=data.total;
                self.tableData=data.list;
        });
     },
     getGiftList:function(){
        var self=this;
        searchAllGiftList({}).then(result => {
            self.criteria.giftList = [{value: '', label: '所有礼物'}].concat(result.data);
        
        });
     },
     reload:function(){
         var self=this;
          self.loadData(null,self.currentPage,self.pagesize);
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
          console.log("search");
          this.loadData(self.criteria, this.currentPage, this.pagesize);
      },
  }
}
</script>