package potenday.pilsa.pilsaContents.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import potenday.pilsa.pilsaContents.domain.repository.PilsaContentsRepository;

@Service
@RequiredArgsConstructor
@Transactional
public class PilsaContentService {

    private final PilsaContentsRepository pilsaContentRepository;
}

