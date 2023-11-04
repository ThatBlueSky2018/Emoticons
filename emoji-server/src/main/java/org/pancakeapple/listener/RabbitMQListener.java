package org.pancakeapple.listener;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
public class RabbitMQListener {
    @RabbitListener(queues = "test.queue")
    public void listenSimpleQueue(String msg) {
        System.out.println("消费者接收到test.queue的消息:【"+msg+"】");
    }
}
