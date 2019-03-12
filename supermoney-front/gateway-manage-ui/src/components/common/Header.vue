<template>
    <div class="header">
        {{msg}}
    </div>
</template>
<script>
export default {
  name: 'Header',
  data () {
    return {
    msg:"header content"
    }
  },
  computed:{
     showInfo(){

     }
  },
  methods:{
        

  }
}
</script>

<style>
.el-header{
    background-color: #1f262d !important;
    padding:0 !important;
    margin:0 !important;
     height:auto  !important;
   
}
.header{
 height:40px ;
    border-left: 2px solid #000000 !important;
}
  
</style>