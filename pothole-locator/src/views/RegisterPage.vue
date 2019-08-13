<template>
<div>
 <div id="message-container" class="alert alert-danger" role="alert" v-if="registrationErrors">
      {{errors}}
      </div>
  <div id="register" class="content">
    
    <form class="form-register" @submit.prevent="register" >
      <h1 class="h3 mb-3 font-weight-normal">Create Account</h1>
     
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
      <br>
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
      <br>
      <b-col>
        <input
          type="password"
          id="confirmPassword"
          class="form-control"
          placeholder="Confirm Password"
          v-model="user.confirmPassword"
          required
        />
      </b-col>
      <br>
      <button class="btn btn-lg btn-primary" type="submit">
        Create Account
      </button>
      <br> <br>
      <router-link :to="{ name: 'login' }">
        Have an account?
      </router-link>
    </form>
  </div>
  </div>
</template>

<script>
export default {
  name: 'register',
  props: {
    apiURL: String
  },
  data() {
    return {
      user: {
        username: '',
        password: '',
        confirmPassword: '',
        role: 'user',
      },
      errors: [],
      registrationErrors: false,
    };
  },
  methods: {
    register() {
      fetch(this.apiURL + "register", {
        method: 'POST',
        headers: {
          Accept: 'application/json',
          'Content-Type': 'application/json',
        },
        body: JSON.stringify(this.user),
      })
        .then((response) => {
          if (response.ok) {
            return (response.json());
          } else {
            this.registrationErrors = true;
            throw 'Register returned: ' + response.status;
          }
        })
        .then ((parsedData) => {
            if (parsedData.success) {
              this.$router.push({ path: '/login', query: { registration: 'success' } });
            } else {
              this.errors = parsedData.errors;
              this.registrationErrors = true;
            }
        })
        .catch((err) => console.log(err));
    },
  },
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
    height: 400px;
}


form {
  width: 300px;
  display: absolute;
  align-items: center;
  justify-content: center;
  
}
#message-container {
  
}


</style>

