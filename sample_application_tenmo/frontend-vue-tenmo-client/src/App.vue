<template>
  <div id="app">
    <header>
      <div id="icon"><img src="./img/tenmo_icon.png" /></div>
      <div>
        <span>{{$store.state.user.username || 'Logged out'}}</span>
      </div>
    </header>
    <nav>
        <router-link v-bind:to="{ name: 'home' }">Home</router-link>
        <router-link v-bind:to="{ name: 'logout' }" v-if="$store.state.token != ''">Logout</router-link>
        <div>Other</div>
    </nav>
    <main>
      <router-view />
    </main>
    <footer>
      <div>Footer</div>
    </footer>
  </div>
</template>

<script>
export default {

}
</script>

<style>

</style>