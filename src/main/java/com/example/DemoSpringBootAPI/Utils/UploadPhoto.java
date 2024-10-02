package com.example.DemoSpringBootAPI.Utils;

import java.io.File;
import java.nio.file.*;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.springframework.web.multipart.MultipartFile;

public class UploadPhoto {
	public static void createUploadDirectory(String uploadDirectory) {
	    File directory = new File(uploadDirectory);
	    if (!directory.exists()) {
	        boolean created = directory.mkdirs();
	        if (!created) {
	            throw new RuntimeException("Could not create upload directory: " + uploadDirectory);
	        }
	    }
	}
	
	public static List<String> uploadFiles(List<MultipartFile> files, String uploadDirectory) {
	    List<String> fileUrls = new ArrayList<>();

	    if (uploadDirectory.endsWith("/") || uploadDirectory.endsWith("\\")) {
	        uploadDirectory = uploadDirectory.substring(0, uploadDirectory.length() - 1);
	    }

	    if (files != null) {
	        for (MultipartFile file : files) {
	            try {
	                String originalFileName = file.getOriginalFilename();
	                String fileExtension = "";
	                String fileNameWithoutExtension = originalFileName;

	                int dotIndex = originalFileName.lastIndexOf(".");
	                if (dotIndex > 0 && dotIndex < originalFileName.length() - 1) {
	                    fileNameWithoutExtension = originalFileName.substring(0, dotIndex);
	                    fileExtension = originalFileName.substring(dotIndex);
	                }

	                String fileName = fileNameWithoutExtension + "_" + UUID.randomUUID().toString() + fileExtension;
	                Path filePath = Paths.get(uploadDirectory, fileName);
	                Files.write(filePath, file.getBytes());

	                fileUrls.add("/" + uploadDirectory + "/" + fileName);
	            } catch (Exception e) {
	                e.printStackTrace();
	                throw new RuntimeException("Error uploading file: " + file.getOriginalFilename());
	            }
	        }
	    }

	    return fileUrls;
	}

}
