<template>
    <div class="container-fluid text-right">
        <h5 class="my-3 mb-5 mr-3 text-secondary">لیست کاربران</h5>
    <a-table
            :columns="columns"
            :row-key="record => record.login.uuid"
            :data-source="data"
            :pagination="pagination"
            :loading="loading"
            bordered
            @change="handleTableChange"
    >
        <template slot="name" slot-scope="name"> {{ name.first }} {{ name.last }} </template>
    </a-table>
    </div>
</template>
<script>
    const columns = [
        {
            title: 'name',
            dataIndex: 'name',
            sorter: true,
            width: '20%',
            scopedSlots: {
                customRender: 'name',
            },
        },
        {
            title: 'Gender',
            dataIndex: 'gender',
            filters: [
                { text: 'Male', value: 'male' },
                { text: 'Female', value: 'female' },
            ],
            width: '20%',
        },
        {
            title: 'Email',
            dataIndex: 'email',
        },
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