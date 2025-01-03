package com.example.EmissionDetectorApplication.Anthropic;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class EmissionCheckResponse {
    private String numberPlate;
    private String status;
    private String message;
}
