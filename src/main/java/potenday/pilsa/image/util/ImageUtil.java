package potenday.pilsa.image.util;

import com.amazonaws.services.s3.model.CannedAccessControlList;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.amazonaws.services.s3.model.PutObjectRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import potenday.pilsa.global.config.BucketConfig;
import potenday.pilsa.global.exception.BadRequestException;
import potenday.pilsa.global.exception.ExceptionCode;
import potenday.pilsa.image.domain.Image;
import potenday.pilsa.image.dto.response.ImageResponse;

import java.io.IOException;
import java.io.InputStream;

@Component
@RequiredArgsConstructor
@Slf4j
public class ImageUtil {
    @Value("${ncp.bucket.bucket}")
    private String bucketName;
    @Value("${ncp.bucket.endPoint}")
    private String endPoint;

    private final BucketConfig bucketConfig;

    public ImageResponse uploadImage(String folderName, Image image) {
        String uploadFileName = image.getId();

        ObjectMetadata objectMetadata = new ObjectMetadata();
        objectMetadata.setContentType(image.getContentType());
        objectMetadata.setContentLength(image.getContentLength());

        try {
            String keyName = folderName + "/" + uploadFileName;

            InputStream inputStream = image.getFile().getInputStream();

            bucketConfig.S3Client().putObject(
                    new PutObjectRequest(
                            bucketName, keyName, inputStream, objectMetadata
                    ).withCannedAcl(CannedAccessControlList.PublicRead)
            );

            String uploadFileUrl = endPoint + "/" + bucketName + "/" + keyName;

            return ImageResponse.builder()
                    .fileName(image.getFile().getOriginalFilename())
                    .imageUrl(uploadFileUrl)
                    .build();
        } catch (IOException e) {
            throw new BadRequestException(ExceptionCode.FAIL_TO_UPLOAD_IMAGE);
        }
    }

}
