package com.example.EmissionDetectorApplication.Anthropic;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.*;

@Service
public class ImageAnalysisService {

    @Value("${anthropic.api.key}")
    private String apiKey;

    @Value("${anthropic.api.url}")
    private String apiUrl;

    private final RestTemplate restTemplate;

    public ImageAnalysisService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public EmissionResponse analyzeImage(byte[] imageBytes) {
        try {
            String base64Image = Base64.getEncoder().encodeToString(imageBytes);

            // Prepare the request body
            Map<String, Object> requestBody = prepareRequestBody(base64Image);

            // Set up headers
            HttpHeaders headers = prepareHeaders();

            // Make the API request
            HttpEntity<Map<String, Object>> requestEntity = new HttpEntity<>(requestBody, headers);
            ResponseEntity<AnthropicResponse> response = restTemplate.exchange(
                    apiUrl,
                    HttpMethod.POST,
                    requestEntity,
                    AnthropicResponse.class
            );

            // Process response
            if (response.getBody() != null && response.getBody().getContent() != null) {
                String result = response.getBody().getContent().get(0).get("text");
                return EmissionResponse.builder()
                        .hasEmission(Integer.parseInt(result.trim()))
                        .message("Analysis completed successfully")
                        .build();
            }

            throw new RuntimeException("Invalid response from Anthropic API");

        } catch (Exception e) {
            return EmissionResponse.builder()
                    .hasEmission(-1)
                    .message("Error analyzing image: " + e.getMessage())
                    .build();
        }
    }

    private Map<String, Object> prepareRequestBody(String base64Image) {
        Map<String, Object> messageContent = new HashMap<>();
        messageContent.put("type", "image");
        messageContent.put("source", Map.of(
                "type", "base64",
                "media_type", "image/jpeg",
                "data", base64Image
        ));

        Map<String, Object> textContent = new HashMap<>();
        textContent.put("type", "text");
        textContent.put("text", "Does this image show vehicle emissions? just Return 1 if yes, 0 if no.");

        return Map.of(
                "model", "claude-3-haiku-20240307",
                "max_tokens", 1000,
                "messages", List.of(Map.of(
                        "role", "user",
                        "content", List.of(messageContent, textContent)
                ))
        );
    }

    private HttpHeaders prepareHeaders() {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.set("x-api-key", apiKey);
        headers.set("anthropic-version", "2023-06-01");
        return headers;
    }
}