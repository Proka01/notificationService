package com.raf.restdemo.listener;

import com.raf.restdemo.dto.ClientDto;
import com.raf.restdemo.dto.CreateNotificationDto;
import com.raf.restdemo.dto.ManagerDto;
import com.raf.restdemo.listener.helper.MessageHelper;
import com.raf.restdemo.service.EmailService;
import com.raf.restdemo.service.NotificationService;
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
    private EmailService emailService;

    private RestTemplate userServiceApiClient;


    public NotificationListener(MessageHelper messageHelper, NotificationService notificationService,
                                EmailService emailService, RestTemplate userServiceApiClient) {
        this.messageHelper = messageHelper;
        this.notificationService = notificationService;
        this.emailService = emailService;
        this.userServiceApiClient = userServiceApiClient;
    }

    @JmsListener(destination = "${destination.createNotification}", concurrency = "5-10")
    public void addNotifAndSendMail(Message message) throws JMSException {
        CreateNotificationDto createNotificationDto = messageHelper.getMessage(message, CreateNotificationDto.class);
        System.out.println("Nova notifikacia");
        System.out.println(createNotificationDto.getEmbededMsg());

        Long client_id = createNotificationDto.getUserId();
        Long manager_id = createNotificationDto.getManagerId();
        System.out.println(client_id);

        if(client_id != null)
        {
            ResponseEntity<ClientDto> clientDto = userServiceApiClient.exchange("/client/" + client_id, HttpMethod.GET,
                    null, ClientDto.class);

            //TODO ovo clientDto.getBody().getEmail() treba staviti umesto mog mock mejl-a
            emailService.sendSimpleMessage("aleksa.prokic888@gmail.com", "ACTIVATION_EMAIL", clientDto.getBody().getUsername());
            notificationService.insertNotification(createNotificationDto);
        }
        else if(manager_id != null)
        {
            ResponseEntity<ManagerDto> managerDto = userServiceApiClient.exchange("/manager/" + manager_id, HttpMethod.GET,
                    null, ManagerDto.class);

            //TODO ovo clientDto.getBody().getEmail() treba staviti umesto mog mock mejl-a
            emailService.sendSimpleMessage("aleksa.prokic888@gmail.com", "ACTIVATION_EMAIL", managerDto.getBody().getUsername());
            notificationService.insertNotification(createNotificationDto);
        }

    }
}
