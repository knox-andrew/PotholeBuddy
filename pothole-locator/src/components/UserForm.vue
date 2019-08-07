<template>
    <div>
        <h2>Pothole Report Form</h2>
        <b-form>
            <b-form-group label="Severity Level" label-for="severity">
                <b-form-select value="severity" id="severity">
                    <option value="minor">Minor</option>
                    <option value="moderate">Moderate</option>
                    <option value="serious">Serious</option>
                    <option value="severe">Severe</option>
                    <option value="critcal">Critical</option>
                </b-form-select>
            </b-form-group>
            <b-form-group label="Comments" label-for="comments">
                <b-form-input type="text" placeholder="additional comments..." id="comments"/>
            </b-form-group>
            <div>
                <button type="submit" @click="addMarker"> Submit Report</button>
                <button @click.prevent="$emit('wasCanceled')">Cancel</button>
            </div>
        </b-form>
    </div>
</template>

<script>
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
            }
        }
    }
</script>

<style>

</style>
