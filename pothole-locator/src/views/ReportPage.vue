<template>
  <div class="content" id="container">
    <h3 id="welcome">Welcome to the reporting page.</h3>
    <h4>Each marker on the map represents a reported pothole.
        You can click on a marker to view details about the pothole.</h4>
    <h4>You can also click on the map where you would like to report a pothole, and fill out the short form.</h4>
    <div id="report-map">
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
#report-map {
  padding: 30px;
  height: 100%;
  width: 45%;
}
#form {
  padding: 30px;
  width: 45%;
}
.fullScreen {
  height: 550px;
  width: 1200px;
}
.halfMap {
  height: 550px;
  width: 600px;
}
</style>
