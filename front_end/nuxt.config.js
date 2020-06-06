const webpack = require("webpack");
export default {
  mode: 'universal',
  /*
  ** Headers of the page
  */
  head: {
    title: 'سامانه پروپوزال دانشجویی',
    meta: [
      { charset: 'utf-8' },
      { name: 'viewport', content: 'width=device-width, initial-scale=1' },
      { hid: 'description', name: 'description', content: process.env.npm_package_description || '' }
    ],
    script: [
      { src: '/jquery.min.js' },
      { src: '/bootstrap.min.js' },
      { src: '/popper.min.js' }
    ],
    link: [
      { rel: 'icon', type: 'image/x-icon', href: '/favicon.ico' },
      { rel: 'stylesheet', href: '/bootstrap.min.css' },
      { rel: 'stylesheet', href: '/Styles/Glob.css' },
    ]
  },
  /*
  ** Customize the progress-bar color
  */
  loading: { color: '#fff' },
  /*
  ** Global CSS
  */
  css: [
    'ant-design-vue/dist/antd.css'
  ],
  /*
  ** Plugins to load before mounting the App
  */
  plugins: [
    '@/plugins/antd-ui' ,
    {src: '~plugins/datePicker.js', ssr: false},
  ],
  /*
  ** Nuxt.js dev-modules
  */
  buildModules: [
  ],
  /*
  ** Nuxt.js modules
  */
  modules: [
    ['@nuxtjs/axios']
  ],
  axios: {
    baseURL: process.env.BACKEND_BASE_URL || 'http://194.5.188.112:8000',
    common: {
      'Accept': 'application/json, text/plain, */*'
    },
      // https:true
  },
  /*
  ** Build configuration
  */
  build: {
    // babel: {
    //   plugins: [
    //     [
    //       "import",
    //       { libraryName: 'ant-design-vue', style: 'css' }
    //     ]
    //   ]
    // },
    /*
    ** Run ESLint on save
    */
    vendor: ["jquery", "bootstrap"],
    plugins: [
      new webpack.ProvidePlugin({
        $: 'jquery',
        jQuery: 'jquery',
        'window.jQuery': 'jquery'
      }),
      //new webpack.IgnorePlugin(/^\.\/locale$/, /moment$/)
    ],
    extend(config) {

    },

  }
}
