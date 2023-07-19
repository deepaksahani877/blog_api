package com.app.blog_api.dto;


import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@RequiredArgsConstructor
public class SignupDto {
    @NotBlank(message = "First name required.")
    private String firstName;

    @NotBlank(message = "Last name required.")
    private String lastName;

    @Email(message = "Invalid email.")
    @NotBlank(message = "Email required.")
    private String email;

    @NotBlank(message="Password Required.")
    private String password;
}
