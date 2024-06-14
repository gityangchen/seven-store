<template>
  <div class="card-main">
  <div class = "card-header">

  </div>
  <div class="container">
    <div v-for="(obj,n) in cardList" :key="n" class="card" @click="flipCard(n)">
      <div :class="['inner', { flipped: flippedCards.includes(n) },{ shuffle: isShuffling }]">
        <div class="front"></div>
        <div class="back">{{obj.name}}</div>
      </div>
    </div>
  </div>
    <div class="card-footer">
      <div class="start-bnt" @click="flipAllCards">
      </div>
    </div>
    <Dlog ref="DlogRef"/>
  </div>
</template>

<script setup>
import {computed, getCurrentInstance, onMounted, ref} from 'vue'
import Dlog from "@/components/Dlog.vue";
const start = ref(false)
const DlogRef = ref()
const flippedCards = ref([])
const cardList = ref([])
const { proxy } = getCurrentInstance()
const axios = proxy.$axios

const shuffleArray = arr => {
  for (let i = arr.length - 1; i > 0; i--) {
    const j = Math.floor(Math.random() * (i + 1));
    [arr[i], arr[j]] = [arr[j], arr[i]];
  }
  return arr;
};

function rearrangeArray(arr,index,id) {
  arr = shuffleArray(arr)
  // Find the index of the object with id 7
  const index7 = arr.findIndex(item => item.id === id);
  if (index7 === -1) return arr; // If no such object, return the original array

  // Remove the object with id 7
  const [item7] = arr.splice(index7, 1);

  // Insert the object with id 7 at the index 3 position
  arr.splice(index, 0, item7);

  return arr;
}


const flipCard = async (index) => {
  if (start.value){
    start.value=false
    if (flippedCards.value.includes(index)) {
      flippedCards.value = flippedCards.value.filter(card => card !== index)
    } else {
      const data =await checkSp()
      if(data){
        cardList.value = rearrangeArray(cardList.value,index,data.id)
        flippedCards.value.push(index)
      }else {
        showModal('余额不足，请联系hugo充值!')
      }

    }
  }else {
    showModal('请点击开始抽奖按钮!')
  }
}
const isShuffling = ref(false)

const shuffleCards = () => {
  const cards = document.querySelectorAll('.card')
  cards.forEach(card => {
    card.style.setProperty('--random-x',  Math.random() * 10)
    card.style.setProperty('--random-y', Math.random() * 10)
  })
  isShuffling.value = true
  setTimeout(() => {
    isShuffling.value = false
    start.value = true
  }, 1000) // Duration of the shuffle animation
}
const flipAllCards = () => {
  if (flippedCards.value.length === 9) {
    flippedCards.value = [];
  } else {
    flippedCards.value = Array.from({ length: 9 }, (_, i) => i )
  }
  setTimeout(() => {
    fetchData()
    flippedCards.value = []
    shuffleCards()
  }, 3000)
  //
};
const checkSp = async ()=>{
  try {
    const ids = computed(() => cardList.value.map(item => item.id));
    const response = await axios.get('/prizeView/check?ids='+ids.value)
    return response.data
    // Handle the response data
  } catch (error) {
    console.error('There was an error!', error)
  }
}
const fetchData = async () => {
  try {
    const response = await axios.get('/prizeView/getShowList')
    cardList.value = response.data
    // Handle the response data
  } catch (error) {
    console.error('There was an error!', error)
  }
}
const showModal = (data) => {
  DlogRef.value.openModal('<p>'+data+'</p>');
};
onMounted(()=>{
  fetchData()
})

</script>

<style scoped>
.card-footer{
  display: flex;
  flex-wrap: wrap;
  justify-content: center;
}
.card-header{
  width: 100%;
  height: 100px;
  background-size: cover; /* 使图片覆盖整个背景 */
  background-position: center; /* 使图片居中 */
  background-repeat: no-repeat; /* 防止图片重复 */
  background-image: url("/statues/title.png");
}
.start-bnt{
  width: 150px;
  height: 150px;
  background-size: cover; /* 使图片覆盖整个背景 */
  background-position: center; /* 使图片居中 */
  background-repeat: no-repeat; /* 防止图片重复 */
  background-image: url("/statues/start.png");
}
.container {
  display: flex;
  flex-wrap: wrap;
  justify-content: center;
  gap: 10px;
}

.card {
  width: 30%;
  height: 150px;
  perspective: 1000px;
}

.inner {
  width: 100%;
  height: 100%;
  transition: transform 0.6s;
  transform-style: preserve-3d;
  position: relative;
}

.inner.flipped {
  transform: rotateY(180deg);
}

.front, .back {
  position: absolute;
  width: 100%;
  height: 100%;
  backface-visibility: hidden;
  display: flex;
  align-items: center;
  justify-content: center;
  font-weight: 900;
  font-size: 20px;
  border-radius: 15px; /* 设置圆角半径 */
  overflow: hidden; /* 防止内容溢出显示 */
  color: #1f2703;
}

.front {
  background-image: url('../../assets/card.png');
  background-size: cover; /* 使图片覆盖整个背景 */
  background-position: center; /* 使图片居中 */
  background-repeat: no-repeat; /* 防止图片重复 */
  border-radius: 15px; /* 设置圆角半径 */
  overflow: hidden; /* 防止内容溢出显示 */
}

.back {
  background-image: url('/statues/cardback.png');
  background-size: cover; /* 使图片覆盖整个背景 */
  background-position: center; /* 使图片居中 */
  background-repeat: no-repeat; /* 防止图片重复 */
  border-radius: 15px; /* 设置圆角半径 */
  overflow: hidden; /* 防止内容溢出显示 */
  transform: rotateY(180deg);
}

@keyframes shuffle {
  0% { transform: translate(0, 0); }
  25% { transform: translate(calc(20px - 40px * var(--random-x)), calc(20px - 40px * var(--random-y))); }
  50% { transform: translate(calc(-20px + 40px * var(--random-x)), calc(-20px + 40px * var(--random-y))); }
  75% { transform: translate(calc(20px - 40px * var(--random-x)), calc(20px - 40px * var(--random-y))); }
  100% { transform: translate(0, 0); }
}

.shuffle {
  animation: shuffle 1s ease-in-out;
}

</style>
