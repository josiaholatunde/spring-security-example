package com.olatunde.conferencesecurity.listeners;

import com.olatunde.conferencesecurity.events.AccountRegistrationEvent;
import com.olatunde.conferencesecurity.models.User;
import com.olatunde.conferencesecurity.services.AccountCreationEmailSendingService;
import com.olatunde.conferencesecurity.services.UserVerificationService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
@RequiredArgsConstructor
public class AccountRegistrationListener implements ApplicationListener<AccountRegistrationEvent> {

    private final UserVerificationService userVerificationService;
    private final AccountCreationEmailSendingService accountCreationEmailSendingService;

    @Override
    public void onApplicationEvent(AccountRegistrationEvent accountRegistrationEvent) {
        User createdUser = accountRegistrationEvent.getUser();

        String token = UUID.randomUUID().toString();
        userVerificationService.persistVerificationToken(createdUser, token);
        accountCreationEmailSendingService.sendEmail(createdUser, token);
    }
}
