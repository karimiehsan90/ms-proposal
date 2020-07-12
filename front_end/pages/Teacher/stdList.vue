<template>
    <div class="container text-right">
        <div class="row">
            <div class="col">
                <div class="mb-4 mt-2">
                    <a-radio-group class="w-100" v-model="mode" button-style="solid">
                        <a-radio-button value="b" class="w-50 text-center">
                            داور
                        </a-radio-button>
                        <a-radio-button value="a" class="w-50 text-center">
                            استاد راهنما
                        </a-radio-button>
                    </a-radio-group>
                </div>
            </div>
        </div>
        <a-table :columns="cols" :data-source="mode==='a'?list1:list2" dir="rtl" :pagination="false">
            <template slot="state" slot-scope="text">
                <a-tag color="green" v-if="text===1">تاییده شده</a-tag>
            </template>
            <template slot="index" slot-scope="text,record,index">
                {{index + 1}}
            </template>
            <template slot="email" slot-scope="text">
                <a-button type="link" >{{text}}</a-button>
            </template>
        </a-table>
    </div>
</template>

<script>
    export default {
        name: "stdList",
        middleware:"teaAuth",
        data(){
            return{
                visible:false,
                mode:'a',
                list1:[{
                    name:'علی صدقی' ,
                    id:'95243034',
                    date:'1399/4/12',
                    title:'سامانه کنترل ترافیک' ,
                    email:'alisdg1376@gmail.com',
                    state:1,
                }],
                list2:[{
                    name:'احسان کریمی' ,
                    id:'95243048',
                    date:'1399/4/12',
                    title:'سامانه مدیریت بانکی' ,
                    email:'karimiehsan90@gmail.com',
                    state:1,
                }],
                cols:[
                    {
                        key:'index',
                        width:'5%',
                        align:'center',
                        title:'#',
                        scopedSlots: { customRender: 'index' },
                    },
                    {
                        dataIndex: 'name',
                        key: 'name',
                        title:'نام دانشجو' ,
                        align:'center'
                    },
                    {
                        title:'شماره دانشجویی',
                        width:'15%',
                        dataIndex:'id',
                        align:'center'
                    },
                    {
                        title: 'ایمیل دانشجو',
                        key: 'email',
                        align:'center',
                        scopedSlots: { customRender: 'email' },
                        dataIndex: 'email',
                    },
                    {
                        title: 'عنوان پروژه',
                        dataIndex: 'title',
                        align:'center'
                    },
                    {
                        title: 'تاریخ ثبت',
                        dataIndex: 'date',
                        key: 'date',
                        align:'center',
                    },
                    {
                        title: 'وضعیت',
                        dataIndex: 'state',
                        scopedSlots: { customRender: 'state' },
                        align:'center'
                    }
                ]
            }
        },
        mounted(){
            this.$store.commit('setPage','لیست دانشجویان')
            this.$store.commit('setActiveKey',['1'])
        },
        methods:{
            openNotificationWithIcon(type , msg , desc) {
                this.$notification[type]({
                    message: msg,
                    description: desc,
                });
            },
    }
    }
</script>

<style>

</style>