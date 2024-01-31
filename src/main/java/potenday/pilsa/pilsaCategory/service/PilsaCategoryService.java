package potenday.pilsa.pilsaCategory.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import potenday.pilsa.pilsa.domain.repository.PilsaRepository;
import potenday.pilsa.pilsaCategory.domain.repository.PilsaCategoryRepository;

@Service
@RequiredArgsConstructor
@Transactional
public class PilsaCategoryService {

    private final PilsaCategoryRepository pilsaCategoryRepository;
}

