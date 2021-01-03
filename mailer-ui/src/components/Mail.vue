<template>
  <div>
    <NavBar />
    <MailBody />
  </div>
</template>

<script>
import NavBar from './NavBar';
import MailBody from './MailBody';

export default {
  name: "Mail",
  components: {
      NavBar,
      MailBody
  },
  methods: {
      logout() {
          console.log("close")
      }
  }
};
</script>