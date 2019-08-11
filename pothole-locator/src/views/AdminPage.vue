<template>
  <div class="content" id="container">
    <pothole-list :markers="markers"></pothole-list>
  </div>
</template>

<script>
import PotholeList from "@/components/PotholeList.vue";

export default {
  props: {
    apiURL: String,
    markers: Array
  },
  data() {
    return {
      showForm: false,
      mPosition: Object,
      rating: "",
      comments: ""
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
