package raf.teamEpic.listener;

import org.springframework.jms.annotation.JmsListener;
import raf.teamEpic.service.UserService;

import javax.jms.JMSException;
import javax.jms.Message;

public class NotificationListener {

    private MessageHelper messageHelper;
    private UserService userService;

    public NotificationListener(MessageHelper messageHelper, UserService userService) {
        this.messageHelper = messageHelper;
        this.userService = userService;
    }

    @JmsListener(destination = "${destination.createReservation}", concurrency = "5-10")
    public void createReservation(Message message) {
        Long l = messageHelper.getMessage(message, Long.class);
        userService.addReservation(l);
    }

    @JmsListener(destination = "${destination.deleteReservation}", concurrency = "5-10")
    public void deleteReservation(Message message) {
        Long l = messageHelper.getMessage(message, Long.class);
        userService.removeReservation(l);
    }
}
