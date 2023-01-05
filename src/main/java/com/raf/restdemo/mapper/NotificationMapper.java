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
        notif.setEmailMsg(notificationDto.getEmailMsg());
        notif.setNotificationDate(notificationDto.getNotificationDate());
        notif.setClientId(notificationDto.getClientId());
        notif.setManagerId(notificationDto.getManagerId());
        notif.setClientEmail(notificationDto.getClientEmail());
        notif.setManagerEmail(notificationDto.getManagerEmail());

        return notif;
    }

    public Notification createNotifDtoToNotification(CreateNotificationDto createNotificationDto)
    {
        Notification notif = new Notification();
        notif.setNotificationType(createNotificationDto.getNotificationType());
        notif.setEmailMsg(createNotificationDto.getEmailMsg());
        notif.setNotificationDate(createNotificationDto.getNotificationDate());
        notif.setClientId(createNotificationDto.getClientId());
        notif.setManagerId(createNotificationDto.getManagerId());
        notif.setClientEmail(createNotificationDto.getClientEmail());
        notif.setManagerEmail(createNotificationDto.getManagerEmail());

        return notif;
    }

    public NotificationDto notificationToNotificationDto(Notification notification)
    {
        NotificationDto notifDto = new NotificationDto();
        notifDto.setNotificationDate(notification.getNotificationDate());
        notifDto.setNotificationType(notification.getNotificationType());
        notifDto.setManagerId(notification.getManagerId());
        notifDto.setClientId(notification.getClientId());
        notifDto.setEmailMsg(notification.getEmailMsg());
        notifDto.setClientEmail(notification.getClientEmail());
        notifDto.setManagerEmail(notification.getManagerEmail());

        return notifDto;
    }
}
