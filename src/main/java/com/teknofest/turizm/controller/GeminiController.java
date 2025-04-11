package com.teknofest.turizm.controller;

import com.teknofest.turizm.request.GeminiRequest;
import com.teknofest.turizm.response.GeminiResponse;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@RestController
public class GeminiController {
    @Value("${spring.ai.openai.api-key}")
    private String apiKey;

    private final RestTemplate restTemplate;

    public GeminiController(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @GetMapping("/generate")
    public ResponseEntity<String> generateContent(@RequestParam String prompt) {
        String instruction = "Aşağıdaki soruyu değerlendir:\n" +
                "- Eğer turizmle ilgiliyse değerli bilgiler vererek arkadaş canlısı şekilde cevapla ancak lafı çok dolandırma.\n" +
                "- Eğer turizmle ilgili değilse sadece şunu yaz: 'Maalesef sadece turizm ile ilgili soruları cevaplayabilirim.'\n\n" +
                "Soru: " + prompt;

        String url = "https://generativelanguage.googleapis.com/v1beta/models/gemini-2.0-flash:generateContent?key=" + apiKey;

        GeminiRequest requestBody = new GeminiRequest(
                List.of(new GeminiRequest.Content(
                        List.of(new GeminiRequest.Part(instruction))
                ))
        );

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        HttpEntity<GeminiRequest> entity = new HttpEntity<>(requestBody, headers);

        ResponseEntity<GeminiResponse> response = restTemplate.exchange(
                url, HttpMethod.POST, entity, GeminiResponse.class
        );

        String text = response.getBody()
                .candidates().get(0)
                .content().parts().get(0)
                .text();

        return ResponseEntity.ok(text);
    }
}
