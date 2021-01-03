package api.mailer.controller;

import api.mailer.model.MailContent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;

@Controller
public class MessagingController {
    @Autowired private SimpMessagingTemplate messagingTemplate;

    @MessageMapping("/chat")
    public void processMessage(@Payload MailContent mailContent) {
        messagingTemplate.convertAndSendToUser(
                mailContent.getTo(),
                "/queue/messages",
                mailContent
        );
    }
}
