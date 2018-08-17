package com.springboot.swagger.entity;


import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import javax.persistence.*;
import java.io.Serializable;
import java.time.ZonedDateTime;

@Entity
@Table(name = "t_user")
@ApiModel(value = "用户实体类")
public class User extends BaseEntity implements Serializable {
    private static final long serialVersionUID = 7484593197505800799L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)//IDENTITY：主键由数据库自动生成（主要是自动增长型）
    @ApiModelProperty(value = "用户id")
    private long id;

    @ApiModelProperty(value = "用户名")
    private String userName;

    @ApiModelProperty(value = "密码")
    private String password;

    @Column(name = "real_name")
    @ApiModelProperty(value = "姓名")
    private String realName;

    @Column(name = "nick_name")
    @ApiModelProperty(value = "昵称")
    private String nickName;

    @ApiModelProperty(value = "性别")
    private Integer sex;

    @Column(name = "register_date")
    @ApiModelProperty(value = "注册日期")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private ZonedDateTime registerDate = ZonedDateTime.now();

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRealName() {
        return realName;
    }

    public void setRealName(String realName) {
        this.realName = realName;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public Integer getSex() {
        return sex;
    }

    public void setSex(Integer sex) {
        this.sex = sex;
    }

    public ZonedDateTime getRegisterDate() {
        return registerDate;
    }

    public void setRegisterDate(ZonedDateTime registerDate) {
        this.registerDate = registerDate;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", userName='" + userName + '\'' +
                ", password='" + password + '\'' +
                ", realName='" + realName + '\'' +
                ", nickName='" + nickName + '\'' +
                ", sex=" + sex +
                ", registerDate=" + registerDate +
                '}';
    }
}
