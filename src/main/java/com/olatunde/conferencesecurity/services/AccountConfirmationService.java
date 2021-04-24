package com.olatunde.conferencesecurity.services;

public interface AccountConfirmationService {
    void confirmAccount(String token) throws Exception;
}
