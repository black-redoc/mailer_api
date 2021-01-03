import Vuex from 'vuex';
import Vue from 'vue';
import emails from './modules/mails';

// Load Vuex
Vue.use(Vuex);

// Create store
export default new Vuex.Store({
    modules: {
        emails
    },
})
