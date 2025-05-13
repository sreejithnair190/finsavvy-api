package com.finsavvy.api.finsavvy_api.v1.dto.user;

import com.finsavvy.api.finsavvy_api.v1.dto.auth.TokenDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class NewUserDto {
    TokenDto tokens;
    UserDto user;
}
