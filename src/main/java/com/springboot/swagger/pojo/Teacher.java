package com.springboot.swagger.pojo;

import com.springboot.swagger.enums.EnumUtil;

import java.io.Serializable;

/**
 * @author lixiaole
 * @date 2018/9/12 16:45
 */
public class Teacher implements Serializable {
    private static final long serialVersionUID = -748634236495410610L;

    private Long id;
    private String userName;
    private String passWord;
    private EnumUtil.UserSexEnum userSex;
    private String nickName;

    public Teacher() {
        super();
    }

    public Teacher(String userName, String passWord, EnumUtil.UserSexEnum userSex) {
        super();
        this.passWord = passWord;
        this.userName = userName;
        this.userSex = userSex;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassWord() {
        return passWord;
    }

    public void setPassWord(String passWord) {
        this.passWord = passWord;
    }

    public EnumUtil.UserSexEnum getUserSex() {
        return userSex;
    }

    public void setUserSex(EnumUtil.UserSexEnum userSex) {
        this.userSex = userSex;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    @Override
    public String toString() {
        // TODO Auto-generated method stub
        return "userName " + this.userName + ", pasword " + this.passWord + "sex " + userSex.name();
    }
}
