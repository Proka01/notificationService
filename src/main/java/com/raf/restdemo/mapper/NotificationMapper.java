package com.raf.restdemo.mapper;

import com.raf.restdemo.domain.Notification;
import com.raf.restdemo.dto.CreateNotificationDto;
import com.raf.restdemo.dto.NotificationDto;
import com.raf.restdemo.repository.NotificationRepository;
import org.springframework.stereotype.Component;

@Component
public class NotificationMapper {
    private NotificationRepository notificationRepository;

    public NotificationMapper(NotificationRepository notificationRepository) {
        this.notificationRepository = notificationRepository;
    }

    public Notification notificationDtoToNotification(NotificationDto notificationDto)
    {
        Notification notif = new Notification();
        notif.setNotificationType(notificationDto.getNotificationType());
        notif.setEmbededMsg(notificationDto.getEmbededMsg());
        notif.setNotificationDate(notificationDto.getNotificationDate());
        notif.setUserId(notificationDto.getUserId());
        notif.setManagerId(notificationDto.getManagerId());

        return notif;
    }

    public Notification createNotifDtoToNotification(CreateNotificationDto createNotificationDto)
    {
        Notification notif = new Notification();
        notif.setNotificationType(createNotificationDto.getNotificationType());
        notif.setEmbededMsg(createNotificationDto.getEmbededMsg());
        notif.setNotificationDate(createNotificationDto.getNotificationDate());
        notif.setUserId(createNotificationDto.getUserId());
        notif.setManagerId(createNotificationDto.getManagerId());

        return notif;
    }

    public NotificationDto notificationToNotificationDto(Notification notification)
    {
        NotificationDto notifDto = new NotificationDto();
        notifDto.setNotificationDate(notification.getNotificationDate());
        notifDto.setNotificationType(notification.getNotificationType());
        notifDto.setManagerId(notification.getManagerId());
        notifDto.setUserId(notifDto.getUserId());
        notifDto.setEmbededMsg(notification.getEmbededMsg());

        return notifDto;
    }
}
