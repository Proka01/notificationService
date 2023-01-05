package com.raf.restdemo.service.impl;

import com.raf.restdemo.domain.NotificationType;
import com.raf.restdemo.dto.NotificationTypeDto;
import com.raf.restdemo.exception.NotFoundException;
import com.raf.restdemo.mapper.NotificationTypeMapper;
import com.raf.restdemo.repository.NotificationTypeRepository;
import com.raf.restdemo.service.NotificationTypeService;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@Transactional
public class NotificationTypeServiceImpl implements NotificationTypeService {

    private NotificationTypeRepository notificationTypeRepository;
    private NotificationTypeMapper notificationTypeMapper;

    public NotificationTypeServiceImpl(NotificationTypeRepository notificationTypeRepository, NotificationTypeMapper notificationTypeMapper) {
        this.notificationTypeRepository = notificationTypeRepository;
        this.notificationTypeMapper = notificationTypeMapper;
    }

    @Override
    public NotificationTypeDto insertNotificationType(NotificationTypeDto notificationTypeDto) {
        NotificationType notificationType = notificationTypeMapper.notificationTypeDtoToNotificationType(notificationTypeDto);
        notificationTypeRepository.save(notificationType);
        return notificationTypeDto;
    }

    @Override
    public NotificationTypeDto getNotificationTypeByType(String type) {
        return notificationTypeRepository.findNotificationTypeByType(type)
                .map(notificationTypeMapper::notificationTypeToNotificationTypeDto)
                .orElseThrow(() -> new NotFoundException(String.format("Notif with type: %type not found.", type)));
    }
}
