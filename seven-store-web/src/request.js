import axios from 'axios'

const axiosInstance = axios.create({
    baseURL: '/api', // 替换为你的 API 基础路径
    timeout: 10000,
    headers: {
        'Content-Type': 'application/json',
    },
})

export default {
    install: (app) => {
        app.config.globalProperties.$axios = axiosInstance
    }
}