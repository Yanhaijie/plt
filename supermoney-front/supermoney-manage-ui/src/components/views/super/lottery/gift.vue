<template>
<div class="conwarp">
<!-- grid -->
<div class="grid">
    <div class="toolbar">
          <el-button type="primary"  icon="el-icon-plus" @click="dialogGiftFormVisible=true" >添加</el-button>
    </div>
    <!-- <div class="searchbar">
        <el-row>
            <el-col :span="3"><el-input  placeholder="请输入礼物名"   class="w18"  prefix-icon="el-icon-search" v-model="criteria.giftName" ></el-input> </el-col>

            <el-col :span="4">  
                <el-select v-model="criteria.giftId" placeholder="礼物">
                    <el-option v-for="item in criteria.giftList" :key="item.value"  :label="item.label" :value="item.value"> </el-option>
                 </el-select>
            </el-col>

            <el-col :span="4">  
                <el-select v-model="criteria.isSent" placeholder="是否发放">
                    <el-option v-for="item in criteria.isSentList" :key="item.value"  :label="item.label" :value="item.value"> </el-option>
                 </el-select>
            </el-col>
            <el-col :span="2.5"><el-button type="primary" icon="el-icon-search" @click="search">搜索</el-button></el-col>
        </el-row>
    </div> -->
    <div class="gmain">
        <el-table :data="tableData"  ref="multipleTable"  tooltip-effect="dark"  @select-all="handleSelectionChange"  @selection-change="handleSelectionChange"  stripe style="width: 100%">
            <el-table-column type="selection" width="55"></el-table-column>
            <el-table-column  prop="giftName"   label="礼物名" ></el-table-column>
            <el-table-column  prop="giftCount"   label="单次抽奖数量" ></el-table-column>
            <el-table-column  prop="probability"   label="概率" ></el-table-column>
            <el-table-column  prop="useWayName"   label="使用范围" ></el-table-column>
            <el-table-column  prop="remainCount"   label="礼物剩余总数"></el-table-column>
            <el-table-column  prop="maxCount"   label="礼物总数" ></el-table-column>
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
<el-dialog title="添加礼物" :visible.sync="dialogGiftFormVisible" @close="close" :width="dialogWidth" class="demo-ruleForm">
    <el-form :model="giftFrom" ref="giftFrom" :rules="rules" label-width="80px">
        <el-form-item label="礼物名称" prop="giftName">
            <el-input v-model="giftFrom.giftName"></el-input>
        </el-form-item>
        <el-form-item label="中奖概率" prop="probability">
            <el-input v-model="giftFrom.probability" placeholder="请输入100以内的小数"></el-input>
        </el-form-item>
        <el-form-item label="礼物图片" :label-width="formLabelWidth" prop="giftImg">
            <img :src="giftFrom.giftImg" width="20%" alt="front" />
            <input type="file"  name="file" @change="getFile($event)" />
        </el-form-item>
        <el-form-item label="礼物描述" prop="giftDesc">
            <el-input v-model="giftFrom.giftDesc"></el-input>
        </el-form-item>
        <el-form-item label="选择使用范围" prop="useWay">
            <el-select v-model="giftFrom.useWay">
                    <el-option v-for="item in useWayList" :key="item.value"  :label="item.label" :value="item.value"> </el-option>
            </el-select>
        </el-form-item>
        <el-form-item label="选择礼物类型" prop="giftType">
            <el-select v-model="giftFrom.giftType">
                    <el-option v-for="item in giftTypeList" :key="item.value"  :label="item.label" :value="item.value"> </el-option>
            </el-select>
        </el-form-item>
        <el-form-item label="单次发放数量" prop="giftCount">
            <el-input v-model="giftFrom.giftCount"></el-input>
        </el-form-item>
        <el-form-item label="剩余数量" prop="remainCount">
            <el-input v-model="giftFrom.remainCount"></el-input>
        </el-form-item>
        <el-form-item label="发放总量" prop="maxCount">
            <el-input v-model="giftFrom.maxCount"></el-input>
        </el-form-item>
    </el-form>
    <div slot="footer" class="dialog-footer">
        <el-button @click="close()">取 消</el-button>
        <el-button type="primary" @click="saveGift()">保 存</el-button>
    </div>
</el-dialog>

</div>
</template>

