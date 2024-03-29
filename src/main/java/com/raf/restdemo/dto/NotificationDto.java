package com.raf.restdemo.dto;

import lombok.Getter;
import lombok.Setter;

import java.sql.Date;

@Getter
@Setter
public class NotificationDto {
    private Long id;

    private String emailMsg;
    private Long clientId;
    private String clientEmail;
    private Long managerId;
    private String managerEmail;
    private Date notificationDate;
    private String notificationType;
}
