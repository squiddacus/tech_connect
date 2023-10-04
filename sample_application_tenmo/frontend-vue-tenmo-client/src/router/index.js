import Vue from 'vue';
import Router from 'vue-router';
import Home from '../views/HomeDashboard.vue';
import LogIn from '../views/LogIn.vue';
import LogOut from '../views/LogOut.vue';
import RegisterUser from '../views/RegisterUser.vue';
import Tranfers from '../views/Tranfers.vue';
import AccountDetail from '../views/AccountDetail.vue';
import Pending from '../views/Pending.vue';
import Request from '../views/Request.vue';
import TransferDetailView from '../views/TransferDetailView.vue';

import store from '../store/index'

Vue.use(Router)

/**
 * The Vue Router is used to "direct" the browser to render a specific view component
 * inside of App.vue depending on the URL.
 *
 * It also is used to detect whether or not a route requires the user to have first authenticated.
 * If the user has not yet authenticated (and needs to) they are redirected to /login
 * If they have (or don't need to) they're allowed to go about their way.
 */

const router = new Router({
  mode: 'history',
  base: process.env.BASE_URL,
  routes: [
    {
      path: '/',
      name: 'home',
      component: Home,
      meta: {
        requiresAuth: true
      }
    },
    {
      path: "/login",
      name: "login",
      component: LogIn,
      meta: {
        requiresAuth: false
      }
    },
    {
      path: "/logout",
      name: "logout",
      component: LogOut,
      meta: {
        requiresAuth: false
      }
    },
    {
      path: "/register",
      name: "register",
      component: RegisterUser,
      meta: {
        requiresAuth: false
      }
    },
    {
      path: "/transfers",
      name: "transfers",
      component: Tranfers,
      meta: {
        requiresAuth: true
      }
    },
    {
      path: "/account",
      name: "account",
      component: AccountDetail,
      meta: {
        requiresAuth: true
      }
    },
    {
      path: "/pending",
      name: "pending",
      component: Pending,
      meta: {
        requiresAuth: true
      }
    },
    {
      path: "/request",
      name: "request",
      component: Request,
      meta: {
        requiresAuth: true
      }
    },
    {
      path: "/transfer/:id",
      name: "transfer",
      component: TransferDetailView,
      meta: {
        requiresAuth: true
      }
    }
  ]
})

router.beforeEach((to, from, next) => {
  // Determine if the route requires Authentication
  const requiresAuth = to.matched.some(x => x.meta.requiresAuth);

  // If it does and they are not logged in, send the user to "/login"
  if (requiresAuth && store.state.token === '') {
    next("/login");
  } else {
    // Else let them go to their next destination
    next();
  }
});

export default router;
