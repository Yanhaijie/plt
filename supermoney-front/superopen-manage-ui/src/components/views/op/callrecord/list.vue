<template>
<div class="conwarp">
<!-- grid -->
<div class="grid">
    <div class="searchbar">
        <el-row>
            <el-col :span="4">  
                <el-select v-model="criteria.interfaceId">
                    <el-option v-for="item in interfaceList" :key="item.value"  :label="item.label" :value="item.value"> </el-option>
                </el-select>
            </el-col>
            <el-col :span="4"><el-input  placeholder="请输入商户ID"   class="w18"  prefix-icon="el-icon-search" v-model="criteria.merchantId" ></el-input> </el-col>

            <el-col :span="4">  
                <el-select v-model="criteria.requestStatus" placeholder="请求状态">
                    <el-option v-for="item in requestStatusList" :key="item.value"  :label="item.label" :value="item.value"> </el-option>
                 </el-select>
            </el-col>
            <el-col :span="4"><el-button type="primary" icon="el-icon-search" @click="search">搜索</el-button></el-col>
        </el-row>       
    </div>
    <div class="gmain">
        <el-table :data="tableData"  ref="multipleTable"  tooltip-effect="dark"  @select-all="handleSelectionChange"  @selection-change="handleSelectionChange"  stripe style="width: 100%">
            <el-table-column type="selection" width="55"></el-table-column>
            <el-table-column  prop="interfaceName"   label="接口名称" width="100"></el-table-column>
            <el-table-column  prop="merchantId"   label="商户ID" width="200"></el-table-column>
            <el-table-column  prop="paramJson"   label="参数"></el-table-column>
            <el-table-column  prop="respondJson"   label="返回值" ></el-table-column>
            <el-table-column  prop="resultId"   label="关联结果" width="80"></el-table-column>
            <el-table-column  prop="requestStatus"   label="请求状态" width="100">
                <template scope="scope">
                    <span v-if="scope.row.requestStatus==0">成功</span>
                    <span v-if="scope.row.requestStatus==1">失败</span>
                </template>
            </el-table-column>
            <!-- <el-table-column label="操作"  width="150" >
                 <template slot-scope="scope">
                    <el-button size="small" type="primary"  @click="handleEdit(scope.$index, scope.row)">查看详情</el-button>
                 </template>
            </el-table-column> -->
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
import {opCallRecordList,opInterfaceListAll} from '@/api'
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
                 interfaceId:'',
                 merchantId:'',
                 requestStatus: '',
             },
             interfaceList:[
             ],
             requestStatusList:[
                {value: '', label: '请选择'},
                {value: '0', label: '成功'},
                {value: '1', label: '失败'},
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
     this.loadInterfaceData();
  },
  methods:{
     //分页数据加载
     loadData: function(criteria, pageNum, pagesize){ 
          console.log("load");
        var self=this;
        var params={page:pageNum,size:pagesize,search:""};

        if(criteria!=null){
            params.search=JSON.stringify({
                 interfaceId:self.criteria.interfaceId,
                 merchantId:self.criteria.merchantId,
                 requestStatus:self.criteria.requestStatus,
            });    
        }
        else{
            params.search=JSON.stringify({});
        }
        opCallRecordList(params).then(result => {
                var data=result.data;
                self.pagesize=data.pageSize;
                self.totalCount=data.total;
                self.tableData=data.list;
        });
     },
     //获取接口列表
      loadInterfaceData: function(){ 
        opInterfaceListAll({size:100}).then(result => {
            if(result.code == 200){
                var list = [];
                list.push({'label':'请选择','value':''});
                result.data.forEach(p=>{ 
                    list.push({'label':p.label,'value':p.value});
                });
                console.log('list :' + list);
                this.interfaceList = list;
            }
            else{
                this.$message.error('接口列表获取失败！');
            }
            
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