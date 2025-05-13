package com.finsavvy.api.finsavvy_api.v1.controllers;

import com.finsavvy.api.finsavvy_api.v1.common.ApiResponse;
import com.finsavvy.api.finsavvy_api.v1.dto.account.AccountDto;
import com.finsavvy.api.finsavvy_api.v1.dto.account.NewAccountDto;
import com.finsavvy.api.finsavvy_api.v1.services.AccountService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.finsavvy.api.finsavvy_api.v1.utils.Constants.API_VERSION;

@RestController
@RequestMapping(API_VERSION + "/accounts")
@RequiredArgsConstructor
public class AccountController {

    private final AccountService accountService;

//    TODO: Add Pagination, Sorting, Search and Filter
    @GetMapping
    public ResponseEntity<ApiResponse<List<AccountDto>>> getAccountsOfUser() {
        List<AccountDto> accounts = accountService.getAccountsOfUser();
        ApiResponse<List<AccountDto>> apiResponse = new ApiResponse<>(
          accounts,
          "Accounts of user successfully retrieved"
        );
        return ResponseEntity.ok(apiResponse);
    }

    @PostMapping
    public ResponseEntity<ApiResponse<AccountDto>> createAccount(@RequestBody NewAccountDto newAccountDto) {
        AccountDto accountDto = accountService.createAccount(newAccountDto);
        ApiResponse<AccountDto> apiResponse = new ApiResponse<>(
                accountDto,
                "Account created successfully"
        );
        return ResponseEntity.ok(apiResponse);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<AccountDto>> getAccountOfUserByAccountId() {
        return null;
    }

    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse<AccountDto>> updateAccountOfUserByAccountId() {
        return null;
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<Object>> deleteAccountOfUserByAccountId() {
        return null;
    }
}
