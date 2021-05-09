import Vue from 'vue'
import './plugins/axios'
import App from './App.vue'
import router from './router'
import store from './store'
import './plugins/element.js'
import { mavonEditor } from "mavon-editor";
import "mavon-editor/dist/css/index.css";
Vue.component("mavon-editor", mavonEditor);
import highlight from 'highlight.js'
Vue.directive('highlight', (el) => {
  let blocks = el.querySelectorAll('pre code')
  blocks.forEach((block) => {
    highlight.highlightBlock(block)
  })
})
Vue.config.productionTip = false

new Vue({
  router,
  store,
  render: h => h(App)
}).$mount('#app')
