package com.finsavvy.api.finsavvy_api.v1.dto.account;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class BankAccountTypeDto {
    private Long id;
    private String uuid;
    private String bankAccountTypeName;
    private String bankAccountTypeDescription;
}
