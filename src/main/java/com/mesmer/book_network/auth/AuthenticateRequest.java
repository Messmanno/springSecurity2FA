package com.mesmer.book_network.auth;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
@Builder
public class AuthenticateRequest {

    @Email(message = "mauvais format de l'email --> name@gmail.com")
    @NotEmpty(message = "le mail est requis")
    @NotBlank(message = "le mail est requis")
    private String email;

    @NotEmpty(message = "le password est requis")
    @NotBlank(message = "le password est requis")
    @Size(min = 5, message = "le mot de passe doit contenir au moins 5 caractères")
    private String password;
}
