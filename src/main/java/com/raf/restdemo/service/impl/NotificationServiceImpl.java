package com.raf.restdemo.service.impl;

import com.raf.restdemo.domain.Notification;
import com.raf.restdemo.dto.CreateNotificationDto;
import com.raf.restdemo.dto.NotificationDto;
import com.raf.restdemo.mapper.NotificationMapper;
import com.raf.restdemo.repository.NotificationRepository;
import com.raf.restdemo.service.NotificationService;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class NotificationServiceImpl implements NotificationService {
    private NotificationRepository notificationRepository;
    private NotificationMapper notificationMapper;

    public NotificationServiceImpl(NotificationRepository notificationRepository, NotificationMapper notificationMapper) {
        this.notificationRepository = notificationRepository;
        this.notificationMapper = notificationMapper;
    }


    @Override
    public NotificationDto insertNotification(CreateNotificationDto createNotificationDto) {
        Notification notification = notificationMapper.createNotifDtoToNotification(createNotificationDto);
        notificationRepository.save(notification);
        return notificationMapper.notificationToNotificationDto(notification);
    }

    @Override
    public List<NotificationDto> getAllClientNotification(Long id) {
        List<Notification> notificationList = notificationRepository.findNotificationsByClientId(id);

        List<NotificationDto> notificationDtoList = new ArrayList<>();

        for(Notification notif : notificationList)
        {
            notificationDtoList.add(notificationMapper.notificationToNotificationDto(notif));
        }

        return notificationDtoList;
    }
}
