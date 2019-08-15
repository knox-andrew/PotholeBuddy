<template>
  <div class="content" id="container">
    <h4 id="welcome">
      Select a pothole to view details.
      Additionally, selecting a pothole on the map will highlight it in the list, and
      selecting the marker's ID will pan the map to that marker.
    </h4>
    <h5 id="tracking">Currently Tracking {{markers.length}} Potholes.</h5>

    <div id="list">
      <table>
        <tr>
          <th>Marker ID</th>
          <th>Reported By : <br> yyyy-mm-dd</th>
          <th>To be repaired <br> yyyy-mm-dd</th>
          <th>Severity</th>
          <th>Delete / Schedule</th>
        </tr>
        <tr
          :class="[marker.id === selectedRow ? 'selected' : '']"
          v-for="marker in markers"
          :key="marker.id"
        >
          <td
            class="id"
            @click="selectedMarker = marker, selectedRow = marker.id"
          >{{marker.id}}</td>
          <td>{{`${marker.userName} on `}} <br> {{`${marker.reportDate}`}}</td>
          <td>{{marker.repairDate != null ? `${marker.repairDate}` : 'Not Scheduled'}}</td>
          <td>{{marker.rating}}</td>
          <td>
            <b-button
              class="t-button"
              size="sm"
              variant="dark"
              @click="showReportForm(marker.id, marker), selectedRow = marker.id, selectedMarker = marker"
            >{{marker.repairDate != null ? 'reschedule' : 'schedule'}}</b-button>
            <br>
            <b-button
              class="t-button"
              size="sm"
              type="delete"
              variant="danger"
              @click="deleteMarker(marker.id)"
            >delete</b-button>
          </td>
        </tr>
      </table>
    </div>
    <div id="placeholder"></div>
    <div id="map">
      <pothole-map
        ref="potholeMap"
        style="height: 53vh; width: 37vw;"
        :selectedMarker="selectedMarker"
        :markers="markers"
        @mSelected="markerSelected"
      />
    </div>
    <div id="repair-form" v-if="showForm === true">
      <admin-form v-on:wasCanceled="hideForm" v-on:submitted="updateMarker"></admin-form>
    </div>
  </div>
</template>

<script>
import PotholeMap from "@/components/PotholeMap.vue";
import AdminForm from "@/components/AdminForm.vue";
import auth from "@/auth.js";

export default {
  props: {
    apiURL: String,
    markers: Array
  },
  data() {
    return {
      showForm: false,
      mPosition: Object,
      rating: "",
      comments: "",
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
      let result = confirm(`WAIT! Are you sure you want to delete marker with ID of ${id}?`);
      if (result) {
        fetch(this.apiURL + "markers/" + `${id}`, {
          method: "DELETE",
          headers: {
            Accept: "application/json",
            "Content-Type": "application/json",
            Authorization: "Bearer " + auth.getToken()
          }
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
      marker.repairDate = repairDate;
      fetch(this.apiURL + "markers/" + `${id}`, {
        method: "PUT",
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
        .then((this.showForm = false));
    },
    showReportForm(id, marker) {
      this.markerID = id;
      this.marker = marker;
      this.showForm = true;
    },
    hideForm() {
      this.showForm = false;
    }
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
  justify-content: space-around;
}
#map {
  padding: 30px;
  width: 45%;
  position: fixed;
  left: 55vw;
  top: 25vh;
}
#placeholder {
  height: 100%;
  width: 40vw;
  padding: 30px;
}
#list {
  padding: 30px;
  width: 51vw;
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
  background-color: rgba(120, 120, 120, 0.7);
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
  position: fixed;
  border-radius: 10px;
  height: 40vh;
  width: 45vw;
}
#tracking {
  width: 100%;
}
</style>
