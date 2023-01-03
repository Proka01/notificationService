package com.raf.restdemo.listener;

import com.raf.restdemo.dto.CreateNotificationDto;
import com.raf.restdemo.listener.helper.MessageHelper;
import com.raf.restdemo.service.EmailService;
import com.raf.restdemo.service.NotificationService;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

import javax.jms.JMSException;
import javax.jms.Message;

@Component
public class NotificationListener {
    private MessageHelper messageHelper;
    private NotificationService notificationService;
    private EmailService emailService;


    public NotificationListener(MessageHelper messageHelper, NotificationService notificationService, EmailService emailService) {
        this.messageHelper = messageHelper;
        this.notificationService = notificationService;
        this.emailService = emailService;
    }

    @JmsListener(destination = "${destination.createNotification}", concurrency = "5-10")
    public void addNotifAndSendMail(Message message) throws JMSException {
        CreateNotificationDto createNotificationDto = messageHelper.getMessage(message, CreateNotificationDto.class);
        System.out.println("Nova notifikacia");
        System.out.println(createNotificationDto.getEmbededMsg());
        emailService.sendSimpleMessage("aleksa.prokic888@gmail.com", "ACTIVATION_EMAIL", createNotificationDto.getEmbededMsg());
        notificationService.insertNotification(createNotificationDto);
    }
}
