<template>
    <div class="content">
        <h1 id="welcome">Welcome to Pothole Buddy!</h1>
        <p>
            Developed By: Andrew Knox, Brendan Bressler, and Stephen Henn <br>
            Powered By: <img id="g-maps" src="https://img.icons8.com/color/48/000000/google-maps.png">
        </p>
        <div id="info">
            <h2>
                Helping you get from
                <img src="https://img.icons8.com/material/48/000000/marker-a.png"> to
                <img src="https://img.icons8.com/material-sharp/48/000000/marker-b.png">
                while avoiding this: 
                <img id="pothole-img" src="http://cityofinkster.com/ImageRepository/Document?documentID=1099" />
                along the way.
            </h2>
            <h3>
                With this application, anyone can view reported potholes in their area. <br>
                Logged in users can report potholes they find along their travels
                for anyone to view and avoid.
            </h3>
        </div>
        <div id="user-info">
            If you wish to view potholes in the area, 
            <router-link :to="{name: 'anonymous-view'}">click here</router-link>.
            <br> To contribute and report any potholes you find, you can 
            <router-link :to="{name: 'login'}">login</router-link> or 
            <router-link :to="{name: 'register'}">register for a free account</router-link>.
        </div>
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
#info h3 {
    line-height: 2;
    padding: 30px;
}
#user-info {
    line-height: 1.5;
    font-size: 1.5em;
    font-weight: bold;
}
#pothole-img {
    height: 70px;
    width: 70px;
}
#road-img {
    height: 300px;
}
#g-maps {
    height: 1.7rem;
}

</style>
