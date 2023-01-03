package com.raf.restdemo.dto;

import lombok.Getter;
import lombok.Setter;

import java.sql.Date;

@Getter
@Setter
public class CreateNotificationDto {

    private String notificationType;
    private String embededMsg;
    private Long userId;
    private Date notificationDate;
    private Long managerId;
}
