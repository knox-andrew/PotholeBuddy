<template>
  <div class="container">
    <b-form @submit.prevent="submitForm">
      <b-form-group id="input-group-1" label="Email:" label-for="input-1">
        <b-form-input
          id="input-1"
          v-model="regForm.email"
          type="email"
          required
          placeholder="Enter email"
        />
      </b-form-group>

      <b-form-group id="input-group-2" label="Password:" label-for="input-2">
        <b-form-input id="input-2" v-model="regForm.password" type="password" required />
      </b-form-group>
      <b-form-group id="input-group-3" label="Confirm Password:" label-for="input-3">
        <b-form-input id="input-3" v-model="regForm.confirmPassword" type="password" required />
      </b-form-group>
      <b-button>Create Account</b-button>
    </b-form>
  </div>
</template>

<script>
export default {
  data() {
    return {
      regForm: {
        email: "",
        password: "",
        confirmPassword: ""
      }
    };
  },
  methods: {
    submitForm() {
      JSON.stringify(this.regForm);
    }
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
