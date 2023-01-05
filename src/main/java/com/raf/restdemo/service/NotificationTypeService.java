package com.raf.restdemo.service;

import com.raf.restdemo.domain.NotificationType;
import com.raf.restdemo.dto.NotificationTypeDto;

public interface NotificationTypeService {
    NotificationTypeDto insertNotificationType(NotificationTypeDto notificationTypeDto);

    NotificationTypeDto getNotificationTypeByType(String type);
}
