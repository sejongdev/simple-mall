<template>
  <div>
    <!-- Fixed navbar -->
    <nav class="navbar navbar-default navbar-fixed-top">
      <div class="container">
        <div class="navbar-header">
          <router-link to="/" class="navbar-brand">Simple Mall</router-link>
        </div>
        <div id="navbar-main" class="navbar-collapse collapse">
          <ul class="nav navbar-nav">
            <li>
              <router-link to="/">Home</router-link>
            </li>
          </ul>
          <ul class="nav navbar-nav navbar-right">
            <li class="nav-item reco-uid-header">RECO_UID : {{ recoUid }}</li>
            <li>
              <router-link to="/signup" class="nav-item nav-link"> Sign Up </router-link>
            </li>
          </ul>
        </div><!--/.nav-collapse -->
      </div>
    </nav>
  </div>
</template>

<script>
export default {
    props: [
        'recoUid'
    ]
}
</script>

<style lang="sass" scoped>
.reco-uid-header
  font-size: 1.5em
  background: seagreen
  color: yellow
  padding: 10px
</style>
