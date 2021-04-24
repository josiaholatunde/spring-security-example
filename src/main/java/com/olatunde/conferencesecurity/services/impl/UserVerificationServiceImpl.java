package com.olatunde.conferencesecurity.services.impl;

import com.olatunde.conferencesecurity.models.User;
import com.olatunde.conferencesecurity.models.VerificationToken;
import com.olatunde.conferencesecurity.repositories.UserVerificationRepository;
import com.olatunde.conferencesecurity.services.UserVerificationService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserVerificationServiceImpl implements UserVerificationService {

    private final UserVerificationRepository userVerificationRepository;

    @Value("${verification.expiration.duration:24}")
    private int userVerificationExpiryDuration;

    @Override
    public VerificationToken persistVerificationToken(User user, String token) {
        VerificationToken verificationToken = VerificationToken.builder().build();
        verificationToken.setExpirationDate(verificationToken.calculateExpirationDate(userVerificationExpiryDuration));
        verificationToken.setToken(token);
        verificationToken.setUser(user);

        return userVerificationRepository.save(verificationToken);
    }
}
