package com.example.backend.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class N8nWebhookService {
	private final RestTemplate restTemplate;
    private final String webhookUrl;

    public N8nWebhookService(RestTemplate restTemplate,
                       @Value("${n8n.webhook.url}") String webhookUrl) {
        this.restTemplate = restTemplate;
        this.webhookUrl = webhookUrl;
    }

    public void sendToN8n(Object payload) {
        try {
            restTemplate.postForEntity(webhookUrl, payload, String.class);
        } catch (Exception e) {
            // Don't break your API if n8n is down. Just log.
            System.out.println("⚠️ Failed to send to n8n: " + e.getMessage());
        }
    }

}
