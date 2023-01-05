package com.raf.restdemo.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class ActivationEmailDataDto {
    private Long userId;
    private String notificationType;
    private String firstName;
    private String lastName;
    private String activationLink;
    private String activationCode;
}
