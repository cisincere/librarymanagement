package com.l.library.demo.producer.task;

import com.l.library.demo.producer.events.BusSender;
import com.l.library.demo.producer.events.RedisSender;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

// 定时任务
@Component
@EnableScheduling
public class ScheduledTasks {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private RedisSender redisSender;

    @Autowired
    private BusSender busSender;

    // 间隔
    @Scheduled(fixedRate = 20000)
    public void sendMessageToRedis() {
        logger.info("Send Hello To Redis With chat");
        redisSender.send("chat", "Hello from Redis!");
    }

    @Scheduled(fixedRate = 30000)
    public void sendMessageToMq() {
        logger.info("Send Hello To RabbitMQ With mq");
        busSender.send("mq", "Hello To RabbitMQ!");
    }
}