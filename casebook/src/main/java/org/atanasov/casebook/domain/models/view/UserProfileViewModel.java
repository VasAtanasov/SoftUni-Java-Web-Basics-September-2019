package org.atanasov.casebook.domain.models.view;

import lombok.*;
import org.atanasov.casebook.domain.enums.Gender;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserProfileViewModel {
    private String username;
    private Gender gender;
}
