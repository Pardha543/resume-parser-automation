package com.example.backend.service;

import java.io.IOException;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.NoSuchFileException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.Set;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public class ResumeStorageService {
	 private final Path uploadDir;

	    private static final Set<String> ALLOWED_TYPES = Set.of(
	            "application/pdf",
	            "application/vnd.openxmlformats-officedocument.wordprocessingml.document"
	    );

	    public ResumeStorageService(@Value("${app.uploads.dir:uploads}") String uploadDir) throws IOException {
	        this.uploadDir = Paths.get(uploadDir).toAbsolutePath().normalize();
	        Files.createDirectories(this.uploadDir);
	    }

	    public String save(MultipartFile file) throws IOException {
	        if (file == null || file.isEmpty()) {
	            throw new IllegalArgumentException("File is empty");
	        }

	        String contentType = file.getContentType();
	        if (contentType == null || !ALLOWED_TYPES.contains(contentType)) {
	            throw new IllegalArgumentException("Only PDF/DOCX resumes are allowed");
	        }

	        String originalName = file.getOriginalFilename();
	        String ext = getExtension(originalName);

	        String fileId = UUID.randomUUID().toString();
	        String storedName = fileId + (ext.isBlank() ? "" : "." + ext);

	        Path target = uploadDir.resolve(storedName);
	        Files.copy(file.getInputStream(), target, StandardCopyOption.REPLACE_EXISTING);

	        return fileId;
	    }

	    public Path resolvePath(String fileId) throws IOException {
	        try (DirectoryStream<Path> stream = Files.newDirectoryStream(uploadDir, fileId + "*")) {
	            for (Path p : stream) return p;
	        }
	        throw new NoSuchFileException("Resume not found for fileId=" + fileId);
	    }

	    private String getExtension(String name) {
	        if (name == null) return "";
	        int dot = name.lastIndexOf('.');
	        if (dot == -1) return "";
	        return name.substring(dot + 1).toLowerCase();
	    }
}
