<template>
  <div class="container">
    <br>
    <a-form layout="inline" :form="form" @submit="handleSubmit">
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
          form: this.$form.createForm(this, { name: 'login' })
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
            this.form.validateFields((err, values) => {
                if (!err) {
                    console.log('Karim Khan! Make Request to server pls ' , values);
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
  display: flex;
  justify-content: center;
  align-items: center;
  text-align: center;
}


</style>
