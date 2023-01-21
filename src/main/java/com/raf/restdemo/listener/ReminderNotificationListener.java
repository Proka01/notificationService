package com.raf.restdemo.listener;

import com.raf.restdemo.dto.*;
import com.raf.restdemo.helper.MailTextFormater;
import com.raf.restdemo.listener.helper.MessageHelper;
import com.raf.restdemo.service.EmailService;
import com.raf.restdemo.service.NotificationService;
import com.raf.restdemo.service.NotificationTypeService;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import javax.jms.JMSException;
import javax.jms.Message;
import java.sql.Date;
import java.time.LocalDate;

@Component
public class ReminderNotificationListener {
    private MessageHelper messageHelper;
    private NotificationService notificationService;
    private NotificationTypeService notificationTypeService;
    private EmailService emailService;

    private RestTemplate userServiceApiClient;

    public ReminderNotificationListener(MessageHelper messageHelper, NotificationService notificationService, NotificationTypeService notificationTypeService, EmailService emailService, RestTemplate userServiceApiClient) {
        this.messageHelper = messageHelper;
        this.notificationService = notificationService;
        this.notificationTypeService = notificationTypeService;
        this.emailService = emailService;
        this.userServiceApiClient = userServiceApiClient;
    }

    @JmsListener(destination = "${destination.reminderNotification}", concurrency = "5-10")
    public void addNotifAndSendMail(Message message) throws JMSException, IllegalAccessException {
        ReminderNotificationDto reminderNotificationDto = messageHelper.getMessage(message, ReminderNotificationDto.class);
        System.out.println("Nova notifikacia");

        Long userId = reminderNotificationDto.getUserId();

//REST template kontaktiranje drugog servisa
        ResponseEntity<ClientDto> clientDto = userServiceApiClient.exchange("/client/" + userId, HttpMethod.GET,
                null, ClientDto.class);


        NotificationTypeDto notificationTypeDto = notificationTypeService.getNotificationTypeByType(reminderNotificationDto.getNotificationType());
        MailTextFormater mailTextFormater = new MailTextFormater();
        String mailMsg = mailTextFormater.formatText(notificationTypeDto.getEmbededMsg(), reminderNotificationDto);

        //TODO ovo clientDto.getBody().getEmail() treba staviti umesto mog mock mejl-a
        emailService.sendSimpleMessage(clientDto.getBody().getEmail(), "REMINDER_EMAIL", mailMsg);

        CreateNotificationDto createNotificationDto =
                new CreateNotificationDto(mailMsg,userId,clientDto.getBody().getEmail(),null,null, Date.valueOf(LocalDate.now()),"REMINDER_EMAIL");
        notificationService.insertNotification(createNotificationDto);

    }
}
