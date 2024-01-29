package potenday.pilsa.relationPilsaCategory.domain;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import potenday.pilsa.pilsa.domain.Pilsa;
import potenday.pilsa.pilsaCategory.domain.PilsaCategory;

import java.util.List;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "relationPilsaCategory")
public class RelationPilsaCategory {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // 필사 ID
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "id")
    List<Pilsa> pilsaList;

    // 카테고리 ID
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "id")
    List<PilsaCategory> categoryList;
}
