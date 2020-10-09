package com.artsgard.hotelbookingbackend.controller;

import com.artsgard.hotelbookingbackend.DTO.FileLoadInfoDTO;
import com.artsgard.hotelbookingbackend.serviceimpl.FileLoadService;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import javax.imageio.ImageIO;
import javax.servlet.http.HttpSession;
import com.sun.org.apache.xerces.internal.impl.dv.util.Base64;
import org.springframework.http.HttpStatus;

@RestController
public class FileLoadController {

    private static final Logger logger = LoggerFactory.getLogger(FileLoadController.class);

    @Autowired
    private FileLoadService fileService;

    @PostMapping("/uploadFile")
    public ResponseEntity<FileLoadInfoDTO> uploadFile(@RequestParam("file") MultipartFile file, HttpSession session) {
        FileLoadInfoDTO dto = fileService.uploadFile(file);

        String fileDownloadUri = ServletUriComponentsBuilder.fromCurrentContextPath()
                .path("/downloadFile/")
                .path(dto.getFileName())
                .toUriString();
        dto.setFileDownloadUri(fileDownloadUri);

        return new ResponseEntity<>(dto, HttpStatus.OK);
    }

    @GetMapping("/downloadFile/{fileName:.+}")
    public @ResponseBody Map<String, String> downloadFile(@PathVariable String fileName, HttpServletRequest request) throws IOException {
        Resource resource = fileService.downloadFile(fileName);

        ByteArrayOutputStream baos = new ByteArrayOutputStream(50000);
        BufferedImage img = ImageIO.read(resource.getFile());
        ImageIO.write(img, "jpg", baos);
        baos.flush();

        String base64String = Base64.encode(baos.toByteArray());
        baos.close();

        byte[] bytearray = Base64.decode(base64String);
   
        Map<String, String> jsonMap = new HashMap<>();

        //jsonMap.put("content", new String(bytearray));
        jsonMap.put("content", base64String);

        return jsonMap;
    }

}
