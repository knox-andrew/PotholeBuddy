<template>
  <gmap-map ref="potholeMap" :center="center" :zoom="11" @click="mapClicked">
    <gmap-info-window
      :options="infoOptions"
      :position="infoWindowPos"
      :opened="infoWinOpen"
      @closeclick="infoWinOpen=false"
    >
      Severity: {{severity}}
      <br />
      Additional Info: {{comments}}
      <br />

      To be Repaired On: {{repairDate != null ? `${repairDate}` : 'Not Scheduled'}}
    </gmap-info-window>

    <gmap-marker
      :key="i"
      v-for="(m,i) in markers"
      :position="getPosition(m)"
      :clickable="true"
      @click="toggleInfoWindow(m,i), $emit('mSelected', m.id)"
    ></gmap-marker>
    <gmap-marker
      id="tempMarker"
      v-if="showTempMarker"
      :position="{lat: tempLat, lng: tempLng}"
      :clickable="false"
      :icon="{url:'http://maps.google.com/mapfiles/ms/icons/yellow-dot.png'}"
    ></gmap-marker>
  </gmap-map>
</template>

<script>
// import auth from '@/auth.js'

export default {
  props: {
    markers: Array,
    canReport: Boolean,
    showTempMarker: Boolean,
    selectedMarker: Function
  },
  data() {
    return {
      tempLat: 0,
      tempLng: 0,
      center: { lat: 39.151898, lng: -84.4676563 },
      severity: "",
      comments: "",
      repairDate: null,
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
      this.infoWinOpen = false;
      if (this.canReport) {
        this.$refs.potholeMap.$mapObject.panTo(event.latLng);
        if (this.$refs.potholeMap.$mapObject.getZoom() != 15) {
          this.$refs.potholeMap.$mapObject.setZoom(15);
        }
        this.tempLat = event.latLng.lat();
        this.tempLng = event.latLng.lng();
        this.$emit("mapClicked", {
          latitude: this.tempLat,
          longitude: this.tempLng
        });
      }
    },
    toggleInfoWindow: function(marker, idx) {
      this.$refs.potholeMap.$mapObject.panTo(this.getPosition(marker));
      this.infoWindowPos = this.getPosition(marker);
      this.severity = marker.rating;
      this.comments = marker.comments;
      this.repairDate = marker.repairDate;
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
  },
  watch: {
    selectedMarker: function(marker) {
      if (marker != null) {
        this.toggleInfoWindow(marker, marker.id);
      }
    }
  }
};
</script>

<style>
</style>

