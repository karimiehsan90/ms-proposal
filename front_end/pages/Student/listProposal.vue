<template>
    <div class="container text-right">
        <a-table :columns="cols" :data-source="list" bordered dir="rtl" >
            <span slot="state" slot-scope="text , record">
                <a-tag style="cursor: pointer" color="green" @click="$message.success( 'پروپوزال شما با موفقیت تایید شده بدون مشکلی',5)">{{record.state}}</a-tag>
            </span>
        </a-table>
    </div>
</template>

<script>
    export default {
        name: "listProposal",
        middleware:"stdAuth",
        data(){
            return{
                list:[{
                    name:'سامانه آنلاین کنترل ترافیک' ,
                    date: '1399/4/2',
                    id:1,
                    group:'نرم افزار' ,
                    teacher:'حقیقی' ,
                    state:'تایید شده' ,
                    refree: '-'
                }],
                cols:[
                    {
                        title:'#',
                        width:'5%',
                        dataIndex:'id',
                        sorter:(a,b)=> a.id - b.id
                    },
                    {
                        dataIndex: 'name',
                        key: 'name',
                        title:'عنوان پروپوزال' ,
                        align:'center'
                    },
                    {
                        title: 'تاریخ',
                        dataIndex: 'date',
                        key: 'date',
                        align:'center',
                        sorter:()=> a.date > b.date
                    },
                    {
                        title: 'گروه',
                        key: 'group',
                        align:'center',
                        dataIndex: 'group',
                    },
                    {
                        title: 'استاد',
                        key: 'teacher',
                        dataIndex: 'teacher',
                        align:'center'
                    },
                    {
                        title: 'داور',
                        key: 'refree',
                        dataIndex: 'refree',
                        align:'center'
                    },
                    {
                        title: 'وضعیت',
                        key: 'state',
                        scopedSlots: { customRender: 'state' },
                        align:'center'
                    }
                ]
            }
        },
        mounted(){
            this.$store.commit('setPage','لیست پروپوزال')
            this.$store.commit('setActiveKey',['2'])
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