package raf.teamEpic.listener;

import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;
import raf.teamEpic.dto.CreateNotificationDTO;
import raf.teamEpic.service.NotificationService;

import javax.jms.JMSException;
import javax.jms.Message;

@Component
public class NotificationListener {
    private Helper helper;
    private NotificationService notificationService;

    @JmsListener(destination = "${destination.sendNotification}", concurrency = "5-10")
    public void sendNotification(Message message) throws JMSException{
        CreateNotificationDTO dto = helper.getMessage(message,CreateNotificationDTO.class);
        notificationService.sendNotification(dto);
    }
}
