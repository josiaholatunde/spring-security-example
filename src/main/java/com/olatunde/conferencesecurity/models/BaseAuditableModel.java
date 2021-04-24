package com.olatunde.conferencesecurity.models;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import java.sql.Timestamp;

@MappedSuperclass
public class BaseAuditableModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @CreatedDate
    private Timestamp createdAt;

    @LastModifiedDate
    private Timestamp updatedAt;

}
