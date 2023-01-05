package com.raf.restdemo.controller;

import com.raf.restdemo.dto.CreateNotificationDto;
import com.raf.restdemo.dto.NotificationDto;
import com.raf.restdemo.dto.NotificationTypeDto;
import com.raf.restdemo.service.NotificationTypeService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/notification_type")
public class NotificationTypeController {

    private NotificationTypeService notificationTypeService;

    public NotificationTypeController(NotificationTypeService notificationTypeService) {
        this.notificationTypeService = notificationTypeService;
    }

    @PostMapping("/insert")
    public ResponseEntity<NotificationTypeDto> saveNotificationType(@RequestBody @Validated NotificationTypeDto notificationTypeDto) {
        return new ResponseEntity<>(notificationTypeService.insertNotificationType(notificationTypeDto), HttpStatus.CREATED);
    }


}
