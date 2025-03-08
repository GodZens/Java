<template>
  <div id="app">
    <router-view /> <!-- 這樣才能顯示對應的路由組件 -->
  </div>
</template>

<script>
  export default {
    name: 'App',
  };
</script>

<style>
#app {
  font-family: Avenir, Helvetica, Arial, sans-serif;
  -webkit-font-smoothing: antialiased;
  -moz-osx-font-smoothing: grayscale;
  text-align: center;
  color: #2c3e50;
  margin-top: 60px;
}
</style>
