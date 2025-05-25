package com.finsavvy.api.finsavvy_api.v1.services.Impl;

import com.finsavvy.api.finsavvy_api.v1.dto.CountryCurrencyDto;
import com.finsavvy.api.finsavvy_api.v1.entities.Budget;
import com.finsavvy.api.finsavvy_api.v1.repositories.CountryCurrencyRepository;
import com.finsavvy.api.finsavvy_api.v1.services.CountryCurrencyService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import static com.finsavvy.api.finsavvy_api.v1.utils.Util.generatePageable;

@Service
@RequiredArgsConstructor
public class CountryCurrencyServiceImpl implements CountryCurrencyService {

    private final CountryCurrencyRepository countryCurrencyRepository;
    private final ModelMapper modelMapper;

    @Override
    public Page<CountryCurrencyDto> getCountryCurrencies(
            Integer pageNumber,
            Integer size,
            String sortBy,
            String sortDir
    ) {
        String[] sortable = {"bank_account_type_name", "id"};
        Pageable pageable = generatePageable(
                pageNumber,
                size,
                sortBy,
                sortDir,
                sortable
        );
        Page<Budget.CountryCurrency> countryCurrencies = countryCurrencyRepository.findAll(pageable);

        return countryCurrencies.map(countryCurrency ->
                modelMapper.map(countryCurrency, CountryCurrencyDto.class
        ));
    }
}
