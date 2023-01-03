package com.raf.restdemo.service;

import com.raf.restdemo.dto.CreateNotificationDto;
import com.raf.restdemo.dto.NotificationDto;

public interface NotificationService {
    NotificationDto insertNotification(CreateNotificationDto createNotificationDto);
}
