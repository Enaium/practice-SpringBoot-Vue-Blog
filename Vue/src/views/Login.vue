<template>
  <el-form :model="loginForm" status-icon :rules="rules" ref="loginForm" label-width="100px" class="demo-ruleForm">
    <el-form-item label="用户名" prop="username">
      <el-input type="username" v-model="loginForm.username"></el-input>
    </el-form-item>
    <el-form-item label="密码" prop="password">
      <el-input type="password" v-model="loginForm.password"></el-input>
    </el-form-item>
    <el-form-item>
      <el-button type="primary" @click="submitForm('loginForm')">提交</el-button>
    </el-form-item>
  </el-form>
</template>

<script>
export default {
  data() {
    const validatePassword = (rule, value, callback) => {
      if (value === '') {
        callback(new Error('请输入'));
      } else {
        if (this.loginForm.username !== '') {
          this.$refs.loginForm.validateField('checkPass');
        }
        callback();
      }
    };
    return {
      loginForm: {
        username: '',
        password: '',
      },
      rules: {
        username: [
          {validator: validatePassword, trigger: 'blur'}
        ],
        password: [
          {validator: validatePassword, trigger: 'blur'}
        ]
      }
    };
  },
  methods: {
    submitForm(formName) {
      this.$refs[formName].validate((valid) => {
        if (valid) {
          axios.post("http://localhost:8081/login", this.loginForm).then(t => {
            if (t.data.data !== null) {
              localStorage.setItem("satoken", t.data.data);
            }

            this.$message({
              message: t.data.msg,
              type: 'info'
            });
          })
        } else {
          console.log('error submit!!');
          return false;
        }
      });
    }
  }
}
</script>

<style scoped>

</style>