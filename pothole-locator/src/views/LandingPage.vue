<template>
  <div>
    <landing-info />
  </div>
</template>


<script>
import LandingInfo from "@/components/LandingInfo.vue";
import auth from "@/auth.js";

export default {
  name: "home",
  components: {
    LandingInfo
  },
  data() {
    return {
      user: auth.getUser()
    };
  },
  methods: {
    logout() {
      auth.logout();
      this.$router.push("/login");
    }
  }
};
</script>

<style>
</style>
