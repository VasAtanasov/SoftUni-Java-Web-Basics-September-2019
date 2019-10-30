package org.atanasov.casebook.domain.models.view;

import lombok.*;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
public class UserFriendsViewModel {
    @Singular
    private List<FriendViewModel> friends;
}
