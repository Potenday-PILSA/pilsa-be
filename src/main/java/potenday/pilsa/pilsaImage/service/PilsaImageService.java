package potenday.pilsa.pilsaImage.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import potenday.pilsa.pilsaImage.domain.repository.PilsaImageRepository;

@Service
@RequiredArgsConstructor
@Transactional
public class PilsaImageService {

    private final PilsaImageRepository pilsaImageRepository;
}

