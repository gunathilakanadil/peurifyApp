package com.example.EmissionDetectorApplication.Anthropic;

import lombok.Data;
import java.util.List;
import java.util.Map;

@Data
public class AnthropicResponse {
    private String id;
    private String model;
    private String role;
    private List<Map<String, String>> content;
}
