package com.example.EmissionDetectorApplication.Exception;

public class  ResourceNotFoundException extends RuntimeException {
    public ResourceNotFoundException(String message) {
        super(message);
    }
}
