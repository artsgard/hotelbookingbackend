package com.artsgard.hotelbookingbackend.controller;

import com.artsgard.hotelbookingbackend.DTO.FileLoadInfoDTO;
import com.artsgard.hotelbookingbackend.serviceimpl.FileLoadService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import javax.servlet.http.HttpSession;
import org.springframework.http.HttpStatus;

@RestController
public class FileLoadController {

    private static final Logger logger = LoggerFactory.getLogger(FileLoadController.class);

    @Autowired
    private FileLoadService fileService;
    
    @PostMapping("/uploadFile")
    public ResponseEntity<FileLoadInfoDTO> uploadFile(@RequestParam("file") MultipartFile file, HttpSession session) {
        FileLoadInfoDTO dto = fileService.storeFile(file);

        String fileDownloadUri = ServletUriComponentsBuilder.fromCurrentContextPath()
                .path("/downloadFile/")
                .path(dto.getFileName())
                .toUriString();
        dto.setFileDownloadUri(fileDownloadUri);
        
        return new ResponseEntity<>(dto, HttpStatus.OK);
    }

    @GetMapping("/downloadFile/{fileName:.+}")
    public ResponseEntity<Resource> downloadFile(@PathVariable String fileName, HttpServletRequest request) {
        // Load file as Resource
        Resource resource = fileService.loadFileAsResource(fileName);

        // Try to determine file's content type
        String contentType = null;
        try {
            contentType = request.getServletContext().getMimeType(resource.getFile().getAbsolutePath());
        } catch (IOException ex) {
            logger.info("Could not determine file type.");
        }

        // Fallback to the default content type if type could not be determined
        if(contentType == null) {
            contentType = "application/octet-stream";
        }

        return ResponseEntity.ok()
                .contentType(MediaType.parseMediaType(contentType))
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + resource.getFilename() + "\"")
                .body(resource);
    }

}
