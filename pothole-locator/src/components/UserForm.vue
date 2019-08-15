<template>
  <div>
    <h2>Pothole Report Form</h2>
    <b-form @submit.prevent="submitted" @reset.prevent="canceled">
      <b-form-group label="Severity Level" label-for="severity">
        <b-form-select v-model="form.severity" :options="severityLevels" id="severity" required></b-form-select>
      </b-form-group>
      <b-form-group label="Comments" label-for="comments">
        <b-form-input type="text" placeholder="additional comments..." v-model="form.comments" />
      </b-form-group>
      <b-form-group label="Report Pothole?">
        <b-button type="submit" variant="primary" class="mr-2">Submit Report</b-button>
        <b-button type="reset" variant="primary">Cancel</b-button>
      </b-form-group>
    </b-form>
  </div>
</template>

<script>
export default {
  data() {
    return {
      form: {
        severity: null,
        comments: ""
      },
      severityLevels: [
        { text: "Choose Severity", value: null },
        "Minor",
        "Moderate",
        "Serious",
        "Severe",
        "Critical"
      ]
    };
  },
  methods: {
    submitted() {
      this.$emit("submitted", this.form);
    },
    canceled() {
      this.$emit("canceled");
    }
  }
};
</script>

<style>
</style>
