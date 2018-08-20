package com.springboot.swagger.tasks;

import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author lixiaole
 * @date 2018/8/20 15:59
 */
@Component
public class TestTask {
    private static final SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");

    //  定义每3秒执行一次任务
    // @Scheduled(fixedRate = 3000)
    //@Scheduled(cron = "2-15 * * * * ?")// spring boot 不支持年  所以只能有6位
    // cron 配置参考网址 http://cron.qqe2.com/
    public void reportCurrentTime(){
        System.out.println("现在的时间是:"+sdf.format(new Date()));
    }
}
