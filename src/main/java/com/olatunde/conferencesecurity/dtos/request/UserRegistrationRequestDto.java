package com.olatunde.conferencesecurity.dtos.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Email;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;


@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
public class UserRegistrationRequestDto {

    @NotBlank(message="The userName field is required")
    private String userName;

    @NotBlank(message="The password field is required")
    @Min(value = 7, message = "Password should not be less than 7 characters")
    private String password;

    @NotBlank(message="The confirm password field is required")
    private String confirmPassword;

    @NotBlank(message="The first name field is required")
    private String firstName;

    private String lastName;

    @NotBlank(message="The first email is required")
    @Email(message = "must be a valid email")
    @Pattern(regexp = ".+@.+\\..+", message = "must be a valid email address")
    private String email;
}
