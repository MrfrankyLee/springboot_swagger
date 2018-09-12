package com.springboot.swagger.mapper;

import com.springboot.swagger.pojo.Teacher;

import java.util.List;

/**
 * @author lixiaole
 * @date 2018/9/12 17:10
 */
public interface TeacherMapper {
    List<Teacher> getAll();

    Teacher getOne(Long id);

    void insert(Teacher user);

    void update(Teacher user);

    void delete(Long id);
}
