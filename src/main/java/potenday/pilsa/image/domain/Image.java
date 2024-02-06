package potenday.pilsa.image.domain;

import lombok.Getter;
import org.apache.commons.io.FilenameUtils;
import org.springframework.web.multipart.MultipartFile;
import potenday.pilsa.global.exception.BadRequestException;

import java.util.UUID;

import static potenday.pilsa.global.exception.ExceptionCode.INVALID_IMAGE_FILE;

@Getter
public class Image {
    private final String id;
    private final String name;
    private final MultipartFile file;

    public Image(MultipartFile file) {
        validateEmptyFile(file);
        validateImageFile(file);
        this.file = file;
        this.id = createId();
        this.name = getImageName(file);
    }

    private void validateEmptyFile(MultipartFile file) {
        if(file == null || file.isEmpty()) {
            throw new BadRequestException(INVALID_IMAGE_FILE);
        }
    }

    private void validateImageFile(MultipartFile file) {
        if(file.getContentType() == null || !file.getContentType().startsWith("image")) {
            throw new BadRequestException(INVALID_IMAGE_FILE);
        }
    }

    private String createId() {
        return UUID.randomUUID() + "." + this.file.getOriginalFilename();
    }

    private String getImageName(MultipartFile file) {
        return FilenameUtils.getExtension(file.getOriginalFilename());
    }

    public String getContentType() {
        return this.file.getContentType();
    }

    public Long getContentLength() {
        return this.file.getSize();
    }
}
