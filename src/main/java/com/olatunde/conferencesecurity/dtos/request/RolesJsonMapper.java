package com.olatunde.conferencesecurity.dtos.request;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Builder
@Data
public class RolesJsonMapper {

    private String roleName;

    private List<String> authorities;
}
