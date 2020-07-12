<template>
    <div class="container text-right">
        <h6 class="text-secondary mb-4"> لیست دانشجویانی که پروژه آنها تایید شده ولی داور شان هنوز مشخص نیست</h6>
        <a-table :columns="cols" :data-source="list1" dir="rtl" >
            <template slot="state" slot-scope="text,record,index">
                <a-button shape="circle"  icon="edit" @click="visible=true; temp=index"></a-button>
            </template>
            <template slot="index" slot-scope="text,record,index">
                {{index + 1}}
            </template>
        </a-table>
        <a-modal :visible="visible" class="text-right" :closable="false" title="تعیین داور و زمان دفاع" ok-text="تایید" @ok="onSub" cancel-text="انصراف" @cancel="visible=false">
            <p>انتخاب داور </p>
            <a-select
                    dropdownClassName="text-right"
                    class="text-right"
                    style="width: 100%">
                <a-select-option v-for="t in teachers" :value="t.id+'#'+t.group" :key="t.id">
                    {{t.name}}
                </a-select-option>
            </a-select>
            <br><br>
            <p>تاریخ و ساعت دفاع </p>
            <client-only>
                <date-picker
                        type="datetime"
                        v-model="datetime"
                        inputFormat= 'YYYY-MM-DD HH:mm'
                        format='jYYYY-jMM-jDD HH:mm'
                        :editable="false"
                        altFormat='YYYY-MM-DD HH:mm'
                />
            </client-only>
            <span class="text-success">{{datetime}}</span>
        </a-modal>
    </div>
</template>

<script>
    export default {
        name: "Requests",
        middleware:"manAuth",
        data(){
            return{
                visible:false,
                temp:'',
                datetime:'',
                teachers:[{name:'حسن حقیقی' , group: 'نرم افزار' , id:0},
                    {name:'علیرضا شاملی' , group: 'نرم افزار' , id:1},],
                list1:[{
                    name:'علی صدقی' ,
                    id:'95243034',
                    group:'نرم افزار',
                    title:'سامانه کنترل ترافیک' ,
                    teacher:'حسن حقیقی' ,
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
                        title: 'گروه',
                        dataIndex: 'group',
                        align:'center',
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
            this.$store.commit('setPage','تعیین داور')
            this.$store.commit('setActiveKey',['6'])
        },
        methods:{
            openNotificationWithIcon(type , msg , desc) {
                this.$notification[type]({
                    message: msg,
                    description: desc,
                });
            },
            onSub(){
                this.openNotificationWithIcon('success','باموفقیت داور تعییت شد.' , this.datetime)
                setTimeout(()=>{
                    this.list1.splice(this.temp,1)
                    this.visible=false
                    this.datetime=''
                    },700)
            }
        }
    }
</script>

<style>

</style>