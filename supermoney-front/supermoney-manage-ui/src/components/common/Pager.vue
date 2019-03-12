<template>
<!--<el-table :data="grid.data"  height="250"  style="width: 100%">
      <div v-for="item in grid.columns">
             <el-table-column prop="{{grid.columns.key}}" label="{{grid.columns.name}}" width="180"></el-table-column>
      </div>
        

</el-table>-->

</template>


<script>
export default {
    name: 'Pager',
    components:{


    },
    data () {
        return{
           grid:{
               pageUrl:"/sone/home/list"
               columns:[
                   {name:"名称",key:"productName",type:"string",grid:true,search:true,hidden:false},
                   {name:"类型",key:"productType",type:"string",grid:true,search:true,hidden:false},
                   {name:"价格",key:"price", type:"number",grid:true,search:false,hidden:false}
               ]，
               data:[


               ]
              
           }

        }
    }

}
</script>