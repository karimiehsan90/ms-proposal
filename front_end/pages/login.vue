<template>
  <div class="d-flex" style="height: 100vh">
    <div class="overlay"><div class="mask"></div></div>
    <div class="container d-flex  align-items-center align-middle">
      <br>
        <div id="ll" class="container justify-content-center text-center rounded my-3 py-4">
      <a-form layout="inline" :form="form" class="clearfix  my-2" @submit="handleSubmit">
        <a-form-item>
          <a-input
                  @click="blu"
                  @blur="blu"
                  v-decorator="[
          'userName',
          { rules: [{ required: true, message: 'لطفا نام کاربری خود را وارد کنید ' }] },
        ]"
                  placeholder="نام کاربری"
          >
            <a-icon slot="prefix" type="user" style="color:rgba(0,0,0,.25)" />
          </a-input>
        </a-form-item>
        <a-form-item >
          <a-input
                  @click="blu"
                  @blur="blu"
                  v-decorator="[
          'password',
          { rules: [{ required: true, message: 'لطفا پسورد خود را وارد کنید ' }] },
        ]"
                  type="password"
                  placeholder="پسورد"
          >
            <a-icon slot="prefix" type="lock" style="color:rgba(0,0,0,.25)" />
          </a-input>
        </a-form-item>
        <a-form-item>
          <a-button type="primary" html-type="submit" >
            ورود
          </a-button>
        </a-form-item>
      </a-form>

      <div :class="status?'text-success':'text-danger'">
        {{test}}
      </div>
        </div>
    </div>
  </div>

</template>

<script>
    import Logo from '~/components/Logo.vue'
    import Button from 'ant-design-vue/lib/button'
    import Form from 'ant-design-vue/lib/form'
    import Input from 'ant-design-vue/lib/input'
    import Icon from 'ant-design-vue/lib/icon'


    const Cookie = process.client ? require('js-cookie') : undefined
    export default {
        middleware:'notAuth',
        components: {
            Logo , AButton: Button , AForm: Form , AFormItem: Form.Item , AInput: Input , AIcon: Icon
        },
        name:'login',
        data(){
            return{
                form: this.$form.createForm(this, { name: 'login' }),
                test:'',
                status:false,
            }
        },
        mounted() {
            $(document).ready(function () {
                console.log("Bia Karim inam az Jquery! :)")
            })

        },

        methods:{
            handleSubmit(e) {
                e.preventDefault();
                this.test = ''
                this.form.validateFields((err, values) => {
                    if (!err) {
                        // console.log('Karim Khan! Make Request to server pls ' , values.password);
                        let pass = values.password
                        let us = values.userName
                        this.$axios
                            .post('http://localhost:6060/auth/login', null, { params: {
                                    username : us,
                                    password :pass
                                }})
                            .then(response => {
                                this.status = response.data.success
                                if (!response.data.success)
                                    this.test = response.data.error
                                else{
                                    const auth = {
                                        accessToken: response.data.data.token,
                                        role: response.data.data.role
                                    }
                                    this.$store.commit('setAuth', auth) // mutating to store for client rendering
                                    Cookie.set('auth', auth) // saving token in cookie for server rendering
                                    this.$router.push('/')
                                }

                            })
                            .catch(error => {
                                console.log(error)
                            })
                    }
                });
            },
            blu(e){
                this.test = '';
            },
        }
    }
</script>

<style scoped>


  .overlay {
    background: url('/images/rq_prop.jpg') no-repeat center center fixed;
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
   #ll{
      background: linear-gradient( rgba(0,0,0,0.5) , rgba(255,255,255,0.2) , rgba(0,0,0,0.3));
       align-items: center;
       display: flex;
       vertical-align: center;
       height: 200px;
    }
</style>
