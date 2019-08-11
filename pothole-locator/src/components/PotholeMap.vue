<template>
  <gmap-map :center="center" :zoom="11" @click="mapClicked">
    <gmap-info-window
      :options="infoOptions"
      :position="infoWindowPos"
      :opened="infoWinOpen"
      @closeclick="infoWinOpen=false"
    >
      Severity: {{severity}}
      <br />
      Additional Info: {{comments}}
    </gmap-info-window>

    <gmap-marker
      :key="i"
      v-for="(m,i) in markers"
      :position="getPosition(m)"
      :clickable="true"
      @click="toggleInfoWindow(m,i)"
    ></gmap-marker>
  </gmap-map>
</template>

<script>
export default {
  props: {
    markers: Array,
    canReport: Boolean
  },
  data() {
    return {
      center: { lat: 39.151898, lng: -84.4676563 },
      severity: "",
      comments: "",
      infoWindowPos: null,
      infoWinOpen: false,
      currentMidx: null,
      //optional: offset infowindow so it visually sits nicely on top of our marker
      infoOptions: {
        pixelOffset: {
          width: 0,
          height: -35
        }
      }
      /*
      EXAMPLE ROADS API
      nearRoadsApi = "https://roads.googleapis.com/v1/nearestRoads?points=60.170880,24.942795|60.170879,24.942796|60.170877,24.942796&key=YOUR_API_KEY",
      */
    };
  },
  methods: {
    getPosition(marker) {
      return { lat: marker.latitude, lng: marker.longitude };
    },
    mapClicked(event) {
      if (this.canReport) {
        const marker = {
          latitude: event.latLng.lat(),
          longitude: event.latLng.lng()
        };
        this.$emit("mapClicked", marker);
      }
    },
    toggleInfoWindow: function(marker, idx) {
      this.infoWindowPos = this.getPosition(marker);
      this.severity = marker.rating;
      this.comments = marker.comments;
      //check if its the same marker that was selected if yes toggle
      if (this.currentMidx == idx) {
        this.infoWinOpen = !this.infoWinOpen;
      }
      //if different marker set infowindow to open and reset current marker index
      else {
        this.infoWinOpen = true;
        this.currentMidx = idx;
      }
    }
  },
  created() {
    //document.addEventListener('DOMContentLoaded')
  }
};
</script>
