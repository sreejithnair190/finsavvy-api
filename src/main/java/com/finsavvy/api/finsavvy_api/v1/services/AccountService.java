package com.finsavvy.api.finsavvy_api.v1.services;

import com.finsavvy.api.finsavvy_api.v1.dto.account.AccountDto;
import com.finsavvy.api.finsavvy_api.v1.dto.account.NewAccountDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Sort;

import java.util.List;

public interface AccountService {
    Page<AccountDto> getAccountsOfUser(Integer pageNumber, Integer size, String sortBy, String sortDir);
    AccountDto createAccount(NewAccountDto newAccountDto);
    AccountDto getAccountOfUserByAccountId();
    AccountDto updateAccountOfUserByAccountId();
    String deleteAccountOfUserByAccountId();
}
