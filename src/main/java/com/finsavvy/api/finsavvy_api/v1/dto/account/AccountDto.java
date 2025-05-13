package com.finsavvy.api.finsavvy_api.v1.dto.account;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AccountDto {
    private Long id;
    private String uuid;
    private Long userId;
    private String name;
    private String bankName;
    private Long currencyId;
    private List<AccountUserDto> accountUsers;
    private LocalDateTime deletedAt;
}
