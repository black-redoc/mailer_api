<template>
  <div>
    <nav
      class="navbar fixed-top navbar-expand-lg navbar-dark bg-info justify-content-between"
    >
      <p class="navbar-brand mt-2 mr-2">Mailer</p>

      <ul class="navbar-nav mr-auto">
        <li class="nav-item-active">
        </li>
      </ul>

      <div class="row">
        <div class="col align-self-end">
          <div class="dropdown">
            <button
              title="Ajustes"
              class="btn btn-light"
              id="dropdownMenuButton"
              style="border-radius: 50%"
              data-toggle="dropdown"
              aria-haspopup="true"
              aria-expanded="false"
            >
              <font-awesome-icon icon="user" />
            </button>
            <div
              class="dropdown-menu"
              aria-labelledby="dropdownMenuButton"
              style="transform: translateX(-72%)"
            >
              <button @click="logout" class="dropdown-item">
                Cerrar Sesion
              </button>
            </div>
          </div>
        </div>
      </div>
    </nav>
  </div>
</template>

<script>
export default {
  name: "NavBar",
  methods: {
    logout() {
      this.$router.history.push("/");
    },
  },
};
</script>

<style>
</style>