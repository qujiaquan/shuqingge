package com.snut.material;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageBuilder;
import org.springframework.amqp.core.MessageDeliveryMode;
import org.springframework.amqp.rabbit.connection.CorrelationData;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.util.DigestUtils;
//import redis.clients.jedis.Jedis;
import org.springframework.util.concurrent.ListenableFutureCallback;
import com.snut.material.model.Admin;

import java.nio.charset.StandardCharsets;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

@Slf4j
@SpringBootTest
class SpringbootApplicationTests {

    @Autowired
    RedisTemplate redisTemplate;

    @Autowired
    RabbitTemplate rabbitTemplate;


    @Test
    void contextLoads() {
        //方式1 官方提供的方式
//		Jedis jedis = new Jedis("101.200.129.214", 6379);
//			  jedis.auth("auth123");
//		System.out.println(jedis.get("news"));
//		jedis.set("b","bb");
//		jedis.del("a");
//		//方式2

    }

    @Test
    void contextLoads1() {

        //方式2  使用springBoot集成redis

        ValueOperations valueOperations = redisTemplate.opsForValue();
//		redisTemplate.setKeySerializer();//设置字符串key序列化方式
//		redisTemplate.setValueSerializer();//设置字符串value序列化方式
        valueOperations.set("c", "cc");
        valueOperations.set("num", 0);
        System.out.println(valueOperations.get("c"));
        valueOperations.increment("num");
        System.out.println(valueOperations.get("num"));
        valueOperations.set("admin", new Admin());

        //设置键值并指定失效时间
        valueOperations.set("d", "dd", 10, TimeUnit.SECONDS);


    }

    @Test
    void testSendMessage2Queue() {
        // 队列名称
        String queueName = "simple.queue";
        // 消息
        String msg = "hello,mq";
        // 发送消息
        rabbitTemplate.convertAndSend(queueName, msg);
    }

    @Test
    void testWorking() throws InterruptedException {

        String queueName = "work.queue";

        for (int i = 0; i < 50; i++) {
            String msg = "hello,worker,mq" + i;
            rabbitTemplate.convertAndSend(queueName, msg);
        }
    }

    @Test
    void testSendFanoutExchange() {
        String exchange = "shuqg.fanout";
        // 消息
        String msg = "hello,everyone";
        // 发送消息
        rabbitTemplate.convertAndSend(exchange,null,  msg);
    }

    @Test
    void testSendDriectExchange() {
        String exchange = "shuqg.direct";
        // 消息
        String msg = "红色警告";
        // 发送消息
        rabbitTemplate.convertAndSend(exchange,"red",  msg);
    }

    @Test
    void testSendDriectExchange1() {
        String exchange = "shuqg.direct";
        // 消息
        String msg = "蓝色警告";
        // 发送消息
        rabbitTemplate.convertAndSend(exchange,"blue",  msg);
    }

    @Test
    void testSendTopicExchange() {
        String exchange = "shuqg.topic";
        // 消息
        String msg = "红色警告";
        // 发送消息
        rabbitTemplate.convertAndSend(exchange,"japan.news",  msg);
    }

    @Test
    void testConfirmCallback() throws InterruptedException {
        // 创建cd
        CorrelationData cd = new CorrelationData(UUID.randomUUID().toString());
        // 添加ConfirmCallback
        cd.getFuture().addCallback(new ListenableFutureCallback<CorrelationData.Confirm>() {
            @Override
            public void onFailure(Throwable ex) {
                log.error("消息回调失败", ex);
            }

            @Override
            public void onSuccess(CorrelationData.Confirm result) {
                log.debug("收到confirm callback回执");
                if (result.isAck()) {
                    // 消息发送成功
                    log.debug("消息发送成功，返回ack");
                }else{
                    // 消息发送失败
                    log.error("消息发送失败，返回nack，原因{}", result.getReason());
                }
            }
        });
        rabbitTemplate.convertAndSend("shuqg.direct", "red","hello", cd);
        Thread.sleep(2000);
    }

    @Test
    void testPageOut(){
        Message message = MessageBuilder
                .withBody("hello".getBytes(StandardCharsets.UTF_8))
                .setDeliveryMode(MessageDeliveryMode.PERSISTENT).build();
        for (int i = 0; i < 1000000; i++) {
            rabbitTemplate.convertAndSend("simple.queue", message);
        }
    }


    public static void main(String[] args) {

        System.out.println(DigestUtils.md5DigestAsHex("111".getBytes()));

    }


}
