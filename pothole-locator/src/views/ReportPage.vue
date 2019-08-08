<template>
  <div class="content" id="container">
    <div id="map">
        <pothole-map v-bind:class="[showForm ? 'halfMap' : 'fullScreen']" v-on:mapClicked="mapClicked($event)" :markers="markers" :canReport="canReport"></pothole-map>
    </div>

    <div id="form">
      <user-form v-if="showForm" v-on:wasCanceled="removeMarker" v-on:submitted="addMarker"></user-form>
    </div>
  </div>
</template>

<script>
import PotholeMap from '@/components/PotholeMap.vue'
import UserForm from '@/components/UserForm.vue'
import auth from '@/auth.js'

export default{
    props: {
        apiURL: String
    },
    data() {
        return {
            showForm: false,
            markers: [],
            mPosition: Object,
            currentUser: Object,
            rating: '',
            comments: '',
            canReport: true,
        }
    },
    components: {
        PotholeMap,
        UserForm
    },
    methods: {
        mapClicked(marker) {
            this.mPosition = marker;
            this.showForm = true;
        },
        removeMarker() {
            this.showForm = false;
        },
        addMarker(formData) {

            const marker = {
                comments: formData.comments,
                latitude: parseFloat(this.mPosition.latitude),
                longitude: parseFloat(this.mPosition.longitude),
                rating: formData.rating,
                userId: auth.getUser().uid
            }

            this.markers.push(marker);

            fetch(this.apiURL + "markers", {
                method: 'POST',
                headers: {'Content-Type': 'application/json'},
                body: JSON.stringify(marker)
            });

            this.showForm = false;
        }
    },
     
    created() {
        this.canReport = true;
        fetch(this.apiURL + "markers")
            .then(response => response.json())
            .then(parsedData => (this.markers = parsedData))
            .catch(err => console.log(err));
    }
}
</script>

<style>

* {
    text-align: center;
}
#container {
    display: flex;
    flex-direction: row;
    justify-content: space-between;
}
#map {
    padding: 30px;
    height: 100%;
    width: 45%;
}
#form {
    padding: 30px;
    width: 45%;
}
.fullScreen {
    height: 600px;
    width: 1225px;
}
.halfMap {
    height: 600px;
    width: 600px;
}

</style>
