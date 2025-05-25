package com.finsavvy.api.finsavvy_api.v1.services.Impl;

import com.finsavvy.api.finsavvy_api.v1.dto.account.BankAccountTypeDto;
import com.finsavvy.api.finsavvy_api.v1.entities.account.BankAccountType;
import com.finsavvy.api.finsavvy_api.v1.repositories.BankAccountTypeRepository;
import com.finsavvy.api.finsavvy_api.v1.services.BankAccountTypeService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import static com.finsavvy.api.finsavvy_api.v1.utils.Util.generatePageable;

@Service
@RequiredArgsConstructor
public class BankAccountTypeServiceImpl implements BankAccountTypeService {

    private final BankAccountTypeRepository bankAccountTypeRepository;
    private final ModelMapper modelMapper;

    @Override
    public Page<BankAccountTypeDto> getBankAccountTypes(
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

        Page<BankAccountType> bankAccountTypes = bankAccountTypeRepository.findAll(pageable);

        return bankAccountTypes.map(bankAccountType ->
                modelMapper.map(bankAccountType, BankAccountTypeDto.class
        ));
    }
}
