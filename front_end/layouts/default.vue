<template>
    <div v-if="$store.state.auth" style="height: 100vh" class="wrapper">
      <a-layout id="components-layout-demo-custom-trigger">
        <a-layout>
          <a-layout-header class="p-0 text-white bg-or">
            <a-icon
                    class="trigger float-right"
                    :type="collapsed ? 'menu-unfold' : 'menu-fold'"
                    @click="() => (collapsed = !collapsed)"
            />
          </a-layout-header>
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
          <a-menu theme="dark" mode="inline" :default-selected-keys="['1']">
            <a-menu-item key="1" v-if="role ==='ADMIN'">
              <nuxt-link to="/admin/addUser">
              <a-icon type="user" />
              <span>اضافه کردن کاربر</span>
              </nuxt-link>
            </a-menu-item>
            <a-menu-item key="2" v-if="role ==='ADMIN'">
              <a-icon type="video-camera" />
              <span>لیست کاربران</span>
            </a-menu-item>
          </a-menu>
        </a-layout-sider>
      </a-layout>
    </div>
  <div v-else>
    <nuxt />
  </div>
</template>

<script>
  const Cookie = process.client ? require('js-cookie') : undefined
  export default {
    data() {
      return {
        collapsed: false,
        role:'NONE'
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
      if (this.$store.state.auth)
        this.role = this.$store.state.auth.role
    }
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
