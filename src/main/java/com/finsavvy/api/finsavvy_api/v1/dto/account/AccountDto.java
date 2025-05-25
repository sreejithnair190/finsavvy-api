package com.finsavvy.api.finsavvy_api.v1.dto.account;

import com.finsavvy.api.finsavvy_api.v1.dto.CountryCurrencyDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AccountDto {
    private Long id;
    private String uuid;
    private Long userId;
    private String bankName;
    private List<AccountUserDto> accountUsers;
    private BankAccountTypeDto bankAccountType;
    private CountryCurrencyDto countryCurrency;
}
