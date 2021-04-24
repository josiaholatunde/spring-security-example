package com.olatunde.conferencesecurity.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity(name = "authorities")
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Authority extends BaseAuditableModel {

    @Column(nullable = false)
    private String title;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

}
