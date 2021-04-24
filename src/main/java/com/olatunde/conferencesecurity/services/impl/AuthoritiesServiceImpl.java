package com.olatunde.conferencesecurity.services.impl;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.olatunde.conferencesecurity.dtos.request.RolesJsonMapper;
import com.olatunde.conferencesecurity.models.Authority;
import com.olatunde.conferencesecurity.models.User;
import com.olatunde.conferencesecurity.repositories.AccountRegistrationRepository;
import com.olatunde.conferencesecurity.services.AuthoritiesService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.util.ResourceUtils;

import java.io.File;
import java.nio.file.Files;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
@RequiredArgsConstructor
public class AuthoritiesServiceImpl implements AuthoritiesService {

    private String authoritiesFilePath = "classpath:config/authorities.json";
    private ObjectMapper objectMapper = new ObjectMapper();
    private static final String DEFAULT_ROLE = "NORMAL_USER";

    private final AccountRegistrationRepository accountRegistrationRepository;

    @Override
    public List<Authority> assignUserDefaultApplicationAuthorities(User user) throws Exception {
        RolesJsonMapper defaultApplicationRole = readDefaultApplicationAuthorities();
        List<Authority> authorityList = defaultApplicationRole.getAuthorities().stream().map((authority) -> Authority.builder()
                .title(authority)
                .user(user)
                .build())
                .collect(Collectors.toList());

        user.getAuthorities().addAll(authorityList);
        User updatedUser = accountRegistrationRepository.saveAndFlush(user);
        return updatedUser.getAuthorities();
    }

    private RolesJsonMapper readDefaultApplicationAuthorities() throws Exception {
        File file = ResourceUtils.getFile(authoritiesFilePath);
        String content = new String(Files.readAllBytes(file.toPath()));
        List<RolesJsonMapper> rolesJsonMapperList = objectMapper.readValue(content, new TypeReference<List<RolesJsonMapper>>(){});
        RolesJsonMapper defaultRole = rolesJsonMapperList.stream().filter(role -> role.getRoleName().equals(DEFAULT_ROLE)).findFirst()
                .orElseThrow(() -> new Exception("Default role not found in authorities file"));
        return defaultRole;
    }
}
