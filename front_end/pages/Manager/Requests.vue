<template>
    <div class="container text-right">
        <div class="row">
            <div class="col">
                <div class="mb-4 mt-2">
                    <a-radio-group class="w-100" v-model="mode" button-style="solid">
                        <a-radio-button value="c" class="text-center" style="width: 33%">
                            رد شده
                        </a-radio-button>
                        <a-radio-button value="b" class="text-center" style="width: 34%">
                            تایید شده
                        </a-radio-button>
                        <a-radio-button value="a" class="text-center" style="width: 33%">
                            جدید
                        </a-radio-button>
                    </a-radio-group>
                </div>
            </div>
        </div>
        <a-table v-if="mode==='a'" :columns="cols" :data-source="list1" dir="rtl" >
            <template slot="state" slot-scope="text,record,index">
                <a-button shape="circle" class="text-success " icon="check" @click="pass(index)"></a-button>
                <a-button shape="circle" class="text-danger " icon="close" @click="npass(index)"></a-button>
            </template>
            <template slot="index" slot-scope="text,record,index">
                {{index + 1}}
            </template>
            <template slot="download" slot-scope="text">
                <a-button type="dashed" icon="download">  دانلود  </a-button>
            </template>
        </a-table>
        <a-table v-if="mode!=='a'" :columns="cols" :data-source="mode==='b'?list2:list3" dir="rtl">
            <template slot="state" slot-scope="text,record,index">
                <a-button v-if="mode==='c'" shape="circle" class="text-success" icon="check" @click="pass2(index)"></a-button>
                <a-button v-if="mode==='b'" shape="circle" class="text-danger " icon="close" @click="npass2(index)"></a-button>
            </template>
            <template slot="index" slot-scope="text,record,index">
                {{index + 1}}
            </template>
            <template slot="download" slot-scope="text">
                <a-button type="dashed" icon="download">  دانلود  </a-button>
            </template>
        </a-table>
    </div>
</template>

<script>
    export default {
        name: "Requests",
        middleware:"manAuth",
        data(){
            return{
                visible:false,
                mode:'a',
                list1:[{
                    name:'علی صدقی' ,
                    id:'95243034',
                    date:'1399/4/12',
                    title:'سامانه کنترل ترافیک' ,
                    teacher:'حسن حقیقی' ,
                    state:1,
                }],
                list2:[{
                    name:'احسان کریمی' ,
                    id:'95243048',
                    date:'1399/4/12',
                    title:'سامانه مدیریت بانکی' ,
                    teacher:'علیرضا شاملی' ,
                    state:2,
                }],
                list3:[],
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
                        title: 'استاد راهنما',
                        key: 'teacher',
                        align:'center',
                        dataIndex: 'teacher',
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
                        title: 'فرم درخواست',
                        scopedSlots: { customRender: 'download' },
                        align:'center'
                    },
                    {
                        title: 'عملیات',
                        scopedSlots: { customRender: 'state' },
                        align:'center'
                    }
                ]
            }
        },
        mounted(){
            this.$store.commit('setPage','لیست درخواست ها')
            this.$store.commit('setActiveKey',['5'])
        },
        methods:{
            openNotificationWithIcon(type , msg , desc) {
                this.$notification[type]({
                    message: msg,
                    description: desc,
                });
            },
            pass(index){
               this.list2.push(
                   this.list1[index]
               )
                this.list1.splice(index,1)
            },
            pass2(index){
                this.list2.push(
                    this.list3[index]
                )
                this.list3.splice(index,1)
            },
            npass(index){
                this.list3.push(
                    this.list1[index]
                )
                this.list1.splice(index,1)
            },
            npass2(index){
                this.list3.push(
                    this.list2[index]
                )
                this.list2.splice(index,1)
            }
        }
    }
</script>

<style>

</style>