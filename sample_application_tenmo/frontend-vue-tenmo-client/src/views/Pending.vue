<template>
    <div>
        <h1>Pending</h1>
        <transfer-list transfer="pending"/>
    </div>
</template>

<script>
import TransferList from '../components/TransferList.vue'

export default {
  components: { TransferList },

}
</script>

<style>

</style>