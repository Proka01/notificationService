package com.raf.restdemo.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


import javax.persistence.*;
import java.sql.Date;

@Getter
@Setter
@Entity
@Table
@AllArgsConstructor
@NoArgsConstructor
public class Notification {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String emailMsg;
    private Long clientId;
    private Long clientEmail;
    private Long managerId;
    private Long managerEmail;
    private Date notificationDate;
    private String notificationType;

}
