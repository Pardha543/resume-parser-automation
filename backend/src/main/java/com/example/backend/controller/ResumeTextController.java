package com.example.backend.controller;

import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.backend.service.ResumeTextExtractorService;

@RestController
@RequestMapping("/api/resumes")
public class ResumeTextController {
	private final ResumeTextExtractorService extractorService;

    public ResumeTextController(ResumeTextExtractorService extractorService) {
        this.extractorService = extractorService;
    }

    @GetMapping("/{fileId}/text")
    public ResponseEntity<?> extractText(@PathVariable String fileId) throws Exception {
        String text = extractorService.extractText(fileId);
        return ResponseEntity.ok(Map.of(
                "fileId", fileId,
                "text", text
        ));
    }
}
