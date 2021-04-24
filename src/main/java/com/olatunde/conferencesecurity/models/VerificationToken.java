package com.olatunde.conferencesecurity.models;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Builder;
import lombok.Data;
import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Calendar;
import java.util.Date;

@Entity(name = "verification_tokens")
@JsonIgnoreProperties({"hibernateLazyInitializer","handler"})
@Builder
@Data
public class VerificationToken extends BaseAuditableModel {

    private Long id;
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @Column(name = "expiry_date", nullable = false)
    private Timestamp expirationDate;

    private String token;

    public Timestamp calculateExpirationDate(int expirationDuration) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(new Date());
        calendar.add(Calendar.HOUR, expirationDuration);
        return new Timestamp(calendar.getTimeInMillis());
    }
}
