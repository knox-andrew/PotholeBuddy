<template>
  <div class="content" id="container">
    <div>
        <h3>
            Welcome to the viewing page.
            Each marker on the map represents a reported pothole.
            You can click on a marker to view details about the pothole.
        </h3>
    </div>
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
  flex-direction: column;
  justify-content: space-between;
  flex-wrap: wrap;
}
#map {
  padding: 30px;
  
}
</style>
