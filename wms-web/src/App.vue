<template>
  <div id="app">
     <router-view/>
  </div>
</template>

<script>

// import Index from "./components/Index"

export default {
  name: 'App',
  components: {},
  data(){
    return{
      user: JSON.parse(sessionStorage.getItem('CurUser'))
    }


  },

  watch: {
    '$store.state.menu':{
      handler(val,old){
        console.log(val,old);
        if (!old && this.user && this.user.no){
          this.$store.commit("setRouter",val)
        }
      },
      immediate:true
    },

  },
  mounted() {
    window.addEventListener('beforeunload', function (event) {
      // 在页面关闭前执行一些操作
      this.sendConfirmationToBackend
      // 不能阻止页面关闭，只能提供一个提示信息
      event.returnValue = '您确定要离开吗？';
    });

  },

  methods: {
    sendConfirmationToBackend() {
      try {
        const response =this.$axios.post(this.$httpUrl + '/user/logOut', this.user);
        console.log('后端响应:', response);
        // 在异步操作完成后执行关闭页面的操作
      } catch (error) {
        console.error('请求错误:', error);
      }
    }

  },
}
</script>

<style>
#app {
  /*font-family: Avenir, Helvetica, Arial, sans-serif;*/
  /*-webkit-font-smoothing: antialiased;*/
  /*-moz-osx-font-smoothing: grayscale;*/
  /*text-align: center;*/
  /*color: #2c3e50;*/
  height: 100%;
  
}
</style>
