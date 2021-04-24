package com.olatunde.conferencesecurity.services.impl;

import com.olatunde.conferencesecurity.dtos.request.UserRegistrationRequestDto;
import com.olatunde.conferencesecurity.dtos.response.UserRegistrationResponseDto;
import com.olatunde.conferencesecurity.events.AccountRegistrationEvent;
import com.olatunde.conferencesecurity.exceptions.BadRequestException;
import com.olatunde.conferencesecurity.models.User;
import com.olatunde.conferencesecurity.repositories.AccountRegistrationRepository;
import com.olatunde.conferencesecurity.services.AccountRegistrationService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
public class AccountRegistrationServiceImpl implements AccountRegistrationService {

    private final AccountRegistrationRepository accountRegistrationRepository;
    private final PasswordEncoder passwordEncoder;
    private final ApplicationEventPublisher applicationEventPublisher;

    @Override
    public UserRegistrationResponseDto createAccount(UserRegistrationRequestDto userRegistrationRequestDto) {
        validateAccountRegistrationRequest(userRegistrationRequestDto);

        userRegistrationRequestDto.setPassword(passwordEncoder.encode(userRegistrationRequestDto.getPassword()));
        User createdUser =  accountRegistrationRepository.save(convertDataToEntity(userRegistrationRequestDto));
        applicationEventPublisher.publishEvent(new AccountRegistrationEvent(createdUser, "conference_scheduler"));
        return convertEntityToData(createdUser);
    }

    private void validateAccountRegistrationRequest(UserRegistrationRequestDto userRegistrationRequestDto) {
        if (!userRegistrationRequestDto.getPassword().equals(userRegistrationRequestDto.getConfirmPassword())) {
            throw new BadRequestException("Passwords do not match. Please check and correct");
        }
        if (isUsernameTaken(userRegistrationRequestDto)) {
            throw new BadRequestException("Username has been taken");
        }
        if (isEmailTaken(userRegistrationRequestDto)) {
            throw new BadRequestException("Email has been taken");
        }
    }

    private boolean isEmailTaken(UserRegistrationRequestDto userRegistrationRequestDto) {
        return accountRegistrationRepository.existsByUserName(userRegistrationRequestDto.getEmail());
    }

    private boolean isUsernameTaken(UserRegistrationRequestDto userRegistrationRequestDto) {
        return accountRegistrationRepository.existsByUserName(userRegistrationRequestDto.getUserName());
    }

    private User convertDataToEntity(UserRegistrationRequestDto userRegistrationRequestDto) {
        User user = User.builder().build();
        BeanUtils.copyProperties(userRegistrationRequestDto, user);
        return user;
    }

    private UserRegistrationResponseDto convertEntityToData(User user) {
        UserRegistrationResponseDto userRegistrationResponseDto = UserRegistrationResponseDto.builder().build();
        BeanUtils.copyProperties(user, userRegistrationResponseDto);
        return userRegistrationResponseDto;
    }


}
