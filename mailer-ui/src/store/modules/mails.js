const state = {
    emails: [],
    currentEmail: {},
    messageApi: null,
};
const getters = {
    allEmails: state => state.emails,
    currentEmail: state => state.currentEmail,
    messageApi: state => state.messageApi,
};
const actions = {
    async fetchEmails({ commit }) {
        const res = await fetch(`/api/mail/${localStorage.getItem('email') || ''}`);
        commit('setEmails', await res.json());
    },
    setEmail({ commit }, email) {
        commit('setCurrentEmail', email);
    },
    appendEmails({ commit }, email) {
        commit('pushEmails', email);
    },
    setMessageApi({ commit }, websocket) {
        commit('setMessageApi', websocket);
    }
};
const mutations = {
    setEmails: (state, emails) => (state.emails = emails),
    setCurrentEmail: (state, email) => (state.currentEmail = email),
    pushEmails: (state, email) => (state.emails.push(email)),
    setMessageApi: (state, websocket) => (state.messageApi = websocket)
};

export default {
    state,
    getters,
    actions,
    mutations
};