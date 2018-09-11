package com.springboot.swagger.controller;

import com.alibaba.fastjson.JSON;
import com.springboot.swagger.dao.UserRepository;
import com.springboot.swagger.entity.User;
import com.springboot.swagger.service.RocketMQProvider;
import com.springboot.swagger.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RequestMapping("/api")
@RestController
@Api(tags="用户API")
public class UserController {

    @Autowired
    UserRepository userRepository;
    @Autowired
    UserService userService;
    @Autowired
    RedisTemplate redisTemplate;

    @Autowired
    StringRedisTemplate stringRedisTemplate;


    @ApiOperation(value="查询所有用户",notes = "查询所有用户")
    @RequestMapping(value = "/findAll",method = RequestMethod.GET)
    public Map<String , Object> findall(HttpServletRequest request){
        List<User> list = userService.findall();
        Map<String,Object> map = new HashMap<>();
        map.put("count",list.size());
        map.put("rows",list);
        return map;
    }

    @ApiOperation(value = "根据用户ID查找用户", notes = "根据用户ID查找用户")
    @RequestMapping(value = "/findById",method = RequestMethod.POST)
    public User findAuthorById(@RequestParam @ApiParam(value = "用户id") long id, HttpServletRequest request){
          User author ;
          author = JSON.parseObject(stringRedisTemplate.opsForValue().get(String.valueOf(id)),User.class);
          if(author == null){
              author = userService.findAuthorById(id);
              if(author != null){
                  stringRedisTemplate.opsForValue().set(String.valueOf(id),JSON.toJSONString(author));
              }else {
                  throw new RuntimeException("查无此人");
              }
          }
        return author;
    }


    @PostMapping("/setRedis")
    @ApiOperation(value="设置缓存",notes = "setRedis")
    public String set(@RequestParam @ApiParam(value = "缓存key")String key,@RequestParam @ApiParam(value = "缓存值") String value) {
    //注意这里的 key不能为null spring 内部有检验
        stringRedisTemplate.opsForValue().set(key, value);
        return key + "," + value;
    }

    @PostMapping("/getRedis")
    @ApiOperation(value="根据key获取缓存",notes = "getRedis")
    public String get(@RequestParam @ApiParam(value = "缓存key") String key) {

        return "key=" + key + ",value=" + stringRedisTemplate.opsForValue().get(key);
    }

    @Autowired
    RocketMQProvider rocketMQProvider;

    @RequestMapping(value = "/testMQ" , method = RequestMethod.GET)
    public String testMq() {
        rocketMQProvider.defaultMQProducer();
        return null;
    }

    @RequestMapping(value = "/redisObj",method = RequestMethod.GET)
    @ApiOperation(value="缓存对象到redis",notes = "RedisSaveObj")
    public String RedisSaveObj(HttpServletRequest request){
        List<User> list = userService.findall();
        try {
            for(User user :list){
                redisTemplate.opsForValue().set(user.getId(),user);
            }
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }

        return "successs";
    }
    @RequestMapping(value = "/getObj",method = RequestMethod.GET)
    @ApiOperation(value="redis对象取出到list",notes = "getRedisObj")
    public List<User> getRedisObj(HttpServletRequest request){
        List<User> u = userService.findall();

        List<User> list = new ArrayList<>();
        for(User user : u){
            String us = stringRedisTemplate.opsForValue().get(JSON.toJSONString(user.getId()));
            User user1 = JSON.parseObject(us,User.class);
            list.add(user1);
        }
        return list;
    }

    @RequestMapping(value = "/getRedisUser",method = RequestMethod.GET)
    @ApiOperation(value="redis中取出对象",notes = "getRedisUser")
    public User getRedisUser(@RequestParam @ApiParam(value = "redis键") long key , HttpServletRequest request){
        return JSON.parseObject(stringRedisTemplate.opsForValue().get(String.valueOf(key)),User.class);
    }

    @ApiOperation(value = "删除缓存",notes ="deleteRedis" )
    @RequestMapping(value = "/deleteRedis" ,method = RequestMethod.POST)
    public String deleteRedis(@RequestParam @ApiParam(value = "缓存键") String key,HttpServletRequest request){
        stringRedisTemplate.delete(key);
        if(stringRedisTemplate.opsForValue().get(key) == null){
            return "success";
        }else {
            return "error";
        }
    }

    @ApiOperation(value = "新增用户",notes = "insertUser")
    @RequestMapping(value = "/insertUser",method = RequestMethod.POST)
    public String insertUser(@RequestParam String realName ,@RequestParam String nickName,HttpServletRequest RE
    ){
        User user = new User();
        user.setRealName(realName);
        user.setNickName(nickName);
        userRepository.save(user);
        return userService.findAuthorById(user.getId()) == null ?"error" : "success";
    }

    @ApiOperation(value = "返回用户json类型",notes = "getUser")
    @PostMapping(value = "/getUser")
    public User getUser(@RequestParam @ApiParam(value = "用户id") long id,HttpServletRequest request){
        User u = userService.findAuthorById(id);

        return u;
    }

    @RequestMapping(value = "/hello",method = RequestMethod.GET)
    public String hello() throws Exception {
        int i = 1/0;
        return "templates/error";
    }
}
