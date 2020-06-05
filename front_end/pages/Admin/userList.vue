<template>
    <div class="container-fluid">
    <a-table
            :scroll="{ x: 1500 }"
            style="overflow: hidden !important;"
            :columns="columns"
            :row-key="record => record.login.uuid"
            :data-source="data"
            :pagination="pagination"
            :loading="loading"
            bordered
            @change="handleTableChange"
    >
        <template slot="name" slot-scope="name"> {{ name.first }} {{ name.last }} </template>
        <template slot="op" >
            <a-button shape="circle" icon="edit" @click="$router.push('/admin/addUser')"></a-button>
            <a-popconfirm
                        placement="left"
                        title="از حذف کاربر مطمینید ؟"
                        ok-text="تایید"
                        cancel-text="انصراف">
                    <a-button shape="circle" icon="delete" />
            </a-popconfirm>
        </template>
    </a-table>
    </div>
</template>
<script>
    const columns = [
        {
            title: 'نام کاربری',
            dataIndex: 'name',
            sorter: true,
            width: '20%',
            scopedSlots: {
                customRender: 'name',
            },
            align:'center'
        },
        {
            title: 'جنسیت',
            dataIndex: 'gender',
            filters: [
                { text: 'Male', value: 'male' },
                { text: 'Female', value: 'female' },
            ],
            width: '10%',
            align:'center'
        },
        {
            title: 'ایمیل',
            dataIndex: 'email',
            align:'center'
        },
        {
            title: 'شماره شناسایی',
            dataIndex: 'x',
            width: '10%',
            align:'center'
        },
        {
            title: 'تلفن',
            dataIndex: 'y',
            width: '10%',
            align:'center'
        },
        {title: 'عملیات',
            dataIndex: 'operation',
            width: '15%',
            align:'center',
            scopedSlots: {
                customRender: 'op',
            },
            fixed: 'right'
        }
    ];

    export default {
        middleware:'adminAuth',
        data() {
            return {
                searchText: '',
                searchInput: null,
                searchedColumn: '',
                data: [],
                pagination: {
                    pageSize:5,
                    current:1,
                },
                loading: false,
                columns,
            };
        },
        mounted() {
            this.$store.commit('setPage','لیست کاربران')
            this.$store.commit('setActiveKey', ['2'])
            this.fetch({
                results: 5,
                page: 1,
            });
        },
        methods: {
            handleTableChange(pagination, filters, sorter) {
                console.log(pagination);
                const pager = { ...this.pagination };
                pager.current = pagination.current;
                this.pagination = pager;
                this.fetch({
                    results: pagination.pageSize,
                    page: pagination.current,
                    sortField: sorter.field,
                    sortOrder: sorter.order,
                    ...filters,
                });
            },
            fetch(params = {}) {
                console.log('params:', params);
                this.loading = true;
                this.$axios.get('https://randomuser.me/api',{ params: params
                }).then(data => {
                    const pagination = { ...this.pagination };
                    // Read total count from server
                    // pagination.total = data.totalCount;
                    pagination.total = 100;
                    this.loading = false;
                    this.data = data.data.results;
                    console.log(this.data)
                    this.pagination = pagination;
                }).catch(error => {
                    console.log(error)
                });
            },
        },
    };
</script>