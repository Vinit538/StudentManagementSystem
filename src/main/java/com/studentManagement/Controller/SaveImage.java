package com.studentManagement.Controller;

import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
import java.util.UUID;

public class SaveImage {

	private String saveImage(MultipartFile image) throws IOException {
	    if (image == null || image.isEmpty()) {
	        return null;
	    }

	    String fileName = generateUniqueFileName(image.getOriginalFilename());
	    String uploadDirectory = "C:\\Users\\Vini\\OneDrive\\Documents\\StudentManagementProject\\stuProfileImages"; // Replace with the desired directory path

	    Path uploadPath = Path.of(uploadDirectory);
	    if (!Files.exists(uploadPath)) {
	        Files.createDirectories(uploadPath);
	    }

	    try {
	        Path filePath = uploadPath.resolve(fileName);
	        Files.copy(image.getInputStream(), filePath, StandardCopyOption.REPLACE_EXISTING);
	        return fileName;
	    } catch (IOException e) {
	        // Handle the exception appropriately
	        throw new IOException("Failed to save the image file", e);
	    }
	}

	private String generateUniqueFileName(String originalFileName) {
	    String fileExtension = StringUtils.getFilenameExtension(originalFileName);
	    String uniqueFileName = UUID.randomUUID().toString();

	    if (StringUtils.hasText(fileExtension)) {
	        return uniqueFileName + "." + fileExtension;
	    } else {
	        return uniqueFileName;
	    }
	}

}
