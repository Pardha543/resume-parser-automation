package com.example.backend.service;

import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.text.PDFTextStripper;
import org.apache.poi.xwpf.extractor.XWPFWordExtractor;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.springframework.stereotype.Service;

@Service
public class ResumeTextExtractorService {
	private final ResumeStorageService storageService;

    public ResumeTextExtractorService(ResumeStorageService storageService) {
        this.storageService = storageService;
    }

    public String extractText(String fileId) throws Exception {
        Path filePath = storageService.resolvePath(fileId);
        String name = filePath.getFileName().toString().toLowerCase();

        if (name.endsWith(".pdf")) {
            return extractFromPdf(filePath);
        } else if (name.endsWith(".docx")) {
            return extractFromDocx(filePath);
        }

        throw new IllegalArgumentException("Unsupported file type. Only PDF/DOCX are supported.");
    }

    private String extractFromPdf(Path path) throws Exception {
        try (PDDocument document = PDDocument.load(path.toFile())) {
            PDFTextStripper stripper = new PDFTextStripper();
            return normalize(stripper.getText(document));
        }
    }

    private String extractFromDocx(Path path) throws Exception {
        try (InputStream is = Files.newInputStream(path);
             XWPFDocument doc = new XWPFDocument(is);
             XWPFWordExtractor extractor = new XWPFWordExtractor(doc)) {
            return normalize(extractor.getText());
        }
    }

    private String normalize(String text) {
        if (text == null) return "";
        // Clean up extra whitespace but keep line breaks useful for parsing
        return text.replace("\r", "")
                   .replaceAll("[ \t]+", " ")
                   .replaceAll("\n{3,}", "\n\n")
                   .trim();
    }


}
