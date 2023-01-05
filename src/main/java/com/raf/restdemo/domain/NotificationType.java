package com.raf.restdemo.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@Entity
@Table
public class NotificationType {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String type;
    private String embededMsg;

    //all types
    private String clientFirstName;
    private String clientLastName;

    //ACTIVATION_EMAIL
    private String activationLink;
    private String activationCode;

    //PASSWORD_CHANGE_EMAIL
    private String successPasswordChangeInfo; //<successInfo> menjanje lozinke

    //CAR_RESERVATION_EMAIL
    private String successCarRentInfo;
    private String carBrand;
    private String carModel;
    private String registrationNum;

    //CANCEL_CAR_RESERVATION_EMAIL
    private String cancellationRentLink;

    //3-DAY_REMINDER_EMAIL
    //no specific params
}
