package com.olatunde.conferencesecurity.services.impl.sendgrid;

import com.olatunde.conferencesecurity.models.User;
import com.olatunde.conferencesecurity.services.impl.BaseAccountCreationEmailSendingService;
import com.sendgrid.*;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
@RequiredArgsConstructor
@Slf4j
public class SendgridAccountCreationEmailSendingService extends BaseAccountCreationEmailSendingService<Mail, Response> {

    private final SendGrid sendGridClient;

    private static String PROVIDER_NAME = "SEND GRID";

    @Override
    protected Response makeApiCallToProvider(Mail mail) {
        return makeApiCallToSendGrid(mail);
    }

    @Override
    protected Mail buildMailObject(User user, String token) {
        Mail mail = new Mail(new Email(fromEmail), accountRegistrationSubject, new Email(user.getEmail()), new Content("text/html", buildDynamicEmailContent(user, token));
        return mail;
    }

    @Override
    protected void logProviderResponse(User user, Response response) {
        log.info("Successfully made api to sendgrid for user with userName {} \n Status code {}  \n Response Body {} \n Response Headers {}",
                getUserFullName(user), response.getStatusCode(), response.getBody(), response.getHeaders());
    }

    private Response makeApiCallToSendGrid(Mail mail) {
        Response response = null;
        try {
            Request request = buildSendGridRequest(mail);
            response = sendGridClient.api(request);
        } catch (IOException ex) {
            log.error("An error occurred while making API call to {} API -> {}", ex.getMessage(), ex);
        } catch (Exception ex) {
            log.error(String.format("An error occurred while making API call to %s API -> Error Message: %s", getProviderName(), ex.getMessage()), ex);
        }
       return response;
    }

    private Request buildSendGridRequest(Mail mail) throws IOException {
        Request request = new Request();

        request.setMethod(Method.POST);
        request.setEndpoint("mail/send");
        request.setBody(mail.build());
        return request;
    }

    @Override
    protected String getProviderName() {
        return PROVIDER_NAME;
    }


}
