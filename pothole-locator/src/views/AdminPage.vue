<template>

  <div class="content" id="container">

    <h3 id="welcome">Welcome to the admin page.</h3>
    <h4>Select a pothole to view details, update its status, or schedule it for repair. 
        Additionally, selecting a pothole on the map will highlight it in the list. 
        Select the marker's ID to schedule it for repair.</h4>

    <div id="list">
        <table>
      <tr>
        <th>Marker ID</th>
        <th>Reported By</th>
        <th>Reported On</th>
        <th>Severity</th>
        <th>Comments</th>
        <th>Delete</th>
      </tr>
      <tr class="selected" v-for="marker in markers" :key="marker.id">
        <td>{{marker.id}}</td>
        <td>{{marker.userName}}</td>
        <td>{{marker.reportDate.toString()}}</td>
        <td>{{marker.rating}}</td>
        <td>{{marker.comments}}</td>
        <td><button type="delete" v-on:click="deleteMarker(marker.id)">delete</button></td>
      </tr>
    </table>
    </div>
    <div id="map">
        <pothole-map ref="potholeMap" style="height: 600px; width: 525px;" :markers="markers" />
    </div>

  </div>


</template>

<script>


import PotholeMap from '@/components/PotholeMap.vue'

import auth from "@/auth.js";


export default {
  props: {
    apiURL: String,
   
  },
  data() {
    return {

      showForm: false,
      mPosition: Object,
      rating: "",
      comments: "",
       markers: []
    };
  },
  components: {
    PotholeMap,

 },
 
  methods: {
    deleteMarker(id) {
        let result = confirm("Admin wait are you sure you want to delete this specific marker?");
        if (result) {
            fetch(this.apiURL + "markers/" + `${id}`, {
                method: 'DELETE',
                headers: {
                    Accept: "application/json",
                    "Content-Type": "application/json",
                    Authorization: "Bearer " + auth.getToken()
                },
               
            }) 
        .then(response => {
          if (response.ok) {
            const index = this.markers.map(marker => marker.id).indexOf(id);
            this.markers.splice(index, 1);
          }
        })
        .catch(err => console.error(err));
      }
     }
    },
  created() {
    fetch(this.apiURL + "markers")
      .then(response => response.json())
      .then(parsedData => (this.markers = parsedData))
      .catch(err => console.log(err));
  }
}
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
tr th {
  padding: 10px;
  border: 1px solid black;
}
tr td {
  padding: 10px;
  border: 1px solid black;
}
.selected {
  background-color: rgba(149,38,38,0.7);
}
</style>
