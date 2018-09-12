package com.springboot.swagger.controller;

import com.springboot.swagger.mapper.TeacherMapper;
import com.springboot.swagger.pojo.Student;
import com.springboot.swagger.pojo.Teacher;
import com.springboot.swagger.response.Response;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author lixiaole
 * @date 2018/9/12 17:22
 */
@RestController
@RequestMapping(value = "/teacher")
@Api(tags = "老师API")
public class TeacherController {
    @Resource
    private TeacherMapper teacherMapper;

    @RequestMapping(value = "/getUsers",method = RequestMethod.POST)
    public Response<List<Teacher>> getUsers() {
        Response response = new Response();
        List<Teacher> users=teacherMapper.getAll();
        if(users == null){
            response.setSuccees(false);
        }
        response.setSuccees(true);
        response.setDate(users);
        return response;
    }

    @RequestMapping(value = "/getUser",method = RequestMethod.POST)
    public Teacher getUser(Long id) {
        Teacher user=teacherMapper.getOne(id);
        return user;
    }

    @RequestMapping(value = "/add" ,method = RequestMethod.POST)
    public void save(Teacher user) {
        teacherMapper.insert(user);
    }

    @RequestMapping(value="update",method = RequestMethod.POST)
    public void update(Teacher user) {
        teacherMapper.update(user);
    }

    @RequestMapping(value="/delete/{id}",method = RequestMethod.DELETE)
    public void delete(@PathVariable("id") Long id) {
        teacherMapper.delete(id);
    }
}
