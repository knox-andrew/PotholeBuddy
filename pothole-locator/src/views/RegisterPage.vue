<template>
  <div class="container">
    <b-form @submit.prevent="submitForm">
      <b-form-group id="input-group-1" label="username:" label-for="input-1">
        <b-form-input
          id="input-1"
          v-model="regForm.username"
          type="text"
          required
          placeholder="Enter Username"
        />
      </b-form-group>

      <b-form-group id="input-group-2" label="Password:" label-for="input-2">
        <b-form-input id="input-2" v-model="regForm.password" type="password" required />
      </b-form-group>
      <b-form-group id="input-group-3" label="Confirm Password:" label-for="input-3">
        <b-form-input id="input-3" v-model="regForm.confirmPassword" type="password" required />
      </b-form-group>
      
      <router-link :to="{name: 'login'}"> <b-button :disabled="!isValidForm" @click="createUser(), signOn()" type="submit">Create Account</b-button></router-link>
    </b-form>
  </div>
</template>

<script>

export default {
  
  props: {
    apiURL: String,
    userId: Number
  },
  data() {
    return {
      signedIn: false,
      regForm: {
        username: "",
        password: "",
        confirmPassword: "",
        role: "user"
      }
    };
  },
  methods: {
    
    submitForm() {
      JSON.stringify(this.regForm);
    },
     createUser() {
      fetch( this.apiURL + "register",{
        method: 'POST',
        headers: {
          "Content-Type": "application/json"
        },
        body: JSON.stringify(this.regForm)
      })
      .then((response) => {
        if(response.ok) {
          this.$emit("Create Account");
        }
      })
      .catch((err) => console.error(err));
    },
    signOn(){
      this.signedIn = true;
      <p>Welcome {{username: ""}}</p>
    }
  },
  computed: {
    isValidForm() {
      return this.regForm.username != '' && this.regForm.password != '' && this.regForm.confirmPassword != '';
    },
  }
};
</script>

<style scoped>
.container {
  width: 50%;
  background-color: aliceblue;
  padding: 10px;
}
</style>
