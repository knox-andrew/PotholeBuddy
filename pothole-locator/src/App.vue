<template>
  <div id="app">
    <nav class="nav">
    <ul>
      <router-link :to="{name: 'landing-page'}" tag="li" exact>Home</router-link>
      <router-link :to="{name: 'login'}" tag="li">Login</router-link>
      <router-link :to="{name: 'register'}" tag="li">Register</router-link>
      <router-link :to="{name: 'anonymous-view'}" tag="li">View Map</router-link>
      <router-link :to="{name: 'report'}" tag="li">Report View</router-link>
      <router-link :to="{name: 'administrator'}" tag="li">Admin View</router-link>
      
    </ul>
    </nav>
    <router-view/>
  </div>
</template>

<style>
body {
  margin: 0;
  padding: 0;
}
.content {
  width:800px;
  margin: 20px auto !important;
}
header {
  background-color: #E34232;
}
.nav {
  margin:0;
  padding:0;
  list-style-type: none;
  display: flex;
  justify-content: center;
}
.nav li {
  display: inline-block;
  padding:20px;
  color: #fff;
  background-color: #E34232;
}
.nav li a {
  color: #fff;
  text-transform: uppercase;
  font-size: 14px;
  text-decoration: none;
}
.nav li:hover {
  background-color: rgb(216, 216, 216);
  cursor: pointer;
  color:rgb(44, 44, 44);
}
.nav li a:hover {
  color:rgb(44, 44, 44);
}
.nav .router-link-active {
  background-color: rgb(44, 44, 44);
}
</style>
