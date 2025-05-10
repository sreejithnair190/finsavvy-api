package com.finsavvy.api.finsavvy_api.v1.dto.response;

import com.finsavvy.api.finsavvy_api.v1.dto.TokenDto;
import com.finsavvy.api.finsavvy_api.v1.dto.UserDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class NewUserResponseDto {
    TokenDto tokens;
    UserDto user;
}
