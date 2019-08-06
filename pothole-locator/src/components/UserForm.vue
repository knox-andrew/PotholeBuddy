<template>
    <div>
        <form action="submit">
            <div>
                <select value="severity">
                    <option value="1-bad">Minor</option>
                    <option value="2-bad">Moderate</option>
                    <option value="3-bad">Severe</option>
                    <option value="4-bad">Serious</option>
                    <option value="5-bad">Critical</option>
                </select>
            </div>
            <div>
                <input type="text" placeholder="additional comments...">
            </div>
            <div>
                <button type="submit"> Submit Report</button>
            </div>
        </form>
    </div>
</template>

<script>
export default {

}
</script>

<style>

</style>
