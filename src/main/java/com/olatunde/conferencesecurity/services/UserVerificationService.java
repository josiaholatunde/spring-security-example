package com.olatunde.conferencesecurity.services;

import com.olatunde.conferencesecurity.models.User;
import com.olatunde.conferencesecurity.models.VerificationToken;

public interface UserVerificationService {
    VerificationToken persistVerificationToken(User user, String token);
}
