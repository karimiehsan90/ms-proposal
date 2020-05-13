<template>
  <div class="container">
    <br>
    <a-form layout="inline" :form="form" class="clearfix  mt-5 mb-2" @submit="handleSubmit">
      <a-form-item>
        <a-input
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
          وارد شوید
        </a-button>
      </a-form-item>
    </a-form>
    <div :class="status?'text-success':'text-danger'">
      {{test}}
    </div>
  </div>
</template>

<script>
import Logo from '~/components/Logo.vue'
import Button from 'ant-design-vue/lib/button'
import Form from 'ant-design-vue/lib/form'
import Input from 'ant-design-vue/lib/input'
import Icon from 'ant-design-vue/lib/icon'

export default {
  components: {
    Logo , AButton: Button , AForm: Form , AFormItem: Form.Item , AInput: Input , AIcon: Icon
  },
    data(){
      return{
          form: this.$form.createForm(this, { name: 'login' }),
          test:'',
          status:false,
      }
    },
    mounted() {
      $(document).ready(function () {
         console.log("Bia Karim inam az Jquery! :) \nvali ba axios request mizanimaaa! hanooz addesh nakrdm ==> w8 yekam :)")
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
                            else
                                this.test = 'هوراااااا'
                            setTimeout(function(){ this.test = '' }, 3000);
                        })
                        .catch(error => {
                            console.log(error)
                        })
                }
            });
        },
    }
}
</script>

<style>
.container {
  margin: 0 auto;
  min-height: 100vh;
  justify-content: center;
  align-items: center;
  text-align: center;
}


</style>
