package com.example.backend.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.backend.dto.ParsedResume;
import com.example.backend.service.ResumeParserService;
import com.example.backend.service.ResumeTextExtractorService;

@RestController
@RequestMapping("/api/resumes")
public class ResumeParserController {
	 private final ResumeTextExtractorService extractor;
	    private final ResumeParserService parser;

	    public ResumeParserController(ResumeTextExtractorService extractor,
	                                  ResumeParserService parser) {
	        this.extractor = extractor;
	        this.parser = parser;
	    }

	    @GetMapping("/{fileId}/parse")
	    public ResponseEntity<ParsedResume> parse(@PathVariable String fileId) throws Exception {

	        String text = extractor.extractText(fileId);

	        ParsedResume parsed = parser.parse(text);

	        return ResponseEntity.ok(parsed);
	    }

}
