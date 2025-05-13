package com.finsavvy.api.finsavvy_api.v1.dto.account;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class NewAccountDto {
    private String name;
    private String bankName;
    private Long currencyId;
}
