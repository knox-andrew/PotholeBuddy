<template>
  <div id="app">
    <b-navbar toggleable="lg" type="dark" variant="dark">
      <b-navbar-brand :to="{name: 'landing-page'}">Home</b-navbar-brand>

      <b-navbar-toggle target="nav-collapse"></b-navbar-toggle>

      <b-collapse id="nav-collapse" is-nav>
        <b-navbar-nav>
          <b-nav-item :to="{name: 'anonymous-view'}">View Potholes</b-nav-item>
          <b-nav-item v-if="isLoggedIn()" :to="{name: 'report'}">Report a Pothole</b-nav-item>
          <b-nav-item v-if="isAdmin()" :to="{name: 'administrator'}">Admin View</b-nav-item>
        </b-navbar-nav>

        <!-- Right aligned nav items -->
        <b-navbar-nav v-if="isLoggedIn()" class="ml-auto">
          <b-nav-text class="pr-3">Hello User!</b-nav-text>
          <b-button @click.prevent="logout()">Logout</b-button>
        </b-navbar-nav>
        <b-navbar-nav v-else class="ml-auto">
          <b-nav-item-dropdown text="User" right>
            <b-dropdown-item tag="button" href="/login">Login</b-dropdown-item>
            <b-dropdown-item href="/register">Need an account?</b-dropdown-item>
          </b-nav-item-dropdown>
        </b-navbar-nav>
        <!--
        <b-navbar-nav v-else class="ml-auto">
          <b-nav-item :to="{name: 'register'}">Register</b-nav-item>
          <b-nav-item :to="{name: 'login'}">Login</b-nav-item>
        </b-navbar-nav>
        -->
      </b-collapse>
    </b-navbar>
    <router-view :apiURL="API_URL" />
  </div>
</template>
<script>
import auth from "@/auth.js";
export default {
  data() {
    return {
      API_URL: "http://localhost:8080/AuthenticationApplication/"
    };
  },
  methods: {
    isLoggedIn() {
      return auth.getUser() != null;
    },
    isAdmin() {
      return auth.getUser() != null && auth.getUser().rol === "admin";
    },
    logout() {
      auth.logout();
      this.$router.push("/");
      this.$forceUpdate();
    }
  }
};
</script>
<style>
body {
  margin: 0;
  padding: 0;
  background-image: url("https://i.pinimg.com/originals/5f/c2/9c/5fc29ce899168b5abd2fb8faea9726f4.jpg");
  color: black;
}
.content {
  background-color: rgba(200, 200, 200, 0.7);
  border-radius: 10px;
  padding: 10px;
  margin: 10px;
}
</style>
