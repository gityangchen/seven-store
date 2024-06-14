import './assets/main.css'

import { createApp } from 'vue'
import App from './App.vue'
import router from './router'
import axiosPlugin from './request'


import axios from 'axios'
// 设置基础访问路径
axios.defaults.baseURL='/api'


const app = createApp(App)
app.use(axiosPlugin)
app.use(router)
app.mount('#app')
