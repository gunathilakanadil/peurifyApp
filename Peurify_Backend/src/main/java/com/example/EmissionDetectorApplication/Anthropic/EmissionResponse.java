package com.example.EmissionDetectorApplication.Anthropic;

import lombok.Data;
import lombok.Builder;

@Data
@Builder
public class EmissionResponse {
    private int hasEmission;
    private String message;
}
