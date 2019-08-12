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
      </tr>
    </table>
  </div>
</template>

<script>

export default {
  props: {
    markers: Array,
  },
  data() {
    return {
      
    }
  },
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
