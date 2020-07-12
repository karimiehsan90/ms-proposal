<template>
    <div class="container text-right">
        <a-form :form="form" :colon="false" @submit="handleSubmit" class="mx-auto text-right">
            <a-collapse v-model="activeKey">
                <a-collapse-panel key="1" header="مشخصات دانشجو">
                    <div class="row">
                        <div class="col-md order-md-4">
                            <a-form-item label="نام و نام خانوادگی">
                                <a-input value="علی صدقی" disabled class="text-right"/>
                            </a-form-item>
                        </div>
                        <div class="col-md order-md-3">
                            <a-form-item label="شماره دانشجویی">
                                <a-input value="95243034" disabled class="text-right"/>
                            </a-form-item>
                        </div>
                        <div class="col-md order-md-2">
                            <a-form-item label="رشته تحصیلی">
                                <a-input value="مهندسی کامپیوتر" disabled class="text-right"/>
                            </a-form-item>
                        </div>
                        <div class="col-md-2 order-md-1">
                            <a-form-item label="تعداد واحد گذرانده" >
                                <a-input-number
                                        v-decorator="['vahed',{ rules: [
                                        { required: true, message: 'اجباری است' }] },
                                        ]"
                                        :min="60" :max="145" class="w-100" />
                            </a-form-item>
                        </div>
                    </div>
                </a-collapse-panel>
                <a-collapse-panel key="2" header="مشخصات استاد">
                    <div class="row">
                        <div class="col-md-7 order-md-2">
                        <a-form-item label="نام و نام خانوادگی" extra="میتوانید اسم استاد مورد نظر را تایپ کرده و از لیست انتخاب کنید">
                            <a-select
                                    dropdownClassName="text-right"
                                    v-decorator="['teacher',{ rules: [
                                        { required: true, message: 'انتخاب استاد اجباری است' }] },
                                        ]"
                                    show-search
                                    class="text-right"
                                    option-filter-prop="children"
                                    style="width: 100%"
                                    :filter-option="filterOption"
                                    @change="handleChange"
                            >
                                <a-select-option v-for="t in teachers" :value="t.id+'#'+t.group" :key="t.id">
                                    {{t.name}}
                                </a-select-option>
                            </a-select>
                        </a-form-item>
                        </div>
                        <div class="col-md">
                            <a-form-item label="گروه آموزشی" >
                            <a-input disabled :value="tgp" class="text-right"></a-input>
                            </a-form-item>
                        </div>
                    </div>
                </a-collapse-panel>
                <a-collapse-panel key="3" header="مشخصات پروژه">
                    <div class="row">
                        <div class="col-md">
                            <a-form-item label="عنوان پروژه">
                                <a-input
                                        class="text-right" v-decorator="['title',{ rules: [
                                        { required: true, message: 'عنوان پروژه اجباری است' },
                                        { whitespace: true, message: 'عنوان پروژه نمیتوان خالی باشد' },
                                        ] },
                                        ]" />
                            </a-form-item>
                        </div>
                    </div>
                    <div class="row">
                        <div class="col-md">
                            <a-form-item label="شرح پروژه" extra="سعی کنید بین 4 الی 8 خط شرح دهید" >
                                <a-textarea
                                        class="text-right"
                                        v-decorator="['desc',{ rules: [
                                        { required: true, message: 'شرح پروژه اجباری است' },
                                        { whitespace: true, message: 'شرح پروژه نمیتوان خالی باشد' }] },
                                        ]"
                                        :auto-size="{ minRows: 4, maxRows: 8 }"
                                />
                            </a-form-item>
                        </div>
                    </div>
                    <div class="row">
                        <div class="col-md order-md-2">
                            <a-form-item label="کلمات کلیدی" extra="از لیست پیشنهادی ما انتخاب کنید یا برای جدا سازی کلمات خود از . یا کلید اینتر استفاده کنید" required>
                            <a-select
                                    dropdownClassName="text-right"
                                    mode="tags" style="width: 100%" :token-separators="['.']" @change="loger"
                                      v-decorator="['words',{ rules: [
                                        { required: true, type:'array', validator:(r,v,c)=>{return v.length>2} , message:'حداقل 3 کلمه کلیدی لازم است' },
                                        ],
                                         initialValue:['نرم افزار']},
                                        ]"
                            >
                                <a-select-option v-for="i in words" :value="i" :key="i">
                                    {{i}}
                                </a-select-option>
                            </a-select>
                            </a-form-item>
                        </div>
                        <div class="col-md-3 order-md-1">
                            <a-form-item label="نیم سال تحصیلی"  >
                                <a-select
                                        dropdownClassName="text-right"
                                        v-decorator="['nimSal',{ rules: [
                                        { required: true, message: 'انتخاب اجباری است' }],
                                         initialValue:'1,99/98'},
                                        ]"
                                        style="width: 100%">
                                    <a-select-option value="1,99/98">
                                        نیمسال اول 99/98
                                    </a-select-option>
                                    <a-select-option value="2,99/98">
                                        نیمسال دوم 99/98
                                    </a-select-option>
                                </a-select>
                            </a-form-item>
                        </div>
                    </div>
                    <client-only>
                    <div class="row my-1">
                        <div class="col-md order-md-3">
                            <label class="my-2">تاریخ شروع</label>
                            <date-picker v-model="beginDate"></date-picker>
                        </div>
                        <div class="col-md order-md-2">
                            <label class="my-2">تاریخ ثبت</label>
                            <date-picker v-model="submitDate"></date-picker>
                        </div>
                        <div class="col-md order-md-1">
                            <label class="my-2">تاریخ پایان</label>
                            <date-picker v-model="endDate"></date-picker>
                        </div>
                    </div>
                    </client-only>
                    <div class="row mt-5">
                        <div class="col-md">
                            <a-form-item label="امکانات یا ملاحضات خاص" >
                                <a-textarea
                                        class="text-right"
                                        v-decorator="['spec',{ rules: [] },]"
                                        :auto-size="{ minRows: 3, maxRows: 5 }"
                                />
                            </a-form-item>
                        </div>
                    </div>
                </a-collapse-panel>
            </a-collapse>
            <a-button class="text-white bg-warning border-dark my-2" icon="check" html-type="submit">ثبت</a-button>
        </a-form>
    </div>
</template>

<script>
    export default {
        name: "newProposal",
        middleware:"stdAuth",
        data(){
            return{
                activeKey: ['1','2','3'],
                form: this.$form.createForm(this, { name: 'newProposal' }),
                teachers:[{name:'حسن حقیقی' , group: 'نرم افزار' , id:0},
                    {name:'علیرضا شاملی' , group: 'نرم افزار' , id:1},],
                words:['BigData' , 'Web' , 'AI'],
                tgp:'',
                beginDate:'',
                endDate:'',
                submitDate:'',

            }
        },
        mounted(){
            this.activeKey = ['1']
            this.$store.commit('setPage','ثبت پروپوزال')
            this.$store.commit('setActiveKey',['1'])
        },
        methods:{
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
                        this.form.resetFields()
                        this.openNotificationWithIcon('success', 'موفقیت' , 'پروپوزال اضافه شد')
                    }else {
                        console.log('errr')
                        this.activeKey = ['1','2','3']
                    }
                });
            },
            handleChange(value) {
                console.log(`selected ${value}`);
                let begin = value.indexOf('#')
                this.tgp = value.substring(begin+1)
            },
            filterOption(input, option) {
                return (
                    option.componentOptions.children[0].text.toLowerCase().indexOf(input.toLowerCase()) >= 0
                );
            },
            loger(value){
                console.log(value);

            },
        }
    }
</script>

<style>

</style>