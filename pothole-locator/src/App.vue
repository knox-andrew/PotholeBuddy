<template>
  <div id="app" class="pb-2">
    <b-navbar toggleable="lg" type="dark" variant="dark" sticky>
      <b-navbar-brand @click="active = 'landing-page'" :to="{name: 'landing-page'}">
        <b-img src="./assets/pothole_color.png" height="30px" class="pr-2" />PotholeBuddy
      </b-navbar-brand>

      <b-navbar-toggle target="nav-collapse"></b-navbar-toggle>

      <b-collapse id="nav-collapse" is-nav>
        <b-navbar-nav>
          <b-nav-item
            :active="active === 'potholes'"
            :to="{name: 'potholes'}"
            @click="active = 'potholes'"
          >View & Report Potholes</b-nav-item>
          <b-nav-item
            v-if="isAdmin()"
            :active="active === 'administrator'"
            @click="active = 'administrator'"
            :to="{name: 'administrator'}"
          >Admin View</b-nav-item>
        </b-navbar-nav>

        <b-navbar-nav v-if="isLoggedIn()" class="ml-auto">
          <b-nav-item-dropdown :text="getUserMessage()" right>
            <!--
            <b-dropdown-item @click="active = '#'" href="#">View your reports</b-dropdown-item>
            -->
            <b-dropdown-item-button @click.prevent="logout(), active='landing-page'">Logout</b-dropdown-item-button>
          </b-nav-item-dropdown>
        </b-navbar-nav>
        <b-navbar-nav v-else class="ml-auto">
          <b-nav-item-dropdown :text="getUserMessage()" right>
            <b-dropdown-item @click="active = 'login'" :to="{name: 'login'}">Login</b-dropdown-item>
            <b-dropdown-item @click="active = 'register'" :to="{name: 'register'}">Need an account?</b-dropdown-item>
          </b-nav-item-dropdown>
        </b-navbar-nav>
      </b-collapse>
    </b-navbar>
    <router-view :apiURL="apiURL" :markers="markers" />
  </div>
</template>
<script>
import auth from "@/auth.js";
export default {
  data() {
    return {
      apiURL: "http://localhost:8080/PotholesCapstone/",
      markers: [],
      active: "home"
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
      document.location.reload();
    },
    getRole() {
      return this.isLoggedIn() ? auth.getUser().rol : "";
    },
    getUserMessage() {
      if (auth.getUser() === null) {
        return "Account";
      } else {
        return "Hello, " + auth.getUser().sub;
      }
    }
  },
  created() {
    fetch(this.apiURL + "markers")
      .then(response => response.json())
      .then(parsedData => (this.markers = parsedData))
      .catch(err => console.log(err));
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
/*
.router-link-exact-active {
  font-weight: bold;
}
*/

.background {
  background-color: rgba(200, 200, 200, 0.7);
  border-radius: 10px;
}

.content {
  background-color: rgba(200, 200, 200, 0.7);
  border-radius: 10px;
  padding: 10px;
  margin: 10px;
}
</style>
