package com.finsavvy.api.finsavvy_api.v1.dto.account;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AccountUserDto {
    private Long id;
    private String uuid;
    private Long userId;
    private Long role;
    private LocalDateTime deletedAt;
}
