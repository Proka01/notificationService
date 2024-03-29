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
public class CarRentEmailListener {
    private MessageHelper messageHelper;
    private NotificationService notificationService;
    private NotificationTypeService notificationTypeService;
    private EmailService emailService;

    private RestTemplate userServiceApiClient;

    public CarRentEmailListener(MessageHelper messageHelper, NotificationService notificationService, NotificationTypeService notificationTypeService, EmailService emailService, RestTemplate userServiceApiClient) {
        this.messageHelper = messageHelper;
        this.notificationService = notificationService;
        this.notificationTypeService = notificationTypeService;
        this.emailService = emailService;
        this.userServiceApiClient = userServiceApiClient;
    }

    @JmsListener(destination = "${destination.carRentNotification}", concurrency = "5-10")
    void sendCarRentNotificationEmail(Message message) throws JMSException, IllegalAccessException {
        CarReservationEmailDataDto carReservationEmailDataDto = messageHelper.getMessage(message, CarReservationEmailDataDto.class);
        System.out.println("Car rent notifikacija");

        //TODO ovo clientDto.getBody().getEmail() treba staviti umesto mog mock mejl-a

        Long clientId = carReservationEmailDataDto.getUserId();
        Long managerId = carReservationEmailDataDto.getManagerId();
        System.out.println("Manager ID: " + managerId);

        ResponseEntity<ClientDto> clientDto = userServiceApiClient.exchange("/client/" + clientId, HttpMethod.GET, null, ClientDto.class);
        ResponseEntity<ManagerDto> managerDto = userServiceApiClient.exchange("/manager/" + managerId, HttpMethod.GET, null, ManagerDto.class);
        String clientEmail = clientDto.getBody().getEmail();
        String managerEmail = managerDto.getBody().getEmail();
        String firstName = clientDto.getBody().getFirstName();
        String lastName = clientDto.getBody().getLastName();

        carReservationEmailDataDto.setFirstName(firstName);
        carReservationEmailDataDto.setLastName(lastName);

        NotificationTypeDto notificationTypeDto = notificationTypeService.getNotificationTypeByType(carReservationEmailDataDto.getNotificationType());
        MailTextFormater mailTextFormater = new MailTextFormater();
        String mailMsg = mailTextFormater.formatText(notificationTypeDto.getEmbededMsg(), carReservationEmailDataDto);
        String type = carReservationEmailDataDto.getNotificationType();

        emailService.sendSimpleMessage(clientEmail, type, mailMsg);
        emailService.sendSimpleMessage(managerEmail,type, "Info about client rent: "+mailMsg);


        CreateNotificationDto createNotificationDto =
                new CreateNotificationDto(mailMsg,clientId,clientEmail,managerId,managerEmail, Date.valueOf(LocalDate.now()),type);
        notificationService.insertNotification(createNotificationDto);
    }
}
