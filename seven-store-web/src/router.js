import { createRouter, createWebHistory } from 'vue-router';

import Home from '@/views/Home.vue'; // 引入你的组件
import About from '@/views/About.vue'; // 引入你的组件


const router = createRouter({
    // 配置你的路由规则
    history: createWebHistory(),
    routes: [
        // 定义你的路由
        {
            path: '/',
            component: Home
        },
        {
            path: '/home',
            component: About
        }
    ]
});

export default router;
