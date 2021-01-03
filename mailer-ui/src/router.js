import Vue from 'vue';
import VueRouter from 'vue-router';
import Router from 'vue-router';

Vue.use(Router)

export default new VueRouter({
    mode: 'history',
    base: process.env.BASE_URL,
    routes: [
        {
            path: '/',
            name: 'home',
            component: () => import( /* webpackChunkName: "Home" */ './views/Home.vue')
        },
        {
            path: '/mailer/:email',
            name: 'mailer',
            component: () => import( /* webpackChunkName: "Mailer" */ './views/Mailer.vue')
        }
    ]
})