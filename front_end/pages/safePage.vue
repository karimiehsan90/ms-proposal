<template>
    <div class="container">
      THIS IS SAFE PAGE !
      <p>your token is : {{token}}</p>
      <p>your role is  : {{role}}</p>
    </div>
</template>

<script>
    const Cookie = process.client ? require('js-cookie') : undefined
    export default {
        name: "onlyForUsers",
        middleware:"auth",
        data(){
            return{
                token:'NO_TOKEN',
                role:'NO_ROLE'
            }
        },
        mounted() {
           // this.token = Cookie.get('auth')
            this.token = this.$store.state.auth.accessToken
            this.role = this.$store.state.auth.role
        }
    }
</script>

<style scoped>

</style>
