package com.artsgard.hotelbookingbackend.serviceimpl;

import com.artsgard.hotelbookingbackend.DTO.FileLoadInfoDTO;
import com.artsgard.hotelbookingbackend.DTO.FileUploadPathDTO;
import com.artsgard.hotelbookingbackend.exception.FileNotFoundException;
import com.artsgard.hotelbookingbackend.exception.FileUploadException;
import java.io.File;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;
import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.apache.tomcat.util.http.fileupload.FileUtils;

@Service
public class FileLoadService {

    private final Path fileUploadPath;
    private String filePathString;

    @Autowired
    public FileLoadService(FileUploadPathDTO filePath) {
        filePathString = filePath.getUploadPath();
        this.fileUploadPath = Paths.get(filePathString).toAbsolutePath().normalize();

        try {
            Files.createDirectories(this.fileUploadPath);
        } catch (Exception ex) {
            throw new FileUploadException("Could not create the directory where the uploaded files will be stored.", ex);
        }
    }

    public FileLoadInfoDTO storeFile(MultipartFile file) {
        // Normalize file name
        String fileName = StringUtils.cleanPath(file.getOriginalFilename());

        try {
            // Check if the file's name contains invalid characters
            if (fileName.contains("..")) {
                throw new FileUploadException("Sorry! Filename contains invalid path sequence " + fileName);
            }

            String tempImgDir = createTempDir(filePathString);

            StringBuilder sb = new StringBuilder(filePathString);
            sb.append(File.separator);
            sb.append(tempImgDir);

            Path path = Paths.get(sb.toString()).toAbsolutePath().normalize();

            // Copy file to the target location (Replacing existing file with the same name)
            Path targetLocation = path.resolve(fileName);
            Files.copy(file.getInputStream(), targetLocation, StandardCopyOption.REPLACE_EXISTING);

            FileLoadInfoDTO dto = new FileLoadInfoDTO();
            dto.setFileName(fileName);
            dto.setTempImgDir(tempImgDir);
            dto.setSize(file.getSize());
            dto.setFileType(file.getContentType());

            return dto;
        } catch (IOException ex) {
            throw new FileUploadException("Could not store file " + fileName + ". Please try again!", ex);
        }
    }

    public Resource loadFileAsResource(String fileName) {
        try {
            Path filePath = this.fileUploadPath.resolve(fileName).normalize();
            Resource resource = new UrlResource(filePath.toUri());
            if (resource.exists()) {
                return resource;
            } else {
                throw new FileNotFoundException("File not found " + fileName);
            }
        } catch (MalformedURLException ex) {
            throw new FileNotFoundException("File not found " + fileName, ex);
        }
    }

    // ================================================================================
    public void createDir(String dirName) throws IOException {
        try {
            Path path = Paths.get(fileUploadPath.toString() + File.separator + dirName);
            Files.createDirectories(path);
            System.out.println("Directory is created with name: " + dirName);
        } catch (IOException e) {
            System.err.println("Failed to create directory!" + e.getMessage());
            throw new FileUploadException("Failed to create directory!" + e.getMessage());
        }
    }

    public void renameDir(String oldName, String newName) throws IOException {
        File newFile = new File(newName);
        if (newFile.exists()) {
            throw new java.io.IOException("dir allready exists");
        }
        try {
            Path oldFile = Paths.get(fileUploadPath.toString() + File.separator + oldName);
            Files.move(oldFile, oldFile.resolveSibling(newName), StandardCopyOption.REPLACE_EXISTING);
        } catch (IOException ex) {
            Logger.getLogger(FileLoadService.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void createFileInDir(String dirName, String fileName) {
        try {
            File f = new File(fileUploadPath.toString() + File.separator + dirName + File.separator + fileName);
            f.getParentFile().mkdirs();
            f.createNewFile();
            System.out.println("createFile " + fileName);
        } catch (IOException ex) {
            Logger.getLogger(FileLoadService.class.getName()).log(Level.SEVERE, null, ex);
            throw new FileUploadException("Failed to create dir! " + ex.getMessage());
        }
    }

    public void renameFileInDir(String oldFileName, String newFileName, String dirName) throws IOException {
        File newFile = new File(newFileName);
        if (newFile.exists()) {
            throw new java.io.IOException("file exists");
        }
        try {
            Path oldFile = Paths.get(fileUploadPath.toString() + File.separator + dirName + File.separator + oldFileName);
            Files.move(oldFile, oldFile.resolveSibling(newFileName), StandardCopyOption.REPLACE_EXISTING);
        } catch (IOException ex) {
            Logger.getLogger(FileLoadService.class.getName()).log(Level.SEVERE, null, ex);
            throw new FileUploadException("Failed to rename dir! " + ex.getMessage());
        }
    }

    public void deleteFileInDir(String fileName, String dirName) {
        File file = new File(fileUploadPath.toString() + File.separator + dirName + File.separator + fileName);
        file.exists();
        if (file.delete()) {
            System.out.println("File deleted from wit name: " + fileName);
        } else {
            Logger.getLogger(FileLoadService.class.getName()).log(Level.SEVERE, "No file with name: " + fileName);
            throw new FileUploadException("No file with name: " + fileName);
        }
    }

    public void deleteDir(String dirName) {
        File file = new File(fileUploadPath.toString() + File.separator + dirName);
        file.exists();
        try {
            FileUtils.deleteDirectory(file);
        } catch (IOException ex) {
            Logger.getLogger(FileLoadService.class.getName()).log(Level.SEVERE, null, ex);
            throw new FileUploadException("Failed to delete file! " + ex.getMessage());
        }
    }

    public String createTempDir(String filePath) throws IOException {
        String tempImgDir = "temp-img-dir-" + System.currentTimeMillis();

        StringBuilder sb = new StringBuilder(filePath);
        sb.append(File.separator);
        sb.append(tempImgDir);

        try {
            Path path = Paths.get(sb.toString());
            Files.createDirectories(path);
            System.out.println("Directory is created with path: " + filePath + "    /name: " + tempImgDir);
            return tempImgDir;
        } catch (IOException ex) {
            System.err.println("Failed to create directory!" + ex.getMessage());
            throw new FileUploadException("Could not store dir " + tempImgDir + ". Please try again!", ex);
        }
    }
}
