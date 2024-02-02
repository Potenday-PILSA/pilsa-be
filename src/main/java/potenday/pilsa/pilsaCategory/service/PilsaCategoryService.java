package potenday.pilsa.pilsaCategory.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import potenday.pilsa.pilsa.domain.repository.PilsaRepository;
import potenday.pilsa.pilsaCategory.domain.PilsaCategory;
import potenday.pilsa.pilsaCategory.domain.YN;
import potenday.pilsa.pilsaCategory.domain.repository.PilsaCategoryRepository;
import potenday.pilsa.pilsaCategory.dto.request.RequestCategoryDto;
import potenday.pilsa.pilsaCategory.dto.response.ResponseCategoryListDto;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class PilsaCategoryService {

    private final PilsaCategoryRepository pilsaCategoryRepository;

    public ResponseCategoryListDto getPilsaCategoryList() {

        List<PilsaCategory> categoryList = pilsaCategoryRepository.findByUseYn(YN.Y);

        return ResponseCategoryListDto.from(categoryList);
    }

    public void createPilsaCategory(RequestCategoryDto requestCategoryDto) {
        pilsaCategoryRepository.save(
                new PilsaCategory(requestCategoryDto.getCategoryName(), requestCategoryDto.getDescription(), requestCategoryDto.getUseYn())
        );
    }

}

