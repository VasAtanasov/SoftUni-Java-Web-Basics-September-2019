package org.atanasov.casebook.domain.models.view;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.atanasov.casebook.domain.enums.Gender;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserHomeViewModel {
    private String id;
    private String username;
    private Gender gender;
}
