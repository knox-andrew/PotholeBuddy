import Vue from 'vue'
import Router from 'vue-router'
import LandingPage from './views/LandingPage.vue'
import LoginPage from './views/LoginPage.vue'
import RegisterPage from './views/RegisterPage.vue'
import MapView from './views/MapView.vue'
import ReportPage from './views/ReportPage.vue'
import AdminPage from './views/AdminPage.vue'

Vue.use(Router)

export default new Router({
  mode: 'history',
  routes: [
    {
      path: '/',
      name: 'landing-page',
      component: LandingPage
    },
    {
      path: '/login',
      name: 'login',
      component: LoginPage
    },
    {
      path: '/register',
      name: 'register',
      component: RegisterPage
    },
    {
      path: '/view',
      name: 'anonymous-view',
      component: MapView
    },
    {
      path: '/report',
      name: 'report',
      component: ReportPage
    },
    {
      path: '/admin',
      name: 'administrator',
      component: AdminPage
    }
  ]
})
