package com.springboot.swagger.controller;

import com.springboot.swagger.mapper.StudentMapper;
import com.springboot.swagger.pojo.Student;
import com.springboot.swagger.response.Response;
import com.springboot.swagger.service.StudentService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.io.UnsupportedEncodingException;
import java.util.List;

/**
 * @author lixiaole
 * @date 2018/9/12 13:53
 */
@RequestMapping("/student")
@RestController
@Api(tags="学生API")
public class StudentController {
    @Resource
    private StudentMapper studentMapper;
    @Autowired
    private StudentService studentService;

    @RequestMapping(value = "/getUsers",method = RequestMethod.POST)
    public Response<List<Student>> getUsers() {
        Response response = new Response();
        List<Student> users=studentMapper.getAll();
        if(users == null){
            response.setSuccees(false);
        }
        response.setSuccees(true);
        response.setDate(users);
        return response;
    }

    @RequestMapping(value = "/getUser",method = RequestMethod.POST)
    public Student getUser(Long id) {
        Student user=studentService.getUser(id);
        return user;
    }

    @RequestMapping(value = "/add" ,method = RequestMethod.POST)
    public void save(Student user) {
        try {
            studentService.insert(user);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @RequestMapping(value="update",method = RequestMethod.POST)
    public void update(Student user) {
        studentMapper.update(user);
    }

    @RequestMapping(value="/delete/{id}",method = RequestMethod.DELETE)
    public void delete(@PathVariable("id") Long id) {
        studentMapper.delete(id);
    }
}
