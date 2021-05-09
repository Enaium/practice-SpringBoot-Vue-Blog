<template>
  <div>
    <h1>{{ blog.title }}</h1>
    <div v-highlight v-html="blog.content" style="text-align:left" class="content"></div>
  </div>
</template>

<script>
import marked from 'marked'

export default {
  name: "Blog",
  created() {
    axios.get("http://localhost:8081/blog/" + this.$route.query.blogId, {
      headers: {
        "satoken": localStorage.getItem("satoken")
      }
    }).then(t => {
      this.blog.title = t.data.data.title
      this.blog.content = marked(t.data.data.content)
    })
  },
  data() {
    return {
      blog: {
        title: "",
        content: ""
      }
    }
  }
}
</script>

<style scoped>

</style>