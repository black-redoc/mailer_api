import Vue from 'vue';
import App from './App.vue';
import router from './router';
import store from './store';
import { library } from '@fortawesome/fontawesome-svg-core';
import { faSearch, faUser, faPlus } from '@fortawesome/free-solid-svg-icons';
import { FontAwesomeIcon } from '@fortawesome/vue-fontawesome';
import '@popperjs/core';
import 'popper.js';
import 'jquery';
import 'bootstrap';
import 'bootstrap/dist/css/bootstrap.min.css';


library.add(faSearch);
library.add(faUser);
library.add(faPlus);

Vue.component('font-awesome-icon', FontAwesomeIcon)

new Vue({
    store,
    router,
    ...App
}).$mount("#app");