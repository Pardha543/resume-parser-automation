package com.example.backend.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.backend.dto.ParsedResume;
import com.example.backend.service.N8nWebhookService;
import com.example.backend.service.ResumeParserService;
import com.example.backend.service.ResumeTextExtractorService;

@RestController
@RequestMapping("/api/resumes")
public class ResumeParserController {
	 private final ResumeTextExtractorService extractor;
	    private final ResumeParserService parser;
	    private final N8nWebhookService n8nWebhookService;

	    public ResumeParserController(ResumeTextExtractorService extractor,
	                                  ResumeParserService parser,
	                                  N8nWebhookService n8nWebhookService) {
	        this.extractor = extractor;
	        this.parser = parser;
	        this.n8nWebhookService = n8nWebhookService;
	    }

	    @GetMapping("/{fileId}/parse")
	    public ResponseEntity<ParsedResume> parse(@PathVariable String fileId) throws Exception {

	        String text = extractor.extractText(fileId);
	        ParsedResume parsed = parser.parse(text);

	        // ✅ Send parsed JSON to n8n
	        n8nWebhookService.sendToN8n(parsed);

	        return ResponseEntity.ok(parsed);
	    }

}
