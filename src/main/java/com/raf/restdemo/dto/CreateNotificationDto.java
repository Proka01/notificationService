package com.raf.restdemo.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.sql.Date;

@Getter
@Setter
@AllArgsConstructor
public class CreateNotificationDto {

    private String emailMsg;
    private Long clientId;
    private String clientEmail;
    private Long managerId;
    private String managerEmail;
    private Date notificationDate;
    private String notificationType;
}