<script>
import {saveGift,getAllGiftList,uploadFile,deleteGift} from '@/api'
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
             },
             giftFrom:{
                 id:'',
                 giftName:'',
                 probability:'',
                 giftImg:'',
                 giftDesc:'',
                 useWay:'',
                 giftType:'',
                 giftCount:'',
                 remainCount:'',
                 maxCount:'',
             },
             useWayList:[
                {value: '', label: '所有类型'},
                {value: '0', label: '移动端抽奖'},
                {value: '1', label: '后台抽奖'},
                {value: '2', label: '下架'},
            ],
            giftTypeList:[
                {value: '', label: '所有类型'},
                {value: '0', label: '金币'},
                {value: '1', label: '话费'},
                {value: '2', label: '实体礼物'},
            ],
             rules:{
                giftName: [{ required: true, message: '请输入礼物名称', trigger: 'blur' },{max: 30, message: '长度在30个字符以内', trigger: 'blur' }],
                probability: [{ required: true, message: '请输入中奖概率', trigger: 'blur'}],
                giftImg: [{ required: true, message: '请选择礼物图片', trigger: 'blur'}],
                giftDesc:[{ max: 100, message: '长度在100字符以内', trigger: 'blur'}],
                useWay: [{ required: true, message: '请选择使用范围', trigger: 'blur'}],
                giftType: [{ required: true, message: '请选择礼物类型', trigger: 'blur'}],
                giftCount: [{ required: true, message: '请输入单次发放数量', trigger: 'blur'}],
                remainCount: [{ required: true, message: '请输入剩余数量', trigger: 'blur'}],
                maxCount: [{ required: true, message: '请输入礼物总量', trigger: 'blur'}],
             },
             multipleSelection: [],
             checkIds:[],
             dialogWidth:'600px',
             formLabelWidth:'80px',
             dialogGiftFormVisible:false
             
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
        
        // var startDate = '';
        // var endDate = '';
        // if(criteria != null && criteria.startDate != ''&& criteria.startDate != null){
        //     startDate = MomentPlugin(criteria.startDate).format("YYYY-MM-DD HH:mm:ss");
        // }
        // if(criteria != null && criteria.endDate != '' && criteria.endDate != nul){
        //     endDate = MomentPlugin(criteria.endDate).format("YYYY-MM-DD HH:mm:ss");
        // }
        
        if(criteria!=null){
            params.search=JSON.stringify({userName:criteria.userName,giftId:criteria.giftId,isSent:criteria.isSent});    
        }
        getAllGiftList(params).then(result => {
                var data=result.data;
                self.pagesize=data.pageSize;
                self.totalCount=data.total;
                self.tableData=data.list;
        });
     },
     //添加礼物
     saveGift:function(){
        var self=this;
       //表单验证
       self.$refs['giftFrom'].validate((valid) => {
           if(valid){
                var params={id:self.giftFrom.id,giftName:self.giftFrom.giftName,probability:self.giftFrom.probability,giftImg:self.giftFrom.giftImg,
                giftDesc:self.giftFrom.giftDesc,useWay:self.giftFrom.useWay,giftType:self.giftFrom.giftType,
                giftCount:self.giftFrom.giftCount,remainCount:self.giftFrom.remainCount,maxCount:self.giftFrom.maxCount};
                saveGift(params).then(result => {
                    // console.log("cashCheck:",result);
                    if(result.code!="200"){
                        this.$message.error(result.message);
                        return;
                    }                 
                    self.dialogGiftFormVisible=false; 
                    self.restCheckForm();
                    self.reload();
                });
           }
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
     },
     close:function(){
         var self=this;
         self.dialogGiftFormVisible=false;
         self.restCheckForm();
         
     },
     //重置表单
     restCheckForm:function(){
        var self=this;
        if (self.$refs['giftFrom']!==undefined) {
            self.$refs['giftFrom'].resetFields();
            self.giftFrom = {
                 id:'',
                 giftName:'',
                 probability:'',
                 giftImg:'',
                 giftDesc:'',
                 useWay:'',
                 giftType:'',
                 giftCount:'',
                 remainCount:'',
                 maxCount:''
             };
        }
        self.dialogGiftFormVisible=false;
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
      getFile(event) {
         var file = event.target.files[0];
         if(file != undefined){
            this.uploadFile(file);
         }         
     },
     uploadFile(file){
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
            self.giftFrom.giftImg=result.data.url;
        }});
      },
      //编辑
     handleEdit: function(index, row) {
        console.log("e:",index, row);
        var self=this;
        var detail=self.giftFrom;
        detail.id=row.id;
        detail.giftName=row.giftName;
        detail.probability=row.probability;
        detail.giftImg=row.giftImg;
        detail.giftDesc=row.giftDesc;
        detail.useWay=row.useWay + '';
        detail.giftType=row.giftType + '';
        detail.giftCount=row.giftCount;
        detail.remainCount=row.remainCount;
        detail.maxCount=row.maxCount;
        self.dialogGiftFormVisible=true;
     },
     //删除
     handleDelete: function(index, row) {
        console.log("d:",index, row);
        var self=this;

        this.$confirm('此操作将永久删除该文件, 是否继续?', '提示', {
          confirmButtonText: '确定',
          cancelButtonText: '取消',
          type: 'warning'
        }).then(() => {
          deleteGift({id:row.id}).then(result => {
              console.log('del:',result);
             if(result.code!="200"){
                this.$message.error(result.message);
                return;
             }
             self.reload();
             this.$message({type: 'success',message: '删除成功!'});
          });
          
        }).catch(() => {
        }); 
     },
  }
}
</script>