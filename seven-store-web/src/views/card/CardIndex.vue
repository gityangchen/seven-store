<!-- src/App.vue -->
<template>
    <FlipCard />
</template>

<script>
import FlipCard from './FlipCard.vue'

export default {
  components: {
    FlipCard
  }
}
</script>

<style>
.card-container {
  display: grid;
  grid-template-columns: repeat(3, 1fr);
  gap: 10px;
  padding: 50px;
}
</style>
