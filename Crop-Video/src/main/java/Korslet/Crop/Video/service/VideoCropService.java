package Korslet.Crop.Video.service;

import Korslet.Crop.Video.model.CropRequest;
import Korslet.Crop.Video.utility.FFmpegUtils;
import org.apache.commons.io.FilenameUtils;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import java.io.File;
import java.io.IOException;
import java.util.*;

@Service
public class VideoCropService {

    private final String uploadDir = "uploads/videos/";
    private final String croppedDir = "uploads/cropped/";

    public Map<String, Object> processVideo(MultipartFile file, CropRequest cropRequest) {
        Map<String, Object> response = new HashMap<>();
        try {
            // Save the original video
            String originalFilePath = saveFile(file, uploadDir);

            // Crop the video
            String croppedFilePath = FFmpegUtils.cropVideo(
                    originalFilePath,
                    cropRequest.getWidth(),
                    cropRequest.getHeight(),
                    cropRequest.getX(),
                    cropRequest.getY(),
                    croppedDir
            );

            response.put(file.getOriginalFilename(), croppedFilePath);
        } catch (Exception e) {
            response.put(file.getOriginalFilename(), e.getMessage());
        }
        return response;
    }

    private String saveFile(MultipartFile file, String directory) throws IOException {
        String filePath = directory + UUID.randomUUID() + "_" + FilenameUtils.getName(file.getOriginalFilename());
        File destination = new File(filePath);
        destination.getParentFile().mkdirs();
        file.transferTo(destination);
        return filePath;
    }
}