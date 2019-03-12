<template>
<div class="conwarp">
<div class="grid">
    <div class="toolbar">
        <el-button type="success"  icon="el-icon-plus" @click="addItem()" >添加</el-button>
        <el-button type="primary"  @click="saveBanner" >保存</el-button>
    </div>
    <div class="gmain">
            <el-table v-loading="loading" :data="tableData"    tooltip-effect="dark"  stripe style="width: 100%">
             <el-table-column  prop="id"   label="ID" ></el-table-column>
             <el-table-column  prop="refId"   label="关联ID" >
                <template slot-scope="scope">
                    <el-input v-model="scope.row.refId" placeholder="请输入内容" style="width:120px;"></el-input>
                </template>
             </el-table-column>
            <el-table-column  prop="url"   label="banner图片"  >
                <template slot-scope="scope">
                    <img :src="scope.row.url" width="60%"/>
                </template>
            </el-table-column>
            <el-table-column  prop="bountyName"  label="上传" >
                <template slot-scope="scope">
                     <input type="file"   v-bind:id="scope.row.id" name="file"  @change="getFile($event)" />
                     <el-button size="small" type="success" @click="upload(scope.$index, scope.row)"  >上传</el-button>
                </template>
            </el-table-column>
        </el-table>
    </div>
</div>
</div>
</template>

<script>
import {getBussLableByName,bannerBussLable,uploadFile,getBussBanner} from '@/api'

export default {
  name: 'banner',
  data () {
      return{
             tableData:[
             ],
             //分页-数量
             pagesize: 10,
             //分页-页码
             currentPage: 1,
             //分页-总数
             totalCount: 100,
             uploadAction:'',
             //搜索条件
             criteria: {
            
             },
             multipleSelection: [],
             record:[],
             form:{
                 id:'',
                 bussName:'',
                 bussVal:''
             },
             rules:{
                bussName:[ 
                     { required: true, message: '请输入名称', trigger: 'blur' },
                     {  max:30, message: '长度限制为30 个字符', trigger: 'blur' }
                    ]
             },
             dialogFormVisible: false,
             dialogWidth:'400px',
             formLabelWidth: '80px',
             dialogImageUrl: '',
             dialogVisible: false,
             loading: false
      }
  },
  mounted(){
     var self=this;
     self.loadData();
  },
  methods:{
    addItem(){
        var self=this;
        self.tableData.push({id:(self.tableData.length + 1),refId:'',url:''});
     },
     delItem(idx,row){
        var self=this;
        var ary=[];
        for(var i=0;i< self.tableData.length;i++){
            if(i!=idx){
                ary.push(self.tableData[i]);
            }
        }
        console.log(ary);
        self.tableData=ary;
     },
     loadData: function(criteria, pageNum, pagesize){ 
        console.log("load");
        var self=this;
         getBussBanner({}).then(result => {
             var data=result.data;
            if(result.code=="200"){
                self.tableData=data;
            }                    
        });
     },
    //编辑
     handleEdit: function(index, row) {
        console.log("e:",index, row);
        var self=this;
        var detail=self.form;
        detail.id=row.id;
        detail.bussName=row.bussName;
        detail.bussVal=row.bussVal;
        self.dialogFormVisible=true;
     },
     //保存广告
     saveBanner : function(){
        
        let tableDataList = this.tableData;
        let resultStr = '';
        // tableDataList.forEach(p=>{
        //     if(p.refId != null && p.refId != '' && p.url != null && p.url != ''){
        //         if(resultStr.length == 0)(
        //             resultStr += p.url + '|' + p.refId
        //         )
        //         else{
        //             resultStr += ',' + p.url + '|' + p.refId
        //         }
        //     }
        // });
        console.log(tableDataList)
        for (let i = 0;i < tableDataList.length; i++ ){
            let p = tableDataList[i];
            if(p.refId != null && p.refId != '' && p.url != null && p.url != ''){
                if(resultStr.length == 0)(
                    resultStr += p.url + '|' + p.refId
                )
                else{
                    resultStr += ',' + p.url + '|' + p.refId
                }
            }else{
                this.$message({message:"请输入关联id或上传图片！",type: 'warning',duration:800});
                return false;
            }
        }
        this.loading = true;
        var param={bannerStr:resultStr};
        bannerBussLable(param).then(result => {
            this.loading = false;
                console.log("add:",result);
                if(result.code!="200"){
                    this.$message.error(result.message);
                    return;
                }
                else{
                   this.$message({message: '保存成功',type: 'success'}); 
                }
            });
     },
     //保存表单
    //  formSave(formName){
    //     var self=this;
    //     self.$refs[formName].validate((valid) => {
    //         console.log("valid:",valid);
    //         if(!valid)return;
    //         //add
    //         bannerBussLable(self.form).then(result => {
    //             console.log("add:",result);
    //             if(result.code!="200"){
    //                 alert(result.message);
    //                 return;
    //             }                             
    //         });
           
    //    });
    //   },
      insertItem(){

      },
       handleAvatarSuccess(res, file) {
        this.imageUrl = URL.createObjectURL(file.raw);
      },
      handleRemove(file, fileList) {
        console.log(file, fileList);
      },
      handlePictureCardPreview(file) {
        this.dialogImageUrl = file.url;
        this.dialogVisible = true;
      },
      getFile(event) {
          console.log("file change!");
          var self=this;
          self.record[event.target.id]=event.target.files[0];
          console.log(self.record[event.target.id]);
      },
      upload(idx,row){
         var self=this;
         let formData = new FormData();
         formData.append("file",self.record[row.id]);
         uploadFile({formData:formData,callback:function(result){
            console.log("uploadFileCB",result);
             if(result.code!="200"){
                 console.log()
                alert("err:"+result.message);
                return;
             }        
             row.url=result.data.url;
         }});
          // row.url='http://52.221.182.1/group1/M00/00/02/rB8JB1qBlVmAer2WAAGbMPxkwyA448.png';
      }
  }
}
</script>
