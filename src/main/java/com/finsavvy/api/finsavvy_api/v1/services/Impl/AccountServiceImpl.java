package com.finsavvy.api.finsavvy_api.v1.services.Impl;

import com.finsavvy.api.finsavvy_api.v1.dto.account.AccountDto;
import com.finsavvy.api.finsavvy_api.v1.dto.account.AccountUserDto;
import com.finsavvy.api.finsavvy_api.v1.dto.account.NewAccountDto;
import com.finsavvy.api.finsavvy_api.v1.entities.Budget;
import com.finsavvy.api.finsavvy_api.v1.entities.User;
import com.finsavvy.api.finsavvy_api.v1.entities.account.Account;
import com.finsavvy.api.finsavvy_api.v1.entities.account.AccountUser;
import com.finsavvy.api.finsavvy_api.v1.entities.account.BankAccountType;
import com.finsavvy.api.finsavvy_api.v1.exceptions.ResourceNotFoundException;
import com.finsavvy.api.finsavvy_api.v1.repositories.*;
import com.finsavvy.api.finsavvy_api.v1.services.AccountService;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.hibernate.mapping.Array;
import org.modelmapper.ModelMapper;
import org.modelmapper.PropertyMap;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static com.finsavvy.api.finsavvy_api.v1.enums.AccountRole.OWNER;
import static com.finsavvy.api.finsavvy_api.v1.utils.Auth.getAuthenticatedUserId;
import static com.finsavvy.api.finsavvy_api.v1.utils.Util.generatePageable;

@Service
@RequiredArgsConstructor
public class AccountServiceImpl implements AccountService {

    private final AccountRepository accountRepository;
    private final AccountUserRepository accountUserRepository;
    private final BankAccountTypeRepository bankAccountTypeRepository;
    private final CountryCurrencyRepository countryCurrencyRepository;
    private final UserRepository userRepository;
    private final ModelMapper modelMapper;

    @PostConstruct
    public void configureModelMapper() {
        modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);

        modelMapper.addMappings(new PropertyMap<Account, AccountDto>() {
            @Override
            protected void configure() {
                map().setUserId(source.getUser().getId());
            }
        });
    }

    @Override
    @Transactional(readOnly = true)
    public Page<AccountDto> getAccountsOfUser(Integer pageNumber, Integer size, String sortBy, String sortDir) {
        String[] sortable = {"id", "bank_name"};
        Pageable pageable = generatePageable(pageNumber, size, sortBy, sortDir, sortable);

        Long userId = getAuthenticatedUserId();
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("User not found"));

        Page<Account> accounts = accountRepository.findByUserId(userId, pageable);

        return accounts.map(account -> modelMapper.map(account, AccountDto.class));
    }

    @Override
    @Transactional
    public AccountDto createAccount(NewAccountDto newAccountDto) {
        Long userId = getAuthenticatedUserId();
        User user = userRepository
                .findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("User not found"));

        String bankAccountTypeUUID = newAccountDto.getBankAccountTypeUUID();
        BankAccountType bankAccountType = bankAccountTypeRepository
                .findByUuid(bankAccountTypeUUID)
                .orElseThrow(() -> new ResourceNotFoundException("Bank Type not found"));

        String countryCurrencyUUID = newAccountDto.getCountryCurrencyUUID();
        Budget.CountryCurrency countryCurrency = countryCurrencyRepository
                .findByUuid(countryCurrencyUUID)
                .orElseThrow(() -> new ResourceNotFoundException("Currency not found"));

        Account account = Account.builder()
                .bankName(newAccountDto.getBankName())
                .user(user)
                .bankAccountType(bankAccountType)
                .countryCurrency(countryCurrency)
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
        return accountDto;
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
