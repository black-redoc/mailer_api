<template lang="">
  <div>
    <div class="alert alert-danger" v-bind:class="{'alert-active': isActive, 'hidden': noActive}" role="alert">
      {{error}}
      &nbsp;&nbsp;
      <button @click="closeAlert" type="button" class="close" data-dismiss="alert" aria-label="Close">
        <span aria-hidden="true">&times;</span>
      </button>
    </div>
    <div class="container">
      <div class="row full-size align-items-center justify-content-center">
        <div class="col-5">
          <form @submit.prevent="handleSubmit">
            <div class="row justify-content-center mb-3">
              <div class="col">
                <h1><span class="badge badge-info full-width">Inicio de Sesión</span></h1>
              </div>
            </div>
            <div class="form-group ml-2 mr-2">
              <label for="email">Email</label>
              <input v-model="email" type="email" class="form-control" placeholder="Email" autofocus/>
            </div>

            <div class="form-group ml-2 mr-2">
              <label for="password">Contraseña</label>
              <input v-model="password" type="password" class="form-control" placeholder="Contraseña" />
            </div>

            <div class="button-group ml-2">
              <button class="btn btn-primary" type="submit">Iniciar Sesión</button>
            </div>
          </form>
        </div>
      </div>
    </div>
  </div>
</template>
<script>
export default {
  name: "Index",
  data: () => ({
    email: "",
    password: "",
    error: "error",
    isActive: false,
    noActive: true,
  }),
  methods: {
    async handleSubmit() {
      const { email, password } = this;
      try {
        const res = await fetch("/api/user/login", {
          method: "POST",
          headers: {
            "Content-Type": "application/json",
          },
          body: JSON.stringify({
            email,
            password,
          }),
        });
        const user = await res.json();
        localStorage.setItem("email", user.email);
        this.$router.push({ name: "mailer", params: { email: user.email } });
      } catch (err) {
        if (err) {
          this.error = `Error: ${err}`;
          this.isActive = !this.isActive;
          this.noActive = !this.Active;
        }
      }
    },
    closeAlert() {
      this.error = "";
      this.isActive = !this.isActive;
      this.noActive = !this.Active;
    },
  },
};
</script>
<style lang="scss">
.full-size {
  width: 90vw;
  height: 100vh;
}
.full-width {
  width: 100%;
}
.hidden {
  display: none;
}
.alert-active {
  display: block;
  display: -moz-box;
  display: -webkit-box;
  position: absolute;
  top: 2rem;
  right: 5rem;
}
</style>