package potenday.pilsa.pilsaBasicContents.service;


import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import potenday.pilsa.global.dto.request.RequestPageDto;

import potenday.pilsa.pilsaBasicContents.domain.PilsaBasicContents;
import potenday.pilsa.pilsaBasicContents.domain.repository.PilsaBasicContentsRepository;
import potenday.pilsa.pilsaBasicContents.dto.response.ResponseBasicPilsaListDto;

@Service
@RequiredArgsConstructor
public class PilsaBasicContentService {

    private final PilsaBasicContentsRepository pilsaBasicContentsRepository;


    @Transactional(readOnly = true)
    public ResponseBasicPilsaListDto getAllPilsalList(RequestPageDto request) {

        Page<PilsaBasicContents> pilsas = pilsaBasicContentsRepository.findAll(request.pageable());

        return ResponseBasicPilsaListDto.from(pilsas);
    }
}

