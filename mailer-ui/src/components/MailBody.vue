<template>
  <div class="mx-3 before-nav">
    <div class="row">
      <div class="col-4 overflowy">
        <MailList />
      </div>
      <div class="col overflowy">
        <MailContent />
      </div>
    </div>
  </div>
</template>

<script>
import MailList from "./MailList";
import MailContent from "./MailContent";
export default {
  name: "MailBody",
  components: {
    MailList,
    MailContent,
  },
};
</script>

<style lang="scss">
.before-nav {
  transform: translateY(20%);
}
.overflowy {
  overflow-y: scroll;
  height: 80vh;
}
</style>