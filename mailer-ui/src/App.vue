<template>
  <div>
    <router-view />
    <Modals />
  </div>
</template>

<style lang="scss">
</style>

<script>
import Modals from "./components/Modals";

export default {
  name: "App",
  components: {
    Modals,
  },
};
</script>
