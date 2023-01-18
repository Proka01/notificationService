package com.raf.restdemo.service;

import com.raf.restdemo.domain.Notification;
import com.raf.restdemo.dto.CreateNotificationDto;
import com.raf.restdemo.dto.NotificationDto;

import java.util.List;

public interface NotificationService {
    NotificationDto insertNotification(CreateNotificationDto createNotificationDto);
    List<NotificationDto> getAllClientNotification(Long id);

    List<NotificationDto> getAllNotifications();
}
