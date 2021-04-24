package com.olatunde.conferencesecurity.services.impl;

import com.olatunde.conferencesecurity.exceptions.BadRequestException;
import com.olatunde.conferencesecurity.models.User;
import com.olatunde.conferencesecurity.models.VerificationToken;
import com.olatunde.conferencesecurity.repositories.AccountRegistrationRepository;
import com.olatunde.conferencesecurity.repositories.UserVerificationRepository;
import com.olatunde.conferencesecurity.services.AccountConfirmationService;
import com.olatunde.conferencesecurity.services.AuthoritiesService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Timestamp;
import java.time.LocalDateTime;

@Service
@Slf4j
@RequiredArgsConstructor
public class AccountConfirmationServiceImpl implements AccountConfirmationService {

    private final UserVerificationRepository userVerificationRepository;
    private final AccountRegistrationRepository accountRegistrationRepository;

    private final AuthoritiesService authoritiesService;

    @Override
    @Transactional
    public void confirmAccount(String token) throws Exception {
        VerificationToken verificationToken = userVerificationRepository.findByToken(token).orElseThrow(
                () -> new BadRequestException("Invalid token")
        );
        validateToken(verificationToken);
        User user = verificationToken.getUser();
        authoritiesService.assignUserDefaultApplicationAuthorities(user);
        verifyUser(user);
        userVerificationRepository.delete(verificationToken);
    }

    private void validateToken(VerificationToken verificationToken) {
        if (!isTokenStillValid(verificationToken)) {
            throw new BadRequestException("Token has expired. Kindly contact admin");
        }
        if (verificationToken.getUser().isEnabled()) {
            throw new BadRequestException("User has already been enabled. Proceed to login");
        }
    }

    private boolean isTokenStillValid(VerificationToken token) {
        return token.getExpirationDate().after(Timestamp.valueOf(LocalDateTime.now()));
    }


    private User verifyUser(User user) {
        user.setEnabled(true);
        return accountRegistrationRepository.saveAndFlush(user);
    }
}
