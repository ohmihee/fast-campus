package com.example.projectboard.dto;

import com.example.projectboard.domain.UserAccount;

import java.io.Serializable;

/**
 * DTO for {@link UserAccount}
 */
public record UserAccountDto(Long userId, String name, String nickname) implements Serializable {
    public static UserAccountDto from(UserAccount userAccount) {
        return new UserAccountDto(
                userAccount.getUserId(),
                userAccount.getName(),
                userAccount.getNickname()
        );
    }
}