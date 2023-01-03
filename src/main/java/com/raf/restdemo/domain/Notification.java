package com.raf.restdemo.domain;

import lombok.Getter;
import lombok.Setter;


import javax.persistence.*;
import java.sql.Date;

@Getter
@Setter
@Entity
@Table
public class Notification {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String notificationType;
    private String embededMsg;
    private Long userId;
    private Long managerId;
    private Date notificationDate;

    public Notification() {
    }

    public Notification(String notificationType, String embededMsg, Long userId, Long managerId, Date notificationDate) {
        this.notificationType = notificationType;
        this.embededMsg = embededMsg;
        this.userId = userId;
        this.managerId = managerId;
        this.notificationDate = notificationDate;
    }
}
