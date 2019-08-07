<template>
  <div class="content" id="container">
    <div id="map">
        <pothole-map v-on:mapClicked="mapClicked($event)" :markers="markers"></pothole-map>
    </div>

    <div id="form">
      <user-form v-if="showForm" v-on:wasCanceled="removeMarker" v-on:submitted="addMarker"></user-form>
    </div>
  </div>
</template>

<script>
import PotholeMap from '@/components/PotholeMap.vue'
import UserForm from '@/components/UserForm.vue'

export default{
    data() {
        return {
            showForm: false,
            markers: [],
            mPosition: Object
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
            this.showForm=false;
            this.markers.pop();
        },
        addMarker() {
            this.markers.push({position: this.mPosition});
            this.showForm = false;
        }
    },
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

</style>
