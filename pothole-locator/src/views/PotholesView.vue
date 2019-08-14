<template>
  <b-container class="p-5 mt-2 background">
    <b-row>
      <b-col cols="12" :md="showForm ? 6 : 12" class="mb-2">
        <pothole-map
          class="map"
          @mapClicked="mapClicked($event)"
          :markers="markers"
          :canReport="canReport"
          :showTempMarker="showTempMarker"
        ></pothole-map>
      </b-col>

      <b-col v-if="showForm" cols="12" :md="showForm ? 6 : 0">
        <user-form @canceled="canceled" @submitted="submitted"></user-form>
      </b-col>
    </b-row>
  </b-container>
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
      canReport: false
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
    canceled() {
      this.showTempMarker = false;
      this.showForm = false;
    },
    submitted(formData) {
      const newMarker = {
        latitude: this.tempMarker.latitude,
        longitude: this.tempMarker.longitude,

        comments: formData.comments,
        rating: formData.severity,
        userId: auth.getUser().uid,
        userName: auth.getUser().sub
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
  },
  created() {
    this.canReport =
      auth.getUser() != null &&
      (auth.getUser().rol === "user" || auth.getUser().rol === "admin");
  }
};
</script>

<style scoped>
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
  width: 50vh;
}
.fullScreen {
  height: 550px;
  width: 100%;
}
.map {
  height: 500px;
}
</style>
