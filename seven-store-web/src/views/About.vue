

<template>
<CardIndex ref="cardIndexRef"></CardIndex>
</template>
<script setup>
import {getCurrentInstance, onMounted, ref} from 'vue'
import CardIndex from "@/views/card/CardIndex.vue";

const cardIndexRef = ref()
const { proxy } = getCurrentInstance()
const axios = proxy.$axios

const fetchData = async () => {
  try {
    const response = await axios.get('/prizeView/getShowList')
    console.log(response.data)
    // Handle the response data
  } catch (error) {
    console.error('There was an error!', error)
  }
}
onMounted(() => {
  fetchData()
  // console.log(cardIndexRef)
  // if(cardIndexRef.value){
  //   console.log(cardIndexRef.value)
  // }
})

</script>
<style scoped>

</style>