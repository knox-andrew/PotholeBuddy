<template>
<div>
<div
        class="alert alert-danger"
        role="alert"
        v-if="invalidCredentials"
      >Invalid username or password!</div>
  <div id="login" class="content">
    <form class="form-signin" @submit.prevent="login">
      <h1 class="h3 mb-3 font-weight-normal">Please Sign In</h1>
      
      <div
        class="alert alert-success"
        role="alert"
        v-if="this.$route.query.registration"
      >Thank you for registering, please sign in.</div>
      <label for="username" class="sr-only">Username</label>
      <b-col>
        <input
          type="text"
          id="username"
          class="form-control"
          placeholder="Username"
          v-model="user.username"
          required
          autofocus
        />
      </b-col>
      <br />
      <label for="password" class="sr-only">Password</label>
      <b-col>
        <input
          type="password"
          id="password"
          class="form-control"
          placeholder="Password"
          v-model="user.password"
          required
        />
      </b-col>
      <br />
      <button class="btn btn-lg btn-primary" type="submit">Sign in</button>
      <br />
      <br />
      <router-link :to="{ name: 'register' }">Need an account?</router-link>
    </form>
  </div>
  </div>
</template>

<script>
import auth from "../auth";

export default {
  name: "login",
  props: {
    apiURL: String
  },
  data() {
    return {
      user: {
        username: "",
        password: ""
      },
      invalidCredentials: false
    };
  },
  methods: {
    login() {
      fetch(this.apiURL + "login", {
        method: "POST",
        headers: {
          Accept: "application/json",
          "Content-Type": "application/json"
        },
        body: JSON.stringify(this.user)
      })
        .then(response => {
          if (response.ok) {
            return response.text();
          } else {
            this.invalidCredentials = true;
          }
        })
        .then(token => {
          if (token != undefined) {
            if (token.includes('"')) {
              token = token.replace(/"/g, "");
            }
            auth.saveToken(token);
            this.$router.push("/");
          }
        })
        .catch(err => console.error(err));
    }
  }
};
</script>

<style scoped>
.content{
   position: relative;
    top: 50%;
    left: 42%;
    margin-top: 55px;
    margin-left: -50px;
    width: 320px;
    height: 350px;
}


form {
  width: 300px;
  flex-direction: column;
  display: flex;
  align-items: center;
  justify-content: center;
  
}
</style>
