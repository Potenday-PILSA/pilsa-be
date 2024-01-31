package potenday.pilsa.pilsa.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import potenday.pilsa.pilsa.domain.repository.PilsaRepository;

@Service
@RequiredArgsConstructor
@Transactional
public class PilsaService {

    private final PilsaRepository pilsaRepository;
}
