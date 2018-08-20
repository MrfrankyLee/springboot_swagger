package com.springboot.swagger.tasks;

import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.Future;

/**
 * @author lixiaole
 * @date 2018/8/20 16:33
 */
@RequestMapping("/tasks")
@RestController
public class DoTask {
    @Autowired
    private AsyncTask asyncTask;

    @ApiOperation(value = "异步任务的调用")
    @RequestMapping(value = "/test",method = RequestMethod.GET)
    public String testTask() throws Exception {
        long start = System.currentTimeMillis();
        Future<Boolean> a = asyncTask.doTask1();
        Future<Boolean> b = asyncTask.doTask2();
        Future<Boolean> c = asyncTask.doTask3();
        while (!a.isDone() ||!b.isDone() ||!c.isDone()){
            if(a.isDone() && b.isDone() && c.isDone()){
                break;
            }
        }
        long end = System.currentTimeMillis();
        String tims = "任务全部完成,总耗时："+(end-start)+"毫秒";
        return tims;
    }
}
