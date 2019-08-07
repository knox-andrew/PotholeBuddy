<template>

<gmap-map
:center="center"
:zoom="7"
style="width: 600px; height: 600px"
@click="mapClicked"
>
      <gmap-info-window :options="infoOptions" :position="infoWindowPos" :opened="infoWinOpen" @closeclick="infoWinOpen=false">
        {{infoContent}}
      </gmap-info-window>

      <gmap-marker :key="i" v-for="(m,i) 
      in markers" :position="m.position" :clickable="true" @click="toggleInfoWindow(m,i)">
      </gmap-marker>
</gmap-map>

</template>

<script>
export default {
  props: {
    markers: Array
  },
  data () {
    return {
      center: {lat: 39.151898, lng: -84.4676563},
      infoContent: "this is a spot",
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
    };
  },
  methods: {
    addMarker(event) {
      const marker = {
        lat: event.latLng.lat(),
        lng: event.latLng.lng()
      };
      this.markers.push({ position: marker });
    },
    mapClicked(event) {
      const marker = {
        lat: event.latLng.lat(),
        lng: event.latLng.lng()
      };
      this.$emit("mapClicked", marker);
      this.$emit("formShowing", this.infoWinOpen);
    },
    removeMarker(event) {
      const lat = event.latLng.lat();
      const lng = event.latLng.lng();
      this.markers.pop({ position: { lat: lat, lng: lng } });
    },
    toggleInfoWindow: function(marker, idx) {
      this.infoWindowPos = marker.position;

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
  }
};
</script>
