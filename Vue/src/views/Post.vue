<template>
  <el-form :model="postForm" :rules="rules" ref="postForm" label-width="100px" class="demo-ruleForm">
    <el-form-item label="标题" prop="title">
      <el-input v-model="postForm.title"></el-input>
    </el-form-item>
    <div class="markdown-container">
      <div class="container">
        <div class="title">编辑器</div>
        <mavon-editor
            v-model="postForm.content"
            ref="md"
            style="min-height: 600px"
        />
        <el-form-item>
          <el-button type="primary" @click="submitForm('postForm')">发布</el-button>
        </el-form-item>
      </div>
    </div>
  </el-form>

</template>

<script>
export default {
  data() {
    return {
      postForm: {
        title: "",
        content: ""
      },
      rules: {
        title: [
          {required: true, message: '请输入标题', trigger: 'blur'}
        ],
      },
    }
  },
  created() {
    if (this.$route.query.editBlog === "true") {
      axios.get("http://localhost:8081/blog/" + this.$route.query.blogId, {
        headers: {
          "satoken": localStorage.getItem("satoken")
        }
      }).then(t => {
        this.postForm = t.data.data
      })
    }
  },
  methods: {
    submitForm(formName) {
      this.$refs[formName].validate((valid) => {
        if (valid) {

          let url = "post";
          if (this.$route.query.editBlog === "true") {
            url = "edit/" + this.$route.query.blogId
          }

          axios.post("http://localhost:8081/" + url, this.postForm, {
            headers: {
              "satoken": localStorage.getItem("satoken")
            }
          }).then(t => {
            this.$message({
              message: t.data.msg,
              type: 'info'
            });
          })
        } else {
          console.log('error submit!!');
          return false;
        }
        this.$route.query.editBlog = "false"
      });
    }
  }
}
</script>

<style scoped>

</style>