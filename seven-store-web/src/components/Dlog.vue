<template>
  <div v-if="isVisible" class="modal-overlay" @click="closeModal">
    <div class="modal-content" @click.stop>
      <button @click="closeModal">关闭</button>
      <div v-html="content"></div>
    </div>
  </div>
</template>

<script>
import { ref } from 'vue';

export default {
  setup() {
    const isVisible = ref(false);
    const content = ref('');

    const openModal = (newContent) => {
      content.value = newContent;
      isVisible.value = true;
    };

    const closeModal = () => {
      isVisible.value = false;
    };

    return {
      isVisible,
      content,
      openModal,
      closeModal
    };
  }
};
</script>

<style>
.modal-overlay {
  position: fixed;
  top: 0;
  left: 0;
  right: 0;
  bottom: 0;
  background: rgba(0, 0, 0, 0.5);
  display: flex;
  justify-content: center;
  align-items: center;
}

.modal-content {
  background: white;
  padding: 20px;
  border-radius: 5px;
}
</style>
