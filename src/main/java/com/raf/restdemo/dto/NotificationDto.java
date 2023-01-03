package com.raf.restdemo.dto;

import lombok.Getter;
import lombok.Setter;

import java.sql.Date;

@Getter
@Setter
public class NotificationDto {
    private Long id;

    private String notificationType;
    private String embededMsg;
    private Long userId;
    private Long managerId;
    private Date notificationDate;
}
