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
public class PasswordChangedEmailListener {
    private MessageHelper messageHelper;
    private NotificationService notificationService;
    private NotificationTypeService notificationTypeService;
    private EmailService emailService;

    private RestTemplate userServiceApiClient;

    public PasswordChangedEmailListener(MessageHelper messageHelper, NotificationService notificationService, NotificationTypeService notificationTypeService, EmailService emailService, RestTemplate userServiceApiClient) {
        this.messageHelper = messageHelper;
        this.notificationService = notificationService;
        this.notificationTypeService = notificationTypeService;
        this.emailService = emailService;
        this.userServiceApiClient = userServiceApiClient;
    }

    //passwordChanged=password_changed
    @JmsListener(destination = "${destination.passwordChanged}", concurrency = "5-10")
    public void sendPasswordNotif(Message message) throws JMSException, IllegalAccessException {
        PasswordChangedEmailDTO passwordChangedEmailDTO = messageHelper.getMessage(message, PasswordChangedEmailDTO.class);

        Long userId = passwordChangedEmailDTO.getUserId();

        //REST template kontaktiranje drugog servisa
        ResponseEntity<ClientDto> clientDto = userServiceApiClient.exchange("/client/" + userId, HttpMethod.GET,
                null, ClientDto.class);


        NotificationTypeDto notificationTypeDto = notificationTypeService.getNotificationTypeByType(passwordChangedEmailDTO.getNotificationType());
        MailTextFormater mailTextFormater = new MailTextFormater();
        String mailMsg = mailTextFormater.formatText(notificationTypeDto.getEmbededMsg(), passwordChangedEmailDTO);

        String userEmail = clientDto.getBody().getEmail();
        emailService.sendSimpleMessage(clientDto.getBody().getEmail(), "PASSEWORD_EMAIL", mailMsg);

        CreateNotificationDto createNotificationDto =
                new CreateNotificationDto(mailMsg,userId,userEmail,null,null, Date.valueOf(LocalDate.now()),"PASSEWORD_EMAIL");
        notificationService.insertNotification(createNotificationDto);

    }
}
