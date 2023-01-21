package com.raf.restdemo.sheduler;

import com.raf.restdemo.dto.ClientDto;
import com.raf.restdemo.dto.NotificationDto;
import com.raf.restdemo.listener.helper.MessageHelper;
import com.raf.restdemo.service.EmailService;
import com.raf.restdemo.service.NotificationService;
import com.raf.restdemo.service.NotificationTypeService;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Component
public class NotificationSheduler {

    private MessageHelper messageHelper;
    private NotificationService notificationService;
    private NotificationTypeService notificationTypeService;
    private EmailService emailService;

    private RestTemplate userServiceApiClient;

    public NotificationSheduler(MessageHelper messageHelper, NotificationService notificationService, NotificationTypeService notificationTypeService, EmailService emailService, RestTemplate userServiceApiClient) {
        this.messageHelper = messageHelper;
        this.notificationService = notificationService;
        this.notificationTypeService = notificationTypeService;
        this.emailService = emailService;
        this.userServiceApiClient = userServiceApiClient;
    }

    @Scheduled(fixedDelay = 15000, initialDelay = 2000)
    void sheduleTask()
    {
//        List<NotificationDto> notificationDtoList = notificationService.getAllNotifications();
//
//        for (NotificationDto notifDto : notificationDtoList)
//        {
//            if(notifDto.getClientId() == 46L)
//            {
//                //REST template kontaktiranje drugog servisa
//                ResponseEntity<ClientDto> clientDto = userServiceApiClient.exchange("/client/" + notifDto.getClientId(), HttpMethod.GET,
//                        null, ClientDto.class);
//                emailService.sendSimpleMessage(clientDto.getBody().getEmail(), "REMINDER_EMAIL", "reminder email");
//            }
//        }
    }
}
