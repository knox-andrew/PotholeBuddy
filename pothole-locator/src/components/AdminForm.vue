<template>
    <div>
        <h2>Pothole Report Form</h2>
        <b-form>
            <b-form-group label="Severity Level" label-for="severity">
                <b-form-select v-model="severity" id="severity">
                    <option value="Minor">Minor</option>
                    <option value="moderate">Moderate</option>
                    <option value="serious">Serious</option>
                    <option value="severe">Severe</option>
                    <option value="critcal">Critical</option>
                </b-form-select>
            </b-form-group>
            <b-form-group label="Comments" label-for="comments">
                <b-form-input type="text" placeholder="additional comments..." id="comments" v-model="comments"/>
            </b-form-group>
            <b-form-group label="Report Pothole?">
                <button type="submit" @click.prevent="emitForm">Submit Report</button>
                <button @click.prevent="$emit('wasCanceled')">Cancel</button>
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
                severity: '',
                comments: ''
            }
        },
        methods: {
            emitForm() {
                const formData = {
                    rating: this.severity,
                    comments: this.comments
                }
                this.$emit("submitted", formData);
            }
        }
    }
</script>

<style>

</style>
