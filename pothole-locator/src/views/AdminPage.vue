<template>

  <div class="content" id="container">

    <h3 id="welcome">Welcome to the admin page.</h3>
    <h4>Select a pothole to view details. 
        Additionally, selecting a pothole on the map will highlight it in the list, and 
        selecting the marker's ID will pan the map to that marker.</h4>

    <div id="list">
        <table>
      <tr>
        <th>Marker ID</th>
        <th>Reported By : On</th>
        <th>To be repaired on</th>
        <th>Severity</th>
        <th>Comments</th>
        <th>Delete / Schedule</th>
      </tr>
      <tr :class="[marker.id === selectedRow ? 'selected' : '']" v-for="marker in markers" :key="marker.id">
        <td class="id" @click="selectedMarker = {lat: marker.latitude, lng: marker.longitude}, selectedRow = marker.id">{{marker.id}}</td>
        <td>{{marker.userName}} on {{marker.reportDate.toString()}}</td>
        <td>{{marker.repairDate != null ? `${marker.repairDate.toString()}` : 'active'}}</td>
        <td>{{marker.rating}}</td>
        <td>{{marker.comments}}</td>
        <td>
          <b-button class="t-button" size="sm" type="delete" variant="danger" @click="deleteMarker(marker.id)">delete</b-button>
          <b-button class="t-button" size="sm" variant="success" @click="showReportForm(marker.id, marker)">schedule</b-button>
        </td>
      </tr>
    </table>
    </div>
    <div id="map">
        <pothole-map ref="potholeMap" style="height: 550px; width: 525px;" :selectedMarker="selectedMarker" :markers="markers" @mSelected="markerSelected"/>
    </div>
    <div id="repair-form" v-if="showForm === true">
        <admin-form v-on:wasCanceled="hideForm" v-on:submitted="updateMarker"></admin-form>
    </div>

  </div>


</template>

<script>
import PotholeMap from '@/components/PotholeMap.vue'
import AdminForm from '@/components/AdminForm.vue'
import auth from "@/auth.js"

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
      markers: [],
      selectedRow: 0,
      selectedMarker: Object,
      markerID: 0,
      marker: Object
    };
  },
  components: {
    PotholeMap,
    AdminForm
 },
 
  methods: {
    markerSelected(id) {
      this.selectedRow = id;
    },
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
     },
     updateMarker(repairDate, id, marker) {
       id = this.markerID;
       marker = this.marker;
       const array = repairDate.split('-');
       const dateArray = [];
       for(let i = 0; i < array.length; i++) {
         dateArray.push(parseInt(array[i]));
       }
       marker.repairDate = dateArray;
       fetch(this.apiURL + 'markers/' + `${id}`, {
           method: 'UPDATE',
           headers: {
             Accept: "application/json",
             "Content-Type": "application/json",
             Authorization: "Bearer " + auth.getToken()
           },
           body: JSON.stringify(marker)
         })
         .then(parsedData => {
            this.marker = parsedData;
         })
        .then(this.showForm = false)
     },
     showReportForm(id, marker) {
       this.markerID = id;
       this.marker = marker;
       let result = confirm(`Schedule pothole with id of ${id} for repair?`);
       if (result) {
         this.showForm = true;
       }
     },
     hideForm() {
       this.showForm = false;
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
  background-color: rgba(120,120,120,0.7);
}
.id {
  cursor: grab;
  color: blue;
}
.t-button {
  margin: 2px;
}
#repair-form {
  background-color: rgb(255, 255, 255);
  position: absolute;
  float: left;
  border-radius: 10px;
  height: 75%;
  width: 50%;
}
</style>
