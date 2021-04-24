package com.olatunde.conferencesecurity.dtos.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
public class UserRegistrationResponseDto {

    private String userName;

    private String password;

    private String confirmPassword;

    private String firstName;

    private String lastName;

    private String email;
}
