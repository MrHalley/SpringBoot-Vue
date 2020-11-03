<template>
  <div class="app-container">
    <el-form label-width="120px">
      <el-form-item label="讲师名称">
        <el-input v-model="teacher.name"/>
      </el-form-item>
      <el-form-item label="讲师排序">
        <el-input-number v-model="teacher.sort" controls-position="right" :min="0"/>
      </el-form-item>
      <el-form-item label="讲师头衔">
        <el-select v-model="teacher.level" clearable placeholder="请选择">
          <!--
            数据类型一定要和取出的json中的一致，否则没法回填
            因此，这里value使用动态绑定的值，保证其数据类型是number
          -->
          <el-option :value="1" label="高级讲师"/>
          <el-option :value="2" label="首席讲师"/>
        </el-select>
      </el-form-item>
      <el-form-item label="讲师资历">
        <el-input v-model="teacher.career"/>
      </el-form-item>
      <el-form-item label="讲师简介">
        <el-input v-model="teacher.intro" :rows="10" type="textarea"/>
      </el-form-item>
      <!-- 讲师头像：TODO -->
      <el-form-item>
        <el-button :disabled="saveBtnDisabled" type="primary" @click="saveOrUpdate">保存</el-button>
      </el-form-item>
    </el-form>
  </div>
</template>

<script>
  import teacherApi from '@/api/teacher/teacher'
  export default {
    data() {
      return {
        teacher:{},
        saveBtnDisabled: false
      }
    },
    created() {
      this.init();
    },
    watch: {
      $route(to,from){
        this.init();
      }
    },
    methods: {
      //信息回显与清楚
      init(){
        if(this.$route.params && this.$route.params.id) {
          //信息回显
          const id = this.$route.params.id;
          this.findById(id);
        }else{
          //清楚信息
          this.teacher = {}
        }
      },
      //根据id查询讲师
      findById(id){
        teacherApi.findById(id).then(response =>{
          console.log(response)
          this.teacher = response.data.item;
        })
      },
      //保存或更新（根据teacher.id判断）
      saveOrUpdate(){
        if(!this.teacher.id){
          //添加
          this.save();
        }else{
          this.update();
        }
      },
      //保存讲师
      save(){
        teacherApi.save(this.teacher)
          .then(response => {//添加成功
            //提示信息
            this.$message({
              type: "success",
              message: "添加成功！"
            });
            //回到列表页面 路由跳转
            this.$router.push({path:'/teacher/table'})
          });
      },
      //更新讲师
      update(){
        teacherApi.updateById(this.teacher.id,this.teacher)
          .then(response => {
            //提示信息
            this.$message({
              type: "success",
              message: "修改成功！"
            });
            //回到列表页面 路由跳转
            this.$router.push({path:'/teacher/table'})
          })
      }
    }
  }
</script>

<style scoped>

</style>
