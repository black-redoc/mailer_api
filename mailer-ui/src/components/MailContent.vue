<template>
  <div class="container">
    <div v-if="Object.keys(currentEmail).length > 0">
      <p class="subtitle">
        <span class="strong">De:&nbsp;</span>{{ currentEmail.email.from }}
      </p>
      <p class="subtitle">
        <span class="strong">Para:&nbsp;</span>{{ currentEmail.email.to }}
      </p>
      <p class="subtitle">
        <span class="strong">Asunto:&nbsp;</span
        >{{ currentEmail.email.subject }}
      </p>
      <p style="text-align: justify; font-size: 1.5rem; margin-top: 1.7rem">
        {{ currentEmail.email.body }}
      </p>
    </div>
    <button
      type="button"
      class="btn btn-light btn-new-email"
      data-toggle="modal"
      data-target="#emailFormModal"
    >
      <font-awesome-icon
        icon="plus"
        style="font-size: 1.4rem; color: #3e8a7c"
      />
    </button>
  </div>
</template>

<script>
import { mapGetters } from "vuex";
export default {
  name: "MailContent",
  computed: {
    ...mapGetters(["currentEmail", "messageApi"]),
  },
  methods: {
    openModalMail() {
      console.log("modal");
    },
  },
};
</script>

<style lang="scss">
.btn-new-email {
  position: absolute;
  bottom: 0.1rem;
  right: 1.5rem;
  width: 3rem;
  height: 3rem;
  border-radius: 50%;
  border: 1px rgba(0, 0, 0, 0.1) solid;
}
</style>