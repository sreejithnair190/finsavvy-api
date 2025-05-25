package com.finsavvy.api.finsavvy_api.v1.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CountryCurrencyDto {
    private Long id;
    private String uuid;
    private String countryName;
    private String iso;
    private String iso3;
    private String dialExtension;
    private String currencyCode;
    private String currencySymbol;
    private String currencyName;
}
