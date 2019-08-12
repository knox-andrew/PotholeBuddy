<template>
  <div>
    <table id="list-comp">
      <tr>
        <th>Marker ID</th>
        <th>Reported By</th>
        <th>Reported On</th>
        <th>Severity</th>
        <th>Comments</th>
        
      </tr>
      <tr v-for="marker in markers" :key="marker.id">
        <td>{{marker.id}}</td>
        <td>{{marker.userName}}</td>
        <td>{{marker.reportDate.toString()}}</td>
        <td>{{marker.rating}}</td>
        <td>{{marker.comments}}</td>
        <td><button type="delete" v-on:click="deleteMarker(markers.userId)">delete</button></td>
      </tr>
    </table>
  </div>
</template>

<script>

export default {
  props: {
    apiURL: String,
    markers: Array
  },
  data() {
    return {
      
    }
  },
  methods: {
    deleteMarker(userId) {
        let result = confirm("Admin wait are you sure you want to delete this specific marker?");
        
   
      this.markers.splice(index, 1);
        if (result) {
           fetch(this.apiURL + "markers",{
        method: 'DELETE',
        headers: {
          "Content-Type": "application/json"
        },
        body: JSON.stringify(this.marker)
      })
      .then((response) => {
        if(response.ok) {
        
          const index = this.markers.map(marker => marker.userId).indexOf(marker.userId);
          this.markers.splice(index,1);
        }
      })
      .catch((err) => console.error(err));
      }
     },
    },
  created() {
    fetch(this.apiURL + "markers")
      .then(response => response.json())
      .then(parsedData => (this.markers = parsedData))
      .catch(err => console.log(err));
  }
}
</script>

<style scoped>

tr th {
  padding: 10px;
  border: 1px solid black;
}
tr td {
  padding: 10px;
  border: 1px solid black;
}

</style>
