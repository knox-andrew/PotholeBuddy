<template>
  <div class="content" id="container">
    <header>
        <h3 id="welcome">Welcome to the viewing page.</h3>
        <h4>Each marker on the map represents a reported pothole.
        You can click on a marker to view details about the pothole.</h4>
      
    </header>
    <div id="view-map">
      <pothole-map :canReport="canReport" :markers="markers" style="width: 1225px; height: 550px"></pothole-map>
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
    apiURL: String,
    markers: Array
  },
  data() {
    return {
      canReport: false
    };
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
#view-map {
  padding: 30px;
}
#welcome {
    color: rgb(132,26,26);
}
</style>
