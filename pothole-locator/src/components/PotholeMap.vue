<template>
<gmap-map
:center="center"
:zoom="7"
style="width: 600px; height: 675px"
@click="addMarker"
>
<gmap-marker
:key="index"
v-for="(m, index) in markers"
:position="m.position"
:clickable="true"
:draggable="true"
@dblclick="center=m.position"
@click="showInfo"
></gmap-marker>
</gmap-map>
</template>

<script>
import * as VueGoogleMaps from 'vue2-google-maps';
import Vue2 from 'vue';

Vue2.use(VueGoogleMaps, {
    load: {
        key: 'AIzaSyDgZpARagOEw8JfMFmUVHDYwfldSM3-7NE',
        v: '3.',
        libraries: 'places', //// If you need to use place input
    }
});

export default {
    data () {
        return {
            center: {lat: 39.151898, lng: -84.4676563},
            markers: []
            
        }
    },
    methods: {
        addMarker(event) {
            const lat = event.latLng.lat();
            const lng = event.latLng.lng();
            this.markers.push({position: {lat: lat, lng: lng}});
        },
        removeMarker(event) {
            const lat = event.latLng.lat();
            const lng = event.latLng.lng();
            this.markers.pop({position: {lat: lat, lng: lng}});
        }
    }
}
</script>
