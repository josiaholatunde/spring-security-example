package com.olatunde.conferencesecurity.controllers;

import com.olatunde.conferencesecurity.dtos.request.UserRegistrationRequestDto;
import com.olatunde.conferencesecurity.dtos.response.UserRegistrationResponseDto;
import com.olatunde.conferencesecurity.services.AccountConfirmationService;
import com.olatunde.conferencesecurity.services.AccountRegistrationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "auth", produces = MediaType.APPLICATION_JSON_VALUE)
@RequiredArgsConstructor
public class AuthenticationController {

    private final AccountRegistrationService accountRegistrationService;
    private final AccountConfirmationService accountConfirmationService;

    @PostMapping("/register")
    public UserRegistrationResponseDto register(@RequestBody @Validated UserRegistrationRequestDto userRegistrationDto, BindingResult bindingResult) {

        // check for errors in request entity

        // create user account
        return accountRegistrationService.createAccount(userRegistrationDto);

    }

    @GetMapping("account-confirmation")
    public void accountConfirmation(@RequestParam(name = "token") String token) throws Exception {
         accountConfirmationService.confirmAccount(token);
    }


}
