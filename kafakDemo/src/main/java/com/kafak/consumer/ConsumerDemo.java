package com.kafak.consumer;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

/**
 * @author shiziyang
 * 监听服务器上的kafka是否有相关的消息发过来
 * @date by 2020/11
 */
@Component
public class ConsumerDemo {
    /**
     * 定义此消费者接收topics = "demo"的消息，与controller中的topic对应上即可
     *
     * @param record 变量代表消息本身，可以通过ConsumerRecord<?,?>类型的record变量来打印接收的消息的各种信息
     */
    @KafkaListener(topics = "demo")
    public void listen(ConsumerRecord<?, ?> record) {
        System.out.printf("kafakDemo -> topic is %s, offset is %d, key is %s, value is %s \n", record.topic(), record.offset(), record.key(), record.value());
    }
}
