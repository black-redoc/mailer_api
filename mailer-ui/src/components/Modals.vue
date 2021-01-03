<template>
  <div>
    <div
      class="alert hidden"
      v-bind:class="{
        'show-alert': activeAlert,
        'alert-success': isSuccess,
        'alert-danger': isDanger,
      }"
      role="alert"
    >
      {{ alertMessage }}
      <button
        @click="closeAlert"
        type="button"
        class="close"
        data-dismiss="alert"
        aria-label="Close"
      >
        <span aria-hidden="true" style="margin-right: 0.5rem">&times;</span>
      </button>
    </div>
    <div
      class="modal fade"
      id="emailFormModal"
      tabindex="-1"
      role="dialog"
      aria-labelledby="exampleModalLabel"
      aria-hidden="true"
    >
      <div class="modal-dialog" role="document">
        <div class="modal-content">
          <div class="modal-header">
            <h5 class="modal-title" id="exampleModalLabel">Enviar Correo</h5>
            <button
              type="button"
              class="close"
              data-dismiss="modal"
              aria-label="Close"
            >
              <span aria-hidden="true">&times;</span>
            </button>
          </div>
          <form @submit.prevent="onSendEmail">
            <div class="modal-body">
              <div class="form-group">
                <label for="to">Para</label>
                <input
                  v-model="toEmail"
                  type="email"
                  class="form-control"
                  placeholder="user@example.com"
                />
              </div>
              <div class="form-group">
                <label for="subject">Asunto</label>
                <input
                  v-model="subject"
                  type="text"
                  class="form-control"
                  placeholder="Asunto"
                />
              </div>
              <div class="form-group">
                <label for="body">Contenido</label>
                <textarea
                  v-model="body"
                  class="form-control"
                  rows="9"
                ></textarea>
              </div>
            </div>
            <div class="modal-footer">
              <button
                type="button"
                class="btn btn-secondary"
                data-dismiss="modal"
              >
                Cancelar
              </button>
              <button type="submit" class="btn btn-primary">Enviar</button>
            </div>
          </form>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
import $ from "jquery";
import { Client } from "@stomp/stompjs";
import SockJS from "sockjs-client";
import { mapActions, mapGetters } from "vuex";

export default {
  name: "Modals",
  data: () => ({
    toEmail: "",
    subject: "",
    body: "",
    alertMessage: "",
    activeAlert: false,
    isSuccess: false,
    isDanger: false,
    userEmail: localStorage.getItem("email") || "user@example.com",
  }),
  computed: {
    ...mapGetters(["messageApi"]),
  },
  created() {
    this.connectWS();
  },
  methods: {
    ...mapActions(["appendEmails", "setMessageApi"]),
    connectWS() {
      const newClient = new Client({
        debug: (str) => console.log(str),
        reconnectDelay: 5000,
        heartbeatIncoming: 4000,
        heartbeatOutgoing: 4000,
      });
      newClient.webSocketFactory = () => new SockJS("http://localhost:9000/ws");

      newClient.onConnect = () => console.log("connected to websocket");
      newClient.onWebSocketError = function (frame) {
        this.alertMessage = `Error al conectar al websocket: ${JSON.stringify(
          frame
        )}`;
        this.isDanger = true;
        this.activeAlert = true;
      };
      newClient.onStompError = function (frame) {
        this.alertMessage = `Error al conectar al websocket: ${JSON.stringify(
          frame
        )}`;
        this.isDanger = true;
        this.activeAlert = true;
      };
      newClient.activate();
      this.setMessageApi(newClient);
    },
    async onSendEmail() {
      const { toEmail, subject, body, $router } = this;
      const fromEmail = $router.history.current.params.email;

      try {
        const newEmail = { to: toEmail, from: fromEmail, subject, body };
        const res = await fetch("/api/mail", {
          method: "POST",
          headers: {
            "Content-Type": "application/json",
          },
          body: JSON.stringify({ ...newEmail }),
        });

        this.activeAlert = true;
        this.alertMessage = "Correo enviado satisfactoriamente";
        this.isSuccess = true;

        this.appendEmails(newEmail);

        this.messageApi.publish({
          destination: "/app/chat",
          body: JSON.stringify(newEmail),
          headers: {
            "Content-Type": "application/json",
          },
        });

        $("#emailFormModal").modal("hide");
        this.toEmail = "";
        this.fromEmail = "";
        this.subject = "";
        this.body = "";
        setTimeout(this.closeAlert, 7000);
      } catch (error) {
        this.isDanger = true;
        this.alertMessage = error;
      }
    },
    closeAlert() {
      this.alertMessage = "";
      this.isSuccess = false;
      this.isDanger = false;
      this.activeAlert = false;
    },
  },
};
</script>
<style lang="scss" scoped>
.hidden {
  display: none;
}

.show-alert {
  position: absolute !important;
  z-index: 9000;
  top: 1.5rem;
  right: 1.5rem;
  display: block;
}
</style>