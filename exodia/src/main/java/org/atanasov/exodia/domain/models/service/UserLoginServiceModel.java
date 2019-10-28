package org.atanasov.exodia.domain.models.service;

import lombok.*;

import javax.validation.constraints.NotNull;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
public class UserLoginServiceModel {
    @NotNull
    private String username;

    @NotNull
    private String password;
}
