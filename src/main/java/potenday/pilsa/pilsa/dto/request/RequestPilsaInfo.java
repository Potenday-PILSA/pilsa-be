package potenday.pilsa.pilsa.dto.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import potenday.pilsa.pilsa.domain.YN;
import potenday.pilsa.pilsaContents.domain.PilsaContents;

import java.util.List;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RequestPilsaInfo {

    private String title;
    private String author;
    private String publisher;
    private YN privateType;
    private Long followPilsaId;
    private List<Contents> contents;

    @Getter
    @Builder
    @AllArgsConstructor
    @NoArgsConstructor
    private class Contents {

        private String content;
        private String backgroundImageUrl;
        private String backgroundColor;
        private List<Images> images;

        @Getter
        @Builder
        @AllArgsConstructor
        @NoArgsConstructor
        private class Images {

            private String imageUrl;
            private YN thumbnail;

        }

    }


}
