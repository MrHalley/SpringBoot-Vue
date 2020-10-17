package com.sang;

import com.sang.model.Message;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.jms.core.JmsMessagingTemplate;
import org.springframework.stereotype.Component;

import javax.jms.Queue;

/**
 * @author Mr.Du
 * @date 2020/10/16 10:24
 */
@Slf4j
@Component
public class JmsComponent {
    private final JmsMessagingTemplate jmsMessagingTemplate;
    private final Queue queue;

    public JmsComponent(JmsMessagingTemplate jmsMessagingTemplate, Queue queue) {
        this.jmsMessagingTemplate = jmsMessagingTemplate;
        this.queue = queue;
    }

    public void send(Message message){
        jmsMessagingTemplate.convertAndSend(this.queue,message);
    }

    /**
     * @JmsListener表示相应的方法是一个消息消费者，
     * 消息消费者订阅的消息destination为amq
     * @param message
     */
    @JmsListener(destination = "amq")
    public void receive(Message message){
        log.warn("receive:"+message);
    }
}
