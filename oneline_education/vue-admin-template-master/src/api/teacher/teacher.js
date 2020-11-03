import request from '@/utils/request'

export default {
  //1.讲师列表(条件分页查询)
  getTeacherListPage(current,size,teacherQuery){
    return request({
      //url: '/eduservice/edu-teacher/'+current+'/'+size,
      url: `/eduservice/edu-teacher/${current}/${size}`,
      method: 'post',
      data: teacherQuery
    })
  },
  //2.讲师列表(条件分页查询)
  deleteById(id){
    return request({
      url: `/eduservice/edu-teacher/${id}`,
      method: 'delete'
    })
  },
  //3.添加讲师
  save(teacher){
    return request({
      url: `/eduservice/edu-teacher/`,
      method: 'post',
      data: teacher
    })
  },
  //4.查询讲师（根据id）
  findById(id) {
    return request({
      url: `/eduservice/edu-teacher/${id}`,
      method: 'get'
    })
  },
  //5.修改讲师（根据id）
  updateById(id,teacher){
    return request({
      url: `/eduservice/edu-teacher/${id}`,
      method: 'put',
      data: teacher
    })
  }

}
