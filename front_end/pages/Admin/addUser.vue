<template>
    <div class="container text-right">
        <h5 class="text-secondary my-2">افزودن کاربر</h5>
        <a-form :form="form" :colon="false" @submit="handleSubmit" class="w-75 mx-auto text-right">
            <div class="row">
                <div class="col-md order-md-2">
                    <a-form-item label="نام">
                        <a-input
                                class="text-right"
                                v-decorator="['firstName', { rules: [{ required: true, message: 'لطفا نام را وارد کنید' }] }]"
                        />
                    </a-form-item>
                </div>
                <div class="col-md">
                    <a-form-item label="نام خانوادگی">
                        <a-input
                                class="text-right"
                                v-decorator="['lastName', { rules: [{ required: true, message: 'لطفا نام خانوادگی را وارد کنید' }] }]"
                        />
                    </a-form-item>
                </div>
            </div>
            <div class="row">
                <div class="col-md">
                    <a-form-item label="شماره دانشجویی یا کد پرسنلی">
                        <a-input
                                class="text-right"
                                @keypress="pp"
                                v-decorator="['pid', { rules:[
                                { required: true, message: 'لطفا کد پرسنلی را وارد کنید' },
                                {len:8 , message:'کد پرسنلی عددی 8 رقمی میباشد'}
                                ]}]"
                        />
                    </a-form-item>
                </div>
            </div>
            <div class="row">
                <div class="col-md">
                    <a-form-item label="نقش">
                        <a-select
                                class="mysel"
                                v-decorator="['role',{ rules: [
                                { required: true, message: 'لطفا نقش را انتخاب کنید' }] },
                                 ]"
                                placeholder="نقش کاربر را انتخاب کنید"
                        >
                            <a-select-option value="STUDENT">
                                دانشجو
                            </a-select-option>
                            <a-select-option value="TEACHER">
                                استاد
                            </a-select-option>
                            <a-select-option value="MANAGER">
                                مدیر
                            </a-select-option>
                        </a-select>
                    </a-form-item>
                </div>
            </div>
            <div class="row">
                <div class="col-md order-md-2">
                    <a-form-item label="نام کاربری">
                        <a-input
                                class="text-right"
                                v-decorator="['username', { rules: [{ required: true, message: 'لطفا نام کاربری را وارد کنید' }] }]"
                        />
                    </a-form-item>
                </div>
                <div class="col-md">
                    <a-form-item label="کلمه عبور">
                        <a-input-password
                                class="text-right"
                                v-decorator="['password', { rules: [
                                { required: true, message: 'لطفا کلمه عبور را وارد کنید' },
                                { min: 4, message: 'لکلممه عبور حداقل 4 کاراکتر باید باشد' }
                                ] }]"
                        />

                    </a-form-item>
                </div>
            </div>
            <div class="row">
                <div class="col-md">
                    <a-form-item label="رشته">
                        <a-select
                                class="mysel"
                                v-decorator="['field',{ rules: [
                                { required: true, message: 'لطفا رشته را انتخاب کنید' }] },
                                 ]"
                                placeholder="رشته یا گروه آموزشی را انتخاب کنید"
                        >
                            <a-select-option value="1">
                                هوش مصنوعی
                            </a-select-option>
                            <a-select-option value="2">
                                نرم افزار
                            </a-select-option>
                            <a-select-option value="3">
                                شبکه
                            </a-select-option>
                            <a-select-option value="4">
                                معماری
                            </a-select-option>
                        </a-select>
                    </a-form-item>
                </div>
            </div>

            <a-form-item>
                <a-button icon="check" class="bg-warning text-white border-dark" html-type="submit">
                    ثبت
                </a-button>
            </a-form-item>
        </a-form>
    </div>
</template>

<script>
    export default {
        middleware:"adminAuth",
        name: "addUser",
        data(){
            return{
                form: this.$form.createForm(this, { name: 'addUser' }),
            }
        },
        methods:{
            pp(e){
                console.log(e)
                var keyCode = e.which;
                console.log(keyCode)
                if ( !(keyCode >= 48 && keyCode <= 57)
                    && keyCode != 8 && keyCode != 32) {
                    e.preventDefault();
                }
            },
            openNotificationWithIcon(type , msg , desc) {
                this.$notification[type]({
                    message: msg,
                    description: desc,
                });
            },
            handleSubmit(e) {
                e.preventDefault();
                this.form.validateFields((err, values) => {
                    if (!err) {
                        let pass = values.password
                        let un = values.username
                        let name = values.firstName + values.lastName
                        let pid = values.pid
                        let role = values.role
                        let field = values.field
                        let token = this.$store.state.auth.accessToken
                        this.$axios
                            .post(this.$store.state.env.baseUrl+'/user/add',{
                                username : un,
                                password :pass,
                                role :role,
                                name :name,
                                field :field,
                                id_number:pid
                            } ,
                                {
                                    headers: {
                                        ms_proposal_token:token
                                    }
                            })
                            .then(response => {
                                this.status = response.data.success
                                if (!response.data.success)
                                    this.openNotificationWithIcon('error' , response.data.error , '')
                                else{
                                    this.openNotificationWithIcon('success' , ' با موفقیت ثبت شد' + un , '')
                                    this.form.resetFields();
                                }

                            })
                            .catch(error => {
                                console.log(error)
                                this.openNotificationWithIcon('info' , 'request to server Error' , error)
                            })
                    }
                });
            },
        }
    }
</script>

<style>
    .mysel .ant-select-selection__rendered , .ant-select-search__field , .ant-select-selection-selected-value {
        transform: rotateY(180deg);
    }
    .mysel .ant-select-selection__placeholder{
        transform: rotateY(180deg);
        text-align: right;
        padding-right: 15px;
    }
    .mysel .ant-select-search__field{
        padding-right: 15px ;
    }
    .mysel .ant-select-dropdown-menu{
        text-align: right;
    }
</style>