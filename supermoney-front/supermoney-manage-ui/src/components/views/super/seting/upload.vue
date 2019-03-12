<template>
<div class="conwarp">
     <el-form  style="width:800px;">
        <el-form-item label="上传文件:" :label-width="formLabelWidth" style="text-algin:left;">
            <input type="file"   name="file" @change="getFile($event)" />
            <el-button type="success" @click="uploadFile">上传</el-button>
        </el-form-item>
         <el-form-item label="文件地址:" :label-width="formLabelWidth" >
            <el-input v-model="fileUrl" auto-complete="off"></el-input>
        </el-form-item>

    </el-form>
</div>
</template>

<script>
import {uploadFile,uploadFileUrl} from '@/api'
import axios from 'axios'

export default {
  name: 'upload-file',
  data () {
      return {
          file:'',
          fileUrl:'',
          formLabelWidth:'100px'
      }
  },
  mounted(){
  
  },
  methods:{
      getFile(event) {
         this.file = event.target.files[0];
         console.log(this.file);
      },
      getBoundary:function(){
        var BOUNDARYPREFIX = 'nbglme';
        var max = 9007199254740992;
        var dec = Math.random() * max;
        var hex = dec.toString(36);
        var boundary = BOUNDARYPREFIX + hex;
        return 'boundary=----'+boundary;
      },
      uploadFile:function(){
         event.preventDefault();
         var self=this;
         let formData = new FormData();
         formData.append("file",self.file);
         uploadFile({formData:formData,callback:function(result){
            console.log("uploadFile",result);
             if(result.code!="200"){
                alert(result.message);
                return;
             }        
             self.fileUrl=result.data.url;
        }});
      }
  }
}
</script>