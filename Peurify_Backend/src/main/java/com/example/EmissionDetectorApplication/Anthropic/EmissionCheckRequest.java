package com.example.EmissionDetectorApplication.Anthropic;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class EmissionCheckRequest {
    private String numberPlate;
    // No need to include image here as it will be part of MultipartFile
}