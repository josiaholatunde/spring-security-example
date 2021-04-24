package com.olatunde.conferencesecurity.services;

import com.olatunde.conferencesecurity.dtos.request.UserRegistrationRequestDto;
import com.olatunde.conferencesecurity.dtos.response.UserRegistrationResponseDto;

public interface AccountRegistrationService {

    UserRegistrationResponseDto createAccount(UserRegistrationRequestDto userRegistrationRequestDto);
}
