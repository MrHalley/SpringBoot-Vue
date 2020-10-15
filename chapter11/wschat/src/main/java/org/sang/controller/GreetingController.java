package org.sang.controller;

import org.sang.model.Chat;
import org.sang.model.Message;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;

import java.security.Principal;

@Controller
public class GreetingController {
    private final SimpMessagingTemplate simpMessagingTemplate;

    public GreetingController(SimpMessagingTemplate simpMessagingTemplate) {
        this.simpMessagingTemplate = simpMessagingTemplate;
    }

    /**
     * 根据config的配置
     * @MessageMapping("/hello")注解的方法将用来接收“/app/hello”
     * 路径发送来的消息，在注解方法中对消息进行处理后，再将消息转发到
     * @SendTo定义的路径上，而@SentTo路径是一个前缀为“/topic”的路径，
     * 因此该消息将被交给消息代理broker,再由broker进行广播
     * @param message
     * @return
     * @throws Exception
     */
    @MessageMapping("/hello")
    @SendTo("/topic/greetings")
    public Message greeting(Message message) throws Exception {
        return message;
    }

    /**
     * 这里使用 SimpMessagingTemplate进行消息的发送,在SpringBoot
     * 中，SimpMessagingTemplate已经配置好,开发者直接注入进来即可。
     * 使用 SimpMessagingTemplate的convertAndSend开发者可以在任
     * 意地方发送消息到broker，也可以发送消息给某一个用户,这就是点对
     * 点的消息发送。
     * @param chat
     * @throws Exception
     */
    @MessageMapping("/chat")    //来自 /app/chat路径的消息将被
    public void chat(Principal principal, Chat chat){
        String name = principal.getName();
        chat.setFrom(name);
        simpMessagingTemplate.convertAndSendToUser(chat.getTo(),"/queue/chat",chat);
    }
}
