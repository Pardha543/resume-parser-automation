package com.example.backend.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.example.backend.dto.UploadResponse;
import com.example.backend.service.ResumeStorageService;

@RestController
@RequestMapping("/api/resumes")
public class ResumeUploadController {
	private final ResumeStorageService storageService;

    public ResumeUploadController(ResumeStorageService storageService) {
        this.storageService = storageService;
    }

    @PostMapping("/upload")
    public ResponseEntity<UploadResponse> upload(
            @RequestParam(value = "file", required = false) MultipartFile file,
            @RequestParam(value = "File", required = false) MultipartFile File
    ) throws Exception {

        MultipartFile actual = (file != null) ? file : File;

        if (actual == null || actual.isEmpty()) {
            throw new IllegalArgumentException("Missing file. Use form-data key 'file'");
        }

        String fileId = storageService.save(actual);

        UploadResponse res = new UploadResponse(
                fileId,
                actual.getOriginalFilename(),
                actual.getContentType(),
                actual.getSize()
        );

        return ResponseEntity.ok(res);
    }
}
