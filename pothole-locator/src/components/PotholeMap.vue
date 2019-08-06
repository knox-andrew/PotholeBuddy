<template>
<gmap-map
:center="center"
:zoom="7"
style="width: 600px; height: 675px"
@click="addMarker"
>
 <gmap-marker 
 :if="mapLoaded"
:key="index"
v-for="(m, index) in markers"
:position="m.position"
:clickable="true"
 @click="toggleInfo(m, index)" ></gmap-marker> 
 
  <gmap-info-window
 :options="infoOptions"
 :position="infoPosition"
 :opened="infoOpened"
 :content="infoContent"
@closeclick="infoOpened=false">

 </gmap-info-window>

</gmap-map>
</template>

<script>


export default {
    data () {
        return {
            map: null,
      mapLoaded: false,
            center: {lat: 39.151898, lng: -84.4676563},
            markers: [],
           infoPosition: null,
      infoContent: null,
      infoOpened: false,
      infoCurrentKey: null,
      infoOptions: {
        pixelOffset: {
          width: 0,
          height: -35
        },
        content:
          '<div id="content">'+
      '<div id="siteNotice">'+
      '</div>'+
      '<h1 id="firstHeading" class="firstHeading">Uluru</h1>'+
      '<div id="bodyContent">'+
      '<p><b>Uluru</b>, also referred to as <b>Ayers Rock</b>, is a large ' +
      'sandstone rock formation in the southern part of the '+
      'Northern Territory, central Australia. It lies 335&#160;km (208&#160;mi) '+
      'south west of the nearest large town, Alice Springs; 450&#160;km '+
      '(280&#160;mi) by road. Kata Tjuta and Uluru are the two major '+
      'features of the Uluru - Kata Tjuta National Park. Uluru is '+
      'sacred to the Pitjantjatjara and Yankunytjatjara, the '+
      'Aboriginal people of the area. It has many springs, waterholes, '+
      'rock caves and ancient paintings. Uluru is listed as a World '+
      'Heritage Site.</p>'+
      '<p>Attribution: Uluru, <a href="https://en.wikipedia.org/w/index.php?title=Uluru&oldid=297882194">'+
      'https://en.wikipedia.org/w/index.php?title=Uluru</a> '+
      '(last visited June 22, 2009).</p>'+
      '</div>'+
      '</div>'
      }
            
        }
    },
    methods:{
   
        addMarker(event) {
      
             const marker = {
             lat: event.latLng.lat(),
             lng: event.latLng.lng()
             }
            this.markers.push({position: marker});
            
           
        },
        removeMarker(event) {
            const lat = event.latLng.lat();
            const lng = event.latLng.lng();
            this.markers.pop({position: {lat: lat, lng: lng}});
        },
        toggleInfoWindow: function(marker, idx) {
            this.infoWindowPos = marker.position;
            this.infoContent = marker.infoText;

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
}
</script>
