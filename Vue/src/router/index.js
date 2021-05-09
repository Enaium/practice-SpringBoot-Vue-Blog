import Vue from 'vue'
import VueRouter from 'vue-router'
import Home from '../views/Home.vue'
import Blogs from "../views/Blogs";
import Login from "../views/Login";
import Post from "../views/Post";
import Blog from "../views/Blog";

Vue.use(VueRouter)

const routes = [
  {
    path: '/',
    name: 'Home',
    component: Home
  },
  {
    path: '/about',
    name: 'About',
    // route level code-splitting
    // this generates a separate chunk (about.[hash].js) for this route
    // which is lazy-loaded when the route is visited.
    component: () => import(/* webpackChunkName: "about" */ '../views/About.vue')
  },
  {
    path: '/login',
    name: 'login',
    component: Login
  },
  {
    path: '/blogs',
    name: 'blogs',
    component: Blogs
  },
  {
    path: '/post',
    name: 'post',
    component: Post
  },
  {
    path: '/blog',
    name: 'blog',
    component: Blog
  }
]

const router = new VueRouter({
  mode: 'history',
  base: process.env.BASE_URL,
  routes
})

export default router
