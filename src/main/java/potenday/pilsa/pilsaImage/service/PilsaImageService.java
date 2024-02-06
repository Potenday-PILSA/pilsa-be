package potenday.pilsa.pilsaImage.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import potenday.pilsa.pilsaImage.domain.PilsaImage;
import potenday.pilsa.pilsaImage.domain.repository.PilsaImageRepository;
import potenday.pilsa.pilsaImage.dto.response.ResponseImageListDto;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class PilsaImageService {

    private final PilsaImageRepository pilsaImageRepository;

    public ResponseImageListDto getPilsaImageList(Long memberId) {

        List<PilsaImage> pilsaImageList = pilsaImageRepository.findByPilsa_Member_Id(memberId);

        return ResponseImageListDto.from(pilsaImageList);


    }
}

