<template>
    <div class="container text-right">
        <div class="row my-2 mb-4">
            <div class="col-1 order-2 ml-auto"><label>: دانشجو</label></div>
            <div class="col-11 mx-auto">
                <a-select class="w-50" @change="onChange">
                    <a-select-option value="1">
                        احسان کریمی - 95243048
                    </a-select-option>
                </a-select>
            </div>
        </div>

        <a-table :columns="cols" :data-source="visible?list:empty" dir="rtl" :pagination="false">
            <template slot="state" slot-scope="text,record,index">
                <a-tooltip placement="top">
                    <template slot="title">
                        <span>دانلود</span>
                    </template>
                    <a-button type="dashed" icon="download" shape="circle"></a-button>
                </a-tooltip>
                <a-tooltip placement="top">
                    <template slot="title">
                        <span>نمره دهی</span>
                    </template>
                    <a-button icon="form" shape="circle" @click="op=true"></a-button>
                </a-tooltip>
            </template>
            <template slot="index" slot-scope="text,record,index">
                {{index + 1}}
            </template>
        </a-table>
        <a-modal :visible="op" class="text-right" :closable="false" title="گزارش فاز 1" ok-text="ثبت نظر" @ok="onGrade" cancel-text="انصراف" @cancel="op=false">
            <p>نمره </p>
            <a-input v-model="score"  class="text-right"></a-input>
            <br><br>
            <p>نظرات </p>
            <a-input v-model="comment" class="text-right"></a-input>
        </a-modal>
    </div>
</template>

<script>
    export default {
        name: "judge",
        middleware:"teaAuth",
        data(){
            return{
                visible:false,
                op:false,
                score:'',
                comment:'',
                empty:[],
                list:[{
                    name:'گزارش فاز 1' ,
                    date:'1399/4/12',
                    title:'سامانه مدیریت بانکی' ,
                    score:'-',
                    comment:'-'
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
                        title:' عنوان گزارش' ,
                        align:'center'
                    },
                    {
                        title: 'تاریخ ثبت گزارش',
                        dataIndex: 'date',
                        key: 'date',
                        align:'center',
                    },
                    {
                        title: 'عنوان پروژه',
                        dataIndex: 'title',
                        align:'center'
                    },
                    {
                        title: 'نمره شما',
                        dataIndex: 'score',
                        align:'center'
                    },
                    {
                        title: 'نظر شما',
                        dataIndex: 'comment',
                        width:'25%',
                        align:'center'
                    },
                    {
                        title: 'عملیات',
                        scopedSlots: { customRender: 'state' },
                        align:'center',
                        width:'10%'
                    }
                ]
            }
        },
        mounted(){
            this.$store.commit('setPage','گزارشات داوری')
            this.$store.commit('setActiveKey',['3'])
        },
        methods:{
            openNotificationWithIcon(type , msg , desc) {
                this.$notification[type]({
                    message: msg,
                    description: desc,
                });
            },
            onChange(){
                this.visible=true
            },
            onGrade(){
                this.openNotificationWithIcon('success','نمره شما با مووفقیت ذخیره شد')
                setTimeout(()=>{
                    this.op = false
                    this.list[0].score = this.score;
                    this.list[0].comment = this.comment;
                },700)
            }
        }
    }
</script>

<style>

</style>