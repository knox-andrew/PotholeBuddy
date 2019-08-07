<template>
    <div>
    <form action="Submit">
        <input type="text" placeholder="Username" required>
        <input type="password" placeholder="Password" required>
        <button type="submit">Submit</button>
        </form>
       
    </div>
</template>

<script>

export default {
   props: {
        apiPath: String
    },
    data() {
        return {
            users: [],
            searchText: ''
        }
    },
    methods: {
        
    }

}
</script>

<style>

</style>
