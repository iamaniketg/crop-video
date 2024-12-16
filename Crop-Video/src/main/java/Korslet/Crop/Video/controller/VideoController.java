package Korslet.Crop.Video.controller;

import Korslet.Crop.Video.model.CropRequest;
import Korslet.Crop.Video.service.VideoCropService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Map;

@RestController
public class VideoController {

    @Autowired
    private VideoCropService videoCropService;
    @PostMapping(value = "/upload-and-crop", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<Map<String, Object>> uploadAndCropVideo(
            @RequestParam("file") MultipartFile file,
            @RequestPart(value = "cropRequest", required = true) @Valid CropRequest cropRequest) {
        Map<String, Object> response = videoCropService.processVideo(file, cropRequest);
        return ResponseEntity.ok(response);
    }


}

