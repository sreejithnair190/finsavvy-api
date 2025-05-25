package com.finsavvy.api.finsavvy_api.v1.services;

import com.finsavvy.api.finsavvy_api.v1.dto.account.BankAccountTypeDto;
import org.springframework.data.domain.Page;

public interface BankAccountTypeService {
    Page<BankAccountTypeDto> getBankAccountTypes(Integer pageNumber, Integer size, String sortBy, String sortDir);
}
