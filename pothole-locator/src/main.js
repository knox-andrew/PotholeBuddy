import '@babel/polyfill'
import 'mutationobserver-shim'
import Vue from 'vue'
import './plugins/bootstrap-vue'
import App from './App.vue'
import router from './router'
import * as VueGoogleMaps from 'vue2-google-maps'
import Vue2 from 'vue'

Vue2.use(VueGoogleMaps, {
    load: {
        key: 'AIzaSyDgZpARagOEw8JfMFmUVHDYwfldSM3-7NE',
        v: '3.',
        libraries: 'places', //// If you need to use place input
    }
});

Vue.config.productionTip = false

new Vue({
  router,
  render: h => h(App)
}).$mount('#app')
