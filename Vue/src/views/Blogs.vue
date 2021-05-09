<template>
  <el-table
      :data="blogs"
      style="width: 100%">
    <el-table-column
        prop="id"
        label="ID"
        width="180">
    </el-table-column>
    <el-table-column
        prop="title"
        label="标题"
        width="280">
    </el-table-column>
    <el-table-column
        prop="memberName"
        label="用户名"
        width="180">
    </el-table-column>
    <el-table-column
        prop="postDate"
        label="发布日期"
        width="280">
    </el-table-column>
    <el-table-column
        fixed="right"
        label="操作"
        width="200">
      <template slot-scope="scope">
        <el-button @click="viewClick(scope.row)" type="text" size="small">查看</el-button>
        <el-button @click="deleteClick(scope.row)" type="text" size="small">删除</el-button>
        <el-button @click="editClick(scope.row)" type="text" size="small">修改</el-button>
      </template>
    </el-table-column>
  </el-table>
</template>

<script>
export default {
  data() {
    return {
      blogs: []
    }
  },
  created() {
    axios.get("http://localhost:8081/blogs", {
      headers: {
        "satoken": localStorage.getItem("satoken")
      }
    }).then((t) => {
      this.blogs = t.data.data
    })
  },
  methods: {
    viewClick(row) {
      this.$router.push({
        path: "/Blog",
        query: {
          blogId: row.id
        }
      });
    },
    editClick(row) {
      this.$router.push({
        path: "/Post",
        query: {
          blogId: row.id,
          editBlog: "true"
        }
      });
    },
    deleteClick(row) {
      axios.post("http://localhost:8081/delete/" + row.id, null, {
        headers: {
          "satoken": localStorage.getItem("satoken")
        }
      }).then(t => {
        this.$message({
          message: t.data.msg,
          type: 'info'
        });
      })
    }
  }
}
</script>

<style scoped>

</style>