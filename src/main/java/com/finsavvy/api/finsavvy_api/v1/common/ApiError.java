package com.finsavvy.api.finsavvy_api.v1.common;

import lombok.Builder;
import lombok.Data;
import org.springframework.http.HttpStatus;

import java.util.List;

@Data
@Builder
public class ApiError {
    private HttpStatus status;
    private String message;
    private List<String> errors;
}
