<template>
    <div class="container text-right">
        <div class="row mb-5" style="min-height: 100px">
            <div class="col-sm-6 order-sm-2">
                <label>عنوان گزارش </label>
                <a-input v-model="title" class="text-right"></a-input>
            </div>
            <div class="col-sm-6 text-center">
                <a-upload
                        action=""
                        :multiple="true"
                        :file-list="fileList"
                        @change="handleChange"
                >
                    <a-button block class="mt-2"> <a-icon type="upload" /> آپلود فایل </a-button>
                </a-upload>
            </div>
        </div>

        <button class="btn btn-sm btn-block btn-warning text-white border border-dark" style="margin-top: 300px"
        @click="onSubmit"
        >ثبت</button>
    </div>
</template>

<script>
    export default {
        name: "submitReport",
        middleware:"stdAuth",
        data(){
            return{
                title:'',
                fileList: [
                ],
            }
        },
        mounted(){
            this.$store.commit('setPage','ثبت گزارش')
            this.$store.commit('setActiveKey',['3'])
        },
        methods:{
            openNotificationWithIcon(type , msg , desc) {
                this.$notification[type]({
                    message: msg,
                    description: desc,
                });
            },
            handleChange(info) {
                let fileList = [...info.fileList];

                // 1. Limit the number of uploaded files
                //    Only to show two recent uploaded files, and old ones will be replaced by the new
                fileList = fileList.slice(-2);

                // 2. read from response and show file link
                fileList = fileList.map(file => {
                    if (file.response) {
                        // Component will show file.url as link
                        file.url = file.response.url;
                    }
                    return file;
                });

                this.fileList = fileList;
            },
            onSubmit(){
              this.title = '',
              this.fileList = [],
              this.openNotificationWithIcon('success', 'با موفقیت ذخیره شد')
            },
        }
    }
</script>

<style>

</style>