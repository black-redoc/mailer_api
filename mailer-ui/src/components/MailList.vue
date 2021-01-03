<template>
  <div>
    <div @click="openMail(email)" v-for="email in allEmails" :key="email.id">
      <div v-if="email.from === userEmail" class="row colored-hover mxr-5">
        <div class="col mb-1">
          <p class="subtitle">
            <span class="strong">De:&nbsp;</span>{{ email.from }}
          </p>
          <p class="subtitle">
            <span class="strong">Para:&nbsp;</span>{{ email.to }}
          </p>
          <p class="subtitle">
            <span class="strong">Contenido:&nbsp;</span
            >{{ trimContent(email.body) }}
          </p>
        </div>
      </div>
      <div v-else class="row colored-hover red-hover mxr-5">
        <div class="col mb-1">
          <p class="subtitle">
            <span class="strong">De:&nbsp;</span>{{ email.from }}
          </p>
          <p class="subtitle">
            <span class="strong">Para:&nbsp;</span>{{ email.to }}
          </p>
          <p class="subtitle">
            <span class="strong">Contenido:&nbsp;</span
            >{{ trimContent(email.body) }}
          </p>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
import { mapActions, mapGetters } from "vuex";

export default {
  name: "MailList",
  methods: {
    ...mapActions(["fetchEmails", "setEmail", "appendEmails"]),
    openMail(email) {
      this.setEmail({ email });
    },
    trimContent(content) {
      return content.length > 80 ? content.substring(0, 80) + "..." : content;
    },
    updateMessages(msg) {
      const email = JSON.parse(msg.body);
      this.appendEmails({ ...email });
      this.alertMessage = "Haz recibido un nuevo email";
      this.activeAlert = true;
      this.isSuccess = true;
      setTimeout(this.closeAlert, 7000);
    },
  },
  computed: {
    ...mapGetters(["allEmails", "messageApi"]),
  },
  created() {
    this.fetchEmails();
    this.messageApi.subscribe(
      `/user/${this.userEmail}/queue/messages`,
      this.updateMessages
    );
  },
  data() {
    return {
      userEmail: localStorage.getItem("email") || "user@example.com",
    };
  },
};
</script>

<style lang="scss">
.colored-hover {
  cursor: pointer;
  border-bottom: 1px #ccc solid;
  &:hover {
    background-color: rgb(177, 207, 221);
  }
  &.red-hover {
    &:hover {
      background-color: rgb(228, 181, 201);
    }
  }
}
.subtitle {
  text-align: justify;
  margin-bottom: 0.5rem;
  display: block;
  margin: 0;
  padding: 0;
  &::after {
    margin-bottom: 0.5rem;
  }
  &::before {
    margin-bottom: 0.5rem;
  }
}
.mxr-5 {
  margin-left: 0.5rem;
  margin-right: 0.5rem;
}
.strong {
  margin: 0;
  padding: 0;
  font-weight: 600;
  display: inline;
  &::after {
    margin-bottom: 0.5rem;
  }
  &::before {
    margin-bottom: 0.5rem;
  }
}
</style>
