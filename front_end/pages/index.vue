<template>
  <div>
    <div>
      <div class="overlay"><div class="mask"></div></div>
        <div class="text-center justify-content-center w-50 p-5" style="height: 100vh">
          <h3 class="text-white m-5 p-5" >
             به سامانه پروپوزال دانشجویی خوش آمدید
          </h3>
          <br><br>
          <nuxt-link to="/login">
            <button class="btn btn-block btn-warning text-center text-white w-75 mx-auto" >! وارد شوید </button>
          </nuxt-link>
      </div>
    </div>
  </div>
</template>

<script>

const Cookie = process.client ? require('js-cookie') : undefined

export default {
  middleware:'notAuth',
  layout:'notAuth',
  components: {
  },
    data(){
      return{

      }
    },
    mounted() {


    },

    methods:{
        logout () {
            Cookie.remove('auth')
            this.$store.commit('setAuth', null)
        }
    }
}
</script>

<style scoped>
.overlay {
  background: url('/images/bg.jpg') no-repeat center center fixed;
  z-index: -1;
  -webkit-background-size: cover;
  -moz-background-size: cover;
  -o-background-size: cover;
  background-size: cover;
  position: fixed;
  top: 0; bottom: 0; left: 0; right: 0;
  }
  .mask{
    background-color: rgba(0,0,0,0.4);
    z-index: 0;
    position: absolute;
    top: 0; bottom: 0; left: 0; right: 0;
  }


</style>
