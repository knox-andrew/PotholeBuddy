<template>
  <div id="container">
    <div id="map">
        <h1>Map goes here</h1>
        <pothole-map></pothole-map>
    </div>

    <div id="form">
      <h1>form or whatever goes here</h1>
    </div>
  </div>
</template>

<script>
import PotholeMap from '@/components/PotholeMap.vue'
export default{
    components: {
        PotholeMap
    },
    }
</script>

<style>

#container {
    display: flex;
    flex-direction: row;
    justify-content: space-around;
}
#map {
    padding: 10px;
}
#form {
    padding: 10px;
}
#map {
    height: 100%;
}

</style>
