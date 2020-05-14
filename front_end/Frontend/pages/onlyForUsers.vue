<template>
    <div class="container">
      u see this only if you login .
      <p>your token is : {{token}}</p>
      <NuxtLink to="/">
        Back home
      </NuxtLink>
    </div>
</template>

<script>
    const Cookie = process.client ? require('js-cookie') : undefined
    export default {
        name: "onlyForUsers",
        middleware:"auth",
        data(){
            return{
                token:'NO_TOKEN'
            }
        },
        mounted() {
           // this.token = Cookie.get('auth')
            this.token = this.$store.state.auth.accessToken
        }
    }
</script>

<style scoped>

</style>
