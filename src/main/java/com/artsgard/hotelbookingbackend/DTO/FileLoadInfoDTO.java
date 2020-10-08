package com.artsgard.hotelbookingbackend.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class FileLoadInfoDTO {
    private String fileName;
    private String fileDownloadUri;
    private String fileType;
    private long size;
    private String tempImgDir;

}
