<template>
    <div class="container text-right">
        <a-table :columns="cols" :data-source="list" bordered dir="rtl" >
            <span slot="comment" slot-scope="text , record" >
                <a-button type="link" @click="visible=true;showCom=text">دیدن</a-button>
            </span>
        </a-table>
        <a-drawer
                title="نظرات"
                placement="right"
                :closable="false"
                :visible="visible"
                @close="onClose"
        >
            <p>{{showCom}}</p>
        </a-drawer>
    </div>
</template>

<script>
    export default {
        name: "listReport",
        middleware:"stdAuth",
        data(){
            return{
                visible:false,
                showCom:'',
                list:[{
                    name:'گزارش فاز 1' ,
                    id:1,
                    date:'1399/4/12',
                    score:'15' ,
                    d_score:'13' ,
                    comment:'خوب بود.' ,
                    d_comment:'بد بود.' ,
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
                        title:'عنوان' ,
                        align:'center'
                    },
                    {
                        title: 'تاریخ',
                        dataIndex: 'date',
                        key: 'date',
                        align:'center',
                    },
                    {
                        title: 'نمره استاد',
                        key: 'score',
                        align:'center',
                        dataIndex: 'score',
                    },
                    {
                        title: 'نظرات استاد',
                        dataIndex: 'comment',
                        scopedSlots: { customRender: 'comment' },
                        align:'center'
                    },
                    {
                        title: 'نمره داور',
                        key: 'd_score',
                        dataIndex: 'd_score',
                        align:'center'
                    },
                    {
                        title: 'نظرات داور',
                        dataIndex: 'd_comment',
                        scopedSlots: { customRender: 'comment' },
                        align:'center'
                    }
                ]
            }
        },
        mounted(){
            this.$store.commit('setPage','لیست گزارشات')
            this.$store.commit('setActiveKey',['4'])
        },
        methods:{
            openNotificationWithIcon(type , msg , desc) {
                this.$notification[type]({
                    message: msg,
                    description: desc,
                });
            },
            onClose() {
                this.visible = false;
            },
        }
    }
</script>

<style>

</style>