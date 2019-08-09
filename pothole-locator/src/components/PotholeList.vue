<template>
  <div class="content">
    <table>
      <tr>
        <th>User ID</th>
        <th>Latitude</th>
        <th>Longitude</th>
        <th>Severity</th>
        <th>Comments</th>
      </tr>
      <tr v-for="marker in markers" :key="marker.id">
        <td>{{marker.userId}}</td>
        <td>{{marker.latitude}}</td>
        <td>{{marker.longitude}}</td>
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

<style>

</style>
