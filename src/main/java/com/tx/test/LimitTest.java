package com.tx.test;

import com.tx.lua.MyLimit;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

@Component
public class LimitTest implements ApplicationRunner {
    @Resource
    private MyLimit myLimit;

    AtomicInteger cnt = new AtomicInteger(0);

    public void invoked() {
        String now = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS").format(new Date());
        if (!myLimit.limit("limit", 5, 1000)) {
            System.out.println(now + ": 执行当前cnt=" + cnt.incrementAndGet());
        }else {
            System.out.println(now + "被拒绝了！");
        }
    }

    @Override
    public void run(ApplicationArguments args) {
        ScheduledExecutorService executor = Executors.newScheduledThreadPool(10);
        for (int i = 0; i < 10; i++) {
            executor.scheduleAtFixedRate(this::invoked, 0, 10, TimeUnit.MILLISECONDS);
        }
    }
}
