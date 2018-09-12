package com.springboot.swagger.service;

import com.springboot.swagger.enums.EnumUtil;
import com.springboot.swagger.mapper.StudentMapper;
import com.springboot.swagger.pojo.Student;
import com.springboot.swagger.utils.EncryptUtil;
import net.bytebuddy.implementation.bytecode.Throw;
import org.apache.ibatis.annotations.*;
import org.apache.tomcat.util.codec.binary.Base64;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.io.UnsupportedEncodingException;
import java.util.List;

/**
 * @author lixiaole
 * @date 2018/9/12 14:36
 */
@Service
public class StudentService {
    @Resource
    private StudentMapper studentMapper;
   public List<Student> getAll(){
        List<Student> list = studentMapper.getAll();
       if(list != null){
           return list;
       }
       return  null;
    }

   public  void insert(Student user) throws UnsupportedEncodingException {
       // 密码加密
        String password = EncryptUtil.encrypt(user.getPassWord());
        user.setPassWord(password);
        studentMapper.insert(user);
    }
    public Student getUser(Long id) {
       Student student = studentMapper.getOne(id);
        try {
            String password = EncryptUtil.decrypt(student.getPassWord());
            student.setPassWord(password);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return student;
    }

}
