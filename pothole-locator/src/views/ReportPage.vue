<template>
  <div class="content" id="container">
    <h3>
      Welcome to the reporting page.
      <br />Each marker on the map represents a reported pothole.
      You can click on a marker to view details about the pothole.
      You can also click on the map where you would like to report a pothole.
    </h3>
    <div id="map">
      <pothole-map
        v-bind:class="[showForm ? 'halfMap' : 'fullScreen']"
        @mapClicked="mapClicked($event)"
        :markers="markers"
        :canReport="canReport"
        :showTempMarker="showTempMarker"
      ></pothole-map>
    </div>

    <div id="form">
      <user-form v-if="showForm" v-on:wasCanceled="removeMarker" @submitted="submitted"></user-form>
    </div>
  </div>
</template>

<script>
import PotholeMap from "@/components/PotholeMap.vue";
import UserForm from "@/components/UserForm.vue";
import auth from "@/auth.js";

export default {
  props: {
    apiURL: String,
    markers: Array
  },
  data() {
    return {
      showTempMarker: false,
      showForm: false,
      tempMarker: Object,
      currentUser: Object,
      rating: "",
      comments: "",
      canReport: true
    };
  },
  components: {
    PotholeMap,
    UserForm
  },
  methods: {
    mapClicked(tempMarker) {
      this.tempMarker = {
        latitude: tempMarker.latitude,
        longitude: tempMarker.longitude
      };
      this.showTempMarker = true;
      this.showForm = true;
    },
    removeMarker() {
      this.showTempMarker = false;
      this.showForm = false;
    },
    submitted(formData) {
      const newMarker = {
        latitude: this.tempMarker.latitude,
        longitude: this.tempMarker.longitude,
        comments: formData.comments,
        rating: formData.rating,
        userId: auth.getUser().uid
      };

      fetch(this.apiURL + "markers", {
        method: "POST",
        headers: { "Content-Type": "application/json" },
        body: JSON.stringify(newMarker)
      });

      this.markers.push(newMarker);
      this.showForm = false;
      this.showTempMarker = false;
    }
  }
};
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
