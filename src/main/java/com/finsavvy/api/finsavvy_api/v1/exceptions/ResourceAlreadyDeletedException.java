package com.finsavvy.api.finsavvy_api.v1.exceptions;

public class ResourceAlreadyDeletedException extends RuntimeException {
    public ResourceAlreadyDeletedException(String message) {
        super(message);
    }
}
