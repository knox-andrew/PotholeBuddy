<template>
  <div class="content" id="container">
    <div id="map">
      <pothole-map :canReport="canReport" :markers="markers" style="width: 1225px; height: 600px"></pothole-map>
    </div>
  </div>
</template>

<script>
import PotholeMap from "@/components/PotholeMap.vue";

export default {
  components: {
    PotholeMap
  },
  props: {
    apiURL: String
  },
  data() {
    return {
      markers: [],
      canReport: false
    };
  },
  created() {
    fetch(this.apiURL + "markers")
      .then(response => response.json())
      .then(parsedData => (this.markers = parsedData))
      .catch(err => console.log(err));
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
  width: 100%;
}
</style>
