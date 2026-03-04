package com.example.backend.dto;

public class UploadResponse {
	 	private String fileId;
	    private String originalName;
	    private String contentType;
	    private long size;

	    public UploadResponse() {}

	    public UploadResponse(String fileId, String originalName, String contentType, long size) {
	        this.fileId = fileId;
	        this.originalName = originalName;
	        this.contentType = contentType;
	        this.size = size;
	    }

	    public String getFileId() { return fileId; }
	    public void setFileId(String fileId) { this.fileId = fileId; }

	    public String getOriginalName() { return originalName; }
	    public void setOriginalName(String originalName) { this.originalName = originalName; }

	    public String getContentType() { return contentType; }
	    public void setContentType(String contentType) { this.contentType = contentType; }

	    public long getSize() { return size; }
	    public void setSize(long size) { this.size = size; }
}
