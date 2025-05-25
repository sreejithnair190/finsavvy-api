package com.finsavvy.api.finsavvy_api.v1.controllers;

import com.finsavvy.api.finsavvy_api.v1.common.ApiResponse;
import com.finsavvy.api.finsavvy_api.v1.dto.CountryCurrencyDto;
import com.finsavvy.api.finsavvy_api.v1.dto.account.BankAccountTypeDto;
import com.finsavvy.api.finsavvy_api.v1.services.BankAccountTypeService;
import com.finsavvy.api.finsavvy_api.v1.services.CountryCurrencyService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import static com.finsavvy.api.finsavvy_api.v1.utils.Constants.API_VERSION;

@RestController
@RequestMapping(API_VERSION + "/country-currencies")
@RequiredArgsConstructor
public class CountryCurrencyController {
    private final CountryCurrencyService countryCurrencyService;

    @GetMapping
    public ResponseEntity<ApiResponse<Page<CountryCurrencyDto>>> getBankAccountType(
            @RequestParam(defaultValue = "1") Integer pageNumber,
            @RequestParam(defaultValue = "10") Integer size,
            @RequestParam(defaultValue = "id") String sortBy,
            @RequestParam(defaultValue = "desc") String sortDir,
            @RequestParam(defaultValue = "") String search
    ) {
        Page<CountryCurrencyDto> countryCurrencies = countryCurrencyService.getCountryCurrencies(
                pageNumber,
                size,
                sortBy,
                sortDir
        );

        ApiResponse<Page<CountryCurrencyDto>> apiResponse = new ApiResponse<>(
                countryCurrencies,
                "Country Currencies successfully retrieved"
        );

        return ResponseEntity.ok(apiResponse);
    }
}
