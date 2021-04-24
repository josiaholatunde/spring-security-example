package com.olatunde.conferencesecurity.services.impl;

import com.olatunde.conferencesecurity.models.User;
import com.olatunde.conferencesecurity.services.AccountCreationEmailSendingService;
import lombok.extern.slf4j.Slf4j;
import org.apache.logging.log4j.util.Strings;
import org.springframework.beans.factory.annotation.Value;

@Slf4j
public abstract class BaseAccountCreationEmailSendingService<T, R> implements AccountCreationEmailSendingService {

    @Value("${mail.from:admin@unque-solutions.com}")
    protected String fromEmail;

    protected static final String accountRegistrationSubject = "Account Confirmation";

    @Value("${application.name:Unique Solutions}")
    protected String applicationName;

    @Value("${application.url:http://localhost:8080}")
    protected String appBaseUrl;

    protected String getUserFullName(User user) {
        String fullName = user.getFirstName();
        if (!Strings.isBlank(user.getLastName())) {
            fullName += " " + user.getLastName();
        }
        return fullName;
    }

    protected String buildDynamicEmailContent(User user, String token) {
        String confirmationUrl = appBaseUrl + "/account-confirm?token="+token;
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(String.format(
                "Hi %s, <br/> Welcome to %s <br> Kindly confirm your account using this url: <a href='%s'> Verify Account </a>", getUserFullName(user),
                applicationName, confirmationUrl));
        return stringBuilder.toString();
    }

    protected abstract String getProviderName();

    @Override
    public void sendEmail(User user, String token) {

        T mail = buildMailObject(user, token);

        R response = makeApiCallToProvider(mail);
        if (response == null) {
            log.error("Null response received from {}", getProviderName());
        }
        logProviderResponse(user, response);
    }

    protected abstract R makeApiCallToProvider(T mail);

    protected abstract T buildMailObject(User user, String token);

    protected abstract void logProviderResponse(User user, R responseObject);
}
