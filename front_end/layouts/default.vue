<template>
    <div style="height: 100vh" class="wrapper">
      <a-layout id="components-layout-demo-custom-trigger">
        <a-layout>
          <a-layout-header class="p-0 text-white bg-or">
            <a-icon
                    class="trigger float-right"
                    :type="collapsed ? 'menu-unfold' : 'menu-fold'"
                    @click="() => (collapsed = !collapsed)"
            />
          </a-layout-header>
          <a-breadcrumb class="text-right mx-5 mt-3">
            <a-breadcrumb-item>{{$store.state.auth.role==='ADMIN'?'ادمین':
              ($store.state.auth.role==='STUDENT'?'دانشجو':
              ($store.state.auth.role==='TEACHER'?'استاد':
              'مدیر'
              ))}}</a-breadcrumb-item>
            <a-breadcrumb-item>{{$store.state.page}}</a-breadcrumb-item>
          </a-breadcrumb>
          <a-layout-content
                  :style="{ margin: '24px 16px', padding: '24px', background: '#fff', minHeight: '280px' , overflow:'auto' }"
          >
            <nuxt />
          </a-layout-content>
        </a-layout>
        <a-layout-sider v-model="collapsed" :reverseArrow="true" :trigger="null" collapsible>
          <div class="logo" >
            <button class="btn btn-block btn-warning text-white btn-sm" @click="logout" >خروج</button>
          </div>
          <a-menu theme="dark" mode="inline" :selectedKeys="this.$store.state.siderActiveKey">
            <a-menu-item key="1" v-if="role ==='ADMIN'">
              <nuxt-link to="/admin/addUser">
              <a-icon type="user" />
              <span>اضافه کردن کاربر</span>
              </nuxt-link>
            </a-menu-item>
            <a-menu-item key="2" v-if="role ==='ADMIN'">
              <nuxt-link to="/admin/userList">
                <a-icon type="video-camera" />
                <span>لیست کاربران</span>
              </nuxt-link>
            </a-menu-item>
            <a-menu-item key="1" v-if="role ==='STUDENT'">
              <nuxt-link to="/Student/newProsal">
                <a-icon type="form" />
                <span>ثبت پروپوزال</span>
              </nuxt-link>
            </a-menu-item>
            <a-menu-item key="2" v-if="role ==='STUDENT'">
              <nuxt-link to="/Student/listProposal">
                <a-icon type="profile" />
                <span>لیست پروپوزال</span>
              </nuxt-link>
            </a-menu-item>
            <a-menu-item key="3" v-if="role ==='STUDENT'">
              <nuxt-link to="/Student/submitReport">
                <a-icon type="file-done" />
                <span>ثبت گزارش</span>
              </nuxt-link>
            </a-menu-item>
            <a-menu-item key="4" v-if="role ==='STUDENT'">
              <nuxt-link to="/Student/listReport">
                <a-icon type="profile" />
                <span>لیست گزارش</span>
              </nuxt-link>
            </a-menu-item>
            <a-menu-item key="5" v-if="role === 'MANAGER'">
              <nuxt-link to="/Manager/requests">
                <a-icon type="bars" />
                <span>لیست درخواست ها</span>
              </nuxt-link>
            </a-menu-item>
            <a-menu-item key="6" v-if="role === 'MANAGER'">
              <nuxt-link to="/Manager/setJudge">
                <a-icon type="control" />
                <span>تعیین داور</span>
              </nuxt-link>
            </a-menu-item>
            <a-menu-item key="1" v-if="role ==='TEACHER' || role === 'MANAGER'">
              <nuxt-link to="/teacher/stdList">
                <a-icon type="user" />
                <span>لیست دانشجویان</span>
              </nuxt-link>
            </a-menu-item>
            <a-menu-item key="2" v-if="role ==='TEACHER' || role === 'MANAGER'">
              <nuxt-link to="/teacher/reports">
                <a-icon type="form" />
                <span>گزارشات پروژه</span>
              </nuxt-link>
            </a-menu-item>
            <a-menu-item key="3" v-if="role ==='TEACHER' || role === 'MANAGER'">
              <nuxt-link to="/teacher/judge">
                <a-icon type="crown" />
                <span>داوری</span>
              </nuxt-link>
            </a-menu-item>
            <a-menu-item key="4" v-if="role ==='TEACHER' || role === 'MANAGER'">
              <nuxt-link to="/teacher/calendar">
                <a-icon type="calendar" />
                <span>برنامه دفاع</span>
              </nuxt-link>
            </a-menu-item>
          </a-menu>
        </a-layout-sider>
      </a-layout>
    </div>
</template>

<script>
  const Cookie = process.client ? require('js-cookie') : undefined
  export default {
    data() {
      return {
        collapsed: false,
        // role:'NONE'
      };
    },
    methods:{
      logout () {
        Cookie.remove('auth')
        this.$store.commit('setAuth', null)
        this.$router.push('/')
      }
    },
    mounted() {
      // if (this.$store.state.auth)
      //   this.role = this.$store.state.auth.role
    },
    computed:{
      role(){
        return  this.$store.state.auth?this.$store.state.auth.role:'NONE'
      }
    },
  }
</script>
<style>
html {
  font-family: 'Source Sans Pro', -apple-system, BlinkMacSystemFont, 'Segoe UI',
    Roboto, 'Helvetica Neue', Arial, sans-serif;
  font-size: 16px;
  word-spacing: 1px;
  -ms-text-size-adjust: 100%;
  -webkit-text-size-adjust: 100%;
  -moz-osx-font-smoothing: grayscale;
  -webkit-font-smoothing: antialiased;
  box-sizing: border-box;
}

*,
*:before,
*:after {
  box-sizing: border-box;
  margin: 0;
}

#components-layout-demo-custom-trigger .trigger {
  font-size: 18px;
  line-height: 64px;
  padding: 0 24px;
  cursor: pointer;
  transition: color 0.3s;
}

#components-layout-demo-custom-trigger .trigger:hover {
  color: #1890ff;
}

#components-layout-demo-custom-trigger .logo {
  height: 32px;
  background: rgba(255, 255, 255, 0.2);
  margin: 16px;
}
.wrapper{
  background-color: #1e7e34;
}
#components-layout-demo-custom-trigger{
  position: fixed;
  bottom: 0; top: 0; left: 0; right: 0;
}
  .bg-or{
    background-color: #ffd335;
  }
  .ant-menu-item-selected{
  }
</style>
