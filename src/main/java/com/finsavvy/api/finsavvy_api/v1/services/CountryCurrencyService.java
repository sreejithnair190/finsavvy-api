package com.finsavvy.api.finsavvy_api.v1.services;

import com.finsavvy.api.finsavvy_api.v1.dto.CountryCurrencyDto;
import org.springframework.data.domain.Page;

public interface CountryCurrencyService {
    Page<CountryCurrencyDto> getCountryCurrencies(Integer pageNumber, Integer size, String sortBy, String sortDir);

}
