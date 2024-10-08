package com.example.projectboard.dto;

import com.example.projectboard.domain.UserAccount;

import java.io.Serializable;

/**
 * DTO for {@link UserAccount}
 */
// userid, userpassword, email, nickname, memo, createdAt, createdBy, modifiedAt, modifiedBy
public record UserAccountDto(Long userId, String name, String nickname) implements Serializable {
    public static UserAccountDto from(UserAccount userAccount) {
        return new UserAccountDto(
                userAccount.getUserId(),
                userAccount.getName(),
                userAccount.getNickname()
        );
    }

    public static UserAccountDto of(Long userId, String name, String nickname) {
        return new UserAccountDto(userId, name,nickname);
    }
}