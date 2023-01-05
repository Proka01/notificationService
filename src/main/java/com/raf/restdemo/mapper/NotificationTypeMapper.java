package com.raf.restdemo.mapper;

import com.raf.restdemo.domain.NotificationType;
import com.raf.restdemo.dto.NotificationDto;
import com.raf.restdemo.dto.NotificationTypeDto;
import com.raf.restdemo.repository.NotificationTypeRepository;
import org.springframework.stereotype.Component;

@Component
public class NotificationTypeMapper {
    private NotificationTypeRepository notificationTypeRepository;

    public NotificationTypeMapper(NotificationTypeRepository notificationTypeRepository) {
        this.notificationTypeRepository = notificationTypeRepository;
    }

    public NotificationType notificationTypeDtoToNotificationType(NotificationTypeDto notificationTypeDto)
    {
        NotificationType notifType = new NotificationType();

        notifType.setType(notificationTypeDto.getType());
        notifType.setEmbededMsg(notificationTypeDto.getEmbededMsg());

        //all types
        notifType.setClientFirstName(notificationTypeDto.getClientFirstName());
        notifType.setClientLastName(notificationTypeDto.getClientLastName());

        //ACTIVATION_EMAIL
        notifType.setActivationLink(notificationTypeDto.getActivationLink());
        notifType.setActivationCode(notificationTypeDto.getActivationCode());

        //PASSWORD_CHANGE_EMAIL
        notifType.setSuccessPasswordChangeInfo(notificationTypeDto.getSuccessPasswordChangeInfo());

        //CAR_RESERVATION_EMAIL
        notifType.setSuccessCarRentInfo(notificationTypeDto.getSuccessCarRentInfo());
        notifType.setCarBrand(notificationTypeDto.getCarBrand());
        notifType.setCarModel(notificationTypeDto.getCarModel());
        notifType.setRegistrationNum(notificationTypeDto.getRegistrationNum());

        //CANCEL_CAR_RESERVATION_EMAIL
        notifType.setCancellationRentLink(notificationTypeDto.getCancellationRentLink());

        return notifType;
    }

    public NotificationTypeDto notificationTypeToNotificationTypeDto(NotificationType notificationType)
    {
        NotificationTypeDto notifTypeDto = new NotificationTypeDto();

        notifTypeDto.setType(notificationType.getType());
        notifTypeDto.setEmbededMsg(notificationType.getEmbededMsg());

        //all types
        notifTypeDto.setClientFirstName(notificationType.getClientFirstName());
        notifTypeDto.setClientLastName(notificationType.getClientLastName());

        //ACTIVATION_EMAIL
        notifTypeDto.setActivationLink(notificationType.getActivationLink());
        notifTypeDto.setActivationCode(notificationType.getActivationCode());

        //PASSWORD_CHANGE_EMAIL
        notifTypeDto.setSuccessPasswordChangeInfo(notificationType.getSuccessPasswordChangeInfo());

        //CAR_RESERVATION_EMAIL
        notifTypeDto.setSuccessCarRentInfo(notificationType.getSuccessCarRentInfo());
        notifTypeDto.setCarBrand(notificationType.getCarBrand());
        notifTypeDto.setCarModel(notificationType.getCarModel());
        notifTypeDto.setRegistrationNum(notificationType.getRegistrationNum());

        //CANCEL_CAR_RESERVATION_EMAIL
        notifTypeDto.setCancellationRentLink(notificationType.getCancellationRentLink());

        return notifTypeDto;
    }
}
