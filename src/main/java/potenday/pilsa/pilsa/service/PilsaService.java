package potenday.pilsa.pilsa.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import potenday.pilsa.pilsa.domain.Pilsa;
import potenday.pilsa.pilsa.domain.repository.PilsaRepository;
import potenday.pilsa.pilsa.dto.request.RequestPilsaInfo;

@Service
@RequiredArgsConstructor
public class PilsaService {

    private final PilsaRepository pilsaRepository;

    public Pilsa createPilsa(RequestPilsaInfo request) {

        Pilsa pilsa = pilsaRepository.save(
                Pilsa.builder()
                        .title(request.getTitle())
                        .author(request.getAuthor())
                        .publisher(request.getPublisher())
                        .privateType(request.getPrivateType())
                        .followPilsaId(request.getFollowPilsaId())
                        .build()

        );

        return pilsa;
    }
}
