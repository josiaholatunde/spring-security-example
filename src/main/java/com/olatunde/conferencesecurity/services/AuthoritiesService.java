package com.olatunde.conferencesecurity.services;

import com.olatunde.conferencesecurity.models.Authority;
import com.olatunde.conferencesecurity.models.User;

import java.util.List;

public interface AuthoritiesService {
    List<Authority> assignUserDefaultApplicationAuthorities(User user) throws Exception;
}
