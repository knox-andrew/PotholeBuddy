<template>
    <div>
    <form action="Submit">
        <input type="text" placeholder="Username" required>
        <input type="password" placeholder="Password" required>
        <button type="submit" :disabled="!isValidForm" @click="getUser()">Submit</button>
        </form>
       
    </div>
</template>

<script>

export default {
   props: {
         apiURL: String
    },
    data() {
        return {
           users: {
               username: "",
               password: ""
           }
        }
    },
    methods: {
        getUser() {
      fetch( this.apiURL + "login",{
        method: 'GET',
        headers: {
          "Content-Type": "application/json"
        },
        body: JSON.stringify(this.users)
      })
      .then((response) => {
        if(response.ok) {
          this.$emit("Submit");
        }
      })
      .catch((err) => console.error(err));
    },
        
    },
    computed: {
    isValidForm() {
      return this.users.username != '' && this.users.password != '';
    },
  }

}
</script>

<style>

</style>
