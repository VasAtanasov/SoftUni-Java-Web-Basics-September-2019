package org.atanasov.exodia.domain.models.service;

import lombok.*;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
public class UserRegisterServiceModel {
    @NotNull
    private String username;

    @NotNull
    private String password;

    @NotNull
    private String confirmPassword;

    @NotNull
    @Email
    private String email;
}
