<template>
  <div class="content" id="container">

    <div id="list">
        <pothole-list :markers="markers"></pothole-list>
    </div>
    <div id="map">
        <pothole-map style="height: 600px; width: 525px;" :markers="markers" />
    </div>

  </div>
</template>

<script>
import PotholeList from '@/components/PotholeList.vue'
import PotholeMap from '@/components/PotholeMap.vue'

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
    PotholeList,
    PotholeMap
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
  justify-content: space-around;
}
#map {
  padding: 30px;
  height: 100%;
  width: 45%;
}
#list {
  padding: 30px;
  width: 55%;
}
.halfMap {
  height: 600px;
  width: 600px;
}
</style>
