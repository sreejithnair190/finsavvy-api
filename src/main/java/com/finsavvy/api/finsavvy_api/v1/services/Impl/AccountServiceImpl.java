package com.finsavvy.api.finsavvy_api.v1.services.Impl;

import com.finsavvy.api.finsavvy_api.v1.dto.account.AccountDto;
import com.finsavvy.api.finsavvy_api.v1.dto.account.AccountUserDto;
import com.finsavvy.api.finsavvy_api.v1.dto.account.NewAccountDto;
import com.finsavvy.api.finsavvy_api.v1.entities.User;
import com.finsavvy.api.finsavvy_api.v1.entities.account.Account;
import com.finsavvy.api.finsavvy_api.v1.entities.account.AccountUser;
import com.finsavvy.api.finsavvy_api.v1.exceptions.ResourceNotFoundException;
import com.finsavvy.api.finsavvy_api.v1.repositories.AccountRepository;
import com.finsavvy.api.finsavvy_api.v1.repositories.AccountUserRepository;
import com.finsavvy.api.finsavvy_api.v1.repositories.UserRepository;
import com.finsavvy.api.finsavvy_api.v1.services.AccountService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static com.finsavvy.api.finsavvy_api.v1.enums.AccountRole.OWNER;
import static com.finsavvy.api.finsavvy_api.v1.utils.Auth.getAuthenticatedUserId;

@Service
@RequiredArgsConstructor
public class AccountServiceImpl implements AccountService {

    private final AccountRepository accountRepository;
    private final AccountUserRepository accountUserRepository;
    private final UserRepository userRepository;
    private final ModelMapper modelMapper;

    @Override
    public List<AccountDto> getAccountsOfUser() {
        return List.of();
//        Long userId = getAuthenticatedUserId();
//        User user = userRepository.findById(userId)
//                .orElseThrow(() -> new ResourceNotFoundException("User not found with id: " + userId));
//
//        List<Account> accounts = accountRepository.findByUser(user);
//
//        return accounts
//                .stream()
//                .map(account -> modelMapper.map(account, AccountDto.class))
//                .toList();
    }

    @Override
    @Transactional
    public AccountDto createAccount(NewAccountDto newAccountDto) {
        Long userId = getAuthenticatedUserId();
        User user = userRepository
                .findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("User not found with id: " + userId));

//        Account account = modelMapper.map(newAccountDto, Account.class);
        Account account = Account.builder()
                .name(newAccountDto.getName())
                .bankName(newAccountDto.getBankName())
                .currencyId(newAccountDto.getCurrencyId())
                .owner(user)
                .build();
        Account newAccount = accountRepository.save(account);


        Long newAccountId = newAccount.getId();
        AccountUser newAccountUser = AccountUser.builder()
                .userId(userId)
                .accountId(newAccountId)
                .role(OWNER)
                .build();
        accountUserRepository.save(newAccountUser);

        AccountDto accountDto = modelMapper.map(newAccount, AccountDto.class);
        AccountUserDto accountUserDto = modelMapper.map(newAccountUser, AccountUserDto.class);
        accountDto.setAccountUsers(List.of(accountUserDto));
        return  accountDto;
    }

    @Override
    public AccountDto getAccountOfUserByAccountId() {
        return null;
    }

    @Override
    public AccountDto updateAccountOfUserByAccountId() {
        return null;
    }

    @Override
    public String deleteAccountOfUserByAccountId() {
        return "";
    }
}
