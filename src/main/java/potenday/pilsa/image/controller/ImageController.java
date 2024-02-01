package potenday.pilsa.image.controller;

import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.persistence.criteria.CriteriaBuilder;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import potenday.pilsa.image.domain.Image;
import potenday.pilsa.image.dto.response.ImageResponse;
import potenday.pilsa.image.util.ImageUtil;

import java.util.ArrayList;
import java.util.List;

@Tag(name = "이미지 Controller")
@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/image")
public class ImageController {
    private final ImageUtil imageUtil;

    @PostMapping("")
    public ResponseEntity<List<ImageResponse>> uploadImage(
            @RequestPart(name = "files") List<MultipartFile> files) {

        List<ImageResponse> imageResponses = new ArrayList<>();

        files.forEach(
                file -> {
                    ImageResponse imageResponse = imageUtil.uploadImage("pilsa-content", new Image(file));
                    imageResponses.add(imageResponse);
                }
        );

        return ResponseEntity.ok(imageResponses);
    }

}
