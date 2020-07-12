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
            <template slot="index" slot-scope="text,record,index">
                {{index + 1}}
            </template>
            <template slot="day" slot-scope="text">
                {{text}} - سه شنبه
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
                    date:'1399/4/12',
                    title:'سامانه کنترل ترافیک' ,
                    clock:'11:15',
                }],
                list2:[{
                    name:'احسان کریمی' ,
                    date:'1399/4/12',
                    clock:'10:30',
                    title:'سامانه مدیریت بانکی' ,
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
                        align:'center',
                        width: '20%'
                    },
                    {
                        title: 'عنوان پروژه',
                        dataIndex: 'title',
                        align:'center'
                    },
                    {
                        title: 'تاریخ دفاع',
                        dataIndex: 'date',
                        key: 'date',
                        scopedSlots: { customRender: 'day' },
                        align:'center',
                        width:'25%'

                    },
                    {
                        title: 'ساعت دفاع',
                        dataIndex: 'clock',
                        align:'center',
                        width:'15%'
                    }
                ]
            }
        },
        mounted(){
            this.$store.commit('setPage','برنامه دفاع')
            this.$store.commit('setActiveKey',['4'])
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