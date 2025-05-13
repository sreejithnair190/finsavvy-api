package com.finsavvy.api.finsavvy_api.v1.services;

import com.finsavvy.api.finsavvy_api.v1.dto.account.AccountDto;
import com.finsavvy.api.finsavvy_api.v1.dto.account.NewAccountDto;

import java.util.List;

public interface AccountService {
    List<AccountDto> getAccountsOfUser();
    AccountDto createAccount(NewAccountDto newAccountDto);
    AccountDto getAccountOfUserByAccountId();
    AccountDto updateAccountOfUserByAccountId();
    String deleteAccountOfUserByAccountId();
}
