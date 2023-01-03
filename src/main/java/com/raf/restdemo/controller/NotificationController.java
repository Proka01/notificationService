package com.raf.restdemo.controller;

import com.raf.restdemo.dto.CreateNotificationDto;
import com.raf.restdemo.dto.NotificationDto;
import com.raf.restdemo.service.NotificationService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/notification")
public class NotificationController {
    private NotificationService notificationService;

    public NotificationController(NotificationService notificationService) {
        this.notificationService = notificationService;
    }

    @PostMapping("/insert")
    public ResponseEntity<NotificationDto> saveNotification(@RequestBody @Validated CreateNotificationDto createNotificationDto) {
        return new ResponseEntity<>(notificationService.insertNotification(createNotificationDto), HttpStatus.CREATED);
    }
}
