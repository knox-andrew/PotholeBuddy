<template>
  <div class="content" id="container">
    <div id="map">
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

* {
    text-align: center;
}
#container {
    display: flex;
    flex-direction: row;
    justify-content: space-between;
}
#map {
    padding: 30px;
    height: 100%;
    width: 45%;
}
#form {
    padding: 30px;
    width: 45%;
}

</style>
