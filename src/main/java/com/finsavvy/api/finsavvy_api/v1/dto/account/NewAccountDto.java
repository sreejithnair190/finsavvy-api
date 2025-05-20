package com.finsavvy.api.finsavvy_api.v1.dto.account;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class NewAccountDto {
    private String bankName;
    private String bankAccountTypeUUID;
    private String countryCurrencyUUID;
}
