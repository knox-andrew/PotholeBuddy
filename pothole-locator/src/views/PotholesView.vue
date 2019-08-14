<template>
  <b-container class="p-5 mt-2 background">
    <b-row>
      <b-col cols="12" md="7" class="mb-2">
        <pothole-map
          class="map"
          @mapClicked="mapClicked($event)"
          :markers="markers"
          :canReport="inReportingMode"
          :showTempMarker="showTempMarker"
        ></pothole-map>
      </b-col>
      <b-col cols="12" md="5">
        <div v-if="reportStage === 0">
          <div v-if="canReport">
            <b-card>
              <b-card-header>
                <strong>Want to report a pothole?</strong>
              </b-card-header>
              <b-card-body>
                <b-button
                  @click.prevent="reportMode"
                  variant="primary"
                  class="mr-4"
                >Click me to report!</b-button>
              </b-card-body>
            </b-card>
            <b-alert class="mt-4" :show="alertMessage != null" dismissible>{{alertMessage}}</b-alert>
          </div>
          <div v-else>
            <b-card>
              <b-card-header>
                <strong>Want to report a pothole?</strong>
              </b-card-header>
              <b-card-body>
                <b-button @click.prevent="goToLogin" variant="primary" class="mr-4 mb-2">Login</b-button>
                <b-button @click.prevent="goToRegister" variant="primary">Register</b-button>
              </b-card-body>
            </b-card>
          </div>
        </div>
        <div v-else-if="reportStage === 1">
          <b-card>
            <b-card-header>
              <strong>Want to report a pothole?</strong>
            </b-card-header>
            <b-card-body>
              <p>Click a location on the map to place a pothole pin</p>
            </b-card-body>
          </b-card>
        </div>
        <div v-else>
          <b-card-header>
            <strong>Want to report a pothole?</strong>
          </b-card-header>
          <b-card-body>
            <user-form @canceled="canceled" @submitted="submitted"></user-form>
          </b-card-body>
        </div>
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
      reportStage: 0,
      showTempMarker: false,
      tempMarker: Object,
      currentUser: Object,
      canReport: false,
      inReportingMode: false,
      alertMessage: null
    };
  },
  components: {
    PotholeMap,
    UserForm
  },
  methods: {
    reportMode() {
      this.reportStage = 1;
      this.inReportingMode = true;
    },
    goToLogin() {
      this.$router.push("/login");
      this.$forceUpdate();
    },
    goToRegister() {
      this.$router.push("/register");
      this.$forceUpdate();
    },
    mapClicked(tempMarker) {
      this.tempMarker = {
        latitude: tempMarker.latitude,
        longitude: tempMarker.longitude
      };
      this.showTempMarker = true;
      this.reportStage = 2;
    },
    removeMarker() {
      this.showTempMarker = false;
    },
    canceled() {
      this.showTempMarker = false;
      this.inReportingMode = false;
      this.reportStage = 0;
      this.alertMessage = null;
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
      this.showTempMarker = false;
      this.inReportingMode = false;
      this.reportStage = 0;
      this.alertMessage = "Success: Pothole reported!";
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
