package com.example.DemoSpringBootAPI.Controller;

import java.io.IOException;
import java.nio.file.Files;

import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/uploads")
public class ImageSupportController {
	
	private final ResourceLoader resourceLoader;

    public ImageSupportController(ResourceLoader resourceLoader) {
        this.resourceLoader = resourceLoader;
    }

    @GetMapping("/products/{imageName:.+}/download")
    public ResponseEntity<Resource> downloadImage(@PathVariable("imageName") String imageName) {
        Resource resource = resourceLoader.getResource("classpath:static/uploads/products/" + imageName);
        
        if (!resource.exists()) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + resource.getFilename() + "\"")
                .body(resource);
    }
    
    @GetMapping("/products/{imageName:.+}")
    public ResponseEntity<Resource> getImage(@PathVariable("imageName") String imageName) {
    	// Ex: http://localhost:8080/api/uploads/products/CDL4_1_01d75c71-4a0f-4764-9048-869ae2500a0c.jpg
        Resource resource = resourceLoader.getResource("file:src/main/resources/static/uploads/products/" + imageName);
        
        if (!resource.exists()) {
            return ResponseEntity.notFound().build();
        }

        String contentType;
        try {
            contentType = Files.probeContentType(resource.getFile().toPath());
        } catch (IOException e) {
            contentType = "application/octet-stream";
        }

        return ResponseEntity.ok()
                .contentType(MediaType.parseMediaType(contentType))
                .header(HttpHeaders.CONTENT_DISPOSITION, "inline; filename=\"" + resource.getFilename() + "\"")
                .body(resource);
    }

}
