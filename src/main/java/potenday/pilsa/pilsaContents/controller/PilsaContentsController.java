package potenday.pilsa.pilsaContents.controller;

import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import potenday.pilsa.pilsaContents.service.PilsaContentService;

@Tag(name = "필사 콘텐츠 Controller")
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/pilsa/contents")
public class PilsaContentsController {

    private final PilsaContentService pilsaContentService;

}
