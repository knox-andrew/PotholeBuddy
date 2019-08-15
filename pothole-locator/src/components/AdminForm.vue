<template>
    <div>
        <h2>Schedule Repair Form</h2>
        <b-form>
            <b-form-group label="Date to be Repaired on" label-for="repair-date">
                <b-form-input id="input-live" v-model="repairDate" :state="dateState" type="date" required="true"></b-form-input>
            </b-form-group>
            <b-form-group label="Schedule Repair?">
                <b-button variant="dark" @click.prevent="emitRepairForm(repairDate)">schedule</b-button>
                <b-button @click.prevent="$emit('wasCanceled')" variant="danger">cancel</b-button>
            </b-form-group>
        </b-form>
    </div>
</template>

<script>
    export default {
        props: {
            repairDate: Date
        },
        computed: {
            dateState() {
                return this.repairDate != null ? true : false;
            }
        },
        data () {
            return {
                center: {lat: 39.151898, lng: -84.4676563},
                markers: []
            }
        },
        methods: {
            emitRepairForm(repairDate) {
                if (repairDate != null) {
                    this.$emit('submitted', repairDate);
                } else {
                    alert('please fill out this field');
                }
            }
        }
    }
</script>

<style>

</style>
