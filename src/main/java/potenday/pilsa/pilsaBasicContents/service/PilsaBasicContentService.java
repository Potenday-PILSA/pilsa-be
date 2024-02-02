package potenday.pilsa.pilsaBasicContents.service;


import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import potenday.pilsa.global.dto.request.RequestPageDto;

import potenday.pilsa.global.exception.BadRequestException;
import potenday.pilsa.global.exception.ExceptionCode;
import potenday.pilsa.pilsa.domain.Pilsa;
import potenday.pilsa.pilsaBasicContents.domain.PilsaBasicContents;
import potenday.pilsa.pilsaBasicContents.domain.repository.PilsaBasicContentsRepository;
import potenday.pilsa.pilsaBasicContents.dto.response.ResponseBasicPilsaDto;
import potenday.pilsa.pilsaBasicContents.dto.response.ResponseBasicPilsaListDto;
import potenday.pilsa.pilsaCategory.domain.PilsaCategory;
import potenday.pilsa.pilsaCategory.domain.repository.PilsaCategoryRepository;

import java.util.List;
import java.util.Random;

@Service
@RequiredArgsConstructor
public class PilsaBasicContentService {

    private final PilsaBasicContentsRepository pilsaBasicContentsRepository;
    private final PilsaCategoryRepository pilsaCategoryRepository;

    @Transactional(readOnly = true)
    public ResponseBasicPilsaListDto getAllPilsalList(RequestPageDto request) {

        Page<PilsaBasicContents> pilsas = pilsaBasicContentsRepository.findAll(request.toPageable());

        return ResponseBasicPilsaListDto.from(pilsas);
    }

    public ResponseBasicPilsaDto getPilsaInfoOfCategoryCd(Long categoryCd) {

        List<PilsaBasicContents> basicContents = pilsaBasicContentsRepository.findByCategoryCd(categoryCd);

        if (basicContents.isEmpty()) {
            // 적절한 예외 처리
            throw new EntityNotFoundException("No contents found for category ID " + categoryCd);
        }

        // 리스트에서 랜덤하게 하나의 요소 선택
        PilsaBasicContents selectedContent = basicContents.get(new Random().nextInt(basicContents.size()));

        // 선택된 PilsaBasicContents를 ResponseBasicPilsaDto로 변환
        return ResponseBasicPilsaDto.from(selectedContent);
    }

    public ResponseBasicPilsaDto getPilsaInfoRandom() {

        List<PilsaBasicContents> basicContents = pilsaBasicContentsRepository.findAll();

        // 리스트에서 랜덤하게 하나의 요소 선택
        PilsaBasicContents selectedContent = basicContents.get(new Random().nextInt(basicContents.size()));

        // 선택된 PilsaBasicContents를 ResponseBasicPilsaDto로 변환
        return ResponseBasicPilsaDto.from(selectedContent);
    }

    private PilsaCategory getPilsaCategory(Long categoryCd) {
        return pilsaCategoryRepository.findByCategoryCd(categoryCd).orElseThrow(
                () -> new BadRequestException(ExceptionCode.NOT_FOUND_PILSA_CATEGORY)
        );
    }
}

