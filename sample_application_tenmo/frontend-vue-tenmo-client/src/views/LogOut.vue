<template>
  <h1>Logout</h1>
</template>

<script>
export default {
  name:'log-out',
  created() {
    this.$store.commit("LOGOUT");
    this.$router.push("/login");
  }
};
</script>
