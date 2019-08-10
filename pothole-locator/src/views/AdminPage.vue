<template>
  <div class="content" id="container">
    <pothole-list></pothole-list>
  </div>
</template>

<script>
import PotholeList from "@/components/PotholeList.vue";
import auth from "@/auth.js";

export default {
  props: {
    apiURL: String
  },
  data() {
    return {
      showForm: false,
      mPosition: Object,
      rating: "",
      comments: "",
      canReport: true
    };
  },
  components: {
    PotholeList
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
        rating: formData.rating
      };

      this.markers.push(marker);

      fetch(this.apiURL + "markers", {
        method: "POST",
        headers: { "Content-Type": "application/json" },
        body: JSON.stringify(marker)
      });

      this.showForm = false;
    }
  },

  created() {
    if (auth.getUser().rol != "admin") {
      this.$router.push("/login");
    } else {
      this.canReport = true;
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
