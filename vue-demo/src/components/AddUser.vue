<template>
  <div>
    <button @click="addUser">请求addUser接口</button>
  </div>
</template>


<script>
  import {post} from '../server.js'

  export default {
    name:'AddUser',
    data:{

    },
    methods:{
      addUser(){
        let url = '/springbootTest/swagger';
        let data = '';
        post(url, data).then( res => {
          console.log(res)
        }).catch( res => {
          console.log(res)
        })
      }
    }
  }
</script>
