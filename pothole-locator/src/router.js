import Vue from "vue";
import Router from "vue-router";
import LandingPage from "./views/LandingPage.vue";
import LoginPage from "./views/LoginPage.vue";
import RegisterPage from "./views/RegisterPage.vue";
import MapView from "./views/MapView.vue";
import ReportPage from "./views/ReportPage.vue";
import AdminPage from "./views/AdminPage.vue";
import auth from "./auth";

Vue.use(Router);

const router = new Router({
	mode: "history",
	base: process.env.BASE_URL,
	routes: [
		{
			path: "/",
			name: "landing-page",
			component: LandingPage,
			meta: {
				title: "PotholeBuddy",
				requiredAuth: "none user admin"
			}
		},
		{
			path: "/login",
			name: "login",
			component: LoginPage,
			meta: {
				title: "PotholeBuddy Login",
				requiredAuth: "none user admin"
			}
		},
		{
			path: "/register",
			name: "register",
			component: RegisterPage,
			meta: {
				title: "PotholeBuddy Register",
				requiredAuth: "none user admin"
			}
		},
		{
			path: "/view",
			name: "anonymous-view",
			component: MapView,
			meta: {
				title: "View Potholes",
				requiredAuth: "none user admin"
			}
		},
		{
			path: "/report",
			name: "report",
			component: ReportPage,
			meta: {
				title: "Report a Pothole",
				requiredAuth: "user admin"
			}
		},
		{
			path: "/admin",
			name: "administrator",
			component: AdminPage,
			meta: {
				title: "Administration",
				requiredAuth: "admin"
			}
		}
	]
});
router.beforeEach((to, from, next) => {
	// Determine if the route requires Authentication
	const requiredAuth = to.meta.requiredAuth;
	const user = auth.getUser();
	const userAuth = user != null ? user.rol : "none";
	document.title = to.meta.title;
	// If it does and they are not logged in, send the user to "/login"

	if (requiredAuth.includes(userAuth)) {
		next();
	} else {
		next("/login");
	}
});
export default router;
