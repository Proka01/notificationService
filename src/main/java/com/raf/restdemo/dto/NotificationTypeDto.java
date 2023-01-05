package com.raf.restdemo.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class NotificationTypeDto {
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
