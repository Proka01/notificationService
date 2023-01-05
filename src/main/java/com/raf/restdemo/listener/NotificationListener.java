package com.raf.restdemo.listener;

import com.raf.restdemo.dto.*;
import com.raf.restdemo.helper.MailTextFormater;
import com.raf.restdemo.listener.helper.MessageHelper;
import com.raf.restdemo.service.EmailService;
import com.raf.restdemo.service.NotificationService;
import com.raf.restdemo.service.NotificationTypeService;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import javax.jms.JMSException;
import javax.jms.Message;

@Component
public class NotificationListener {
    private MessageHelper messageHelper;
    private NotificationService notificationService;
    private NotificationTypeService notificationTypeService;
    private EmailService emailService;

    private RestTemplate userServiceApiClient;


    public NotificationListener(MessageHelper messageHelper, NotificationService notificationService,
                                EmailService emailService, RestTemplate userServiceApiClient,NotificationTypeService notificationTypeService) {
        this.messageHelper = messageHelper;
        this.notificationService = notificationService;
        this.emailService = emailService;
        this.userServiceApiClient = userServiceApiClient;
        this.notificationTypeService = notificationTypeService;
    }

    @JmsListener(destination = "${destination.createNotification}", concurrency = "5-10")
    public void addNotifAndSendMail(Message message) throws JMSException, IllegalAccessException {
        ActivationEmailDataDto activationEmailDataDto = messageHelper.getMessage(message, ActivationEmailDataDto.class);
        activationEmailDataDto.setActivationCode("1234");
        activationEmailDataDto.setActivationLink("somelink/confirm_registration");
        System.out.println("Nova notifikacia");

        Long userId = activationEmailDataDto.getUserId();
        System.out.println(userId);

//REST template kontaktiranje drugog servisa
//        ResponseEntity<ClientDto> clientDto = userServiceApiClient.exchange("/client/" + client_id, HttpMethod.GET,
//                null, ClientDto.class);


        NotificationTypeDto notificationTypeDto = notificationTypeService.getNotificationTypeByType(activationEmailDataDto.getNotificationType());
        MailTextFormater mailTextFormater = new MailTextFormater();
        String mailMsg = mailTextFormater.formatText(notificationTypeDto.getEmbededMsg(), activationEmailDataDto);

        //TODO ovo clientDto.getBody().getEmail() treba staviti umesto mog mock mejl-a
        emailService.sendSimpleMessage("aleksa.prokic888@gmail.com", "ACTIVATION_EMAIL", mailMsg);
        //notificationService.insertNotification(createNotificationDto);

    }
}
