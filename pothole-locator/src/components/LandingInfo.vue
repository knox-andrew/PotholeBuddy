<template>
    <div class="content">

    <h1 id="welcome">Welcome to our Pothole Locator Application</h1>
    <h2 id="info">
         This is an application dedicated to preventing automotive mishaps caused by pesky potholes. <br>
         With this application, anyone can view reported potholes in their area, and logged in users can report potholes they find along their travels for anyone to view, and avoid.
    </h2>
    <p id="user-info">
        If you wish to view potholes, you may 
        <router-link :to="{name: 'anonymous-view'}">continue as an anonymous user</router-link>.
        <br> Otherwise, you may 
        <router-link :to="{name: 'login'}">login</router-link> or 
        <router-link :to="{name: 'register'}">register for a free account</router-link> 
        to contribute, and report any potholes you find.
    </p>
    
    </div>
</template>

<script>
export default {
    
}
</script>

<style>

.content {
    margin: 30px;
}
#info {
    line-height: 1.5;
}
#user-info {
    line-height: 1.5;
    font-size: 1.2em;
}

</style>
