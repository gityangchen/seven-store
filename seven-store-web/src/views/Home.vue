

<template>
  <div style="background: white">
    <p>操作</p>
    <button @click="addCount">新增余额</button>

    <button>提交参数</button>


    <p>data</p>
    {{dataList.data}}
    <p>his</p>
    {{hisList.data}}
    <p>count</p>
    {{dataCountList.data}}
  </div>

</template>
<script setup>
import {getCurrentInstance, onMounted, ref} from "vue";

const { proxy } = getCurrentInstance()
const axios = proxy.$axios
const dataCountList = ref([])
const dataList = ref([])
const hisList = ref([])
const fetchDataCount = async () => {
  dataCountList.value = await axios.get('/draw-count')
}
const fetchDataList = async () => {
  dataList.value = await axios.get('/prize')
}

const fetchDataHis = async () => {
  hisList.value = await axios.get('/prizeHistory')
}
const addCount = async ()=>{
  const datacount =dataCountList.value.data[0]
  datacount.drawCount = datacount.drawCount+1

  await axios.put('/draw-count',datacount)
}
onMounted(ref=>{
  fetchDataCount()
  fetchDataList()
  fetchDataHis()
})
</script>

<style scoped>

</style>