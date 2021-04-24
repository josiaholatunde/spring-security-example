package com.olatunde.conferencesecurity.services;

import com.olatunde.conferencesecurity.models.User;

public interface AccountCreationEmailSendingService {

    void sendEmail(User user, String token);
}
