import Vue from 'vue'
import Router from 'vue-router'
import LandingPage from './views/LandingPage.vue'
import LoginPage from './views/LoginPage.vue'
import RegisterPage from './views/RegisterPage.vue'
import MapView from './views/MapView.vue'
import ReportPage from './views/ReportPage.vue'
import AdminPage from './views/AdminPage.vue'
import auth from './auth'

Vue.use(Router)

const router = new Router({
  mode: 'history',
  base: process.env.BASE_URL,
  routes: [
    {
      path: '/',
      name: 'landing-page',
      component: LandingPage,
      meta: {
      requiresAuth: true,
    }
    },
    {
      path: '/login',
      name: 'login',
      component: LoginPage,
      meta: {
      requiresAuth: false,
    }
    },
    {
      path: '/register',
      name: 'register',
      component: RegisterPage,
      meta: {
      requiresAuth: false,
    }
    },
    {
      path: '/view',
      name: 'anonymous-view',
      component: MapView,
      meta: {
        requiresAuth: false,
      }
    },
    {
      path: '/report',
      name: 'report',
      component: ReportPage,
      meta: {
        requiresAuth: true,
      }
    },
    {
      path: '/admin',
      name: 'administrator',
      component: AdminPage,
      meta: {
        requiresAuth: true
      }
    }
  ]
})
router.beforeEach((to, from, next) => {
  // Determine if the route requires Authentication
  const requiresAuth = to.matched.some(x => x.meta.requiresAuth);
  const user = auth.getUser();
  
  
                                  // If it does and they are not logged in, send the user to "/login"
   if (requiresAuth && !user) {
    next("/login");
  }else {
    next();
  }
});
export default router;
