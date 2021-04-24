package com.olatunde.conferencesecurity.models;

import lombok.Builder;
import lombok.Data;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;

import java.util.Collection;

@Builder
@Data
public class ConferenceUserDetail extends User {

    private String nickName;

    public ConferenceUserDetail(String username, String password, Collection<? extends GrantedAuthority> authorities) {
        super(username, password, authorities);
    }
}
