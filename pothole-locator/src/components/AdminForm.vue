<template>
    <div>
        <h2>Schedule Repair Form</h2>
        <b-form>
            <b-form-group label="Date to be Repaired on" label-for="repair-date">
                <b-form-input v-model="repairDate" id="repair-date" type="date"></b-form-input>
            </b-form-group>
            <b-form-group label="Schedule Repair?">
                <b-button variant="success" @click.prevent="$emit('submitted', repairDate)">Schedule</b-button>
                <b-button @click.prevent="$emit('wasCanceled')" variant="danger">Cancel</b-button>
            </b-form-group>
        </b-form>
    </div>
</template>

<script>
    export default {
        data () {
            return {
                center: {lat: 39.151898, lng: -84.4676563},
                markers: [],
                repairDate: Date
            }
        }
    }
</script>

<style>

</style>
