package com.springboot.swagger.mapper;

import com.springboot.swagger.enums.EnumUtil;
import com.springboot.swagger.pojo.Student;
import org.apache.ibatis.annotations.*;

import java.util.List;

/**
 * @author lixiaole
 * @date 2018/9/12 13:43
 */
public interface StudentMapper {


    @Select("select * from tb_student")
    @Results({
            @Result(property = "userSex" ,column = "user_sex",javaType = EnumUtil.UserSexEnum.class),
            @Result(property = "nickName",column = "nick_name")
    })
    List<Student> getAll();

    @Select("SELECT * FROM tb_student WHERE id = #{id}")
    @Results({
            @Result(property = "userSex",  column = "user_sex", javaType = EnumUtil.UserSexEnum.class),
            @Result(property = "nickName", column = "nick_name")
    })
    Student getOne(Long id);

    @Insert("INSERT INTO tb_student(userName,passWord,user_sex,nick_Name) VALUES(#{userName}, #{passWord}, #{userSex},#{nickName})")
    void insert(Student user);

    @Update("UPDATE tb_student SET userName=#{userName},nick_name=#{nickName} WHERE id =#{id}")
    void update(Student user);

    @Delete("DELETE FROM tb_student WHERE id =#{id}")
    void delete(Long id);

}
