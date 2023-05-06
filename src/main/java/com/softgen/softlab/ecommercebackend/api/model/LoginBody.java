package com.softgen.softlab.ecommercebackend.api.model;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LoginBody {
    @NotBlank
    @NotBlank
    private String username;
    @NotBlank
    @NotBlank
    private String password;
}
