package com.olatunde.conferencesecurity.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Entity(name = "users")
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class User extends BaseAuditableModel {

    @Column(name = "user_name", nullable = false)
    private String userName;

    @Column(nullable = false)
    private String password;

    @Column(name = "first_name", nullable = false)
    private String firstName;

    @Column(name = "last_name", nullable = false)
    private String lastName;

    private String email;

    @OneToMany(mappedBy = "user")
    private List<Authority> authorities;

    @OneToMany(mappedBy = "user", cascade = CascadeType.PERSIST)
    private List<VerificationToken> verificationTokens;



}
