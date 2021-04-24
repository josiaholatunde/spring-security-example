package com.olatunde.conferencesecurity.events;

import com.olatunde.conferencesecurity.models.User;
import lombok.Builder;
import lombok.Data;
import org.springframework.context.ApplicationEvent;

@Data
@Builder
public class AccountRegistrationEvent extends ApplicationEvent {

    private User user;
    private String applicationUrl;

    public AccountRegistrationEvent(User user, String applicationUrl) {
        super(user);
        this.user = user;
        this.applicationUrl = applicationUrl;
    }
}
