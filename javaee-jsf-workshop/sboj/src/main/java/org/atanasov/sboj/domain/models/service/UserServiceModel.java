package org.atanasov.sboj.domain.models.service;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
public class UserServiceModel {
    private String id;
    private String username;
}
