package com.raf.restdemo.controller;

import com.raf.restdemo.dto.CreateNotificationDto;
import com.raf.restdemo.dto.NotificationDto;
import com.raf.restdemo.secutiry.CheckSecurity;
import com.raf.restdemo.secutiry.service.TokenService;
import com.raf.restdemo.service.NotificationService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/notification")
public class NotificationController {
    private NotificationService notificationService;
    private TokenService tokenService;

    public NotificationController(NotificationService notificationService, TokenService tokenService) {
        this.notificationService = notificationService;
        this.tokenService = tokenService;
    }

    @PostMapping("/insert")
    public ResponseEntity<NotificationDto> saveNotification(@RequestBody @Validated CreateNotificationDto createNotificationDto) {
        return new ResponseEntity<>(notificationService.insertNotification(createNotificationDto), HttpStatus.CREATED);
    }

    @GetMapping("/getNotificationsForClientId")
    @CrossOrigin(origins = "http://localhost:3000")
    public ResponseEntity<List<NotificationDto>> getNotificationsForClientId(@RequestHeader String authorization) {
        Long id = tokenService.parseId(authorization);
        return new ResponseEntity<>(notificationService.getAllClientNotification(id), HttpStatus.OK);
    }

    @GetMapping("/getAllNotifications")
    @CheckSecurity(roles = {"ROLE_ADMIN"})
    @CrossOrigin(origins = "http://localhost:3000")
    public ResponseEntity<List<NotificationDto>> getAllNotifications(@RequestHeader String authorization) {
        return new ResponseEntity<>(notificationService.getAllNotifications(), HttpStatus.OK);
    }
}
