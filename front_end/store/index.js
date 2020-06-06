const cookieparser = process.server ? require('cookieparser') : undefined

export const state = () => {
  return {
      auth: null,
      page:'',
      siderActiveKey: ['1'],
  }
}
export const mutations = {
    setAuth (state, auth) {
        state.auth = auth
    },
    setPage (state, page) {
        state.page = page
    },
    setActiveKey(state,value){
        state.siderActiveKey = value;
    }
}
export const actions = {
  nuxtServerInit ({ commit }, { req }) {
      console.log('index.vue: '+process.env.BACKEND_BASE_URL)
    let auth = null
    if (req.headers.cookie) {
      const parsed = cookieparser.parse(req.headers.cookie)
      try {
        auth = JSON.parse(parsed.auth)
      } catch (err) {
        // No valid cookie found
      }
    }
    commit('setAuth', auth)
    // if (process.server) {
    //   commit('setEnv', {
    //     baseUrl: process.env.BACKEND_BASE_URL || 'http://localhost:6060'
    //   })
    // }
  }
}
